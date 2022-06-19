package com.ttipl.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.pojo.Options;
import com.ttipl.repository.AnswerOptionRepo;

@Service
public class AnswerOptionService {

	@Autowired
	private AnswerOptionRepo answerOptionRepo;

	public void importCodexlsx(MultipartFile filePart) throws IOException {

		InputStream inputStream = filePart.getInputStream();

		if (inputStream != null) {
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);

			int noOfCols = 0;
			Cell cell = null;
			int questionId = 0;
			int answer = 0;
			int rowCount = 0;
			for (Row row : sheet) {

				if (rowCount >= 1) {
					questionId = 0;
					answer = 0;

					noOfCols = row.getPhysicalNumberOfCells();

					for (int i = 0; i < noOfCols; i++) {

						cell = row.getCell(i);

						if (i == 0) {
							if (cell.getCellType() == CellType.NUMERIC) {
								questionId = (int) cell.getNumericCellValue();

							}

						}
						if (i == 1) {
							if (cell.getCellType() == CellType.NUMERIC) {
								answer = (int) cell.getNumericCellValue();

							}

						}

					}
					if (rowCount >= 1) {

						List<Options> li = answerOptionRepo.findRow(questionId);

						for (Options an : li) {
							System.out.println(answer);

							if (an.getOptionOrder() == answer) {
								an.setAnswer(true);
								answerOptionRepo.save(an);
							}
						}
					}

				}
				rowCount++;

			}
		}
	}
}
