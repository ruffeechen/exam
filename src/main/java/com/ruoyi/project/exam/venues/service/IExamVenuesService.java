package com.ruoyi.project.exam.venues.service;

import com.ruoyi.project.exam.venues.domain.ExamVenues;
import java.util.List;

/**
 * 考试场次Service接口
 * 
 * @author crf
 * @date 2019-09-16
 */
public interface IExamVenuesService 
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
     * 批量删除考试场次
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamVenuesByIds(String ids);

    /**
     * 删除考试场次信息
     * 
     * @param venuesId 考试场次ID
     * @return 结果
     */
    public int deleteExamVenuesById(Long venuesId);
    /**
     * 导入场次信息
     * @param venueList
     * @param updateSupport
     * @return
     */
    public String importVenuelist(List<ExamVenues> venueList, boolean updateSupport);
}
