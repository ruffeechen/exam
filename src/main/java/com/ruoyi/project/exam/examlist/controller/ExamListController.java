package com.ruoyi.project.exam.examlist.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.exam.examlist.domain.ExamFileList;
import com.ruoyi.project.exam.examlist.domain.ExamList;
import com.ruoyi.project.exam.examlist.service.IExamListService;
import com.ruoyi.project.system.post.service.IPostService;

/**
 * 题库Controller
 * 
 * @author crf
 * @date 2019-09-16
 */
@Controller
@RequestMapping("/exam/examlist")
public class ExamListController extends BaseController {
	private String prefix = "exam/examlist";
	@Autowired
	private IPostService postService;
	@Autowired
	private IExamListService examListService;

	@RequiresPermissions("exam:examlist:view")
	@GetMapping()
	public String examlist() {
		return prefix + "/examlist";
	}

	/**
	 * 查询题库列表
	 */
	@RequiresPermissions("exam:examlist:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamList examList) {
		startPage();
		List<ExamList> list = examListService.selectExamListList(examList);
		return getDataTable(list);
	}

	/**
	 * 导出题库列表
	 */
	@RequiresPermissions("exam:examlist:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ExamList examList) {
		List<ExamList> list = examListService.selectExamListList(examList);
		ExcelUtil<ExamList> util = new ExcelUtil<ExamList>(ExamList.class);
		return util.exportExcel(list, "examlist");
	}

	/**
	 * 新增题库
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		return prefix + "/add";
	}

	/**
	 * 新增保存题库
	 */
	@RequiresPermissions("exam:examlist:add")
	@Log(title = "题库", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamList examList) {
		return toAjax(examListService.insertExamList(examList));
	}

	/**
	 * 修改题库
	 */
	@GetMapping("/edit/{examId}")
	public String edit(@PathVariable("examId") Long examId, ModelMap mmap) {
		ExamList examList = examListService.selectExamListById(examId);
		mmap.put("posts", postService.selectPostAll());
		mmap.put("examList", examList);
		mmap.put("file", examListService.selectExamFileById(examId));// 附件
		return prefix + "/edit";
	}

	/**
	 * 修改头像
	 */
	@GetMapping("/avatar/{examId}")
	public String avatar(@PathVariable("examId") Long examId, ModelMap mmap) {
		mmap.put("examlist", examListService.selectExamListById(examId));
		return prefix + "/avatar";
	}

	/**
	 * 保存头像
	 */
	@Log(title = "个人信息", businessType = BusinessType.UPDATE)
	@PostMapping("/updateAvatar/{examId}")
	@ResponseBody
	public AjaxResult updateAvatar(@PathVariable("examId") Long examId,
			@RequestParam("avatarfile") MultipartFile file) {
		ExamList currentExam = examListService.selectExamListById(examId);
		try {
			if (!file.isEmpty()) {
				String avatar = FileUploadUtils.upload(
						RuoYiConfig.getAvatarPath(), file);
				currentExam.setAvatar(avatar);
				examListService.updateExamList(currentExam);
				return success();
			}
			return error();
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}

	/**
	 * 保存头像
	 */
	@Log(title = "多文件上传", businessType = BusinessType.UPDATE)
	@PostMapping("/uploadFiles/{examId}")
	@ResponseBody
	public AjaxResult uploadFiles(@PathVariable("examId") Long examId,
			@RequestParam("avatarfile") MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				ExamFileList examfile = new ExamFileList();
				
				String avatar = FileUploadUtils.upload(
						RuoYiConfig.getAvatarPath(), file);
				examfile.setExamId(examId);
				examfile.setFilepath(avatar);
				String filename = file.getOriginalFilename();
				examfile.setFilename(filename);
				if(!StringUtils.isEmpty(file.getOriginalFilename())) {
					if(filename.indexOf("jpg")>0 || filename.indexOf("png")>0 
							|| filename.indexOf("gif")>0|| filename.indexOf("jpeg")>0) {
						examfile.setType("image");
					} else if(filename.indexOf("pdf")>0 || filename.indexOf("txt")>0 || filename.indexOf("sql")>0) {
						examfile.setType("pdf");
					} else if(filename.indexOf("doc")>0 || filename.indexOf("xlsx")>0 || filename.indexOf("docx")>0) {
						examfile.setType("pdf");
					}
				}
				examListService.updateFiles(examfile);
				return success();
			}
			return error();
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
	
	@GetMapping("/queryFiles/{examId}")
	@ResponseBody
	public AjaxResult queryFiles(@PathVariable("examId") Long examId) {
		AjaxResult ajax = new AjaxResult();
		List<ExamFileList> files = examListService.selectExamFileById(examId);
		List<String> filelist = new ArrayList<String>();
		if(files != null && files.size() > 0) {
			for (ExamFileList file : files) {
				file.setFilepath(file.getFilepath());
				filelist.add("'" + file.getFilepath() + "'");
			}
		}
		ajax.put("value", files);
		return ajax;
	}

	/**
	 * 修改保存题库
	 */
	@RequiresPermissions("exam:examlist:edit")
	@Log(title = "题库", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamList examList) {
		return toAjax(examListService.updateExamList(examList));
	}

	/**
	 * 删除题库
	 */
	@RequiresPermissions("exam:examlist:remove")
	@Log(title = "题库", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(examListService.deleteExamListByIds(ids));
	}

	@Log(title = "题库管理", businessType = BusinessType.IMPORT)
	@RequiresPermissions("exam:examlist:import")
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport)
			throws Exception {
		ExcelUtil<ExamList> util = new ExcelUtil<ExamList>(ExamList.class);
		List<ExamList> userList = util.importExcel(file.getInputStream());
		String message = examListService
				.importExamlist(userList, updateSupport);
		return AjaxResult.success(message);
	}

	@RequiresPermissions("exam:examlist:view")
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<ExamList> util = new ExcelUtil<ExamList>(ExamList.class);
		return util.importTemplateExcel("题库数据");
	}

	/**
	 * 题目状态修改
	 */
	@Log(title = "题目状态管理", businessType = BusinessType.UPDATE)
	@RequiresPermissions("exam:examlist:edit")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(ExamList exam) {
		return toAjax(examListService.changeStatus(exam));
	}
}
