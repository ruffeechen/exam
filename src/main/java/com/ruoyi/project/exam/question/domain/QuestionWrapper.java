package com.ruoyi.project.exam.question.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 请求数据
 * @author chenrifei
 *
 */
public class QuestionWrapper extends BaseEntity{
	private static final long serialVersionUID = 1L;

	private String title;
	private String venues;
	private Long checkId;
	/** 部门 */
	private Long deptId;

	/** 岗位职级 */
	private Long postc;

	/** 岗位 */
	private Long postId;
	
	/**
	 * 题目
	 */
	private String type1;
	private String type2;
	private String type3;
	private String type4;
	private String type5;
	
	/**
	 * 题数
	 */
	private Integer num1;
	private Integer num2;
	private Integer num3;
	private Integer num4;
	private Integer num5;
	
	/**
	 * 考员
	 */
	private String examDept;
	private String examiner;
	
	/**
	 * 考官
	 */
	private String checker;
	private String checkDept;
	
	public Long getCheckId() {
		return checkId;
	}
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVenues() {
		return venues;
	}
	public void setVenues(String venues) {
		this.venues = venues;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getPostc() {
		return postc;
	}
	public void setPostc(Long postc) {
		this.postc = postc;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	public String getType4() {
		return type4;
	}
	public void setType4(String type4) {
		this.type4 = type4;
	}
	public String getType5() {
		return type5;
	}
	public void setType5(String type5) {
		this.type5 = type5;
	}
	public Integer getNum1() {
		return num1;
	}
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}
	public Integer getNum2() {
		return num2;
	}
	public void setNum2(Integer num2) {
		this.num2 = num2;
	}
	public Integer getNum3() {
		return num3;
	}
	public void setNum3(Integer num3) {
		this.num3 = num3;
	}
	public Integer getNum4() {
		return num4;
	}
	public void setNum4(Integer num4) {
		this.num4 = num4;
	}
	public Integer getNum5() {
		return num5;
	}
	public void setNum5(Integer num5) {
		this.num5 = num5;
	}
	public String getExamDept() {
		return examDept;
	}
	public void setExamDept(String examDept) {
		this.examDept = examDept;
	}
	public String getExaminer() {
		return examiner;
	}
	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getCheckDept() {
		return checkDept;
	}
	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}
	
}
