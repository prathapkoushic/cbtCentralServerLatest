
package com.ttipl.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.Options;
import com.ttipl.pojo.Post;
import com.ttipl.pojo.Question;
import com.ttipl.pojo.ReportBean;

@Component
public class ExcelService 
{

	public byte[] doExcelFileReport(List<ReportBean> list, Post post) throws IOException {
		int rowIndex = 1;
		int colIndex = 7;
		int switchEle = 0;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = workbook.createSheet("Data");

		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mmm-dd"));
		// cellStyle.setWrapText(true);

		xssfSheet.setColumnWidth(0, 1200);
		xssfSheet.setColumnWidth(1, 2000);
		xssfSheet.setColumnWidth(2, 4500);
		xssfSheet.setColumnWidth(3, 3500);
		xssfSheet.setColumnWidth(4, 3000);
		xssfSheet.setColumnWidth(5, 3500);
		xssfSheet.setColumnWidth(6, 2500);
		xssfSheet.setColumnWidth(7, 5500);

		Row row = xssfSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);

		row.createCell(0).setCellValue("S.No");
		row.createCell(1).setCellValue("Roll NO");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("DOB");
		row.createCell(4).setCellValue("Total Marks");
		row.createCell(5).setCellValue("Community");
		row.createCell(6).setCellValue("Trade");
		row.createCell(7).setCellValue("Session");

		for (ReportBean entity : list) {
			row = xssfSheet.createRow(rowIndex);
			row.setRowStyle(cellStyle);
			// row.setHeightInPoints((2 ,xssfSheet.getDefaultRowHeightInPoints()));
			for (int cellIndex = 0; cellIndex <= colIndex; cellIndex++) {
				switchEle = cellIndex;
				cell = row.createCell(cellIndex);

				switch (switchEle) {
				case 0:
					cell.setCellType(CellType.NUMERIC);
					// cell.setCellStyle(cellStyle);
					cell.setCellValue(rowIndex);
					break;

				case 1:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getHTnumber());
					break;

				case 2:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCandidateName());
					break;

				case 3:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getDob());
					break;
				case 4:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalObtained());
					break;
				case 5:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCommunity());
					break;
				case 6:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(post.getPost_name());
					break;
				case 7:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getLocationName() + "(" + entity.getSession() + ")");
					break;

				}

			}
			rowIndex++;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
		} catch (Exception e) {
		} finally {
			bos.close();
			workbook.close();
		}

		return bos.toByteArray();
	}

	public byte[] doExcelFileDetailedReport(List<ReportBean> list, ExamBean exam) throws IOException {
		int rowIndex = 1;
		int colIndex = 12;
		int switchEle = 0;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = workbook.createSheet("Sheet 0");

		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mmm-dd"));
		// cellStyle.setWrapText(true);

		xssfSheet.setColumnWidth(0, 1200);
		xssfSheet.setColumnWidth(1, 2000);
		xssfSheet.setColumnWidth(2, 4500);
		xssfSheet.setColumnWidth(3, 3500);
		xssfSheet.setColumnWidth(4, 6000);
		xssfSheet.setColumnWidth(5, 3500);
		xssfSheet.setColumnWidth(6, 3500);
		xssfSheet.setColumnWidth(7, 5500);
		xssfSheet.setColumnWidth(8, 3500);
		xssfSheet.setColumnWidth(9, 3500);
		xssfSheet.setColumnWidth(10, 5500);
		xssfSheet.setColumnWidth(11, 3500);

		Row row = xssfSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);

		row.createCell(0).setCellValue("S.No");
		row.createCell(1).setCellValue("Roll NO");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("DOB");
		row.createCell(4).setCellValue("Responses");
		row.createCell(5).setCellValue("Attempted");
		row.createCell(6).setCellValue("Total Correct");
		row.createCell(7).setCellValue("Total Negetive Marks");
		row.createCell(8).setCellValue("Total Marks");
		row.createCell(9).setCellValue("Community");
		row.createCell(10).setCellValue("Exam Code");
		row.createCell(11).setCellValue("Session");

		for (ReportBean entity : list) {
			row = xssfSheet.createRow(rowIndex);
			row.setRowStyle(cellStyle);
			// row.setHeightInPoints((2 ,xssfSheet.getDefaultRowHeightInPoints()));
			for (int cellIndex = 0; cellIndex <= colIndex; cellIndex++) {
				switchEle = cellIndex;
				cell = row.createCell(cellIndex);

				switch (switchEle) {
				case 0:
					cell.setCellType(CellType.NUMERIC);
					// cell.setCellStyle(cellStyle);
					cell.setCellValue(rowIndex);
					break;

				case 1:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getHTnumber());
					break;

				case 2:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCandidateName());
					break;

				case 3:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getDob());
					break;
				case 4:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getResponses());
					break;
				case 5:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalAnswered());
					break;
				case 6:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalCorrect());
					break;
				case 7:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getNegativeMarks());
					break;
				case 8:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalObtained());
					break;
				case 9:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCommunity());
					break;
				case 10:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(exam.getExam_code());
					break;
				case 11:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getLocationName() + "(" + entity.getSession() + ")");
					break;

				}

			}
			rowIndex++;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
		} catch (Exception e) {
		} finally {
			bos.close();
			workbook.close();
		}

		return bos.toByteArray();
	}

	public List<Question> ExtractQuestionsFromExcel(MultipartFile file, int examId, String setNo) throws IOException {
		InputStream inputStream = file.getInputStream();
		List<Question> questions = new ArrayList<Question>();
		if (inputStream != null) {
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);

			int noOfCols = 0;

			Cell cell = null;
			String question_eng = "", option_eng_1 = "", option_eng_2 = "", option_eng_3 = "", option_eng_4 = "",
					question_hindi = "", option_hindi_1 = "", option_hindi_2 = "", option_hindi_3 = "",
					option_hindi_4 = "";
			int rowCount = 0;
			for (Row row : sheet) {
				if (rowCount >= 1) {
					question_eng = "";
					option_eng_1 = "";
					option_eng_2 = "";
					option_eng_3 = "";
					option_eng_4 = "";
					question_hindi = "";
					option_hindi_1 = "";
					option_hindi_2 = "";
					option_hindi_3 = "";
					option_hindi_4 = "";
					noOfCols = row.getPhysicalNumberOfCells();
					for (int i = 0; i < noOfCols; i++) {
						cell = row.getCell(i);
						if (i == 0) {
							if (cell.getCellType() == CellType.STRING) {
								question_eng = cell.getStringCellValue();
								question_eng = question_eng.replaceAll("'", "");
								question_eng = question_eng.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								question_eng =(int) cell.getNumericCellValue() + "";

							}

						}

						if (i == 1) {
							if (cell.getCellType() == CellType.STRING) {
								option_eng_1 = cell.getStringCellValue();
								option_eng_1 = option_eng_1.replaceAll("'", "");
								option_eng_1 = option_eng_1.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_eng_1 = (int)cell.getNumericCellValue() + "";

							}

						}
						if (i == 2) {
							if (cell.getCellType() == CellType.STRING) {
								option_eng_2 = cell.getStringCellValue();
								option_eng_2 = option_eng_2.replaceAll("'", "");
								option_eng_2 = option_eng_2.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_eng_2 =(int) cell.getNumericCellValue() + "";

							}

						}
						if (i == 3) {
							if (cell.getCellType() == CellType.STRING) {
								option_eng_3 = cell.getStringCellValue();
								option_eng_3 = option_eng_3.replaceAll("'", "");
								option_eng_3 = option_eng_3.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_eng_3 = (int)cell.getNumericCellValue() + "";

							}
						}
						if (i == 4) {
							if (cell.getCellType() == CellType.STRING) {
								option_eng_4 = cell.getStringCellValue();
								option_eng_4 = option_eng_4.replaceAll("'", "");
								option_eng_4 = option_eng_4.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_eng_4 = (int)cell.getNumericCellValue() + "";

							}
						}
						if (i == 5) {
							if (cell.getCellType() == CellType.STRING) {
								question_hindi = cell.getStringCellValue();
								question_hindi = question_hindi.replaceAll("'", "");
								question_hindi = question_hindi.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								question_hindi =(int) cell.getNumericCellValue() + "";

							}
						}
						if (i == 6) {
							if (cell.getCellType() == CellType.STRING) {
								option_hindi_1 = cell.getStringCellValue();
								option_hindi_1 = option_hindi_1.replaceAll("'", "");
								option_hindi_1 = option_hindi_1.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_hindi_1 =(int) cell.getNumericCellValue() + "";

							}
						}
						if (i == 7) {
							if (cell.getCellType() == CellType.STRING) {
								option_hindi_2 = cell.getStringCellValue();
								option_hindi_2 = option_hindi_2.replaceAll("'", "");
								option_hindi_2 = option_hindi_2.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_hindi_2 = (int)cell.getNumericCellValue() + "";

							}
						}
						if (i == 8) {
							if (cell.getCellType() == CellType.STRING) {
								option_hindi_3 = cell.getStringCellValue();
								option_hindi_3 = option_hindi_3.replaceAll("'", "");
								option_hindi_3 = option_hindi_3.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_hindi_3 = (int)cell.getNumericCellValue() + "";

							}
						}
						if (i == 9) {
							if (cell.getCellType() == CellType.STRING) {
								option_hindi_4 = cell.getStringCellValue();
								option_hindi_4 = option_hindi_4.replaceAll("'", "");
								option_hindi_4 = option_hindi_4.trim();
							} else if (cell.getCellType() == CellType.NUMERIC) {
								option_hindi_4 =(int) cell.getNumericCellValue() + "";

							}
						}
					}
					if (rowCount >= 1) {
						Question question = new Question();
						question.setQuestionName(question_eng);
						question.setQuestionHindi(question_hindi);
						question.setQuestionType("Multiple Choice");
						question.setBankQuestionId(18);
						question.setExamLocSessionId(1);
						List<Options> options = new ArrayList<Options>();
						for (int i = 0; i <= 3; i++) {
							Options option = new Options();
							options.add(option);
						}
						options.get(0).setOption(option_eng_1);
						options.get(0).setOptionHindi(option_hindi_1);
						options.get(0).setOptionOrder(1);
						options.get(0).setQuestion(question);

						options.get(1).setOption(option_eng_2);
						options.get(1).setOptionHindi(option_hindi_2);
						options.get(1).setOptionOrder(2);
						options.get(1).setQuestion(question);

						options.get(2).setOption(option_eng_3);
						options.get(2).setOptionHindi(option_hindi_3);
						options.get(2).setOptionOrder(3);
						options.get(2).setQuestion(question);

						options.get(3).setOption(option_eng_4);
						options.get(3).setOptionHindi(option_hindi_4);
						options.get(3).setOptionOrder(4);
						options.get(3).setQuestion(question);

						question.setExamId(examId);
						question.setSetNo(setNo);
						System.out.println(question);
						question.setOptions(options);
						questions.add(question);
					}
				}
				rowCount++;

			}
		}
		return questions;
	}

}
