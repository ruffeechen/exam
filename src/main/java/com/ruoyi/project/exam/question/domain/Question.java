package com.ruoyi.project.exam.question.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

public class Question extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 试卷id */
	private Long qId;

	/** 部门 */
	@Excel(name = "部门")
	private Long deptId;

	/** 岗位职级 */
	@Excel(name = "岗位职级")
	private Long postc;

	/** 岗位 */
	@Excel(name = "岗位")
	private Long postId;

	private String venuesName;
	private String userName;
	
	/** 职位等级 */
	@Excel(name = "职位等级")
	private Integer level;

	/** 考试场次 */
	@Excel(name = "考试场次")
	private String venues;
	
	@Excel(name = "考员")
	private Long userId;

	/** 考试名称 */
	@Excel(name = "考试名称")
	private String name;

	/** 总得分 */
	@Excel(name = "总得分")
	private Double npoint;

	public void setQId(Long qId) {
		this.qId = qId;
	}

	public Long getQId() {
		return qId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setPostc(Long postc) {
		this.postc = postc;
	}

	public Long getPostc() {
		return postc;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getPostId() {
		return postId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return level;
	}

	public void setVenues(String venues) {
		this.venues = venues;
	}

	public String getVenues() {
		return venues;
	}

	public String getVenuesName() {
		return venuesName;
	}

	public void setVenuesName(String venuesName) {
		this.venuesName = venuesName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNpoint(Double npoint) {
		this.npoint = npoint;
	}

	public Double getNpoint() {
		return npoint;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("qId", getQId()).append("deptId", getDeptId())
				.append("postc", getPostc()).append("postId", getPostId())
				.append("level", getLevel()).append("venues", getVenues())
				.append("name", getName()).append("npoint", getNpoint())
				.append("createBy", getCreateBy())
				.append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy())
				.append("updateTime", getUpdateTime()).toString();
	}
}
