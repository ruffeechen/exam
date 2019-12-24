package com.ruoyi.project.system.postc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 岗位职级对象 sys_post_class
 * 
 * @author crf
 * @date 2019-09-23
 */
public class PostClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 岗位职级 */
    private Long classId;

    /** 岗位 */
    @Excel(name = "岗位")
    private Long postId;

    /** 职位等级 */
    @Excel(name = "职位等级")
    private Integer level;

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setPostId(Long postId) 
    {
        this.postId = postId;
    }

    public Long getPostId() 
    {
        return postId;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("postId", getPostId())
            .append("level", getLevel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
