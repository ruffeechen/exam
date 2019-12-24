package com.ruoyi.project.exam.examlist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ruoyi.project.exam.examlist.domain.ExamFileList;
import com.ruoyi.project.exam.examlist.domain.ExamList;

/**
 * 题库Mapper接口
 * 
 * @author crf
 * @date 2019-09-16
 */
@Mapper
public interface ExamListMapper 
{
    /**
     * 查询题库
     * 
     * @param examId 题库ID
     * @return 题库
     */
    public ExamList selectExamListById(Long examId);

    /**
     * 查询题库列表
     * 
     * @param examList 题库
     * @return 题库集合
     */
    public List<ExamList> selectExamListList(ExamList examList);
    /**
     * 根据题号查询题库
     * @param title
     * @return
     */
    public ExamList selectExamByName(String title);
    /**
     * 新增题库
     * 
     * @param examList 题库
     * @return 结果
     */
    public int insertExamList(ExamList examList);

    /**
     * 修改题库
     * 
     * @param examList 题库
     * @return 结果
     */
    public int updateExamList(ExamList examList);

    public int updateFiles(ExamFileList files);
    
    public List<ExamFileList> selectExamFileById(Long examId);
    
    public List<ExamFileList> selectExamFileByIds(String[] examId);
    /**
     * 删除题库
     * 
     * @param examId 题库ID
     * @return 结果
     */
    public int deleteExamListById(Long examId);

    /**
     * 批量删除题库
     * 
     * @param examIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamListByIds(String[] examIds);
    
    public List<ExamList> selectExamListByIds(String[] examId);
}
