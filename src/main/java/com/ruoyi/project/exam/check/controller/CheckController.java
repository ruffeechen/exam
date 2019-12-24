package com.ruoyi.project.exam.check.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.exam.check.domain.CheckList;
@Controller
@RequestMapping("/exam/checklist")
public class CheckController extends BaseController{

	private String prefix = "exam/checklist";
	@RequiresPermissions("exam:check:view")
	@GetMapping()
	public String examlist() {
		return prefix + "/checklist";
	}
	
	/**
	 * 查询题库列表
	 */
	@RequiresPermissions("exam:check:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list() {
		startPage();
		List<CheckList> list = new ArrayList<CheckList>();
		CheckList check = new CheckList();
		check.setSerialno("1");
		check.setUserName("张三"); 
		list.add(check);
		return getDataTable(list);
	}
	
	/**
	 * 导出题库列表
	 */
	@RequiresPermissions("exam:check:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(CheckList examList) {
		List<CheckList> list = new ArrayList<CheckList>();
		CheckList check = new CheckList();
		check.setSerialno("1");
		check.setUserName("张三");
		list.add(check);
		ExcelUtil<CheckList> util = new ExcelUtil<CheckList>(CheckList.class);
		return util.exportExcel(list, "checklist");
	}
}
