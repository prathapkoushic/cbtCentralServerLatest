package com.ttipl.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.pojo.Candidate;
import com.ttipl.pojo.CandidateRegistrationPhoto;
import com.ttipl.pojo.CandidateSeatDetails;
import com.ttipl.pojo.PullCountOfLocationServer;
import com.ttipl.repository.CandidateQueAnsRepo;
import com.ttipl.repository.CandidateRegistrationPhotoRepo;
import com.ttipl.repository.CandidateRepository;
import com.ttipl.repository.CandidateSeatDetailsRepository;
import com.ttipl.repository.Candidate_loginRepo;
import com.ttipl.repository.PullCountOfLocationServerRepo;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	CandidateQueAnsRepo candidateQueAnsRepo;
	@Autowired
	CandidateSeatDetailsRepository candidateSeatDetailsRepository;
	@Autowired
	private Candidate_loginRepo candidate_loginRepo;
	@Autowired
	PullCountOfLocationServerRepo pullCountOfLocationServerRepo;
	@Autowired
	ApplicationContext context;
	@Autowired
	private CandidateRegistrationPhotoRepo registrationPhotoRepo;

	public Candidate getData(Candidate candidate) {
		return candidateRepository.save(candidate);

	}

	public List<Candidate> findByExamSessionId(int id) {
		return candidateRepository.findBylocationName(id);
	}

	public int deleteByExamSessionId(int id) {
		return candidateRepository.deleteCandRecordsByExamLoc_session(id);
	}

	public Candidate findByCandidateId(String id) {
		return candidateRepository.findBycandidateId(id);
	}

	public void saveCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}

	public List<Object[]> getAll() {
		return candidateRepository.getSomething();
	}

	public Candidate getById(int id) {
		return candidateRepository.findById(id);
	}

	public void deleteByOne(int id) {
		candidateRepository.deleteCandRecord(id);
	}

	public void importCodexlsx(MultipartFile filePart, int exam_id, int location_session_id) throws IOException {

		InputStream inputStream = filePart.getInputStream();

		if (inputStream != null) {
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);

			int noOfCols = 0;
			Cell cell = null;
			String CandidateId = "";
			String dob = "";
			String mobileNO = "";
			String firstName = "";
			String lastName = "";
			String gender = "";
			String fatherName = "";
			String community = "";
			String email_id = "";
			String format = wb.getCellStyleAt(2).getDataFormatString();
			int rowCount = 0;
			for (Row row : sheet) {

				if (rowCount >= 1) {
					CandidateId = "";
					dob = "";
					mobileNO = "";
					firstName = "";
					lastName = "";
					gender = "";
					fatherName = "";
					community = "";
					email_id = "";

					noOfCols = row.getPhysicalNumberOfCells();
					System.out.println("no of  cellls" + noOfCols + " " + format);
					if (noOfCols == 9) {
						for (int i = 0; i < noOfCols; i++) {
							cell = row.getCell(i);
							if (i == 0) {
								if (cell.getCellType() == CellType.STRING) {
									CandidateId = cell.getStringCellValue();
									CandidateId = CandidateId.replaceAll("'", "");
									CandidateId = CandidateId.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									CandidateId = cell.getNumericCellValue() + "";

									if (CandidateId.contains(".")) {
										CandidateId = CandidateId.substring(0, CandidateId.indexOf("."));
									}

								}
							}
							if (i == 1) {
								if (cell.getCellType() == CellType.STRING) {
									dob = cell.getStringCellValue();
									dob = dob.replaceAll("'", "");
									dob = dob.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									dob = cell.getNumericCellValue() + "";
									if (DateUtil.isCellDateFormatted(cell)) {
										SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
										dob = dateFormat.format(cell.getDateCellValue());
									} else {
										System.out.print(cell.getNumericCellValue() + "\t\t");
									}

								}
							}

							if (i == 2) {
								if (cell.getCellType() == CellType.STRING) {
									firstName = cell.getStringCellValue();
									firstName = firstName.replaceAll("'", "");
									firstName = firstName.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									firstName = cell.getNumericCellValue() + "";

								}
							}
							if (i == 3) {
								if (cell.getCellType() == CellType.STRING) {
									lastName = cell.getStringCellValue();
									lastName = lastName.replaceAll("'", "");
									lastName = lastName.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									lastName = cell.getNumericCellValue() + "";

								}
							}

							if (i == 4) {
								if (cell.getCellType() == CellType.STRING) {
									gender = cell.getStringCellValue();
									gender = gender.replaceAll("'", "");
									gender = gender.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									gender = cell.getNumericCellValue() + "";

								}
							}
							if (i == 5) {
								if (cell.getCellType() == CellType.STRING) {
									fatherName = cell.getStringCellValue();
									fatherName = fatherName.replaceAll("'", "");
									fatherName = fatherName.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									fatherName = cell.getNumericCellValue() + "";

								}
							}

							if (i == 6) {
								if (cell.getCellType() == CellType.STRING) {
									community = new DataFormatter().formatCellValue(cell);
									community = community.replaceAll("'", "");
									community = community.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									community = cell.getNumericCellValue() + "";
								}
							}

							if (i == 7) {
								if (cell.getCellType() == CellType.STRING) {
									mobileNO = new DataFormatter().formatCellValue(cell);

									mobileNO = mobileNO.replaceAll("'", "");
									mobileNO = mobileNO.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									mobileNO = new DataFormatter().formatCellValue(cell);
									// System.out.println(mobileNO+ "dddddd");
									// mobileNO = cell.getNumericCellValue() + "";

								}
							}

							if (i == 8) {
								if (cell.getCellType() == CellType.STRING) {
									email_id = cell.getStringCellValue();
									email_id = email_id.replaceAll("'", "");
									email_id = email_id.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									email_id = cell.getNumericCellValue() + "";

								}

							}
						}

						if (rowCount >= 1) {
							Candidate cb = new Candidate();
							cb.setExam_id(exam_id);
							cb.setExam_loc_session_id(location_session_id);
							cb.setCandidate_first_name(firstName);
							cb.setCandidate_last_name(lastName);
							cb.setGender(gender);
							cb.setFather_name(fatherName);
							// cb.setCommunity(community);
							cb.setCaste(community);
							cb.setEmail_id(email_id);
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							try {
								cb.setDob(dateFormat.parse(dob));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cb.setCandidate_id(CandidateId);
							try {
								cb.setContact_no(Long.parseLong(mobileNO));
							} catch (NumberFormatException e) {

							}

							saveCandidate(cb);

						}
					}

				}
				rowCount++;
			}

		}

	}

	public int updateExamIsEnd(boolean b,boolean login, String id) {
		
		return candidateRepository.updateExamIsEnd(b,login, id);
	}

	public List<Candidate> findByExamIdAndLocationSession(int examId, int examLocationSessionId) {
		return candidateRepository.findByExamIdAndLocationSession(examId, examLocationSessionId);
	}

	public Object getCandidateByExam_idAndExamSession_Id(Integer examId, Integer sessionId) {

		return candidateRepository.getCandidateByExam_idAndExamSession_Id(examId, sessionId);
	}

	public List<Candidate> findAll() {
		return StreamSupport.stream(candidateRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public int findAnsweredCount(String candidate_id) {
		return candidateQueAnsRepo.findAnsweredCount(candidate_id);
	}

	@SuppressWarnings("static-access")
	public Time findRemainingTime(String candidate_id) 
	{
		Time time = null;
	  Candidate c = candidateRepository.findIsExamEnd(candidate_id);
	   if(c.isEnd_status()==true)
	   {
		   LocalTime localTime = LocalTime.of(00,00,00);  
		  Time tr = time.valueOf(localTime);
		   return tr;
	   }
		return candidateQueAnsRepo.findRemainingTime(candidate_id);

	}

	public String[] findAssignedIpAddress(String candidate_id) {
		return candidateQueAnsRepo.findAssignedIpAddress(candidate_id);
	}

	public int updateNewIpToCandidate(String newIp, String candidateID) {
		return candidateSeatDetailsRepository.updateNewIpToCandidate(newIp, candidateID);
	}

	public int findByCandidateQA(String candidate_id) {
		return candidateQueAnsRepo.findByCandidateQA(candidate_id);

	}

	public int getCanidateCount(int examId) {
		return candidateQueAnsRepo.getCanidateCount(examId);

	}

	public int candidateLoginCount(int examId, int locationSessionId) {
		return candidate_loginRepo.candidateLoginCount(examId, locationSessionId);
	}

	public void savePullCount(PullCountOfLocationServer pullCountOfLocationServer) {
		pullCountOfLocationServerRepo.save(pullCountOfLocationServer);
	}

	public PullCountOfLocationServer findPullCountBasedOnExamIdAndLocationSessionId(int examId, int locationSessionId) {
		return pullCountOfLocationServerRepo.findPullCountBasedOnExamIdAndLocationSessionId(examId, locationSessionId);
	}

	public void updateCountOfPull(int count, int examId, int locationSessionId) {
		pullCountOfLocationServerRepo.updateCountOfPull(count, examId, locationSessionId);
	}

	public void updateCountOfQuestions(int count1, int examId, int locationSessionId) {
		pullCountOfLocationServerRepo.updateCountOfQuestions(count1, examId, locationSessionId);
	}

	public CandidateSeatDetails findSeatNumberBasedOnCandidateId(String candidateID) {
		return candidateSeatDetailsRepository.findSeatNumberBasedOnCandidateId(candidateID);
	}

	public List<Object[]> findallCandidatesPhotos() {
		return registrationPhotoRepo.findAllPhotos();
	}

	public List<CandidateRegistrationPhoto> findById(String candidateId) {
		return registrationPhotoRepo.findPhotoByCandidateId(candidateId);
	}

	public CandidateRegistrationPhoto saveCandidateRegistrationPhoto(CandidateRegistrationPhoto regPhoto) {
		return registrationPhotoRepo.save(regPhoto);
	}

	public List<Object[]> findCandidateRegistrationPhotoById(String id) {
		return registrationPhotoRepo.findCandidatePhotos(id);
	}

	public Map<String, String> findCandidateLoginTime() {
		Map<String, String> map = new HashMap<String, String>();

		for (Object[] objs : registrationPhotoRepo.findCandidatesLogintime()) {
			map.put(objs[0].toString(), objs[1].toString());
		}
		return map;
	}

	public List<Candidate> getCounPulledCandidates(int examId, int locationSessionId) {
		return candidateRepository.getCounPulledCandidates(examId, locationSessionId);
	}

	public int LocalCanidateCount(int examId, int locationSessionId) {
		return candidateRepository.LocalCanidateCount(examId, locationSessionId);
	}

}