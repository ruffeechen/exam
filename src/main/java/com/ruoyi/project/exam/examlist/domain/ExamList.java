package com.ruoyi.project.exam.examlist.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 题库对象 t_exam_list
 * 
 * @author crf
 * @date 2019-09-16
 */
public class ExamList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考题id */
    private Long examId;

    /** 题目类型id */
    @Excel(name = "题目类型")
    private Long typeId;

    /** 考题编码 */
    @Excel(name = "考题编码")
    private String code;

    /** 考题名称 */
    @Excel(name = "考题名称")
    private String title;
    
    private String avatar;
    /** 答案 */
    @Excel(name = "答案路径 (/profile/avatar/1.jpg)")
    private String answer;

    /** 分数 */
    @Excel(name = "分数")
    private Double npoint;

    private String status;
    
    @Excel(name = "部门")
    private Long deptId;
    
    @Excel(name = "岗位")
    private Long postId;

    public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	/** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }
    public void setNpoint(Double npoint) 
    {
        this.npoint = npoint;
    }

    public Double getNpoint() 
    {
        return npoint;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examId", getExamId())
            .append("typeId", getTypeId())
            .append("code", getCode())
            .append("title", getTitle())
            .append("avatar", getAvatar())
            .append("answer", getAnswer())
            .append("npoint", getNpoint())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
