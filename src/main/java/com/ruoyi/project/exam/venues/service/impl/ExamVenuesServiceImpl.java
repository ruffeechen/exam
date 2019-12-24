package com.ruoyi.project.exam.venues.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.exam.venues.domain.ExamVenues;
import com.ruoyi.project.exam.venues.mapper.ExamVenuesMapper;
import com.ruoyi.project.exam.venues.service.IExamVenuesService;

/**
 * 考试场次Service业务层处理
 * 
 * @author crf
 * @date 2019-09-16
 */
@Service
public class ExamVenuesServiceImpl implements IExamVenuesService {
	@Autowired
	private ExamVenuesMapper examVenuesMapper;

	/**
	 * 查询考试场次
	 * 
	 * @param venuesId
	 *            考试场次ID
	 * @return 考试场次
	 */
	@Override
	public ExamVenues selectExamVenuesById(Long venuesId) {
		return examVenuesMapper.selectExamVenuesById(venuesId);
	}

	@Override
	public List<ExamVenues> selectExamVenues() {
		return examVenuesMapper.selectExamVenues();
	}
	
	/**
	 * 查询考试场次列表
	 * 
	 * @param examVenues
	 *            考试场次
	 * @return 考试场次
	 */
	@Override
	public List<ExamVenues> selectExamVenuesList(ExamVenues examVenues) {
		return examVenuesMapper.selectExamVenuesList(examVenues);
	}

	/**
	 * 新增考试场次
	 * 
	 * @param examVenues
	 *            考试场次
	 * @return 结果
	 */
	@Override
	public int insertExamVenues(ExamVenues examVenues) {
		examVenues.setCreateTime(DateUtils.getNowDate());
		return examVenuesMapper.insertExamVenues(examVenues);
	}

	/**
	 * 修改考试场次
	 * 
	 * @param examVenues
	 *            考试场次
	 * @return 结果
	 */
	@Override
	public int updateExamVenues(ExamVenues examVenues) {
		examVenues.setUpdateTime(DateUtils.getNowDate());
		return examVenuesMapper.updateExamVenues(examVenues);
	}

	/**
	 * 删除考试场次对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteExamVenuesByIds(String ids) {
		return examVenuesMapper.deleteExamVenuesByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除考试场次信息
	 * 
	 * @param venuesId
	 *            考试场次ID
	 * @return 结果
	 */
	public int deleteExamVenuesById(Long venuesId) {
		return examVenuesMapper.deleteExamVenuesById(venuesId);
	}

	/**
	 * 导入数据
	 */
	@Override
	public String importVenuelist(List<ExamVenues> venueList,
			boolean updateSupport) {

		if (StringUtils.isNull(venueList) || venueList.size() == 0) {
			throw new BusinessException("导入数据不能为空！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		String operName = ShiroUtils.getLoginName();
		for (ExamVenues venue : venueList) {
			try {
				// 验证是否存在这个题目
				ExamVenues u = examVenuesMapper.selectExamByName(venue);
				if (StringUtils.isNull(u)) {
					venue.setCreateBy(operName);
					this.insertExamVenues(venue);
					successNum++;
					successMsg.append("<br/>" + successNum + "、场次 "
							+ venue.getCode() + venue.getName() + " 导入成功");
				} else {
					failureNum++;
					failureMsg.append("<br/>" + failureNum + "、场次 "
							+ venue.getCode() + venue.getName() + " 已存在");
				}
			} catch (Exception e) {
				failureNum++;
				String msg = "<br/>" + failureNum + "、场次 " + venue.getName()
						+ " 导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		if (failureNum > 0) {
			failureMsg
					.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new BusinessException(failureMsg.toString());
		} else {
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}
}
