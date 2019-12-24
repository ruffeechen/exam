package com.ruoyi.project.exam.question.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.exam.question.domain.ExamQuestionEntity;
import com.ruoyi.project.exam.question.domain.Question;
import com.ruoyi.project.exam.question.domain.QuestionWrapper;
import com.ruoyi.project.exam.question.mapper.QuestionMapper;
import com.ruoyi.project.exam.question.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	/**
	 * 查询考题
	 * 
	 * @param qId
	 *            考题ID
	 * @return 考题
	 */
	@Override
	public Question selectQuestionById(Long qId) {
		return questionMapper.selectQuestionById(qId);
	}

	@Override
	public QuestionWrapper selectCheckById(Long checkId) {
		return questionMapper.selectCheckById(checkId);
	}
	
	public List<ExamQuestionEntity> selectMarkList(Long qId) {
		return questionMapper.selectMarkList(qId);
	}
	/**
	 * 新增考题
	 * 
	 * @param question
	 *            考题
	 * @return 结果
	 */
	@Override
	public int insertQuestion(Question question) {
		question.setCreateTime(DateUtils.getNowDate());
		return questionMapper.insertQuestion(question);
	}

	/**
	 * 查询考题列表
	 * 
	 * @param question
	 *            考题
	 * @return 考题
	 */
	@Override
	public List<Question> selectQuestionList(Question question) {
		return questionMapper.selectQuestionList(question);
	}
	
	@Override
	public List<Question> selectQuestionsList(Question question) {
		return questionMapper.selectQuestionsList(question);
	}

	@Override
	public int createCheck(QuestionWrapper wrap) {
		if(StringUtils.isEmpty(wrap.getTitle())) {
			wrap.setTitle("-");
		}
		wrap.setCreateTime(DateUtils.getNowDate());
		return questionMapper.createCheck(wrap);
	}

	@Override
	public int insertExamQuestion(ExamQuestionEntity entity) {
		return questionMapper.insertExamQuestion(entity);
	}
	
	@Override
	public List<QuestionWrapper> selectCheckerList(QuestionWrapper wrap) {
		return questionMapper.selectCheckerList(wrap);
	}
	
	@Override
	public int updateExamMark(ExamQuestionEntity entity) {
		return questionMapper.updateExamMark(entity);
	}

}
