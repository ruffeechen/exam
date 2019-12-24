package com.ruoyi.project.exam.type.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.exam.type.mapper.ExamTypeMapper;
import com.ruoyi.project.exam.type.domain.ExamType;
import com.ruoyi.project.exam.type.service.IExamTypeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 题目类型Service业务层处理
 * 
 * @author crf
 * @date 2019-09-16
 */
@Service
public class ExamTypeServiceImpl implements IExamTypeService 
{
    @Autowired
    private ExamTypeMapper examTypeMapper;

    /**
     * 查询题目类型
     * 
     * @param typeId 题目类型ID
     * @return 题目类型
     */
    @Override
    public ExamType selectExamTypeById(Long typeId)
    {
        return examTypeMapper.selectExamTypeById(typeId);
    }

    @Override
    public List<ExamType> selectTypeByAll() {
    	return examTypeMapper.selectTypeByAll();
    }
    
    /**
     * 查询题目类型列表
     * 
     * @param examType 题目类型
     * @return 题目类型
     */
    @Override
    public List<ExamType> selectExamTypeList(ExamType examType)
    {
        return examTypeMapper.selectExamTypeList(examType);
    }

    @Override
    public List<ExamType> selectExamTypeByDept(Long deptId) {
    	return examTypeMapper.selectExamTypeByDept(deptId);
    }
    /**
     * 新增题目类型
     * 
     * @param examType 题目类型
     * @return 结果
     */
    @Override
    public int insertExamType(ExamType examType)
    {
        return examTypeMapper.insertExamType(examType);
    }

    /**
     * 修改题目类型
     * 
     * @param examType 题目类型
     * @return 结果
     */
    @Override
    public int updateExamType(ExamType examType)
    {
        return examTypeMapper.updateExamType(examType);
    }

    /**
     * 删除题目类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExamTypeByIds(String ids)
    {
        return examTypeMapper.deleteExamTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除题目类型信息
     * 
     * @param typeId 题目类型ID
     * @return 结果
     */
    public int deleteExamTypeById(Long typeId)
    {
        return examTypeMapper.deleteExamTypeById(typeId);
    }
}
