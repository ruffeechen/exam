package com.ruoyi.project.exam.check.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 评分列表
 * 
 * @author chenrifei
 *
 */
public class CheckList extends BaseEntity {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	// 试卷id
	private String checkId;
	private String serialno;
	// '序号;
	private String userName;
	// '姓名'
	private String idno;
	// '身份证号'
	private String postId;
	// '岗位序列'
	private String title;
	// '申报专业类型'
	private String postName;
	// '申报岗位名称'
	private String postC;
	// '申报岗级'
	private String applymodule;
	// '申报模块'
	private String npoint;
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	// '评分'
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostC() {
		return postC;
	}
	public void setPostC(String postC) {
		this.postC = postC;
	}
	public String getApplymodule() {
		return applymodule;
	}
	public void setApplymodule(String applymodule) {
		this.applymodule = applymodule;
	}
	public String getNpoint() {
		return npoint;
	}
	public void setNpoint(String npoint) {
		this.npoint = npoint;
	}
}
