package com.ttipl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttipl.pojo.ReportBean;
import com.ttipl.pojo.Result;

@Component
public class ResultManager {

	@Autowired
	private CandidateQueAnsService service;

	public ArrayList<ReportBean> getDetailedResults(int exam_loc_session_id, String comm) {

		String marks_per_question = "";
		String neg_marks = "";
		ReportBean bean = null, temp = null;
		String response = "";
		double marks_q = 0;
		double marks_n = 0;
		int attempted = 0;
		int correct = 0;
		double totalMarks = 0;
		double totalNegMarks = 0;
		String attempted1 = "", correct1 = "";
		if (comm == null || (comm != null && comm.equalsIgnoreCase("1"))) {
			comm = "%";
		}
		List<Object[]> objects_candidate = service.findCandidatesByExamSessionIdAndComminuty(exam_loc_session_id, comm);
		System.out.println(response);
		ArrayList<ReportBean> list = new ArrayList<ReportBean>();
		for (Object[] object : objects_candidate) {
			bean = new ReportBean();
			bean.setHTnumber((String) object[0]);
			bean.setCandidateName((String) object[1]);
			bean.setDob((String) object[2]);
			bean.setCommunity((String) object[3]);
			bean.setExam_code((String) object[4]);
			bean.setLocationName((String) object[5]);
			bean.setPost_category((String) object[6]);
			list.add(bean);
		}

		Result r = null;
		List<Object[]> candidate_result_objects = service
				.findCandidateResultByExamSessionIdAndComminuty(exam_loc_session_id, comm);
		for (Object[] objects : candidate_result_objects) {
			r = new Result();
			r.setAnswer_option_id((String) objects[2]);
			r.setCandidate_id((String) objects[0]);
			r.setIs_answer((boolean) objects[3]);
			r.setQuestionId((String) objects[1]);
			marks_per_question = (String) objects[4];
			neg_marks = (String) objects[5];
			updateResultList(list, r);
		}
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			marks_q = Double.parseDouble(marks_per_question);
			marks_n = Double.parseDouble(neg_marks);
			attempted1 = temp.getTotalAnswered();
			if (attempted1 == null)
				attempted1 = "0";
			attempted = Integer.parseInt(attempted1);
			correct1 = temp.getTotalCorrect();
			if (correct1 == null)
				correct1 = "0";
			correct = Integer.parseInt(correct1);
			totalNegMarks = ((attempted - correct) * marks_n);
			totalMarks = (correct * marks_q) - (totalNegMarks);
			temp.setNegativeMarks(totalNegMarks + "");
			temp.setTotalObtained(Math.round(totalMarks) + "");

			/*
			 * pstmt.setString(1, attempted+""); pstmt.setString(2, correct+"");
			 * pstmt.setString(3, totalMarks+""); pstmt.setString(4, temp.getH_T_No());
			 * pstmt.executeUpdate();
			 */
			/*
			 * response = temp.getResponses(); if(response!=null &&
			 * response.lastIndexOf(",")!=-1) response =
			 * response.substring(response.lastIndexOf(",")-1); temp.setResponses(response);
			 */
		}

		return list;
	}

	public void updateResultList(List<ReportBean> list, Result r) {
		ReportBean temp = null, rb = null;
		String attempted = null, total_correct = null, total_neg = null, total_marks = null;
		String response = "";
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			if (temp.getHTnumber().equalsIgnoreCase(r.getCandidate_id())) {
				rb = temp;
				break;
			}
		}
		if (rb != null) {

			attempted = rb.getTotalAnswered();
			total_correct = rb.getTotalCorrect();
			total_neg = rb.getNegativeMarks();
			total_marks = rb.getTotalObtained();
			response = rb.getResponses();
			if (response == null) {
				response = "";
			}
			int a_count = 0, c_count = 0;
			if (!r.getAnswer_option_id().equalsIgnoreCase("0")) {
				if (attempted == null) {
					attempted = "0";
				}
				a_count = Integer.parseInt(attempted);

				rb.setTotalCorrect((a_count + 1) + "");
			}
			if (r.isIs_answer()) {
				if (total_correct == null) {
					total_correct = "0";
				}
				c_count = Integer.parseInt(total_correct);
				rb.setTotalCorrect((c_count + 1) + "");
			}
			if (response.length() == 0)
				response = r.getAnswer_option_id();
			else
				response = response + ',' + r.getAnswer_option_id();

			rb.setResponses(response);
		}
	}

}
