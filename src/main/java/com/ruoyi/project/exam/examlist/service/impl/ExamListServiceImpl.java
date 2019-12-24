package com.ruoyi.project.exam.examlist.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.exam.examlist.domain.ExamFileList;
import com.ruoyi.project.exam.examlist.domain.ExamList;
import com.ruoyi.project.exam.examlist.domain.ExamWrapper;
import com.ruoyi.project.exam.examlist.mapper.ExamListMapper;
import com.ruoyi.project.exam.examlist.service.IExamListService;

/**
 * 题库Service业务层处理
 * 
 * @author crf
 * @date 2019-09-16
 */
@Service
public class ExamListServiceImpl implements IExamListService {
	@Autowired
	private ExamListMapper examListMapper;

	/**
	 * 查询题库
	 * 
	 * @param examId
	 *            题库ID
	 * @return 题库
	 */
	@Override
	public ExamList selectExamListById(Long examId) {
		return examListMapper.selectExamListById(examId);
	}

	/**
	 * 查询题库列表
	 * 
	 * @param examList
	 *            题库
	 * @return 题库
	 */
	@Override
	public List<ExamList> selectExamListList(ExamList examList) {
		return examListMapper.selectExamListList(examList);
	}

	/**
	 * 新增题库
	 * 
	 * @param examList
	 *            题库
	 * @return 结果
	 */
	@Override
	public int insertExamList(ExamList examList) {
		examList.setCreateTime(DateUtils.getNowDate());
		return examListMapper.insertExamList(examList);
	}

	/**
	 * 修改题库
	 * 
	 * @param examList
	 *            题库
	 * @return 结果
	 */
	@Override
	public int updateExamList(ExamList examList) {
		examList.setUpdateTime(DateUtils.getNowDate());
		return examListMapper.updateExamList(examList);
	}
	
	@Override
	public int updateFiles(ExamFileList files) {
		files.setCreateTime(DateUtils.getNowDate());
		return examListMapper.updateFiles(files);
	}

	@Override
	public List<ExamFileList> selectExamFileById(Long examId) {
		return examListMapper.selectExamFileById(examId);
	}
	
	@Override
	public List<ExamFileList> selectExamFileByIds(String[] examId){
		return examListMapper.selectExamFileByIds(examId);
	}
	
	/**
	 * 删除题库对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteExamListByIds(String ids) {
		return examListMapper.deleteExamListByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除题库信息
	 * 
	 * @param examId
	 *            题库ID
	 * @return 结果
	 */
	public int deleteExamListById(Long examId) {
		return examListMapper.deleteExamListById(examId);
	}

	/**
	 * 导入数据
	 */
	@Override
	public String importExamlist(List<ExamList> examList,
			Boolean isUpdateSupport) {

		if (StringUtils.isNull(examList) || examList.size() == 0) {
			throw new BusinessException("导入数据不能为空！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		String operName = ShiroUtils.getLoginName();
		for (ExamList exam : examList) {
			try {
				// 验证是否存在这个题目
//				ExamList u = examListMapper.selectExamByName(exam.getTitle());
				if (!StringUtils.isNull(exam.getTitle())) {
					exam.setCreateBy(operName);
					exam.setStatus("0");// 题目状态
					// 增加一次校验，如果文件路径中未找到相应的文件，则不插入，并记录日志
					String answer = exam.getAnswer();
					answer = answer.replace("/profile", "C:\\ruoyi\\uploadPath");
					File file = new File(answer);
					if(file.exists()) {
						this.insertExamList(exam);
					} else {
						failureNum++;
						String msg = "<br/>" + failureNum + "、题目 " + exam.getCode() + exam.getTitle()
								+ " 导入失败：" + exam.getAnswer();
						failureMsg.append(msg);
					}
					successNum++;
					successMsg.append("<br/>" + successNum + "、账号 "
							+ exam.getTitle() + " 导入成功");
				}
			} catch (Exception e) {
				failureNum++;
				String msg = "<br/>" + failureNum + "、题目 " + exam.getTitle()
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

	/**
	 * 修改题目状态
	 */
	@Override
	public int changeStatus(ExamList exam) {
		return examListMapper.updateExamList(exam);
	}
	
	@Override
	public void createExam(ExamWrapper wrap) {
		// 获取到关于这个人的 出题相关记录，然后随机组题
		
		
		
	}
	
    public List<ExamList> selectExamListByIds(String[] examId) {
		return examListMapper.selectExamListByIds(examId);
    }

}
