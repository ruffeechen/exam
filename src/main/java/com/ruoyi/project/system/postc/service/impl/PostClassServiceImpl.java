package com.ruoyi.project.system.postc.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.system.postc.mapper.PostClassMapper;
import com.ruoyi.project.system.postc.domain.PostClass;
import com.ruoyi.project.system.postc.service.IPostClassService;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;

/**
 * 岗位职级Service业务层处理
 * 
 * @author crf
 * @date 2019-09-23
 */
@Service
public class PostClassServiceImpl implements IPostClassService 
{
    @Autowired
    private PostClassMapper postClassMapper;

    /**
     * 查询岗位职级
     * 
     * @param classId 岗位职级ID
     * @return 岗位职级
     */
    @Override
    public PostClass selectPostClassById(Long classId)
    {
        return postClassMapper.selectPostClassById(classId);
    }

    /**
     * 查询岗位职级列表
     * 
     * @param postClass 岗位职级
     * @return 岗位职级
     */
    @Override
    @DataScope(postAlias = "p", postcAlias = "c")
    public List<PostClass> selectPostClassList(PostClass postClass)
    {
        return postClassMapper.selectPostClassList(postClass);
    }

    /**
     * 新增岗位职级
     * 
     * @param postClass 岗位职级
     * @return 结果
     */
    @Override
    public int insertPostClass(PostClass postClass)
    {
    	postClass.setCreateBy(ShiroUtils.getLoginName());
        postClass.setCreateTime(DateUtils.getNowDate());
        return postClassMapper.insertPostClass(postClass);
    }

    /**
     * 修改岗位职级
     * 
     * @param postClass 岗位职级
     * @return 结果
     */
    @Override
    public int updatePostClass(PostClass postClass)
    {
        postClass.setUpdateTime(DateUtils.getNowDate());
        return postClassMapper.updatePostClass(postClass);
    }

    /**
     * 删除岗位职级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePostClassByIds(String ids)
    {
        return postClassMapper.deletePostClassByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除岗位职级信息
     * 
     * @param classId 岗位职级ID
     * @return 结果
     */
    public int deletePostClassById(Long classId)
    {
        return postClassMapper.deletePostClassById(classId);
    }
}
