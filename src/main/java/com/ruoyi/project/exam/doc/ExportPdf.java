package com.ruoyi.project.exam.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.exam.examlist.domain.ExamList;

public class ExportPdf {

	public String getAbsoluteFile(String filename) {
		String downloadPath = RuoYiConfig.getDownloadPath() + filename;
		File desc = new File(downloadPath);
		if (!desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		return downloadPath;
	}

	public void test1() {

		try {
			// 新建文件
			Document document = new Document();
			PdfWriter.getInstance(document,
					new FileOutputStream("C://TEST.pdf"));
			document.open();

			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 中文字体
			// Font fontChineseBold = new Font(bfChinese, 14,
			// Font.BOLD);//内容字体特殊加粗
			Font titleChinese = new Font(bfChinese, 18, Font.BOLD);// 标题字体
			// Font noteChinese = new Font(bfChinese, 12, Font.BOLD);//设置内容加粗的区
			Font contenttitleChinese = new Font(bfChinese, 12, Font.BOLD);// 内容小标题字体

			ExamList expTranscriptVO = new ExamList();
			expTranscriptVO.setAnswer("dlkajflsjl");
			expTranscriptVO.setTitle("ajsldfjad");
			expTranscriptVO.setUpdateTime(new Date());
			expTranscriptVO.setCreateTime(new Date());
			// 设置名字
			Paragraph par = new Paragraph("考试评分表", titleChinese);
			par.setAlignment(Element.ALIGN_CENTER);
			document.add(par);
			// 每行加空白
			par = new Paragraph(" ", titleChinese);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			PdfPTable table3 = new PdfPTable(2);
			int width3[] = { 40, 60 };
			table3.setWidths(width3);
			PdfPCell cell31 = new PdfPCell(new Paragraph("考场名称："
					+ expTranscriptVO.getTitle(), contenttitleChinese));
			PdfPCell cell32 = new PdfPCell(new Paragraph("监考人员："
					+ expTranscriptVO.getAnswer(), contenttitleChinese));
			cell31.setBorder(0);
			cell32.setBorder(0);

			table3.addCell(cell31);
			table3.addCell(cell32);

			table3.setHorizontalAlignment(Element.ALIGN_LEFT);

			document.add(table3);
			// 加入空行

			PdfPTable table4 = new PdfPTable(2);
			int width4[] = { 40, 60 };
			table4.setWidths(width4);
			PdfPCell cell41 = new PdfPCell(new Paragraph("考试名称："
					+ expTranscriptVO.getTitle(), contenttitleChinese));
			PdfPCell cell42 = new PdfPCell(new Paragraph("考试场次："
					+ expTranscriptVO.getAnswer(), contenttitleChinese));
			cell41.setBorder(0);
			cell42.setBorder(0);
			table4.addCell(cell41);
			table4.addCell(cell42);
			table4.setHorizontalAlignment(Element.ALIGN_LEFT);
			document.add(table4);

			SimpleDateFormat start = new SimpleDateFormat("HH:mm");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
			// String fileName = System.getProperty("user.dir") + File.separator
			// + "南方电网公司交接班记录" + sdf.format(new Date()) + ".pdf";

			PdfPTable table5 = new PdfPTable(2);
			int width5[] = { 80, 20 };
			table5.setWidths(width5);
			PdfPCell cell51 = new PdfPCell(new Paragraph("考试时间:"
					+ sdf.format(expTranscriptVO.getCreateTime()) + "-"
					+ sdf.format(expTranscriptVO.getUpdateTime()),
					contenttitleChinese));
			PdfPCell cell52 = new PdfPCell(new Paragraph("",
					contenttitleChinese));
			cell51.setBorder(0);
			cell52.setBorder(0);
			table5.addCell(cell51);
			table5.addCell(cell52);
			table5.setHorizontalAlignment(Element.ALIGN_LEFT);
			document.add(table5);
			// 加入空行
			Paragraph blankRow51 = new Paragraph(18f, " ", contenttitleChinese);
			document.add(blankRow51);

			int col = 5;
			// PdfPCell cell = new PdfPCell();
			PdfPTable table = new PdfPTable(col);

			BaseColor bc = new BaseColor(102, 204, 255);

			// 设置表格占PDF文档100%宽度
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 20, 20, 20, 20, 20 });

			BaseColor lightGrey01 = new BaseColor(0xCC, 0xCC, 0xCC);

			PdfPCell cell0 = toPdfPCell("序号", Element.ALIGN_CENTER);
			cell0.setBackgroundColor(lightGrey01);
			table.addCell(cell0);
			PdfPCell cell1 = toPdfPCell("学生考号", Element.ALIGN_CENTER);
			cell1.setBackgroundColor(lightGrey01);
			table.addCell(cell1);

			PdfPCell cell2 = toPdfPCell("学生名称", Element.ALIGN_CENTER);
			cell2.setBackgroundColor(lightGrey01);
			table.addCell(cell2);

			PdfPCell cell3 = toPdfPCell("成绩", Element.ALIGN_CENTER);
			cell3.setBackgroundColor(lightGrey01);
			table.addCell(cell3);

			PdfPCell cell4 = toPdfPCell("学生签名", Element.ALIGN_CENTER);
			cell4.setBackgroundColor(lightGrey01);
			table.addCell(cell4);

			java.util.List<ExamList> transcriptUserVOS = new ArrayList<ExamList>();
			ExamList l0 = new ExamList();
			l0.setAnswer("239423");
			l0.setCode("dakjklfjsl");
			l0.setRemark("sdafdsf");
			transcriptUserVOS.add(l0);
			for (int i = 0; i < transcriptUserVOS.size(); i++) {
				// Map<String, Object> map = (Map<String, Object>) data.get(i);
				ExamList transcriptUserVO = transcriptUserVOS.get(i);
				// table.addCell(toPdfPCell((String.valueOf(i+1)),Element.ALIGN_CENTER));
				table.addCell(toPdfPCell(transcriptUserVO.getCode().toString(),
						Element.ALIGN_CENTER));
				table.addCell(toPdfPCell(transcriptUserVO.getTitle(),
						Element.ALIGN_CENTER));
				table.addCell(toPdfPCell(transcriptUserVO.getRemark(),
						Element.ALIGN_CENTER));
				table.addCell(toPdfPCell(null, Element.ALIGN_CENTER));
				table.addCell(toPdfPCell(null, Element.ALIGN_CENTER));

			}

			document.add(table);

			// (xscj(courseId));

			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PdfPCell toPdfPCell(String name, int align)
			throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 中文字体
		Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 内容字体
		PdfPCell cell = new PdfPCell(new Paragraph(name, fontChinese));
		cell.setHorizontalAlignment(align);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		return cell;
	}

	public void testpdf2(HttpServletRequest request,
			HttpServletResponse response) {
		try

		{

			Document document = new Document();

			PdfWriter.getInstance(document, new FileOutputStream(
					"C://test1.pdf"));
			// 设置字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

			// BaseFont bfChinese =
			// BaseFont.createFont("C:/WINDOWS/Fonts/simhei.TTF",
			// BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

			com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(
					bfChinese, 24, com.itextpdf.text.Font.BOLD);

			com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(
					bfChinese, 18, com.itextpdf.text.Font.BOLD);

			com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(
					bfChinese, 16, com.itextpdf.text.Font.BOLD);

			com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(
					bfChinese, 12, com.itextpdf.text.Font.NORMAL);

			com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(
					bfChinese, 11, com.itextpdf.text.Font.BOLD);

			com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(
					bfChinese, 11, com.itextpdf.text.Font.ITALIC);

			com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(
					bfChinese, 11, com.itextpdf.text.Font.NORMAL);

			document.open();

			// test

			Paragraph pg_bt = new Paragraph("深圳市第二中学第七次物理模拟考试", FontChinese18);

			pg_bt.setAlignment(Element.ALIGN_CENTER);

			document.add(pg_bt);

			// document.add(new Paragraph("报告使用说明：",FontChinese18));

			document.add(new Chunk("考场名称：", FontChinese11Normal));

			document.add(Chunk.NEWLINE);

			document.add(new Chunk("考试时间：", FontChinese11Normal));

			document.add(Chunk.NEWLINE);

			// table7

			PdfPTable pt01 = new PdfPTable(11);

			BaseColor lightGrey01 = new BaseColor(0xCC, 0xCC, 0xCC);

			int widthpt01[] = { 20, 20, 20, 20, 20, 20, 15, 20, 20, 15, 15 };

			pt01.setWidths(widthpt01);

			PdfPCell cell01 = new PdfPCell(new Paragraph("序号",
					FontChinese11Bold));

			BaseColor bc = new BaseColor(102, 204, 255);

			cell01.setBackgroundColor(bc);

			PdfPCell cell02 = new PdfPCell(new Paragraph("学生考号",
					FontChinese11Bold));

			cell02.setBackgroundColor(bc);

			PdfPCell cell03 = new PdfPCell(new Paragraph("学生名称",
					FontChinese11Bold));

			cell03.setBackgroundColor(bc);

			PdfPCell cell04 = new PdfPCell(new Paragraph("成绩",
					FontChinese11Bold));

			cell04.setBackgroundColor(bc);

			cell04.setNoWrap(false);

			PdfPCell cell05 = new PdfPCell(new Paragraph("学生签名",
					FontChinese11Bold));

			cell05.setBackgroundColor(bc);

			PdfPCell cell06 = new PdfPCell(new Paragraph("学生签名",
					FontChinese11Bold));

			cell06.setBackgroundColor(bc);

			PdfPCell cell07 = new PdfPCell(new Paragraph("面积",
					FontChinese11Bold));

			cell07.setBackgroundColor(bc);

			PdfPCell cell08 = new PdfPCell(new Paragraph("起时间",
					FontChinese11Bold));

			cell08.setBackgroundColor(bc);

			PdfPCell cell09 = new PdfPCell(new Paragraph("止时间",
					FontChinese11Bold));

			cell09.setBackgroundColor(bc);

			PdfPCell cell10 = new PdfPCell(new Paragraph("年租金",
					FontChinese11Bold));

			cell10.setBackgroundColor(bc);

			PdfPCell cell_11 = new PdfPCell(new Paragraph("承租人/出租人",
					FontChinese11Bold));

			cell_11.setBackgroundColor(bc);

			// 表格高度

			// cell01.setFixedHeight(25);

			// 水平居中

			cell01.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell02.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell03.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell04.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell05.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell06.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell07.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell08.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell09.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell10.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell_11.setHorizontalAlignment(Element.ALIGN_CENTER);

			// 垂直居中

			cell01.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell02.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell03.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell04.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell05.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell06.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell07.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			//
			cell08.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell09.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell10.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell_11.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			// 边框颜色

			cell01.setBorderColor(lightGrey01);

			cell02.setBorderColor(lightGrey01);

			cell03.setBorderColor(lightGrey01);

			cell04.setBorderColor(lightGrey01);

			cell05.setBorderColor(lightGrey01);

			cell06.setBorderColor(lightGrey01);

			cell07.setBorderColor(lightGrey01);

			cell08.setBorderColor(lightGrey01);

			cell09.setBorderColor(lightGrey01);

			cell10.setBorderColor(lightGrey01);

			cell_11.setBorderColor(lightGrey01);

			// 去掉左右边框

			/**
			 * cell71.disableBorderSide(8);
			 **/

			pt01.addCell(cell01);

			pt01.addCell(cell02);

			pt01.addCell(cell03);

			pt01.addCell(cell04);

			pt01.addCell(cell05);

			pt01.addCell(cell06);

			pt01.addCell(cell07);

			pt01.addCell(cell08);

			pt01.addCell(cell09);

			pt01.addCell(cell10);

			pt01.addCell(cell_11);

			document.add(pt01);

			PdfPTable pt02 = new PdfPTable(11);

			BaseColor lightGrey02 = new BaseColor(0xCC, 0xCC, 0xCC);

			int widthpt02[] = { 20, 20, 20, 20, 20, 20, 15, 20, 20, 15, 15 };

			pt02.setWidths(widthpt02);

			PdfPCell cell001 = new PdfPCell(new Paragraph("自用房屋登记",
					FontChinese11Bold));

			PdfPCell cell002 = new PdfPCell(new Paragraph("无产权证",
					FontChinese11Bold));

			PdfPCell cell003 = new PdfPCell(new Paragraph("秦淮路19号",
					FontChinese11Bold));

			PdfPCell cell004 = new PdfPCell(new Paragraph("7464583.31",
					FontChinese11Bold));

			PdfPCell cell005 = new PdfPCell(new Paragraph("7464583.31",
					FontChinese11Bold));

			PdfPCell cell006 = new PdfPCell(new Paragraph("62702.5",
					FontChinese11Bold));

			PdfPCell cell007 = new PdfPCell(new Paragraph("1000",
					FontChinese11Bold));

			PdfPCell cell008 = new PdfPCell(new Paragraph("2005-01-01",
					FontChinese11Bold));

			PdfPCell cell009 = new PdfPCell(
					new Paragraph("", FontChinese11Bold));

			PdfPCell cell100 = new PdfPCell(
					new Paragraph("", FontChinese11Bold));

			PdfPCell cell011 = new PdfPCell(
					new Paragraph("", FontChinese11Bold));

			// 表格高度

			cell001.setFixedHeight(25);

			cell002.setFixedHeight(25);

			cell003.setFixedHeight(25);

			cell004.setFixedHeight(25);

			cell005.setFixedHeight(25);

			cell006.setFixedHeight(25);

			cell007.setFixedHeight(25);

			cell008.setFixedHeight(25);

			cell009.setFixedHeight(25);

			cell100.setFixedHeight(25);

			cell011.setFixedHeight(25);

			// 水平居中

			cell001.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell002.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell003.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell004.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell005.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell006.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell007.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell008.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell009.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell100.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell011.setHorizontalAlignment(Element.ALIGN_CENTER);

			// 垂直居中

			cell001.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell002.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell003.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell004.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell005.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell006.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell007.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell008.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell009.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell100.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			cell011.setHorizontalAlignment(Element.ALIGN_MIDDLE);

			// 边框颜色

			cell001.setBorderColor(lightGrey02);

			cell002.setBorderColor(lightGrey02);

			cell003.setBorderColor(lightGrey02);

			cell004.setBorderColor(lightGrey02);

			cell005.setBorderColor(lightGrey02);

			cell006.setBorderColor(lightGrey02);

			cell007.setBorderColor(lightGrey02);

			cell008.setBorderColor(lightGrey02);

			cell009.setBorderColor(lightGrey02);

			cell100.setBorderColor(lightGrey02);

			cell011.setBorderColor(lightGrey02);

			pt02.addCell(cell001);

			pt02.addCell(cell002);

			pt02.addCell(cell003);

			pt02.addCell(cell004);

			pt02.addCell(cell005);

			pt02.addCell(cell006);

			pt02.addCell(cell007);

			pt02.addCell(cell008);

			pt02.addCell(cell009);

			pt02.addCell(cell100);

			pt02.addCell(cell011);

			document.add(pt02);

			document.close();

			// os.flush();

			// os.close();

		} catch (Exception ex)

		{

			ex.printStackTrace();

		}

		// return null;

	}
}
