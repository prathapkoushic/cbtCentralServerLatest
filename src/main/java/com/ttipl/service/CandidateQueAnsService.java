package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.repository.CandidateQueAnsRepo;

@Service
public class CandidateQueAnsService {
	@Autowired
	private CandidateQueAnsRepo queAnsRepo;

	public List<Object[]> findcandidateQuestionsReport(int id) {
		return queAnsRepo.findcandidateQuestionsReport(id);
	}

	public List<Object[]> findStatisticsReport(int id) {
		return queAnsRepo.findStatisticsReport(id);
	}

	public List<Object[]> findCandidateReportsById(String id) {
		return queAnsRepo.findCandidateReportsById(id);
	}

	public List<Object[]> findCandidateReports() {
		return queAnsRepo.findCandidateReports();
	}

	public List<Object[]> findCandidatesByExamSessionIdAndComminuty(int exam_session_id, String community) {
		return queAnsRepo.findCandidatesByExamSessionIdAndComminuty(exam_session_id, community);
	}

	public List<Object[]> findCandidateResultByExamSessionIdAndComminuty(int exam_session_id, String community) {
		return queAnsRepo.findCandidateResultByExamSessionIdAndComminuty(exam_session_id, community);
	}

	public int deleteByCandidateId(String id) {
		return queAnsRepo.deleteByCandidateId(id);
	}

	public void updateFlagBasedOnCandidateId(boolean b, String cd_id) {
		queAnsRepo.updateFlagBasedOnCandidateId(b, cd_id);
	}

	public Integer getRec(int examId, int locationSessionId) {
		return queAnsRepo.getRecord(examId , locationSessionId);
	}

}
