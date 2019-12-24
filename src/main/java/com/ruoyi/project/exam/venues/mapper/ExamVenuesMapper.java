package com.ruoyi.project.exam.venues.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ruoyi.project.exam.venues.domain.ExamVenues;

/**
 * 考试场次Mapper接口
 * 
 * @author crf
 * @date 2019-09-16
 */
@Mapper
public interface ExamVenuesMapper 
{
    /**
     * 查询考试场次
     * 
     * @param venuesId 考试场次ID
     * @return 考试场次
     */
    public ExamVenues selectExamVenuesById(Long venuesId);

    /**
     * 查询考试场次列表
     * 
     * @param examVenues 考试场次
     * @return 考试场次集合
     */
    public List<ExamVenues> selectExamVenuesList(ExamVenues examVenues);

    public List<ExamVenues> selectExamVenues();
    
    /**
     * 新增考试场次
     * 
     * @param examVenues 考试场次
     * @return 结果
     */
    public int insertExamVenues(ExamVenues examVenues);

    /**
     * 修改考试场次
     * 
     * @param examVenues 考试场次
     * @return 结果
     */
    public int updateExamVenues(ExamVenues examVenues);

    /**
     * 删除考试场次
     * 
     * @param venuesId 考试场次ID
     * @return 结果
     */
    public int deleteExamVenuesById(Long venuesId);

    /**
     * 批量删除考试场次
     * 
     * @param venuesIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamVenuesByIds(String[] venuesIds);
    
    public ExamVenues selectExamByName(ExamVenues venue);
}
