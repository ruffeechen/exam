package com.ruoyi.project.exam.question.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ruoyi.project.exam.question.domain.ExamQuestionEntity;
import com.ruoyi.project.exam.question.domain.Question;
import com.ruoyi.project.exam.question.domain.QuestionWrapper;

@Mapper
public interface QuestionMapper {
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
     * 查询出题记录
     * @param wrap
     * @return
     */
    public List<QuestionWrapper> selectCheckerList(QuestionWrapper wrap);
    /**
     * 创建出题系统数据
     * @param wrap
     * @return
     */
    public int createCheck(QuestionWrapper wrap);
    
    public int insertExamQuestion(ExamQuestionEntity entity);
    /**
     * 新增考题
     * 
     * @param question 考题
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改考题
     * 
     * @param question 考题
     * @return 结果
     */
    public int updateQuestion(Question question);

    /**
     * 删除考题
     * 
     * @param qId 考题ID
     * @return 结果
     */
    public int deleteQuestionById(Long qId);

    /**
     * 批量删除考题
     * 
     * @param qIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteQuestionByIds(String[] qIds);
    
    public int updateExamMark(ExamQuestionEntity entity);
}
