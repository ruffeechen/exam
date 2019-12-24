package com.ruoyi.project.exam.type.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ruoyi.project.exam.type.domain.ExamType;

/**
 * 题目类型Mapper接口
 * 
 * @author crf
 * @date 2019-09-16
 */
@Mapper
public interface ExamTypeMapper 
{
    /**
     * 查询题目类型
     * 
     * @param typeId 题目类型ID
     * @return 题目类型
     */
    public ExamType selectExamTypeById(Long typeId);

    public List<ExamType> selectTypeByAll();
    
    /**
     * 查询题目类型列表
     * 
     * @param examType 题目类型
     * @return 题目类型集合
     */
    public List<ExamType> selectExamTypeList(ExamType examType);

    public List<ExamType> selectExamTypeByDept(Long deptId);
    
    /**
     * 新增题目类型
     * 
     * @param examType 题目类型
     * @return 结果
     */
    public int insertExamType(ExamType examType);

    /**
     * 修改题目类型
     * 
     * @param examType 题目类型
     * @return 结果
     */
    public int updateExamType(ExamType examType);

    /**
     * 删除题目类型
     * 
     * @param typeId 题目类型ID
     * @return 结果
     */
    public int deleteExamTypeById(Long typeId);

    /**
     * 批量删除题目类型
     * 
     * @param typeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamTypeByIds(String[] typeIds);
}
