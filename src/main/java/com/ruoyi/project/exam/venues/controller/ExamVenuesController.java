package com.ruoyi.project.exam.venues.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.exam.venues.domain.ExamVenues;
import com.ruoyi.project.exam.venues.service.IExamVenuesService;

/**
 * 考试场次Controller
 * 
 * @author crf
 * @date 2019-09-16
 */
@Controller
@RequestMapping("/exam/venues")
public class ExamVenuesController extends BaseController
{
    private String prefix = "exam/venues";

    @Autowired
    private IExamVenuesService examVenuesService;

    @RequiresPermissions("exam:venues:view")
    @GetMapping()
    public String venues()
    {
        return prefix + "/venues";
    }

    /**
     * 查询考试场次列表
     */
    @RequiresPermissions("exam:venues:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExamVenues examVenues)
    {
        startPage();
        List<ExamVenues> list = examVenuesService.selectExamVenuesList(examVenues);
        return getDataTable(list);
    }

    /**
     * 导出考试场次列表
     */
    @RequiresPermissions("exam:venues:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamVenues examVenues)
    {
        List<ExamVenues> list = examVenuesService.selectExamVenuesList(examVenues);
        ExcelUtil<ExamVenues> util = new ExcelUtil<ExamVenues>(ExamVenues.class);
        return util.exportExcel(list, "venues");
    }

    /**
     * 新增考试场次
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存考试场次
     */
    @RequiresPermissions("exam:venues:add")
    @Log(title = "考试场次", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExamVenues examVenues)
    {
        return toAjax(examVenuesService.insertExamVenues(examVenues));
    }

    /**
     * 修改考试场次
     */
    @GetMapping("/edit/{venuesId}")
    public String edit(@PathVariable("venuesId") Long venuesId, ModelMap mmap)
    {
        ExamVenues examVenues = examVenuesService.selectExamVenuesById(venuesId);
        mmap.put("examVenues", examVenues);
        return prefix + "/edit";
    }

    /**
     * 修改保存考试场次
     */
    @RequiresPermissions("exam:venues:edit")
    @Log(title = "考试场次", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExamVenues examVenues)
    {
        return toAjax(examVenuesService.updateExamVenues(examVenues));
    }

    /**
     * 删除考试场次
     */
    @RequiresPermissions("exam:venues:remove")
    @Log(title = "考试场次", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(examVenuesService.deleteExamVenuesByIds(ids));
    }
    
    @Log(title = "场次管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("exam:venues:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ExamVenues> util = new ExcelUtil<ExamVenues>(ExamVenues.class);
        List<ExamVenues> venueList = util.importExcel(file.getInputStream());
        String message = examVenuesService.importVenuelist(venueList, updateSupport);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("exam:venues:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<ExamVenues> util = new ExcelUtil<ExamVenues>(ExamVenues.class);
        return util.importTemplateExcel("场次数据");
    }
}
