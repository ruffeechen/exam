package com.ruoyi.project.exam.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.exam.exam.service.ExamMarkService;
import com.ruoyi.project.exam.examlist.domain.ExamList;
import com.ruoyi.project.exam.question.domain.ExamQuestionEntity;
import com.ruoyi.project.exam.question.service.IQuestionService;
import com.ruoyi.project.system.user.domain.User;

@Service
public class ExamMarkImpl implements ExamMarkService{
	@Autowired
	private IQuestionService questionService;
	/**
	 * 更新分数
	 */
	public void updateMark(User user, List<ExamQuestionEntity> list,ExamList data) {
		if(list != null && list.size() > 0) {
			List<String> exam = new ArrayList<String>();
			for (ExamQuestionEntity entity : list) {
				Long examId = entity.getExamId();
				exam.add(examId.toString());
			}
			String code = data.getCode();
			String[] point = code.split(",");
			int index = 0;
			for (ExamQuestionEntity mark : list) {
				if(mark.getChecker() != null && user.getUserId().equals(mark.getChecker())) {
					mark.setChecker(user.getUserId());
					mark.setNpoint(Double.valueOf(point[index]));
				} else if(mark.getChecker1() != null && user.getUserId().equals(mark.getChecker1())) {
					mark.setChecker1(user.getUserId());
					mark.setNpoint1(Double.valueOf(point[index]));
				} else if(mark.getChecker2() != null && user.getUserId().equals(mark.getChecker2())) {
					mark.setChecker2(user.getUserId());
					mark.setNpoint2(Double.valueOf(point[index]));
				} else if(mark.getChecker3() != null && user.getUserId().equals(mark.getChecker3())) {
					mark.setChecker3(user.getUserId());
					mark.setNpoint3(Double.valueOf(point[index]));
				} else {
					if(mark.getChecker() == null) {
						mark.setChecker(user.getUserId());
						mark.setNpoint(Double.valueOf(point[index]));
					} else if(mark.getChecker1() == null) {
						mark.setChecker1(user.getUserId());
						mark.setNpoint1(Double.valueOf(point[index]));
					} else if(mark.getChecker2() == null) {
						mark.setChecker2(user.getUserId());
						mark.setNpoint2(Double.valueOf(point[index]));
					} else if(mark.getChecker3() == null) {
						mark.setChecker3(user.getUserId());
						mark.setNpoint3(Double.valueOf(point[index]));
					}
				}
				questionService.updateExamMark(mark);
				index ++;
			}
			
		}
	}
	
}
