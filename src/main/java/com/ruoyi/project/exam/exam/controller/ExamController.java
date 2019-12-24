package com.ruoyi.project.exam.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.exam.doc.ExportPdf;
import com.ruoyi.project.exam.exam.service.ExamMarkService;
import com.ruoyi.project.exam.examlist.domain.ExamFileList;
import com.ruoyi.project.exam.examlist.domain.ExamList;
import com.ruoyi.project.exam.examlist.service.IExamListService;
import com.ruoyi.project.exam.question.domain.ExamQuestionEntity;
import com.ruoyi.project.exam.question.domain.Question;
import com.ruoyi.project.exam.question.domain.QuestionWrapper;
import com.ruoyi.project.exam.question.service.IQuestionService;
import com.ruoyi.project.exam.type.domain.ExamType;
import com.ruoyi.project.exam.type.service.IExamTypeService;
import com.ruoyi.project.exam.venues.domain.ExamVenues;
import com.ruoyi.project.exam.venues.service.IExamVenuesService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.post.service.IPostService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 题库Controller
 * 
 * @author crf
 * @date 2019-09-16
 */
@Controller
@RequestMapping("/exam/exam")
public class ExamController extends BaseController {
	private String prefix = "exam/exam";

	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IExamTypeService examTypeService;
	@Autowired
	private IExamListService examListService;
	// 考题
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private ExamMarkService service;
	@Autowired
	private IPostService postService;
	@Autowired
	private IExamVenuesService examVenuesService;

	/**
	 * 出题系统
	 * 
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("exam:exam:view")
	@GetMapping()
	public String examlist(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		mmap.put("venues", examVenuesService.selectExamVenues());
		mmap.put("examiner", userService.selectExaminerList(new User()));
		return prefix + "/exam";
	}

	/**
	 * 考试历史
	 * 
	 * @return
	 */
	@RequiresPermissions("exam:history:view")
	@GetMapping("/history")
	public String history(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		mmap.put("venues", examVenuesService.selectExamVenues());
		mmap.put("examiner", userService.selectExaminerList(new User()));
		return prefix + "/hisexam";
	}

	/**
	 * 考试历史-教官评分
	 * 
	 * @return
	 */
	@RequiresPermissions("exam:hisexamlist:view")
	@GetMapping("/hisexamlist")
	public String hisexamlist(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		mmap.put("venues", examVenuesService.selectExamVenues());
		mmap.put("examiner", userService.selectExaminerList(new User()));
		return "exam/checklist/hisexamlist";
	}

	/**
	 * 查看考题
	 */
	@GetMapping("/editQuestion/{qId}")
	public String editQuestion(@PathVariable("qId") Long qId, ModelMap mmap) {
		Question question = questionService.selectQuestionById(qId);
		List<ExamQuestionEntity> list = questionService.selectMarkList(qId);
		if(list != null && list.size() > 0) {
			List<String> exam = new ArrayList<String>();
			for (ExamQuestionEntity entity : list) {
				Long examId = entity.getExamId();
				exam.add(examId.toString());
			}
			List<ExamList> exams = examListService.selectExamListByIds(exam.toArray(new String[0]));
			for (ExamList ex : exams) {
				for (ExamQuestionEntity entity : list) {
					if(ex.getExamId().equals(entity.getExamId())) {
						ex.setNpoint(entity.getNpoint());
						break;
					}
				}
			}
			mmap.put("questions", exams);
		}
		User user = userService.selectUserById(question.getUserId());// 用户
		Dept dept = deptService.selectDeptById(question.getDeptId());// 部门
		ExamVenues venu = examVenuesService.selectExamVenuesById(Long.parseLong(question.getVenues())); // 场次
		mmap.put("userName", user.getUserName());
		mmap.put("deptName", dept.getDeptName());
		mmap.put("venues", venu.getName());
		return prefix + "/question";
	}
	
	/**
	 * 查看考题
	 */
	@RequiresPermissions("exam:exam:checkQuestion")
	@GetMapping("/checkQuestion/{qId}")
	public String checkQuestion(@PathVariable("qId") Long qId, ModelMap mmap) {
		Question question = questionService.selectQuestionById(qId);
		List<ExamQuestionEntity> list = questionService.selectMarkList(qId);
		if(list != null && list.size() > 0) {
			List<String> exam = new ArrayList<String>();
			for (ExamQuestionEntity entity : list) {
				Long examId = entity.getExamId();
				exam.add(examId.toString());
			}
			List<ExamList> exams = examListService.selectExamListByIds(exam.toArray(new String[0]));
			List<ExamFileList> files = examListService.selectExamFileByIds(exam.toArray(new String[0]));
			for (ExamList ex : exams) {
				if(files != null && files.size() > 0) {
					for (ExamFileList file : files) {
						if(ex.getExamId().equals(file.getExamId())) {
							ex.setAvatar(file.getFilepath());
						}
					}
				}
			}
			mmap.put("questions", exams);
		}
		User user = userService.selectUserById(question.getUserId());// 用户
		Dept dept = deptService.selectDeptById(question.getDeptId());// 部门
		ExamVenues venu = examVenuesService.selectExamVenuesById(Long.parseLong(question.getVenues())); // 场次
		mmap.put("qId", qId);
		mmap.put("userName", user.getUserName());
		mmap.put("deptName", dept.getDeptName());
		mmap.put("venues", venu.getName());
		return "exam/checklist/checkquestion";
	}
	
	/**
	 * 评分
	 */
	@PostMapping("/markExam/{qId}")
	@ResponseBody
	public AjaxResult markExam(@PathVariable("qId") Long qId,ExamList data, ModelMap mmap) {
		User user = getSysUser();
		List<ExamQuestionEntity> list = questionService.selectMarkList(qId);
		service.updateMark(user, list, data);
		// 更新数据
		return toAjax(1);
	}

	@GetMapping("/edit/{examId}")
	public String edit(@PathVariable("examId") Long examId, ModelMap mmap) {
		ExamList examList = examListService.selectExamListById(examId);
		mmap.put("posts", postService.selectPostAll());
		mmap.put("examList", examList);
		return prefix + "/hisdetail";
	}

	/**
	 * 随机考试界面
	 * 
	 * @return
	 */
	@RequiresPermissions("exam:check:view")
	@GetMapping("/check")
	public String check(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		mmap.put("venues", examVenuesService.selectExamVenues());
		mmap.put("examiner", userService.selectExaminerList(new User()));
		return prefix + "/selfcheck";
	}

	/**
	 * 生成考卷，返回考题
	 * 
	 * @return
	 */
	@GetMapping("/question/{deptId}/{checkId}/{postId}/{venues}")
	public String question(@PathVariable("deptId") Long deptId,
			@PathVariable("checkId") Long checkId,
			@PathVariable("postId") Long postId,
			@PathVariable("venues") String venues, ModelMap mmap) {
		// 生成试卷规则, 从后台获取到生成的试卷，然后返回至前端
		QuestionWrapper wrap = new QuestionWrapper();
		wrap.setDeptId(deptId);
		wrap.setPostId(postId);
		wrap.setVenues(venues);
		wrap.setCheckId(checkId);

		// 查询出题系统中的
		List<QuestionWrapper> list = questionService.selectCheckerList(wrap);
		if (list != null && list.size() > 0) {
			for (QuestionWrapper question : list) {
				String examiners = question.getExaminer();
				//
				if (examiners.indexOf(String.valueOf(checkId)) > 0) {

				} else {
					return prefix + "/noquestion";
				}
			}
		} else {
			return prefix + "/noquestion";
		}
		User user = userService.selectUserById(checkId);// 用户
		Dept dept = deptService.selectDeptById(deptId);// 部门
		ExamVenues venu = examVenuesService.selectExamVenuesById(Long
				.parseLong(venues)); // 场次
		List<ExamList> exams = getQuestions(list.get(0));
		// 需要校验死否已经生成，如果已经生成，则无需重新生成
		insertHisQuestion(wrap, exams);

		mmap.put("questions", exams);
		mmap.put("userName", user.getUserName());
		mmap.put("deptName", dept.getDeptName());
		mmap.put("venues", venu.getName());
		return prefix + "/question";
	}

	/**
	 * 将所有考题拼接
	 * 
	 * @return
	 */
	private List<ExamList> getQuestions(QuestionWrapper question) {
		String type1 = question.getType1();
		String type2 = question.getType2();
		String type3 = question.getType3();
		String type4 = question.getType4();
		String type5 = question.getType5();

		int num1 = question.getNum1();
		int num2 = question.getNum2();
		int num3 = question.getNum3();
		int num4 = question.getNum4();
		int num5 = question.getNum5() == null ? 0 : question.getNum5();
		
		String examId = random(type1,num1) + "," + 
				random(type2,num2) + "," + random(type3,num3) + "," + random(type4,num4) + ","
				+ random(type5,num5);
		return examListService.selectExamListByIds(examId.split(","));
	}

	/**
	 * 随机抽题算法
	 * @param jj
	 * @param random
	 */
	private String random(String type, int num) {
		if(StringUtils.isEmpty(type) || "0".equals(type) || num == 0) 
			return "0";
		type = type.substring(2, type.length());
		String[] types = type.split(",");
		String title = "";
		boolean isfirst = true;
		if(types.length > 0 && types.length >= num) {
			java.util.Random random = new java.util.Random();// 定义随机类
			Map<Integer,Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < num; i++) {
				int result = random.nextInt(types.length);
				while (map.containsKey(result)) {
					result = random.nextInt(types.length);
				}
				map.put(result, result);
				if(isfirst) {
					title = types[result];
					isfirst = false;
				} else {
					title = title + "," +  types[result];
				}
			}
		}
		return title;
	}
	/**
	 * 生成该考员的试卷
	 * 
	 * @param wrap
	 * @param list
	 */
	private void insertHisQuestion(QuestionWrapper wrap, List<ExamList> list) {
		// 生成考卷
		Question question = new Question();
		question.setDeptId(wrap.getDeptId());
		question.setPostId(wrap.getPostId());
		question.setUserId(wrap.getCheckId());
		question.setVenues(wrap.getVenues());

		questionService.insertQuestion(question);
		// 生成考题内容
		List<ExamQuestionEntity> lists = new ArrayList<ExamQuestionEntity>();
		for (ExamList exam : list) {
			ExamQuestionEntity entity = new ExamQuestionEntity();
			entity.setqId(question.getQId());
			entity.setExamId(exam.getExamId());
			lists.add(entity);
		}
		
		for (ExamQuestionEntity entity : lists) {
			questionService.insertExamQuestion(entity);
		}
	}

	/**
	 * 考题
	 * 
	 * @return
	 */
	@GetMapping("/check/{examId}")
	public String check(@PathVariable("examId") Long examId, ModelMap mmap) {
		ExamList examList = examListService.selectExamListById(examId);
		mmap.put("examList", examList);
		return prefix + "/checkquestion";
	}

	/**
	 * 获取考试历史
	 * 
	 * @param examList
	 * @return
	 */
	@RequiresPermissions("exam:exam:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Question question) {
		startPage();
		List<Question> list = questionService.selectQuestionsList(question);
		resetMark(list);
		return getDataTable(list);
	}
	
	/**
	 * 导出考卷
	 * @param question
	 */
	@RequiresPermissions("exam:question:export")
	@PostMapping("/exportQuestion")
	@ResponseBody
	public AjaxResult exportQuestion(Question question) {
		List<Question> list = questionService.selectQuestionsList(question);
		ExcelUtil<Question> util = new ExcelUtil<Question>(Question.class);
		ExportPdf pdf = new ExportPdf();
		pdf.test1();
		return util.exportExcel(list, "queslist");
	}

	private void resetMark(List<Question> list) {
		for (Question question : list) {
			double point = 0;
			List<ExamQuestionEntity> marks = questionService.selectMarkList(question.getQId());
			int index = 0;
			for (ExamQuestionEntity mark : marks) {
				if(mark.getNpoint() != null) {
					index = 1;
					point += mark.getNpoint();
				} 
				if(mark.getNpoint1() != null){
					if(index == 0) {
						index = 1;
					} else {
						index = 2;
					}
					point += mark.getNpoint1();
				} 
				if(mark.getNpoint2() != null){
					if(index == 0) index = 1;
					if(index == 1) index = 2;
					if(index == 2) index = 3;
					point += mark.getNpoint2();
				}
			}
			if(index == 0) index = 1;
			question.setNpoint((point/marks.size())/index);
		}
	}
	
	/**
	 * 获取题目类型
	 * 
	 * @param examList
	 * @return
	 */
	@RequiresPermissions("exam:exam:queslist")
	@PostMapping("/queslist/{type}")
	@ResponseBody
	public TableDataInfo queslist(@PathVariable("type") int type,
			QuestionWrapper wrap) {
		startPage();
		List<ExamList> list = new ArrayList<ExamList>();
		// 查询题型，然后按题型归类
		Long dept_id = wrap.getDeptId();
		// 部门不能为空
		if ("null".equals(dept_id) || dept_id == null)
			return getDataTable(list);
		List<ExamType> typelist = examTypeService.selectExamTypeByDept(dept_id);
		if (type > typelist.size())
			return getDataTable(list);
		// 获取当前组织所有类型
		ExamList examList = new ExamList();
		examList.setDeptId(dept_id);
		examList.setPostId(wrap.getPostId());
		Long typeId = typelist.get(type - 1) == null ? Long.parseLong("0")
				: typelist.get(type - 1).getTypeId();
		examList.setTypeId(typeId);

		list = examListService.selectExamListList(examList);

		return getDataTable(list);
	}

	@PostMapping("/examdept/{deptId}")
	@ResponseBody
	public TableDataInfo examDept(@PathVariable("deptId") Long deptId) {
		startPage();
		Dept dept = deptService.selectDeptById(deptId);
		List<Dept> list = new ArrayList<Dept>();
		list.add(dept);
		return getDataTable(list);
	}

	@PostMapping("/examiner/{depts}")
	@ResponseBody
	public TableDataInfo examiner(@PathVariable("depts") String dept, User user) {
		startPage();
		setDept(dept, user);
		List<User> list = userService.selectExaminerList(user);
		return getDataTable(list);
	}

	private void setDept(String dept, User user) {
		List<Long> depts = new ArrayList<Long>();
		if (!StringUtils.isEmpty(dept) && "-1".equals(dept)) {
			user.setDepts(null);
		} else {
			String[] dpt = dept.split(",");
			for (String pt : dpt) {
				Long deptId = Long.parseLong(pt);
				depts.add(deptId);
			}
			user.setDepts(depts.toArray(new Long[0]));
		}

	}

	@PostMapping("/checkdept")
	@ResponseBody
	public TableDataInfo checkdept() {
		startPage();
		List<Dept> list = deptService.selectDept();
		return getDataTable(list);
	}

	@PostMapping("/checker/{depts}")
	@ResponseBody
	public TableDataInfo checker(@PathVariable("depts") String dept, User user) {
		startPage();
		setDept(dept, user);
		List<User> list = userService.selectCheckerList(user);
		return getDataTable(list);
	}

	/**
	 * 出题系统生成考卷
	 */
	@Log(title = "出题系统", businessType = BusinessType.INSERT)
	@RequiresPermissions("exam:exam:create")
	@PostMapping("/createExam")
	@ResponseBody
	private AjaxResult createExam(ModelMap mmap) {
		// 此处写生成逻辑
		return toAjax(1);
	}

	/**
	 * 出题历史
	 * 
	 * @return
	 */
	@RequiresPermissions("exam:checkhistory:view")
	@PostMapping("/checkhistory")
	@ResponseBody
	public AjaxResult checkhistory(QuestionWrapper wrap, ModelMap mmap) {
		User user = getSysUser();
		wrap.setCreateBy(user.getUserName());
		questionService.createCheck(wrap);
		return success("出题完成");
	}
	
	/**
	 * 修改题库
	 */
	@GetMapping("/editCheck/{checkId}")
	public String editCheck(@PathVariable("checkId") Long checkId, ModelMap mmap) {
		QuestionWrapper checkList = questionService.selectCheckById(checkId);
		mmap.put("checkList", checkList);
		return prefix + "/editExam";
	}

	/**
	 * 出题系统
	 * 
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("exam:hischeck:view")
	@GetMapping("/hischeck")
	public String hischeck(ModelMap mmap) {
		mmap.put("posts", postService.selectPostAll());
		mmap.put("venues", examVenuesService.selectExamVenues());
		return prefix + "/hischeck";
	}

	@RequiresPermissions("exam:hischeck:list")
	@PostMapping("/checklist")
	@ResponseBody
	public TableDataInfo hischeck(QuestionWrapper wrap) {
		startPage();
		List<QuestionWrapper> list = questionService.selectCheckerList(wrap);
		return getDataTable(list);
	}
	/**
	 * @param mmap
	 * @return
	 */
	@GetMapping("/filterUser/{deptId}")
	@ResponseBody
	public AjaxResult filterUser(@PathVariable("deptId") String deptId, User user, ModelMap mmap) {
		Long[] dept = new Long[1];
		dept[0] = Long.parseLong(deptId);
		user.setDepts(dept);
		AjaxResult ajax = new AjaxResult();
		ajax.put("examiner", userService.selectExaminerList(user));
		return ajax;
	}

}
