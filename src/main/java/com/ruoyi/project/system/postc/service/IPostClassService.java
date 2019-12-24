package com.ruoyi.project.system.postc.service;

import com.ruoyi.project.system.postc.domain.PostClass;
import java.util.List;

/**
 * 岗位职级Service接口
 * 
 * @author crf
 * @date 2019-09-23
 */
public interface IPostClassService 
{
    /**
     * 查询岗位职级
     * 
     * @param classId 岗位职级ID
     * @return 岗位职级
     */
    public PostClass selectPostClassById(Long classId);

    /**
     * 查询岗位职级列表
     * 
     * @param postClass 岗位职级
     * @return 岗位职级集合
     */
    public List<PostClass> selectPostClassList(PostClass postClass);

    /**
     * 新增岗位职级
     * 
     * @param postClass 岗位职级
     * @return 结果
     */
    public int insertPostClass(PostClass postClass);

    /**
     * 修改岗位职级
     * 
     * @param postClass 岗位职级
     * @return 结果
     */
    public int updatePostClass(PostClass postClass);

    /**
     * 批量删除岗位职级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePostClassByIds(String ids);

    /**
     * 删除岗位职级信息
     * 
     * @param classId 岗位职级ID
     * @return 结果
     */
    public int deletePostClassById(Long classId);
}
