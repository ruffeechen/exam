package com.ruoyi.project.exam.examlist.service;

import java.util.List;

import com.ruoyi.project.exam.examlist.domain.ExamFileList;
import com.ruoyi.project.exam.examlist.domain.ExamList;
import com.ruoyi.project.exam.examlist.domain.ExamWrapper;

/**
 * 题库Service接口
 * 
 * @author crf
 * @date 2019-09-16
 */
public interface IExamListService 
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
     * 批量删除题库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamListByIds(String ids);
    
    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importExamlist(List<ExamList> userList, Boolean isUpdateSupport);

    /**
     * 删除题库信息
     * 
     * @param examId 题库ID
     * @return 结果
     */
    public int deleteExamListById(Long examId);
    
    public int changeStatus(ExamList exam);
    
    /**
     * 生成考卷后台
     */
    void createExam(ExamWrapper wrap);
    
    public List<ExamList> selectExamListByIds(String[] examId);

}
