package com.ruoyi.project.exam.examlist.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class ExamFileList extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 考题id */
    private Long examId;
    private Long fileId;
    private String filepath;
    private String filename;
    private String type;
    
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    
}
