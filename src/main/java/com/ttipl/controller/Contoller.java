package com.ttipl.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttipl.pojo.Candidate;
import com.ttipl.pojo.CandidateRegistrationPhoto;
import com.ttipl.pojo.CandidateSeatDetails;
import com.ttipl.pojo.DescriptiveQuestion;
import com.ttipl.pojo.DescriptiveQuestionLanguages;
import com.ttipl.pojo.DescriptiveRandomizeQuestions;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.pojo.LoginBean;
import com.ttipl.pojo.OtpVerification;
import com.ttipl.pojo.PullCountOfLocationServer;
import com.ttipl.pojo.Pull_Otp_verification;
import com.ttipl.pojo.Question;
import com.ttipl.pojo.SeatingPlan;
import com.ttipl.service.BOANSendSMS;
import com.ttipl.service.CandidateQueAnsService;
import com.ttipl.service.CandidateService;
import com.ttipl.service.DatapushDao;
import com.ttipl.service.EndSessionService;
import com.ttipl.service.ExamLocationService;
import com.ttipl.service.ExamService;
import com.ttipl.service.LoginService;
import com.ttipl.service.QuestionService;
import com.ttipl.service.QuestionsPushService;
import com.ttipl.service.SeatingPlanService;

@Controller
public class Contoller
{
	@Autowired
	private ExamService examService;
	@Autowired
	private LoginService loginService;;
	@Autowired
	private Environment env;
	@Autowired
	BOANSendSMS boanSendSMS;
	@Autowired
	HttpSession httpSession;
	@Autowired
	private ExamLocationService examLocationService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private SeatingPlanService seatingPlanService;
	@Autowired
	private CandidateQueAnsService ser;
	@Autowired
	private QuestionsPushService questionsPushService;
	@Autowired
	private CandidateService candidateService;
	
	String ip = null;

	
	@RequestMapping("/getRandom")
	public String getRandom(Model model, @RequestParam("otp") String otp)
	{
		String generatedOtp = "";
		if (httpSession.getAttribute("Location") == null) 
		{
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		int examId = (int) httpSession.getAttribute("examId");
		generatedOtp = (String) httpSession.getAttribute("generatedOtp");
		if (generatedOtp.equals(otp))
		{
			model.addAttribute("values", examService.getResult());
			return "random";
		} 
		else 
		{
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "Enter a Valid OTP");
			return "OtpVerificaticationPage2";
		}

	}
	
	@RequestMapping("/randomize")
	public String randomize(Model model , @RequestParam("easy") int easy , @RequestParam("medium") int medium 
			, @RequestParam("difficult") int difficult) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
	{
		
		if (httpSession.getAttribute("Location") == null) 
		{
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		List<DescriptiveRandomizeQuestions>	listRandomize = new ArrayList<DescriptiveRandomizeQuestions>();
		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		int examId = (int) httpSession.getAttribute("examId");
		Iterable<DescriptiveQuestion> itrbleQuestin = questionService.getDescriptiveQuestions(examId,locationSessionId,easy,medium,difficult);
		
		for(DescriptiveQuestion question : itrbleQuestin)
		{
			DescriptiveRandomizeQuestions randomQuestuion = new DescriptiveRandomizeQuestions();
			randomQuestuion.setEnglishQuestion(question.getQuestionName());
			randomQuestuion.setExam_id(question.getExamId());
			randomQuestuion.setExam_loc_session_id(question.getExamLocSessionId());
			randomQuestuion.setDesquestion_id(question.getDesquestion_id());
			listRandomize.add(randomQuestuion);
		}
		
		List<DescriptiveQuestionLanguages>  listQuestionLanguages= questionService.getDescriptiveLanguageQuestions(itrbleQuestin);
		
		for(int i=0 ; i<listRandomize.size();i++)
		{
			listRandomize.get(i).setHindiQuestion(listQuestionLanguages.get(i).getQuestion());
		}
		
		questionService.saveAllRandomn(listRandomize);
	
		model.addAttribute("values", examService.getResult());
		int count = questionService.findAllRandom(examId);
		model.addAttribute("count", "<span>Final number of questions allotted for the exam "+httpSession.getAttribute("examName")+" is : "+count+"</span>");
		return "random";

	}
	
	@RequestMapping("/")
	public String redirectFirstPage(Model model) {
		return "index";
	}

	@RequestMapping("/logout")
	public String logout() {
		httpSession.invalidate();
		return "index";
	}

	@RequestMapping("/loginurl")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) 
	{
		ModelAndView mv = new ModelAndView("home");
		ModelAndView m1 = new ModelAndView("index");
		List<LoginBean> record = loginService.getdata(username, password);

		if (record.size()>0) {
			model.addAttribute("values", examService.getResult());
			return mv;

		} else {
			m1.addObject("msg", "Invalid Credentials");
			return m1;
		}
	}
	
	
	@PostMapping("/getSession")
	public String getSesson(@RequestParam("id") int id,Model model)
	{
		
		ModelAndView m1 = new ModelAndView("index");
		List<ExamLocationSess> record = examLocationService.findByExamId(id);
	    ExamBean exam = examService.findById(id);
	    httpSession.setAttribute("examName", exam.getExam_name());

		if (record != null)
		{
			if(httpSession.getAttribute("candidateList")=="true")
			{
				httpSession.setAttribute("locationSessionId", record.get(0).getId());
				httpSession.setAttribute("examId", record.get(0).getExamId());
				httpSession.setAttribute("mobileNumber", record.get(0).getMobileNumber());
				model.addAttribute("values", examService.getResult());
				httpSession.setAttribute("Location", record.get(0));
				httpSession.setMaxInactiveInterval(0);
				model.addAttribute("Location", record.get(0));
				return  CandidateList(model);
			}
			httpSession.setAttribute("locationSessionId", record.get(0).getId());
			httpSession.setAttribute("examId", record.get(0).getExamId());
			httpSession.setAttribute("mobileNumber", record.get(0).getMobileNumber());
			model.addAttribute("values", examService.getResult());
			httpSession.setAttribute("Location", record.get(0));
			httpSession.setMaxInactiveInterval(0);
			model.addAttribute("Location", record.get(0));
			model.addAttribute("action", "Candidates");
			try {
				getRequiredCountByName(model, "candidate");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "pullData";

		} 
		else 
		{
			model.addAttribute("msg", "Invalid Credentials");
			return "index";
		}
	}

	@RequestMapping("/pull-data")
	public String redirectPullData(Model model) {
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		try {
			getRequiredCountByName(model, "question");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("action", "Questions");
		model.addAttribute("values", examService.getResult());
		return "pullData";
	}
	
	@RequestMapping("/pull-descriptive_data")
	public String pulDescriptiveData(Model model) {
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		try {
			getRequiredCountByName(model, "descriptiveQuestion");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("action", "descriptiveQuestion");
		model.addAttribute("values", examService.getResult());
		return "pullDescriptiveData";
	}

	@RequestMapping("/pullCandidates")
	public String pullCandidates(Model model) {
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		try {
			getRequiredCountByName(model, "candidate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("action", "Candidates");
		model.addAttribute("values", examService.getResult());
		return "pullData";
	}

	@RequestMapping(value = "/findLocationAndSession", method = RequestMethod.POST)
	public @ResponseBody String findLocationAndSession(Model model, @RequestParam("examId") int id) {
		List<ExamLocationSess> examSessions = examLocationService.findByExamId(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String examsessions = "error";
		try {
			examsessions = objectMapper.writeValueAsString(examSessions);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return examsessions;
	}

	@RequestMapping("/pullDataBasedOnIpaddress")
	public String pullDataBasedOnIpaddress(Pull_Otp_verification pull_Otp_verification,
			@RequestParam("ipaddress") String ipaddress, @RequestParam("action") String action, Model model) {

		Object locationSessionId = httpSession.getAttribute("locationSessionId");
		if (locationSessionId != null) {
			model.addAttribute("Location", httpSession.getAttribute("Location"));

			httpSession.setAttribute("ipaddress", ipaddress);
			ip = (String) httpSession.getAttribute("ipaddress");
			String mobileNumber = (String) httpSession.getAttribute("mobileNumber");
			//String emailId = (String) httpSession.getAttribute("emailId");

			model.addAttribute("Location", httpSession.getAttribute("Location"));
			int examId = (int) httpSession.getAttribute("examId");
			Random rd = new Random();
			String otp = String.format("%04d", rd.nextInt(10000));
			httpSession.setAttribute("generatedOtp", otp);
			Date dt = new Date();
			pull_Otp_verification.setExamId(examId);
			pull_Otp_verification.setExam_loc_session_id((int) locationSessionId);
			pull_Otp_verification.setDateCreated(dt.toString());
			pull_Otp_verification.setOtp(otp);
			examService.save_pull_otps(pull_Otp_verification);
			// sendMailSSLService.sendEmail("hostMailID", "HostPassword", emailId,
			// "Subject", "otp is " + otp);
			boanSendSMS.SendSMS1(mobileNumber,
					"Your Registration is SuccessFul  RegistrationId:one time Password : " + otp);
			model.addAttribute("values", examService.getResult());
			if (action.equalsIgnoreCase("Questions")) {
				return "OtpVerificaticationPage";
			} else {
				return "OtpVerificaticationCandidates";
			}
		} else {
			model.addAttribute("msg", "Session timeout  please login.");
			return "index";

		}
	}
	
	
	@RequestMapping("/pullDataBasedOnIpaddress2")
	public String pullDataBasedOnIpaddress2(Pull_Otp_verification pull_Otp_verification,
			@RequestParam("ipaddress") String ipaddress, @RequestParam("action") String action, Model model) {

		Object locationSessionId = httpSession.getAttribute("locationSessionId");
		if (locationSessionId != null) {
			model.addAttribute("Location", httpSession.getAttribute("Location"));

			httpSession.setAttribute("ipaddress", ipaddress);
			ip = (String) httpSession.getAttribute("ipaddress");
			String mobileNumber = (String) httpSession.getAttribute("mobileNumber");
			//String emailId = (String) httpSession.getAttribute("emailId");

			model.addAttribute("Location", httpSession.getAttribute("Location"));
			int examId = (int) httpSession.getAttribute("examId");
			Random rd = new Random();
			String otp = String.format("%04d", rd.nextInt(10000));
			httpSession.setAttribute("generatedOtp", otp);
			Date dt = new Date();
			pull_Otp_verification.setExamId(examId);
			pull_Otp_verification.setExam_loc_session_id((int) locationSessionId);
			pull_Otp_verification.setDateCreated(dt.toString());
			pull_Otp_verification.setOtp(otp);
			examService.save_pull_otps(pull_Otp_verification);
			// sendMailSSLService.sendEmail("hostMailID", "HostPassword", emailId,
			// "Subject", "otp is " + otp);
			boanSendSMS.SendSMS1(mobileNumber,
					"Your Registration is SuccessFul  RegistrationId:one time Password : " + otp);
			model.addAttribute("values", examService.getResult());
			if (action.equalsIgnoreCase("descriptiveQuestion")) {
				return "OtpVerificaticationPage2";
			} else {
				return "OtpVerificaticationCandidates";
			}
		} else {
			model.addAttribute("msg", "Session timeout  please login.");
			return "index";

		}
	}

	public String getRequiredCountByName(Model model, String name) throws SQLException 
	{
		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		int examId = (int) httpSession.getAttribute("examId");
		DatapushDao dataPushDao = new DatapushDao();
		String localCred = env.getProperty("spring.second-datasource.url");

		DriverManagerDataSource dataSourceLocal = new DriverManagerDataSource();
		dataSourceLocal.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSourceLocal.setUrl(localCred);
		dataSourceLocal.setUsername(env.getProperty("spring.second-datasource.username"));
		dataSourceLocal.setPassword(env.getProperty("spring.second-datasource.password"));
		switch (name.toLowerCase()) 
		{
		case "candidate":
			int count_candidate_local = candidateService.LocalCanidateCount(examId, locationSessionId);
			int count_candidate_admin = dataPushDao.pullCount(dataSourceLocal, examId, locationSessionId);
			if (count_candidate_admin != 0) 
			{
				model.addAttribute("candidate_count_percentage",
						((count_candidate_local / count_candidate_admin) * 100));
				model.addAttribute("competedRecords", count_candidate_local);
				model.addAttribute("totalRecords", count_candidate_admin);
			} 
			else 
			{
				model.addAttribute("candidate_count_percentage_msg", "No data availble");
			}
			break;

		case "question":
			int count_question_local = questionService.LocalQuestionCount(examId, locationSessionId);
			int count_question_admin = dataPushDao.pullCountQue(dataSourceLocal, examId, locationSessionId);
			if (count_question_admin != 0) 
			{
				model.addAttribute("question_count_percentage", ((count_question_local / count_question_admin) * 100));
				model.addAttribute("competedRecords", count_question_local);
				model.addAttribute("totalRecords", count_question_admin);
			} 
			else
			{
				model.addAttribute("question_count_percentage_msg", "No data availble");
			}
			break;
			
		case "descriptivequestion":
			int count_question_local2 = questionService.LocalDesQuestionCount(examId, locationSessionId);
			int count_question_admin2 = dataPushDao.pullDesCountQue(dataSourceLocal, examId, locationSessionId);
			if (count_question_admin2 != 0) 
			{
				
				model.addAttribute("question_count_percentage", ((count_question_local2*100 / count_question_admin2)));
				model.addAttribute("competedRecords", count_question_local2);
				model.addAttribute("totalRecords", count_question_admin2);
			} 
			else
			{
				model.addAttribute("question_count_percentage_msg", "No data availble");
			}
			break;
		case "candidate_qa":
			Integer count_candidate_qa_local = ser.getRec(examId, locationSessionId);
			int count_candidate_qa_admin = dataPushDao.pullCandidateQa(dataSourceLocal, examId, locationSessionId);
			if (count_candidate_qa_local != 0)
			{
				model.addAttribute("candidate_qa_count_percentage",
						((count_candidate_qa_admin / count_candidate_qa_local) * 100));
				model.addAttribute("competedRecords", count_candidate_qa_admin);
				model.addAttribute("totalRecords", count_candidate_qa_local);
			} 
			else 
			{
				model.addAttribute("candidate_qa_count_percentage_msg", "No data availble");
			}
			break;
		}
		return "push";
	}

	@RequestMapping("/push_pullStatistics")
	public String push_pullStatistics(Model m) throws SQLException {
		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		int examId = (int) httpSession.getAttribute("examId");
		// String ipaddress = (String) httpSession.getAttribute("ipaddress");
		List<Candidate> list = candidateService.getCounPulledCandidates(examId, locationSessionId);
		List<Question> listQue = questionService.getCountQue(examId, locationSessionId);
		Integer listans = ser.getRec(examId, locationSessionId);
		;
		DatapushDao dataPushDao = new DatapushDao();
		String localCred = env.getProperty("spring.second-datasource.url");

		DriverManagerDataSource dataSourceLocal = new DriverManagerDataSource();
		dataSourceLocal.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSourceLocal.setUrl(localCred);
		dataSourceLocal.setUsername(env.getProperty("spring.second-datasource.username"));
		dataSourceLocal.setPassword(env.getProperty("spring.second-datasource.password"));
		int count = dataPushDao.pullCount(dataSourceLocal, examId, locationSessionId);
		int count2 = dataPushDao.pullCountQue(dataSourceLocal, examId, locationSessionId);

		int count3 = dataPushDao.pullCandidateQa(dataSourceLocal, examId, locationSessionId);
		m.addAttribute("size", list.size());
		m.addAttribute("size1", listQue.size());
		m.addAttribute("count", count);
		m.addAttribute("count2", count2);
		m.addAttribute("count3", count3);
		m.addAttribute("count4", listans);
		return "push";
	}

	@RequestMapping("/CandidateList")
	public String CandidateList(Model model) 
	{
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		if (httpSession.getAttribute("locationSessionId") != null) 
		{
			//getVerifiedList(model);
			// int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
			
		    httpSession.setAttribute("candidateList", "true");
			model.addAttribute("Location", httpSession.getAttribute("Location"));
			int examId = (int) httpSession.getAttribute("examId");
			int sessionId = (int) httpSession.getAttribute("locationSessionId");
			String candidateAnswered = null;
			String remainingTime = null;
			boolean isCandidateInQa = false;

			List<Candidate> data = candidateService.findByExamSessionId(sessionId);
			Map<String, String> loginTimeMap = candidateService.findCandidateLoginTime();
			List<Candidate> candidateList = new ArrayList<Candidate>();
			for (Candidate candidate : data) 
			{

				int count = candidateService.findByCandidateQA(candidate.getCandidate_id());
				if (loginTimeMap.containsKey(candidate.getCandidate_id())) {
					candidate.setStartTime(loginTimeMap.get(candidate.getCandidate_id()));
				} else {
					candidate.setStartTime("--");
				}
				if (count == 0) {
					candidateAnswered = "--";
					remainingTime = "--";
					isCandidateInQa = false;
				} else {
					candidateAnswered = candidateService.findAnsweredCount(candidate.getCandidate_id()) + "";
					remainingTime = candidateService.findRemainingTime(candidate.getCandidate_id()) + "";
					isCandidateInQa = true;
				}
                
				
				String[] ipAddess = candidateService.findAssignedIpAddress(candidate.getCandidate_id());
				
				candidate.setCandidate_id(candidate.getCandidate_id());
				candidate.setCandidate_first_name(candidate.getCandidate_first_name());
				candidate.setCandidateCountAnswered(candidateAnswered);
				candidate.setRemainingTime(remainingTime);
				candidate.setCandidateIpAddress((ipAddess[0] == null ? "---" : ipAddess[0]));
				candidate.setCandidateInQa(isCandidateInQa);
				candidate.setExam_is_end(candidate.getisExam_is_end());
				candidateList.add(candidate);
			}

			model.addAttribute("candidateList", candidateList);
			model.addAttribute("count", candidateService.getCanidateCount(examId));
			// model.addAttribute("examLocations", examLocationService.getData());
			model.addAttribute("values", examService.getResult());
			model.addAttribute("session", examLocationService.findByExamId(examId));
			return "candidateList";

		} else {
			model.addAttribute("msg", "Session timeout  please login.");
			return "index";
		}

	}

	@RequestMapping("/CandidatelistUrl")
	public String CandidateList(Model model, @RequestParam(name = "sessionId", required = false) Integer sessionId,
			@RequestParam(name = "examId", required = false) Integer examId) {
		//getVerifiedList(model);
		if (httpSession.getAttribute("locationSessionId") != null) {

			model.addAttribute("Location", httpSession.getAttribute("Location"));
			examId = (int) httpSession.getAttribute("examId");
			Map<String, String> loginTimeMap = candidateService.findCandidateLoginTime();
			if (sessionId != null && examId != null) {

				String candidateAnswered = null;
				String remainingTime = null;
				boolean isCandidateInQa = false;
				List<Candidate> data = candidateService.findByExamIdAndLocationSession(examId, sessionId);

				List<Candidate> candidateList = new ArrayList<Candidate>();
				for (Candidate candidate : data) {

					int count = candidateService.findByCandidateQA(candidate.getCandidate_id());
					if (loginTimeMap.containsKey(candidate.getCandidate_id())) {
						candidate.setStartTime(loginTimeMap.get(candidate.getCandidate_id()));
					} else {
						candidate.setStartTime("--");
					}
					if (count == 0) {
						candidateAnswered = "--";
						remainingTime = "--";
						isCandidateInQa = false;
					} else {
						candidateAnswered = candidateService.findAnsweredCount(candidate.getCandidate_id()) + "";
						remainingTime = candidateService.findRemainingTime(candidate.getCandidate_id()) + "";
						isCandidateInQa = true;
					}

					//String ipAddress = candidateService.findAssignedIpAddress(candidate.getCandidate_id());
					String ipAddress = null;
					candidate.setCandidate_id(candidate.getCandidate_id());
					candidate.setCandidate_first_name(candidate.getCandidate_first_name());
					candidate.setCandidateCountAnswered(candidateAnswered);
					candidate.setRemainingTime(remainingTime);
					candidate.setCandidateIpAddress((ipAddress == null ? "---" : ipAddress));
					candidate.setCandidateInQa(isCandidateInQa);
					candidate.setExam_is_end(candidate.getisExam_is_end());
					candidateList.add(candidate);
				}
				model.addAttribute("candidateList", candidateList);

				// model.addAttribute("value",
				// candidateService.getCandidateByExam_idAndExamSession_Id(examId, sessionId));
			} else {
				String candidateAnswered = null;
				String remainingTime = null;
				boolean isCandidateInQa = false;
				List<Candidate> data = candidateService.findAll();

				List<Candidate> candidateList = new ArrayList<Candidate>();
				for (Candidate candidate : data) {

					int count = candidateService.findByCandidateQA(candidate.getCandidate_id());
					if (loginTimeMap.containsKey(candidate.getCandidate_id())) {
						candidate.setStartTime(loginTimeMap.get(candidate.getCandidate_id()));
					} else {
						candidate.setStartTime("--");
					}
					if (count == 0) {
						candidateAnswered = "--";
						remainingTime = "--";
						isCandidateInQa = false;
					} else {
						candidateAnswered = candidateService.findAnsweredCount(candidate.getCandidate_id()) + "";
						remainingTime = candidateService.findRemainingTime(candidate.getCandidate_id()) + "";
						isCandidateInQa = true;
					}

					String[] ipAddess = candidateService.findAssignedIpAddress(candidate.getCandidate_id());

					candidate.setCandidate_id(candidate.getCandidate_id());
					candidate.setCandidate_first_name(candidate.getCandidate_first_name());
					candidate.setCandidateCountAnswered(candidateAnswered);
					candidate.setRemainingTime(remainingTime);
					candidate.setCandidateIpAddress((ipAddess[0] == null ? "---" : ipAddess[0]));
					candidate.setCandidateInQa(isCandidateInQa);
					candidate.setExam_is_end(candidate.getisExam_is_end());
					candidateList.add(candidate);
				}
				model.addAttribute("candidateList", candidateList);
				// model.addAttribute("value", candidateService.getAll());
			}
			model.addAttribute("count", candidateService.getCanidateCount(examId));
			model.addAttribute("values", examService.getResult());
			model.addAttribute("session", examLocationService.findByExamId(examId));
		} else {
			model.addAttribute("msg", "Session timeout  please login.");
			return "index";
		}
		return "candidateList";
	}

	@RequestMapping("/candStart={id}")
	public String updateCandidate(Model model, @PathVariable("id") String id) {
		boolean startFlag = false;
		if (httpSession.getAttribute("Location") != null) {
			//getVerifiedList(model);
			model.addAttribute("Location", httpSession.getAttribute("Location"));
			int examId = (int) httpSession.getAttribute("examId");
			int count = candidateService.updateExamIsEnd(startFlag,startFlag, id);
			Map<String, String> loginTimeMap = candidateService.findCandidateLoginTime();
			
			if (count >= 0) {
				String candidateAnswered = null;
				String remainingTime = null;
				boolean isCandidateInQa = false;
				List<Candidate> data = candidateService.findByExamSessionId((int)httpSession.getAttribute("locationSessionId"));
				List<Candidate> candidateList = new ArrayList<Candidate>();
				for (Candidate candidate : data) {

					int count1 = candidateService.findByCandidateQA(candidate.getCandidate_id());
					if (loginTimeMap.containsKey(candidate.getCandidate_id())) {
						candidate.setStartTime(loginTimeMap.get(candidate.getCandidate_id()));
					} else {
						candidate.setStartTime("--");
					}
					if (count1 == 0) {
						candidateAnswered = "--";
						remainingTime = "--";
						isCandidateInQa = false;
					} else {
						candidateAnswered = candidateService.findAnsweredCount(candidate.getCandidate_id()) + "";
						remainingTime = candidateService.findRemainingTime(candidate.getCandidate_id()) + "";
						isCandidateInQa = true;
					}

					String[] ipAddess = candidateService.findAssignedIpAddress(candidate.getCandidate_id());
					candidate.setCandidate_id(candidate.getCandidate_id());
					candidate.setCandidate_first_name(candidate.getCandidate_first_name());
					candidate.setCandidateCountAnswered(candidateAnswered);
					candidate.setRemainingTime(remainingTime);
					candidate.setCandidateIpAddress((ipAddess[0] == null ? "---" : ipAddess[0]));
					candidate.setCandidateInQa(isCandidateInQa);
					candidate.setExam_is_end(candidate.getisExam_is_end());
					candidateList.add(candidate);
				}
				model.addAttribute("candidateList", candidateList);

				/*
				 * List<Object[]> values = candidateService.getAll();
				 * model.addAttribute("value", values);
				 */
				model.addAttribute("count", candidateService.getCanidateCount(examId));
				model.addAttribute("values", examService.getResult());
				model.addAttribute("session", examLocationService.findByExamId(examId));
				model.addAttribute("status", "Updated Succesfully.");
			} else {
				model.addAttribute("values", examService.getResult());
				model.addAttribute("status", "Didn't updated");
			}

			return "candidateList";

		} else {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";
		}
	}

	@RequestMapping("/candStop={id}")
	public String deleteCandidate(Model model, @PathVariable("id") String id) 
	{
		boolean startFlag = true;
		if (httpSession.getAttribute("Location") != null) 
		{
			//getVerifiedList(model);
			model.addAttribute("Location", httpSession.getAttribute("Location"));
			int examId = (int) httpSession.getAttribute("examId");
			int count = candidateService.updateExamIsEnd(startFlag,startFlag, id);
			Map<String, String> loginTimeMap = candidateService.findCandidateLoginTime();
			if (count >=0) 
			{
				String candidateAnswered = null;
				String remainingTime = null;
				boolean isCandidateInQa = false;
				List<Candidate> data = candidateService.findByExamSessionId((int)httpSession.getAttribute("locationSessionId"));
				List<Candidate> candidateList = new ArrayList<Candidate>();
				for (Candidate candidate : data)
				{

					int count1 = candidateService.findByCandidateQA(candidate.getCandidate_id());
					if (loginTimeMap.containsKey(candidate.getCandidate_id()))
					{
						candidate.setStartTime(loginTimeMap.get(candidate.getCandidate_id()));
					} 
					else
					{
						candidate.setStartTime("--");
					}
					if (count1 == 0) {
						candidateAnswered = "--";
						remainingTime = "--";
						isCandidateInQa = false;
					} else {
						candidateAnswered = candidateService.findAnsweredCount(candidate.getCandidate_id()) + "";
						remainingTime = candidateService.findRemainingTime(candidate.getCandidate_id()) + "";
						isCandidateInQa = true;
					}

					String[] ipAddess = candidateService.findAssignedIpAddress(candidate.getCandidate_id());
					
					candidate.setCandidate_id(candidate.getCandidate_id());
					candidate.setCandidate_first_name(candidate.getCandidate_first_name());
					candidate.setCandidateCountAnswered(candidateAnswered);
					candidate.setRemainingTime(remainingTime);
					candidate.setCandidateIpAddress((ipAddess[0] == null ? "---" : ipAddess[0]));
					candidate.setCandidateInQa(isCandidateInQa);
					candidate.setExam_is_end(candidate.getisExam_is_end());
					candidateList.add(candidate);
				}
				model.addAttribute("candidateList", candidateList);
				model.addAttribute("count", candidateService.getCanidateCount(examId));
				model.addAttribute("values", examService.getResult());
				model.addAttribute("session", examLocationService.findByExamId(examId));
				model.addAttribute("status", "Updated Succesfully.");

			} 
			else 
			{
				model.addAttribute("status", "Didn't updated");
			}
			model.addAttribute("Location", httpSession.getAttribute("Location"));
			return "candidateList";
		} 
		else
		{
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";

		}

	}

	@RequestMapping("/otpVerification")
	public String pushQuestionsUrl(PullCountOfLocationServer pullCountOfLocationServer, Model model,
			@RequestParam("otp") String otp) {
		
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		String generatedOtp = "";
		
		generatedOtp = (String) httpSession.getAttribute("generatedOtp");

		model.addAttribute("Location", httpSession.getAttribute("Location"));

		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		int examId = (int) httpSession.getAttribute("examId");
		String ipaddress = (String) httpSession.getAttribute("ipaddress");

		if (generatedOtp.equals(otp)) {

			try {
				DatapushDao dataPushDao = new DatapushDao();
				String localCred = env.getProperty("spring.second-datasource.url");
				String localDBURL = localCred.replaceAll("localhost", ipaddress);
				DriverManagerDataSource dataSourceLocal = new DriverManagerDataSource();
				dataSourceLocal.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
				dataSourceLocal.setUrl(localDBURL);
				dataSourceLocal.setUsername(env.getProperty("spring.second-datasource.username"));
				dataSourceLocal.setPassword(env.getProperty("spring.second-datasource.password"));
				String serverDBURL = env.getProperty("spring.datasource.url");
				// String serverDBURL = serverCred.replaceAll("localhost", ipaddress);
				DriverManagerDataSource dataSourceServer = new DriverManagerDataSource();
				// dataSourceServer.setDriverClassName(env.getProperty("spring.driver-class-name"));
				dataSourceServer.setUrl(serverDBURL);
				dataSourceServer.setUsername(env.getProperty("spring.datasource.username"));
				dataSourceServer.setPassword(env.getProperty("spring.datasource.password"));
				dataPushDao.pullOtpAuthenticationData(dataSourceLocal, dataSourceServer, examId, locationSessionId);
				
				ExamBean exam= examService.findExamBean(examId);
				int totalExamQuestions = exam.getNoOfQuestions();
                int count1 = questionsPushService.pullQuestionsToLocationServerDb(dataSourceLocal,
				  dataSourceServer, examId, locationSessionId, model,totalExamQuestions);
				 
				model.addAttribute("values", examService.getResult());
				if (count1 > 0) 
				{
					PullCountOfLocationServer countOfLocationServer = candidateService
							.findPullCountBasedOnExamIdAndLocationSessionId(examId, locationSessionId);
					if (countOfLocationServer == null)
					{
						pullCountOfLocationServer.setExamId(examId);
						pullCountOfLocationServer.setSessionId(locationSessionId);
						pullCountOfLocationServer.setQuestionsCount(count1);
						Date dt = new Date();
						pullCountOfLocationServer.setDateCreated(dt.toString());
						candidateService.savePullCount(pullCountOfLocationServer);

					} 
					else 
					{
						candidateService.updateCountOfQuestions(count1, examId, locationSessionId);
					}

					model.addAttribute("status", "Data has been succesfully updated." + count1 + "- Questions ");
					model.addAttribute("count", count1);
					//model.addAttribute("block", "hiii");
				} 
				else if (count1 == -1526) 
				{
					model.addAttribute("errorStatus", "Data Already Updated .");
				} 
				else if (count1 == -404) 
				{
					model.addAttribute("errorStatus", "Data Not Found.");
				}
				else if (count1 == -100) 
				{
					model.addAttribute("errorStatus", "THE NUMBER OF QUESTIONS YOU PULLED IS NOT EQUAL TO NUMBER OF QUESTIONS ALLOTTED TO EXAM PLEASE TRY AGAIN");
				}
				else {
					model.addAttribute("errorStatus", "Data not pulled to Local DB");
				}
				try {
					getRequiredCountByName(model, "question");
					model.addAttribute("action", "Questions");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return "pullData";
			} catch (Exception e) {
				model.addAttribute("errorStatus", "SOMETHING IS WRONG WHILE CONNECTING WITH ADMIN");
				return "pullData";
			}
		} else {
			model.addAttribute("msg", "Enter a Valid OTP");
			model.addAttribute("values", examService.getResult());
			return "OtpVerificaticationPage";
		}
		
	}
	
	
	@RequestMapping("/otpVerification2")
	public String pushDesQuestionsUrl(PullCountOfLocationServer pullCountOfLocationServer, Model model , @RequestParam("otp") String otp)
	{
		
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
	    String generatedOtp = "";
		generatedOtp = (String) httpSession.getAttribute("generatedOtp");
		model.addAttribute("Location", httpSession.getAttribute("Location"));

		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		int examId = (int) httpSession.getAttribute("examId");
		
		String ipaddress = (String) httpSession.getAttribute("ipaddress");
       // questionService.deleteAllDes(examId);
          if (generatedOtp.equals(otp)) {
			try {
				DatapushDao dataPushDao = new DatapushDao();
				String localCred = env.getProperty("spring.second-datasource.url");
				String localDBURL = localCred.replaceAll("localhost", ipaddress);
				DriverManagerDataSource dataSourceLocal = new DriverManagerDataSource();
				dataSourceLocal.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
				dataSourceLocal.setUrl(localDBURL);
				dataSourceLocal.setUsername(env.getProperty("spring.second-datasource.username"));
				dataSourceLocal.setPassword(env.getProperty("spring.second-datasource.password"));
				String serverDBURL = env.getProperty("spring.datasource.url");
				// String serverDBURL = serverCred.replaceAll("localhost", ipaddress);
				DriverManagerDataSource dataSourceServer = new DriverManagerDataSource();
				// dataSourceServer.setDriverClassName(env.getProperty("spring.driver-class-name"));
				dataSourceServer.setUrl(serverDBURL);
				dataSourceServer.setUsername(env.getProperty("spring.datasource.username"));
				dataSourceServer.setPassword(env.getProperty("spring.datasource.password"));
				dataPushDao.pullOtpAuthenticationData(dataSourceLocal, dataSourceServer, examId, locationSessionId);
				
				ExamBean exam= examService.findExamBean(examId);
				 int total = exam.getNo_of_descriptiveQuestions();
				 model.addAttribute("values", examService.getResult());
				 
				  int count1 =
				  questionsPushService.pullDesQuestionsToLocationServerDb(dataSourceLocal,
				  dataSourceServer, examId, locationSessionId, model , total);
								 
				
				if (count1 > 0) 
				{
					PullCountOfLocationServer countOfLocationServer = candidateService
							.findPullCountBasedOnExamIdAndLocationSessionId(examId, locationSessionId);
					if (countOfLocationServer == null)
					{
						pullCountOfLocationServer.setExamId(examId);
						pullCountOfLocationServer.setSessionId(locationSessionId);
						pullCountOfLocationServer.setQuestionsCount(count1);
						Date dt = new Date();
						pullCountOfLocationServer.setDateCreated(dt.toString());
						candidateService.savePullCount(pullCountOfLocationServer);

					} 
					else 
					{
						candidateService.updateCountOfQuestions(count1, examId, locationSessionId);
					}

					model.addAttribute("status", "Final questions allotted for the exam "+count1);
					model.addAttribute("count", count1);
					//model.addAttribute("block", "hiii");
				} 
				else if (count1 == -1526) 
				{
					model.addAttribute("errorStatus", "Data Already Updated .");
				} 
				else if (count1 == -404) 
				{
					model.addAttribute("errorStatus", "Data Not Found.");
				}
				else if (count1 == -100) 
				{
					model.addAttribute("errorStatus", "THE NUMBER OF QUESTIONS YOU PULLED IS NOT EQUAL TO NUMBER OF QUESTIONS ALLOTTED TO EXAM PLEASE TRY AGAIN");
				}
				else {
					model.addAttribute("errorStatus", "Data not pulled to Local DB");
				}
				try {
					getRequiredCountByName(model, "descriptiveQuestion");
					model.addAttribute("action", "descriptiveQuestion");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
				return "pullDescriptiveData";
			} catch (Exception e) {
				model.addAttribute("errorStatus", "SOMETHING IS WRONG WHILE CONNECTING WITH ADMIN");
				model.addAttribute("values", examService.getResult());
				model.addAttribute("action", "descriptiveQuestion");
				return "pullDescriptiveData";
			}
          }
			else {
				model.addAttribute("values", examService.getResult());
				model.addAttribute("msg", "Enter a Valid OTP");
				return "OtpVerificaticationPage2";
			}
		

	}

	@RequestMapping("/otpVerificationCandidates")
	public String pullCandidates(PullCountOfLocationServer pullCountOfLocationServer, Model model,
			@RequestParam("otp") String otp) {
		String generatedOtp = "";
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";
		}
		generatedOtp = (String) httpSession.getAttribute("generatedOtp");

		model.addAttribute("Location", httpSession.getAttribute("Location"));
		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		int examId = (int) httpSession.getAttribute("examId");
		String ipaddress = (String) httpSession.getAttribute("ipaddress");

		if (generatedOtp.equals(otp)) {

			try {
				DatapushDao dataPushDao = new DatapushDao();
				String localCred = env.getProperty("spring.second-datasource.url");
				String localDBURL = localCred.replaceAll("localhost", ipaddress);
				DriverManagerDataSource dataSourceLocal = new DriverManagerDataSource();
				dataSourceLocal.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
				dataSourceLocal.setUrl(localDBURL);
				dataSourceLocal.setUsername(env.getProperty("spring.second-datasource.username"));
				dataSourceLocal.setPassword(env.getProperty("spring.second-datasource.password"));
				String serverDBURL = env.getProperty("spring.datasource.url");
				DriverManagerDataSource dataSourceServer = new DriverManagerDataSource();
				dataSourceServer.setUrl(serverDBURL);
				dataSourceServer.setUsername(env.getProperty("spring.datasource.username"));
				dataSourceServer.setPassword(env.getProperty("spring.datasource.password"));

				int count = dataPushDao.pullCandidatesToLocationServerDb(dataSourceLocal , dataSourceServer, examId , locationSessionId);

				model.addAttribute("values", examService.getResult());

				if (count > 0) 
				{
					PullCountOfLocationServer countOfLocationServer = candidateService
							.findPullCountBasedOnExamIdAndLocationSessionId(examId, locationSessionId);
					if (countOfLocationServer == null) 
					{
						pullCountOfLocationServer.setExamId(examId);
						pullCountOfLocationServer.setSessionId(locationSessionId);
						pullCountOfLocationServer.setCandidateCount(count);
						Date dt = new Date();
						pullCountOfLocationServer.setDateCreated(dt.toString());
						candidateService.savePullCount(pullCountOfLocationServer);

					} else {
						candidateService.updateCountOfPull(count, examId, locationSessionId);
					}
					model.addAttribute("status", "Data has been succesfully updated." + count + "- Candidates ");
				} else if (count == -1526) {
					model.addAttribute("status", "Data Already Updated .");
				} else if (count == -404) {
					model.addAttribute("status", "Data Not Found.");
				} else {
					model.addAttribute("status", "Data not pulled to Local DB");
				}
				try {
					getRequiredCountByName(model, "candidate");
					model.addAttribute("action", "Candidates");
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return "pullData";
			} catch (Exception e) {
				model.addAttribute("errorStatus", "Something Went Wrong ");
				return "pullData";
			}
		} else {
			model.addAttribute("msg", "Enter a Valid OTP");
			return "OtpVerificaticationPage";
		}
	}

	@RequestMapping("/pushCandidateAnswers")
	public String pushCandidateAnswers(Model model) 
	{
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") != null) {
			model.addAttribute("Location", httpSession.getAttribute("Location"));
			try {
				getRequiredCountByName(model, "candidate_qa");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model.addAttribute("values", examService.getResult());
			return "pushCandidateAnswers";

		} else {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
	}

	@RequestMapping("/pushCandidateAnswersUrl")
	public String pushCandidateAnswersUrl(@RequestParam("ipaddress") String ipaddress, Model model) {
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";
		}
		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		int examId = (int) httpSession.getAttribute("examId");
		model.addAttribute("Location", httpSession.getAttribute("Location"));
		DatapushDao dataPushDao = new DatapushDao();
		String localDBURL = env.getProperty("spring.datasource.url");
		DriverManagerDataSource dataSourceLocal = new DriverManagerDataSource();
		dataSourceLocal.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSourceLocal.setUrl(localDBURL);
		dataSourceLocal.setUsername(env.getProperty("spring.datasource.username"));
		dataSourceLocal.setPassword(env.getProperty("spring.datasource.password"));
		String serverCred = env.getProperty("spring.second-datasource.url");
		String serverDBURL = serverCred.replaceAll("localhost", ipaddress);
		// String serverDBURL = localDBURL.replaceAll("localhost", ipaddress);
		DriverManagerDataSource dataSourceServer = new DriverManagerDataSource();
		// dataSourceServer.setDriverClassName(env.getProperty("spring.second-datasource.driver-class-name"));
		dataSourceServer.setUrl(serverDBURL);
		dataSourceServer.setUsername(env.getProperty("spring.second-datasource.username"));
		dataSourceServer.setPassword(env.getProperty("spring.second-datasource.password"));
		Integer count_candidate_qa_local = ser.getRec(examId, locationSessionId);
		int count = dataPushDao.pushCandidateAnswers(dataSourceLocal, dataSourceServer , count_candidate_qa_local );
		model.addAttribute("values", examService.getResult());
		
		if (count == 25) {
			model.addAttribute("msg", "SOMETHING IS WRONG DATA IS NOT PUSHED ");
		} 
		else if (count > 0) {
			model.addAttribute("msg", count + " - Records Pushed.");
		} 
		else if(count==-100)
		{
			model.addAttribute("errorStatus", "Full data is not pushed try again");
		}
		
		else {
			model.addAttribute("errorStatus", "There is no data to push.");
		}
		try {
			getRequiredCountByName(model, "candidate_qa");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "pushCandidateAnswers";
	}

	@RequestMapping("/examStartFlag")
	public String examStartFlag(Model model , OtpVerification otpVer) 
	{
		httpSession.setAttribute("candidateList", "false");		
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		Object locationSessionId = httpSession.getAttribute("locationSessionId");
		if (locationSessionId != null) 
		{

			String mobileNumber = (String) httpSession.getAttribute("mobileNumber");

			int examId = (int) httpSession.getAttribute("examId");
			Random rd = new Random();
			String otp = String.format("%04d", rd.nextInt(10000));
			httpSession.setAttribute("generatedOtp", otp);
			Date dt = new Date();
			otpVer.setExamId(examId);
			otpVer.setExam_loc_session_id((int) locationSessionId);
			otpVer.setDateCreated(dt.toString());
			otpVer.setOtp(otp);
			examService.save_otps(otpVer);
			boanSendSMS.SendSMS1(mobileNumber,
					"Your Registration is SuccessFul  RegistrationId:one time Password : " + otp);
			
		}
		
		model.addAttribute("Location", httpSession.getAttribute("Location"));
		model.addAttribute("values", examService.getResult());
		return "examStartFlag";
	}

	@RequestMapping("/updateExamStartFlagUrl")
	public String updateExamStartFlagUrl(@RequestParam("otp") String otp, Model model) {
		int locationSessionId = 0;
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("msg", "Session Expired Relogin again.");
			return "index";
		}
		model.addAttribute("Location", httpSession.getAttribute("Location"));
		int examId = (int) httpSession.getAttribute("examId");
		locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		OtpVerification data = examService.findByExamStartFlag(examId, locationSessionId, otp);
		if (data != null) {
			boolean exam_start = true;
			Date dt = new Date();
			String exam_start_Updated_time = dt.toString();
			int count = examService.updateExamStartFlag(examId, locationSessionId, exam_start, exam_start_Updated_time);
			model.addAttribute("values", examService.getResult());
			if (count != 0) {
				model.addAttribute("status", "updated Succesfully");
			} else {
				model.addAttribute("errorStatus", "Error While Updating Start Flag");
			}

		} else {
			model.addAttribute("errorStatus", "Enter a valid Otp");
		}
		return "examStartFlag";

	}

	@RequestMapping("/candidateId")
	public String candidateId(Model model) {
		httpSession.setAttribute("candidateList", "false");
		int locationSessionId = 0;
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		model.addAttribute("Location", httpSession.getAttribute("Location"));
		int examId = (int) httpSession.getAttribute("examId");
		List<Candidate> data = examService.findByExamIdandLocationId(examId, locationSessionId);
		model.addAttribute("values1", data);
		model.addAttribute("values", examService.getResult());
		return "listOfCandidateIdAndPassword";

	}

	@RequestMapping("/findSeatNumber")
	public String findSeatNumber(Model model) {
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		model.addAttribute("values", examService.getResult());
		return "findSeatNumber";
	}

	@RequestMapping("/findSeatNumberBasedOnCandidateId")
	public String findSeatNumberBasedOnCandidateId(@RequestParam("candidateID") String candidateID, Model model) {
		CandidateSeatDetails data = candidateService.findSeatNumberBasedOnCandidateId(candidateID);
		if (data != null) {
			model.addAttribute("data",
					"Room Name :- " + data.getRoomName() + " and Seat Number :- " + data.getSeatcode());
		} else {
			model.addAttribute("error", "Candidate data not found");
		}
		model.addAttribute("values", examService.getResult());
		return "findSeatNumber";
	}

	@RequestMapping("/changeIp")
	public String changeIp(Model model) {
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		model.addAttribute("Location", httpSession.getAttribute("Location"));
		model.addAttribute("values", examService.getResult());
		return "changeIp";
	}

	@RequestMapping("/changeIpUrl")
	public String changeIpUrl(@RequestParam("candidateID") String candidateID, @RequestParam("newIp") String newIp,
			Model model) {
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		model.addAttribute("Location", httpSession.getAttribute("Location"));
		model.addAttribute("values", examService.getResult());
		try {
			int count = candidateService.updateNewIpToCandidate(newIp, candidateID);
			if (count > 0) {
				model.addAttribute("status", "Updated Succesfully.");
			} else {
				model.addAttribute("status", "Not Updated due to candidate Ip Not present. ");
			}
		} catch (Exception e) {
			model.addAttribute("status", "Ip is already allotted to another candidate give new Ip.");
		}
		return "changeIp";

	}

	@RequestMapping("/seatingplanpage")
	public String uploadseatingplanpage(Model model) 
	{
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		model.addAttribute("Location", httpSession.getAttribute("Location"));
		model.addAttribute("values", examService.getResult());
		return "seatingplanpage";
	}

	@RequestMapping(value = "/arrangeseatingplan", method = RequestMethod.POST)
	public ResponseEntity<ByteArrayResource> arrangeseatingplan(Model model, @RequestParam("file") MultipartFile file)throws IOException
	{
		
		
		DataFormatter dataFormatter = new DataFormatter();
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		System.out.println(sheet.getPhysicalNumberOfRows());
		List<SeatingPlan> seatingPlans = new ArrayList<SeatingPlan>();
		int skipFirstRow = 0;
		int iu = 0;
		for (Row row : sheet) 
		{
			if(skipFirstRow>0)
			{
				   Cell c = row.getCell(1);
				   c.setCellType(CellType.STRING);
				SeatingPlan seatingPlan = new SeatingPlan();
				seatingPlan.setId(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0))));
				seatingPlan.setCandidateid(dataFormatter.formatCellValue(c));
				seatingPlan.setRoomName(dataFormatter.formatCellValue(row.getCell(2)));
				
				
				seatingPlans.add(seatingPlan);
			}
			skipFirstRow = 1;
		}
        seatingPlanService.deleteAll();
		seatingPlanService.save(seatingPlans);
		List<CandidateSeatDetails> listSeatDetails = new ArrayList<CandidateSeatDetails>();
		 List<SeatingPlan> listSeatingGroup = seatingPlanService.findSeatPlanGroup();
		 for(SeatingPlan spl : listSeatingGroup)
		 {  
			List<SeatingPlan> listSp = seatingPlanService.findSeatsByRoom(spl.getRoomName());
			Collections.shuffle(listSp);
			List<Integer> intList = new ArrayList<Integer>();
			for(int i=1 ; i<=listSp.size() ; i++)
			{
			    intList.add(i);
			}
			Collections.shuffle(intList);
			for(int i=0 ; i<listSp.size() ; i++)
			{
				SeatingPlan splRoom= listSp.get(i);
				CandidateSeatDetails cd = new CandidateSeatDetails();
				cd.setCandidateId(splRoom.getCandidateid());
				cd.setRoomName(splRoom.getRoomName());
			    cd.setSeatcode(String.valueOf(intList.get(i)));
			    
			    listSeatDetails.add(cd);
			}
			
		 }
		 seatingPlanService.saveList(listSeatDetails);
		return downloadExcel();
	}

	public Model getVerifiedList(Model model) 
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.photoverifiction.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.photoverifiction.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.photoverifiction.datasource.password"));
		try {
			conn = dataSource.getConnection();
			statement = conn.prepareStatement("select candidate_id ,ifnull(cast(result as SIGNED),-1) as result\r\n"
					+ "from candidate_photo_verification group by candidate_id order by created_at;");
			rs = statement.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("candidate_id"), rs.getInt("result"));
			}
		} catch (SQLException e) {
			map.put("status", 500);
			// e.printStackTrace();
		}
		model.addAttribute("candidateVerifiedList", map);
		model.addAttribute("photoVerification.api.ip", env.getProperty("photoVerification.api.ip"));
		model.addAttribute("photoVerification.api.server.port", env.getProperty("photoVerification.api.server.port"));

		return model;
	}

	// photo Match methods
	@RequestMapping("photoNotVerifiedList")
	public String getPhotoVerifiedPage(Model model) 
	{
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") != null) {
			model.addAttribute("list", candidateService.findallCandidatesPhotos());
			model.addAttribute("values", examService.getResult());
			return "PhotoMatchManually";

		} else  {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}

	}

	@RequestMapping("veiwCandidatePhoto")
	public String getPhotoVerifiedPage(@RequestParam("candidateId") String id, Model model) {
		if (httpSession.getAttribute("Location") != null) {
			model.addAttribute("candidate", candidateService.findCandidateRegistrationPhotoById(id));
			return "PhotoVerify";

		} else {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";

		}

	}

	@RequestMapping("photoMatchedResponse")
	public String photoMatechedResponse(@RequestParam("candidateId") String id, @RequestParam("status") boolean status,
			Model model) {
		if (httpSession.getAttribute("Location") != null) {

			List<CandidateRegistrationPhoto> photoList = candidateService.findById(id);
			if (photoList != null) {
				for (CandidateRegistrationPhoto photo : photoList) {
					photo.setMatched(status);
					candidateService.saveCandidateRegistrationPhoto(photo);
				}
			}
			model.addAttribute("list", candidateService.findallCandidatesPhotos());
			return "PhotoMatchManually";

		} else {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";

		}

	}

	@RequestMapping("allocationOfSeats")
	public String rediretToUploadCustomSeats(Model model) {
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		
		model.addAttribute("values", examService.getResult());
		return "uploadCustomSeats";
	}

	@RequestMapping("redirectPushCanidateImages")
	public String redirectPushCanidateImages(Model model) 
	{
		httpSession.setAttribute("candidateList", "false");
		if (httpSession.getAttribute("Location") == null) 
		{
			model.addAttribute("values", examService.getResult());
			model.addAttribute("msg", "PLEASE SELECT EXAM.");
			return "home";
		}
		model.addAttribute("values", examService.getResult());
		return "pushImagesPage";
	}

	// push candidate IMages
	@RequestMapping("pushCanidateImages")
	public String pushCanidateImages(Model model) {
		DatapushDao dataPushDao = new DatapushDao();
		Integer count = 0;
		Integer totalCount = 0;
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";
		}
		model.addAttribute("values", examService.getResult());
		for (Candidate candidate : candidateService.findAll()) {
			try {
				count = dataPushDao.candidatePhotoVerificationApi(
						"http://" + env.getProperty("photoVerification.api.ip") + ":"
								+ env.getProperty("photoVerification.api.server.port")
								+ "/PhotoVerificationAPI/syncData",
						candidate.getCandidate_id(), env.getProperty("spring.photoverifiction.datasource.username"),
						env.getProperty("spring.photoverifiction.datasource.password"),
						env.getProperty("spring.photoverifiction.datasource.url"));
				if (count != -500) {
					totalCount = totalCount + count;

				} else {
					model.addAttribute("count", totalCount + " Images Pushed Sucessfully");
					model.addAttribute("error",
							"Error While Pushing Candidate (" + candidate.getCandidate_id() + ") Images ");
					return "pushImagesPage";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (totalCount == 0) {
			model.addAttribute("count", "Data Already Updated.");
		} else {
			model.addAttribute("count", totalCount + " Images Pushed Sucessfully");
		}
		return "pushImagesPage";
	}

	// end session page
	@RequestMapping("redirectEndSession")
	public String endSession(Model model) {
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";
		}
		model.addAttribute("values", examService.getResult());
		return "endSession";
	}

	@RequestMapping("endSession")
	public String endSessionLogic(Model model) {
		Connection conn = null;
		PreparedStatement statement = null;
		// ResultSet rs = null;
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("msg", "Session expired relogin again.");
			return "index";
		}
		int count = 0;
		int errorCount = 0;
		int locationSessionId = (int) httpSession.getAttribute("locationSessionId");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		try {
			conn = dataSource.getConnection();
			for (String tbl_name : EndSessionService.TABLE_TO_RENAME) {
				statement = conn.prepareStatement("RENAME TABLE ".concat(tbl_name).concat(" TO ")
						.concat(tbl_name + "_" + LocalDate.now().toString().replaceAll("-", "_"))
						.concat("_" + locationSessionId));
				try {
					statement.executeUpdate();
					count++;
				} catch (SQLSyntaxErrorException e) {
					errorCount++;
				}
			}
			for (String tbl_name : EndSessionService.TABLE_TO_CREATE) {
				statement = conn.prepareStatement(tbl_name);
				try {
					statement.executeUpdate();
					count++;

				} catch (SQLSyntaxErrorException e) {
					errorCount++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("values", examService.getResult());
		if (count == 0 && errorCount == 10) {
			model.addAttribute("msg", "session ended sucessfully");
		} else if (count == 10) {
			model.addAttribute("msg", "session ended sucessfully");
		} else {
			model.addAttribute("msg", "please try again");
		}
		return "endSession";
	}

	/*
	 * @RequestMapping(value = "/customSeatingplan", method = RequestMethod.POST)
	 * public ResponseEntity<ByteArrayResource> customSeatingplan(Model
	 * model, @RequestParam("file") MultipartFile file) throws IOException { if
	 * (httpSession.getAttribute("Location") == null) { return
	 * ReponseEntity("Session Expired Please Relogin ", "logout"); } //
	 * model.addAttribute("Location", httpSession.getAttribute("Location"));
	 * DataFormatter dataFormatter = new DataFormatter(); XSSFWorkbook workbook =
	 * new XSSFWorkbook(file.getInputStream()); XSSFSheet sheet =
	 * workbook.getSheetAt(0); List<CandidateSeatDetails> seats = new
	 * ArrayList<CandidateSeatDetails>(); int skipFirstRow = 0; boolean
	 * cellcountmatched = false; for (Row row : sheet) { cellcountmatched = false;
	 * if (row.getPhysicalNumberOfCells() == 4) { cellcountmatched = true; } if
	 * (skipFirstRow > 0) { CandidateSeatDetails seat = new CandidateSeatDetails();
	 * seat.setCandidateId(dataFormatter.formatCellValue(row.getCell(0)));
	 * seat.setRoomName(dataFormatter.formatCellValue(row.getCell(1)));
	 * seat.setSeatcode(dataFormatter.formatCellValue(row.getCell(2)));
	 * seat.setIpaddress(dataFormatter.formatCellValue(row.getCell(3))); if
	 * (!cellcountmatched) { workbook.close(); return
	 * ReponseEntity("Cell Count Not Matched .....", "allocationOfSeats"); } if
	 * (!utility.isIPAddressCorrect(seat.getIpaddress())) { workbook.close(); return
	 * ReponseEntity("Please Upload Proper File (You Entered Invalid IP address)",
	 * "allocationOfSeats"); } seats.add(seat);
	 * 
	 * } skipFirstRow = 1; System.out.println(".........." +
	 * dataFormatter.formatCellValue(row.getCell(0))); } workbook.close();
	 * 
	 * for (CandidateSeatDetails seat : seats) { CandidateSeatDetails seatexist =
	 * seatingPlanService.findById(seat.getCandidateId()); if (seatexist == null) {
	 * if (!(seat.getCandidateId().equalsIgnoreCase("") || seat.getCandidateId() ==
	 * null)) { seatingPlanService.save(seat); } } else {
	 * seatexist.setIpaddress(seat.getIpaddress());
	 * seatexist.setRoomName(seat.getRoomName());
	 * seatexist.setSeatcode(seat.getSeatcode());
	 * seatingPlanService.save(seatexist); }
	 * 
	 * } return downloadExcel(); }
	 */

	public ResponseEntity<ByteArrayResource> downloadExcel() 
	{
		try {
			Workbook workbook1 = new XSSFWorkbook();
			CreationHelper createHelper = workbook1.getCreationHelper();
			
				Sheet sheet1 = workbook1.createSheet("details");
				Font headerFont = workbook1.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 14);
				headerFont.setColor(IndexedColors.RED.getIndex());
				CellStyle headerCellStyle = workbook1.createCellStyle();
				headerCellStyle.setFont(headerFont);
				Row headerRow = sheet1.createRow(0);
				String[] columns = { "CandidateId", "Room Name", "Seat No" };
				for (int i = 0; i < columns.length; i++)
				{
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columns[i]);
					cell.setCellStyle(headerCellStyle);
				}

				int rowNum = 1;
				List<CandidateSeatDetails> list= seatingPlanService.findAllCandidateS();
				for (CandidateSeatDetails candidateSeatDetails :list ) {
					Row row = sheet1.createRow(rowNum++);
					row.createCell(0).setCellValue(candidateSeatDetails.getCandidateId());
					row.createCell(1).setCellValue(candidateSeatDetails.getRoomName());
					row.createCell(2).setCellValue(candidateSeatDetails.getSeatcode());
				}

				for (int i = 0; i < columns.length; i++) {
					sheet1.autoSizeColumn(i);
				}
			
			// Write the output to a file
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "force-download"));
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=CandidateSeatingPlan.xlsx");
			workbook1.write(stream);
			workbook1.close();

			return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()), header, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ByteArrayResource> ReponseEntity(String msg, String backPage) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.TEXT_HTML);
		return new ResponseEntity<>(
				new ByteArrayResource("<p style=\"font-size:20px; text-align:center\">".concat(msg)
						.concat("<a href=\"".concat(backPage).concat("\"> Back</a></p>")).getBytes()),
				header, HttpStatus.CREATED);
	}

}
