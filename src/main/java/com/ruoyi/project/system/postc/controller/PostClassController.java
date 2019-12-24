package com.ruoyi.project.system.postc.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.post.service.IPostService;
import com.ruoyi.project.system.postc.domain.PostClass;
import com.ruoyi.project.system.postc.service.IPostClassService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 岗位职级Controller
 * 
 * @author crf
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/system/postc")
public class PostClassController extends BaseController {
	private String prefix = "system/postc";

	@Autowired
	private IPostClassService postClassService;
	@Autowired
    private IPostService postService;
	
	@RequiresPermissions("system:postc:view")
	@GetMapping()
	public String postc(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		return prefix + "/postc";
	}

	/**
	 * 查询岗位职级列表
	 */
	@RequiresPermissions("system:postc:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PostClass postClass) {
		startPage();
		List<PostClass> list = postClassService.selectPostClassList(postClass);
		return getDataTable(list);
	}

	/**
	 * 导出岗位职级列表
	 */
	@RequiresPermissions("system:postc:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(PostClass postClass) {
		List<PostClass> list = postClassService.selectPostClassList(postClass);
		ExcelUtil<PostClass> util = new ExcelUtil<PostClass>(PostClass.class);
		return util.exportExcel(list, "postc");
	}

	/**
	 * 新增岗位职级
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		return prefix + "/add";
	}

	/**
	 * 新增保存岗位职级
	 */
	@RequiresPermissions("system:postc:add")
	@Log(title = "岗位职级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PostClass postClass) {
		return toAjax(postClassService.insertPostClass(postClass));
	}

	/**
	 * 修改岗位职级
	 */
	@GetMapping("/edit/{classId}")
	public String edit(@PathVariable("classId") Long classId, ModelMap mmap) {
		PostClass postClass = postClassService.selectPostClassById(classId);
		mmap.put("posts", postService.selectPostAll());
		mmap.put("postClass", postClass);
		return prefix + "/edit";
	}

	/**
	 * 修改保存岗位职级
	 */
	@RequiresPermissions("system:postc:edit")
	@Log(title = "岗位职级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PostClass postClass) {
		return toAjax(postClassService.updatePostClass(postClass));
	}

	/**
	 * 删除岗位职级
	 */
	@RequiresPermissions("system:postc:remove")
	@Log(title = "岗位职级", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(postClassService.deletePostClassByIds(ids));
	}
}
