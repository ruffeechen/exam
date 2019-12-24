package com.ruoyi.project.exam.venues.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 考试场次对象 t_exam_venues
 * 
 * @author crf
 * @date 2019-09-16
 */
public class ExamVenues extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场次 */
    private Long venuesId;

    /** 年度 */
    @Excel(name = "年度")
    private String nyear;

    /** 月份 */
    @Excel(name = "月份")
    private String nmonth;

    /** 场次编号 */
    @Excel(name = "场次编号")
    private String code;

    /** 场次名称 */
    @Excel(name = "场次名称")
    private String name;

    public void setVenuesId(Long venuesId) 
    {
        this.venuesId = venuesId;
    }

    public Long getVenuesId() 
    {
        return venuesId;
    }
    public void setNyear(String nyear) 
    {
        this.nyear = nyear;
    }

    public String getNyear() 
    {
        return nyear;
    }
    public void setNmonth(String nmonth) 
    {
        this.nmonth = nmonth;
    }

    public String getNmonth() 
    {
        return nmonth;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("venuesId", getVenuesId())
            .append("nyear", getNyear())
            .append("nmonth", getNmonth())
            .append("code", getCode())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
