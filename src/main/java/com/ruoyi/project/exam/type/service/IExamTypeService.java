package com.ruoyi.project.exam.type.service;

import com.ruoyi.project.exam.type.domain.ExamType;
import java.util.List;

/**
 * 题目类型Service接口
 * 
 * @author crf
 * @date 2019-09-16
 */
public interface IExamTypeService 
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

    List<ExamType> selectExamTypeByDept(Long deptId);
    
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
     * 批量删除题目类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamTypeByIds(String ids);

    /**
     * 删除题目类型信息
     * 
     * @param typeId 题目类型ID
     * @return 结果
     */
    public int deleteExamTypeById(Long typeId);
}
