
package com.ttipl.service;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.pojo.Post;
import com.ttipl.pojo.ReportBean;

@Component
public class PDFReport 
{

	public String getStasticsPDFReport(ExamLocationSess examLocation, ExamBean exam, List<Object[]> objects, Post post,
			String xslPath, String pdfPath) throws Exception
	{
		String name = "";
		int size = 0;
		StringBuilder xmlData = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm");
		if (objects != null) {
			size = objects.size();
			if (objects.size() != 0) 
			{

				xmlData.append("<organization>" + "BPSSC" + "</organization>");
				xmlData.append("<examCode>" + exam.getExam_code() + "</examCode>");
				xmlData.append("<generatedTime>" + dateFormat.format(new Date()) + "</generatedTime>");
				xmlData.append("<postName>" + post.getPost_name() + "</postName>");

				xmlData.append("<examLocation>" + examLocation.getLocation_name() + " (" + examLocation.getStart_time()
						+ "-" + examLocation.getEnd_time() + ")" + "</examLocation>");
				xmlData.append("<totalQuestions>" + size + "</totalQuestions>");
				xmlData.append("<examDuration>" + exam.getDuration() + "</examDuration>");

				for (Object[] object : objects) {
					name = (String) object[0];
					xmlData.append("<list>");
					xmlData.append("<HTNumber>" + object[2] + "</HTNumber>");
					xmlData.append("<question>" + object[3] + "</question>");
					xmlData.append("<timeAnswered>" + object[5] + "</timeAnswered>");
					xmlData.append("<remainingTime>" + object[4] + "</remainingTime>");
					xmlData.append("</list>");
				}
			}
			StringReader xml = new StringReader("<row>" + "<name>" + name + "</name>" + xmlData + "</row>");
			System.out.println(xml.toString());

			return pdfPath + "/" + new XMLTransformer().GetTransformedString(xml, "pdf", "stastics", xslPath, pdfPath,
					"statisReports", "pdf");

		} else {
			return "";
		}
	}

	public String getCandidateQueAnsweReport(ExamLocationSess examLocation, ExamBean exam, List<Object[]> objects,
			Post post, String xslPath, String pdfPath) throws Exception
	{
		int answered_questions = 0;
		String answer;
		int correct_answers = 0;
		int size = 0;
		if (objects != null) 
		{
			size = objects.size();
			if (objects.size() != 0) 
			{
				size = objects.size();
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm");

		StringBuilder xmlData = new StringBuilder();
		xmlData.append("<organization>" + "BPSSC" + "</organization>");
		xmlData.append("<examCode>" + exam.getExam_code() + "</examCode>");
		xmlData.append("<generatedTime>" + dateFormat.format(new Date()) + "</generatedTime>");
		xmlData.append("<postName>" + post.getPost_name() + "</postName>");

		xmlData.append("<examLocation>" + examLocation.getLocation_name() + "</examLocation>");
		xmlData.append("<totalQuestions>" + size + "</totalQuestions>");

		for (Object[] object : objects) 
		{
			answer = (String) object[5];
			if (answer.equalsIgnoreCase("N.A")) 
			{
				answered_questions++;
			}
			if (answer.equalsIgnoreCase("1")) 
			{
				correct_answers++;
			}

			xmlData.append("<list>");
			xmlData.append("<HTNumber>" + object[2] + "</HTNumber>");
			xmlData.append("<question>" + object[3] + "</question>");
			xmlData.append("<CandidateAnswer>" + object[4] + "</CandidateAnswer>");
			xmlData.append("<CorrectAnswer>" + object[6] + "</CorrectAnswer>");
			xmlData.append("</list>");
		}
		StringReader xml = new StringReader("<row>" + "<correct_answers>" + correct_answers + "</correct_answers>"
				+ "<TotalAttempted>" + (size - answered_questions) + "</TotalAttempted>" + xmlData + "</row>");
		System.out.println(xml.toString());
		return pdfPath + "/" + new XMLTransformer().GetTransformedString(xml, "pdf", "candidateQuestionAnswerReports",
				xslPath, pdfPath, "CandidateQueAnsweReport", "pdf");
	}

	public String getCandidateResultReport(ExamLocationSess examLocation, ExamBean exam,
			List<ReportBean> reportbeanArray, Post post, String xslPath, String pdfPath) throws Exception 
	{

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm");

		StringBuilder xmlData = new StringBuilder();
		xmlData.append("<organization>" + "BPSSC" + "</organization>");
		xmlData.append("<examCode>" + exam.getExam_code() + "</examCode>");
		xmlData.append("<generatedTime>" + dateFormat.format(new Date()) + "</generatedTime>");
		xmlData.append("<postName>" + post.getPost_name() + "</postName>");

		for (ReportBean reportBean : reportbeanArray) 
		{

			xmlData.append("<list>");
			xmlData.append("<HTNumber>" +      reportBean.getHTnumber() + "</HTNumber>");
			xmlData.append("<examLocation>" +  reportBean.getLocationName() + " (" + reportBean.getSession()
					+ ")</examLocation>");
			xmlData.append("<CandidateName>" + reportBean.getCandidateName() + "</CandidateName>");
			xmlData.append("<TotalQuestions>" +reportBean.getTotalQuestions() + "</TotalQuestions>");
			xmlData.append("<TotalAnswered>" + reportBean.getTotalAnswered() + "</TotalAnswered>");
			xmlData.append("<TotalCorrect>" +  reportBean.getTotalCorrect() + "</TotalCorrect>");
			xmlData.append("<NegativeMarks>" + reportBean.getNegativeMarks() + "</NegativeMarks>");
			xmlData.append("<MarksObtained>" + reportBean.getTotalObtained() + " (" + reportBean.getRounded_marks()
					+ ")</MarksObtained>");
			xmlData.append("</list>");
		}
		StringReader xml = new StringReader("<row>" + xmlData + "</row>");
		System.out.println(xml.toString());
		return pdfPath + "/" + new XMLTransformer().GetTransformedString(xml, "pdf", "candidateResultsReport", xslPath,
				pdfPath, "CandidateQueAnsweReport", "pdf");
	}

}
