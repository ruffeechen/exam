package com.ruoyi.project.exam.question.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 考题与考卷
 * @author chenrifei
 *
 */
public class ExamQuestionEntity  extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Long markId;

	/** 试卷id */
	private Long qId;
	private Long examId;
	private Long checker;
	private Long checker1;
	private Long checker2;
	private Long checker3;
	private Double npoint;
	private Double npoint1;
	private Double npoint2;
	private Double npoint3;
	
	public Long getMarkId() {
		return markId;
	}
	public void setMarkId(Long markId) {
		this.markId = markId;
	}
	public Long getqId() {
		return qId;
	}
	public void setqId(Long qId) {
		this.qId = qId;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getChecker() {
		return checker;
	}
	public void setChecker(Long checker) {
		this.checker = checker;
	}
	public Long getChecker1() {
		return checker1;
	}
	public void setChecker1(Long checker1) {
		this.checker1 = checker1;
	}
	public Long getChecker2() {
		return checker2;
	}
	public void setChecker2(Long checker2) {
		this.checker2 = checker2;
	}
	public Long getChecker3() {
		return checker3;
	}
	public void setChecker3(Long checker3) {
		this.checker3 = checker3;
	}
	public Double getNpoint() {
		return npoint;
	}
	public void setNpoint(Double npoint) {
		this.npoint = npoint;
	}
	public Double getNpoint1() {
		return npoint1;
	}
	public void setNpoint1(Double npoint1) {
		this.npoint1 = npoint1;
	}
	public Double getNpoint2() {
		return npoint2;
	}
	public void setNpoint2(Double npoint2) {
		this.npoint2 = npoint2;
	}
	public Double getNpoint3() {
		return npoint3;
	}
	public void setNpoint3(Double npoint3) {
		this.npoint3 = npoint3;
	}
}
