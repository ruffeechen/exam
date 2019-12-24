package com.ruoyi.project.exam.examlist.domain;

import com.ruoyi.framework.web.domain.BaseEntity;


public class ExamWrapper  extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/** 部门 */
	private Long deptId;

	/** 岗位 */
	private Long postId;

	/** 考试场次 */
	private Long venues;
	/** 用户id*/
	private Long userId;

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

	public Long getVenues() {
		return venues;
	}

	public void setVenues(Long venues) {
		this.venues = venues;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
