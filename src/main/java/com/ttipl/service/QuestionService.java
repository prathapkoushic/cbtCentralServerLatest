package com.ttipl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.pojo.DescriptiveQuestion;
import com.ttipl.pojo.DescriptiveQuestionLanguages;
import com.ttipl.pojo.DescriptiveRandomizeQuestions;
import com.ttipl.pojo.Question;
import com.ttipl.repository.DesImageRepo;
import com.ttipl.repository.DescriptiveQuestionRepo;
import com.ttipl.repository.DescriptiveRandomRepo;
import com.ttipl.repository.QuestionLangRepo;
import com.ttipl.repository.QuestionRepo;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepo repo;
	
	@Autowired
	private DescriptiveQuestionRepo desrepo;
	
	@Autowired
	private DescriptiveRandomRepo descriptiveRandomRepo;
	
	@Autowired
	private DesImageRepo img;
	
	
	
	@Autowired
	private QuestionLangRepo desquestionLangRepository;

	public Question saveQuestion(Question question) {
		return repo.save(question);
	}

	public Question findQuestionById(int id) {
		return repo.findByQuestionId(id);
	}

	public int deleteQuestionByexamLocSessionId(int id) {
		return repo.deleteQuestionByexamLocSessionId(id);
	}

	public List<Question> findAll() {
		return repo.findAll();
	}

	public void doExcelFileReport(List<Question> list) throws IOException {
		int rowIndex = 1;
		int colIndex = 12;
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

		row.createCell(0).setCellValue("Question");
		row.createCell(1).setCellValue("option1");
		row.createCell(2).setCellValue("option2");
		row.createCell(3).setCellValue("option3");
		row.createCell(4).setCellValue("option4");
		row.createCell(5).setCellValue("Question_hindi");
		row.createCell(6).setCellValue("option_hindi_1");
		row.createCell(7).setCellValue("option_hindi_2");
		row.createCell(8).setCellValue("option_hindi_3");
		row.createCell(9).setCellValue("option_hindi_4");
		row.createCell(10).setCellValue("QuestionID");
		row.createCell(11).setCellValue("answer_option");
		for (Question entity : list) {
			row = xssfSheet.createRow(rowIndex);
			row.setRowStyle(cellStyle);
			// row.setHeightInPoints((2 ,xssfSheet.getDefaultRowHeightInPoints()));
			for (int cellIndex = 0; cellIndex <= colIndex; cellIndex++) {
				switchEle = cellIndex;
				cell = row.createCell(cellIndex);

				switch (switchEle) {

				case 0:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getQuestionName());
					break;

				case 1:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(0).getOption());
					break;

				case 2:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(1).getOption());
					break;
				case 3:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(2).getOption());
					break;
				case 4:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(3).getOption());
					break;
				case 5:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getQuestionHindi());
					break;
				case 6:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(0).getOptionHindi());
					break;
				case 7:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(1).getOptionHindi());
					break;
				case 8:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(2).getOptionHindi());
					break;
				case 9:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(3).getOptionHindi());
					break;
				case 10:
					cell.setCellType(CellType.STRING);
					// cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getQuestionId());
					break;
				case 11:
					cell.setCellType(CellType.STRING);
					// cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(3).getOptionOrder());
					break;

				}

			}
			rowIndex++;
		}
		OutputStream bos = new FileOutputStream(new File("C:\\Users\\DELL\\Desktop\\questions.xlsx"));
		try {
			workbook.write(bos);
			;
		} catch (Exception e) {
		} finally {
			bos.close();
			workbook.close();
		}

	}

	public List<Question> getCountQue(int examId, int locationSessionId) {
		return repo.getCounPulledCandidates(examId, locationSessionId);
	}

	public int LocalQuestionCount(int examId, int locationSessionId) {
		return repo.LocalQuestionCount(examId, locationSessionId);
	}
	
	public int LocalDesQuestionCount(int examId, int locationSessionId) {
		return desrepo.LocalQuestionCount(examId, locationSessionId);
	}
	
	public Iterable<DescriptiveQuestion> getDescriptiveQuestions(int examId, int locationSessionId, int easy, int medium, int difficult)  
	{
		List<DescriptiveQuestion> list1 = new ArrayList<DescriptiveQuestion>();
		List<DescriptiveQuestion> list2 = new ArrayList<DescriptiveQuestion>();
		List<DescriptiveQuestion> list3 = new ArrayList<DescriptiveQuestion>();
		Iterable<DescriptiveQuestion> itrQs1 = desrepo.findAllByExamIdAndLocationId1(examId , locationSessionId , easy);
		Iterable<DescriptiveQuestion> itrQs2 = desrepo.findAllByExamIdAndLocationId2(examId , locationSessionId , medium);
		Iterable<DescriptiveQuestion> itrQs3 = desrepo.findAllByExamIdAndLocationId3(examId , locationSessionId , difficult);
		
		Iterator<DescriptiveQuestion> iterator1 = itrQs1.iterator();
		Iterator<DescriptiveQuestion> iterator2 = itrQs2.iterator();
		Iterator<DescriptiveQuestion> iterator3 = itrQs3.iterator();
		iterator1.forEachRemaining(list1::add);
		iterator2.forEachRemaining(list2::add);
		iterator3.forEachRemaining(list3::add);
		list1.addAll(list2);
		list1.addAll(list3);
		return list1;
	}

	public List<DescriptiveQuestionLanguages> getDescriptiveLanguageQuestions(Iterable<DescriptiveQuestion> itrble) 
	{
		List<DescriptiveQuestionLanguages> list = new ArrayList<DescriptiveQuestionLanguages>();
		for(DescriptiveQuestion question : itrble)
		{
			DescriptiveQuestionLanguages language = desquestionLangRepository.findAllByDesquestionId(question.getDesquestion_id());
			list.add(language);
		}
		return list;
	}

	public void saveAllRandomn(List<DescriptiveRandomizeQuestions> listRandomize) 
	{
		descriptiveRandomRepo.saveAll(listRandomize);
	}

	public int findAllRandom(int examId) 
	{
		return descriptiveRandomRepo.findAll(examId);
	}

	public void deleteRandom(int examId) 
	{
		descriptiveRandomRepo.deleteByExam(examId);
	}

	public void deleteAllDes(int examId) 
	{
		List<DescriptiveQuestion> desQuestion = desrepo.findAllByExam(examId);
		if(desQuestion!=null)
		{
		for(DescriptiveQuestion des : desQuestion)
		{
			DescriptiveQuestionLanguages lang = desquestionLangRepository.findByDesId(des.getDesquestion_id());
			if(lang.getQuestion_image_id()!=null)
			img.deleteByImageId(lang.getQuestion_image_id());
			
			if(des.getQuestion_image_id()!=null)
			img.deleteByImageId2(des.getQuestion_image_id());
			desquestionLangRepository.deleteByDesquestionId(des.getDesquestion_id());
		}
		}
		
		desrepo.deleteByExamId(examId);
	}
	 
	
}
