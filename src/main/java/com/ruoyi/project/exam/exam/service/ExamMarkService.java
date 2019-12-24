package com.ruoyi.project.exam.exam.service;

import java.util.List;

import com.ruoyi.project.exam.examlist.domain.ExamList;
import com.ruoyi.project.exam.question.domain.ExamQuestionEntity;
import com.ruoyi.project.system.user.domain.User;

public interface ExamMarkService {
	public void updateMark(User user, List<ExamQuestionEntity> list,ExamList data);
}
