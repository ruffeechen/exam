package com.ruoyi.project.exam.question.service;

import java.util.List;

import com.ruoyi.project.exam.question.domain.ExamQuestionEntity;
import com.ruoyi.project.exam.question.domain.Question;
import com.ruoyi.project.exam.question.domain.QuestionWrapper;

public interface IQuestionService {
	/**
     * 查询考题
     * 
     * @param qId 考题ID
     * @return 考题
     */
    public Question selectQuestionById(Long qId);

    public QuestionWrapper selectCheckById(Long checkId);
    
    public List<ExamQuestionEntity> selectMarkList(Long qId);
    /**
     * 新增考题
     * 
     * @param question 考题
     * @return 结果
     */
    public int insertQuestion(Question question);
    
    public int insertExamQuestion(ExamQuestionEntity entity);

    /**
     * 查询考题列表
     * 
     * @param question 考题
     * @return 考题集合
     */
    public List<Question> selectQuestionList(Question question);
    
    /**
     * 查询考题列表
     * 
     * @param question 考题
     * @return 考题集合
     */
    public List<Question> selectQuestionsList(Question question);
    
    /**
     * 创建随机出题数据
     */
    public int createCheck(QuestionWrapper wrap);

    /**
     * 查询出题记录
     * @param wrap
     * @return
     */
    public List<QuestionWrapper> selectCheckerList(QuestionWrapper wrap);
    
    public int updateExamMark(ExamQuestionEntity entity);
}
