package com.ruoyi.project.exam.type.controller;

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
import com.ruoyi.project.exam.type.domain.ExamType;
import com.ruoyi.project.exam.type.service.IExamTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 题目类型Controller
 * 
 * @author crf
 * @date 2019-09-16
 */
@Controller
@RequestMapping("/exam/type")
public class ExamTypeController extends BaseController
{
    private String prefix = "exam/type";

    @Autowired
    private IExamTypeService examTypeService;

    @RequiresPermissions("exam:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询题目类型列表
     */
    @RequiresPermissions("exam:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExamType examType)
    {
        startPage();
        List<ExamType> list = examTypeService.selectExamTypeList(examType);
        return getDataTable(list);
    }

    /**
     * 导出题目类型列表
     */
    @RequiresPermissions("exam:type:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamType examType)
    {
        List<ExamType> list = examTypeService.selectExamTypeList(examType);
        ExcelUtil<ExamType> util = new ExcelUtil<ExamType>(ExamType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增题目类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存题目类型
     */
    @RequiresPermissions("exam:type:add")
    @Log(title = "题目类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExamType examType)
    {
        return toAjax(examTypeService.insertExamType(examType));
    }

    /**
     * 修改题目类型
     */
    @GetMapping("/edit/{typeId}")
    public String edit(@PathVariable("typeId") Long typeId, ModelMap mmap)
    {
        ExamType examType = examTypeService.selectExamTypeById(typeId);
        mmap.put("examType", examType);
        return prefix + "/edit";
    }

    /**
     * 修改保存题目类型
     */
    @RequiresPermissions("exam:type:edit")
    @Log(title = "题目类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExamType examType)
    {
        return toAjax(examTypeService.updateExamType(examType));
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{typeId}")
    public String detail(@PathVariable("typeId") Long typeId, ModelMap mmap)
    {
        mmap.put("examtype", examTypeService.selectExamTypeById(typeId));
        mmap.put("examList", examTypeService.selectTypeByAll());
        return "exam/examlist/examlist";
    }
    
    /**
     * 删除题目类型
     */
    @RequiresPermissions("exam:type:remove")
    @Log(title = "题目类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(examTypeService.deleteExamTypeByIds(ids));
    }
}
