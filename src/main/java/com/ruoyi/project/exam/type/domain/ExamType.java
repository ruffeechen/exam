package com.ruoyi.project.exam.type.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 题目类型对象 t_exam_type
 * 
 * @author crf
 * @date 2019-09-16
 */
public class ExamType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long typeId;

    @Excel(name = "编码")
    private String code;

    @Excel(name = "类型名称")
    private String name;
    
    @Excel(name = "备注")
    private String vmemo;
    
    private Long deptId;

    public String getVmemo() {
		return vmemo;
	}

	public void setVmemo(String vmemo) {
		this.vmemo = vmemo;
	}

	public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
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

    public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("code", getCode())
            .append("name", getName())
            .append("vmemo", getVmemo())
            .toString();
    }
}
