package com.ttipl.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "descriptive_question")
public class DescriptiveQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "desquestion_id")
	private Integer desquestion_id;
	@Column(name = "exam_id", unique = false, nullable = false)
	private Integer examId;
	@Column(name = "question_paper_code")
	private String setNo;
	@Column(name = "question_type")
	private String questionType;
	@Column(name = "question", columnDefinition = "Longtext")
	private String questionName;
	@Column(name = "question_hindi", columnDefinition = "Longtext")
	private String questionHindi;
	
	@Column(name = "status", nullable = false, columnDefinition = "TINYINT", length = 4)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean status;
	@Column(name = "question_no")
	private Integer questionNo;
	@Column(name = "english_image_id")
	private Integer englishImageId=null;
	@Column(name = "hindi_image_id")
	private Integer hindiImageId =null;
	@Column(name = "question_image_id")
	private Integer question_image_id =null;

	@Column(name = "exam_loc_session_id", unique = false, nullable = false)
	private Integer examLocSessionId;

	@Column(name = "bank_question_id")
	private Integer bankQuestionId;
	
	@Column(name = "level")
	private String level;

    

	public Integer getDesquestion_id() {
		return desquestion_id;
	}

	public void setDesquestion_id(Integer desquestion_id) {
		this.desquestion_id = desquestion_id;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setEnglishImageId(Integer englishImageId) {
		this.englishImageId = englishImageId;
	}

	public void setHindiImageId(Integer hindiImageId) {
		this.hindiImageId = hindiImageId;
	}

	public void setExamLocSessionId(Integer examLocSessionId) {
		this.examLocSessionId = examLocSessionId;
	}

	public String getSetNo() {
		return setNo;
	}

	public void setSetNo(String setNo) {
		this.setNo = setNo;
	}

	
	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	

	public String getQuestionType() {
		return questionType;
	}

	public Integer getExamLocSessionId() {
		return examLocSessionId;
	}

	public void setExamLocSessionId(int examLocSessionId) {
		this.examLocSessionId = examLocSessionId;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionHindi() {
		return questionHindi;
	}

	public void setQuestionHindi(String questionHindi) {
		this.questionHindi = questionHindi;
	}

	public Integer getQuestion_image_id() {
		return question_image_id;
	}

	public void setQuestion_image_id(Integer question_image_id) {
		this.question_image_id = question_image_id;
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public Integer getBankQuestionId() {
		return bankQuestionId;
	}

	public void setBankQuestionId(Integer bankQuestionId) {
		this.bankQuestionId = bankQuestionId;
	}

	public int getEnglishImageId() {
		return englishImageId;
	}

	public void setEnglishImageId(int englishImageId) {
		this.englishImageId = englishImageId;
	}

	public int getHindiImageId() {
		return hindiImageId;
	}

	public void setHindiImageId(int hindiImageId) {
		this.hindiImageId = hindiImageId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
     
	
}
