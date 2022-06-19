package com.ttipl.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.pojo.Candidate;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.pojo.OtpVerification;
import com.ttipl.pojo.Pull_Otp_verification;
import com.ttipl.repository.CandidateRepository;
import com.ttipl.repository.ExamLocaRepository;
import com.ttipl.repository.ExamRepository;
import com.ttipl.repository.OtpVerificationRepo;
import com.ttipl.repository.Pull_Otp_verificationRepo;
import com.ttipl.repository.QuestionRepo;


@Service

public class ExamService {
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	ExamLocaRepository examLocaRepository;

	@Autowired
	private QuestionRepo questionRepo;
	@Autowired
	OtpVerificationRepo otpVerificationRepo;
	@Autowired
	Pull_Otp_verificationRepo pull_Otp_verificationRepo;
	@Autowired
	CandidateRepository candidateRepository;

	public String getData(ExamBean examBean) {
		try {
			return "Saved \" " + examRepository.save(examBean).getExam_name() + "\"successfully";
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataIntegrityViolationException) {
				return "Exam Name already Exits";
			} else {
				return "-1";
			}
		}
	}

	public ExamLocationSess save(ExamLocationSess examLocationSess) {
		return examLocaRepository.save(examLocationSess);
	}

	public List<ExamBean> findAllExams() {
		return examRepository.findExamNames();
	}

	public List<ExamBean> getResult() {
		return (List<ExamBean>) examRepository.findAll();
	}

	public ExamBean getDataForUpdate(int id) {

		return examRepository.findById(id);
	}

	public void deleteByExamid(int id) {

		examRepository.deleteById(id);
	}

	public ExamBean getList(String exName) {
		return examRepository.gettingSand(exName);

	}

	public int getExamId(String exam_name) {
		return examRepository.findExamId(exam_name);
	}

	public ExamBean findById(int id) {
		return examRepository.findById(id);
	}

	public List<ExamBean> getDataBasedOnPostId(int postId) {

		return (List<ExamBean>) examRepository.findByPostId(postId);
	}

	public List<ExamLocationSess> findSessions() {
		return (List<ExamLocationSess>) examLocaRepository.findAll();
	}

	public int getPostIdBasedOnExamId(int examId) {
		return examRepository.getPostIdBasedOnExamId(examId);
	}

	public String getIdAddressBasedOnLocationSessionId(int locationSessionId) {
		return examLocaRepository.getIdAddressBasedOnLocationSessionId(locationSessionId);
	}

	public int[] findQuestionIdBasedOnExamIdAndLocationSessionId(int examId) {

		return questionRepo.findQuestionIdBasedOnExamIdAndLocationSessionId(examId);
	}

	public int updateExamLocationSession(int locationSessionId, int examId, String setNo) {
		return questionRepo.updateExamLocationSession(locationSessionId, examId, setNo);
	}

	public int deleteByPostId(int id) {
		return examRepository.deleteByPostId(id);
	}

	public List<Object[]> findAllExamBeans() {
		return examRepository.findAllExams();
	}

	public List<Object[]> findAllExamBeansByPostId(int id) {
		return examRepository.findAllExamsByPostId(id);
	}

	public List<ExamLocationSess> getDataBasedOnExamIdAndTimings(int examId, String startTime, String endTime) {
		return examLocaRepository.getDataBasedOnExamIdAndTimings(examId, startTime, endTime);
	}

	public void save(OtpVerification otpVerification) {
		otpVerificationRepo.save(otpVerification);
	}

	public void importCodexlsx(MultipartFile file, int exam_id) throws IOException {
		InputStream inputStream = file.getInputStream();

		if (inputStream != null) {
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);

			int noOfCols = 0;
			Cell cell = null;
			String location_name = "";
			String ipaddress = "";
			String moblie_number = "";
			String contact_name = "";
			String address = "";
			String start_time = "";
			String end_time = "";
			// String format = wb.getCellStyleAt(2).getDataFormatString();
			int rowCount = 0;
			for (Row row : sheet) {

				if (rowCount >= 1) {
					location_name = "";
					ipaddress = "";
					moblie_number = "";
					contact_name = "";
					address = "";
					start_time = "";
					end_time = "";

					noOfCols = row.getPhysicalNumberOfCells();
					// System.out.println("no of cellls" + noOfCols + " " + format);
					if (noOfCols == 7) {
						for (int i = 0; i < noOfCols; i++) {
							cell = row.getCell(i);
							if (i == 0) {
								if (cell.getCellType() == CellType.STRING) {
									location_name = cell.getStringCellValue();
									location_name = location_name.replaceAll("'", "");
									location_name = location_name.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									location_name = cell.getNumericCellValue() + "";

									if (location_name.contains(".")) {
										location_name = location_name.substring(0, location_name.indexOf("."));
									}

								}
							}
							if (i == 1) {
								if (cell.getCellType() == CellType.STRING) {
									ipaddress = cell.getStringCellValue();
									ipaddress = ipaddress.replaceAll("'", "");
									ipaddress = ipaddress.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									ipaddress = cell.getNumericCellValue() + "";

								}
							}

							if (i == 2) {
								if (cell.getCellType() == CellType.STRING) {
									contact_name = cell.getStringCellValue();
									contact_name = contact_name.replaceAll("'", "");
									contact_name = contact_name.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									contact_name = cell.getNumericCellValue() + "";

								}
							}
							if (i == 3) {
								if (cell.getCellType() == CellType.STRING) {
									moblie_number = new DataFormatter().formatCellValue(cell);

									moblie_number = moblie_number.replaceAll("'", "");
									moblie_number = moblie_number.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									moblie_number = new DataFormatter().formatCellValue(cell);

								}
							}

							if (i == 4) {
								if (cell.getCellType() == CellType.STRING) {
									address = cell.getStringCellValue();
									address = address.replaceAll("'", "");
									address = address.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									address = cell.getNumericCellValue() + "";

								}
							}

							if (i == 5) {
								if (cell.getCellType() == CellType.STRING) {
									start_time = cell.getStringCellValue();
									start_time = start_time.replaceAll("'", "");
									start_time = start_time.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									start_time = new DataFormatter().formatCellValue(cell) + "";

								}
							}
							if (i == 6) {
								if (cell.getCellType() == CellType.STRING) {
									end_time = cell.getStringCellValue();
									end_time = end_time.replaceAll("'", "");
									end_time = end_time.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									end_time = new DataFormatter().formatCellValue(cell) + "";

								}

							}

						}

						if (rowCount >= 1) {
							ExamLocationSess cb = new ExamLocationSess();
							cb.setExamId(exam_id);
							cb.setLocation_name(location_name);
							cb.setContactName(contact_name);
							cb.setAddress(address);
							cb.setMobileNumber(moblie_number);
							cb.setIpaddress(ipaddress);
							Date dt = new Date();
							cb.setDate_created(LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault()));
							DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
							try {
								Date date = dateFormat.parse(start_time);
								Date date2 = dateFormat.parse(end_time);
								Time time = new Time(date.getTime());
								Time time2 = new Time(date2.getTime());
								cb.setStart_time(time);
								cb.setEnd_time(time2);
							} catch (ParseException e) {
								e.printStackTrace();
							}

							save(cb);

						}
					}

				}
				rowCount++;
			}

		}

	}

	public OtpVerification findByExamStartFlag(int examId, int locationSessionId, String otp) {
		return otpVerificationRepo.findByExamStartFlag(examId, locationSessionId, otp);
	}

	public int updateExamStartFlag(int examId, int locationSessionId, boolean exam_start,
			String exam_start_Updated_time) {
		return examLocaRepository.updateExamStartFlag(exam_start, exam_start_Updated_time, examId, locationSessionId);
	}

	public void save_pull_otps(Pull_Otp_verification pull_Otp_verification) {
		pull_Otp_verificationRepo.save(pull_Otp_verification);
	}

	public List<Candidate> findByExamIdandLocationId(int examId, int locationSessionId) {
		return candidateRepository.findByExamIdandLocationId(examId,locationSessionId);
	}

	public ExamBean findExamBean(int examId)
	{
		return examRepository.findById(examId);
	}

	public void save_otps(OtpVerification otpVer)
	{
		otpVerificationRepo.save(otpVer);
	}

}
