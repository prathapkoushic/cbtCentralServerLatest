package com.ttipl.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.ttil.encryption.EncryptionDecryption;

@Entity
@Table(name = "Question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Integer questionId;
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
	@OneToMany(mappedBy = "Question", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Options> options;
	@Column(name = "status", nullable = false, columnDefinition = "TINYINT", length = 4)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean status;
	@Column(name = "question_no")
	private Integer questionNo;
	@Column(name = "english_image_id")
	private Integer englishImageId=null;
	@Column(name = "hindi_image_id")
	private Integer hindiImageId =null;
	

	@Column(name = "exam_loc_session_id", unique = false, nullable = false)
	private Integer examLocSessionId;

	@Column(name = "bank_question_id")
	private Integer bankQuestionId;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getSetNo() {
		return setNo;
	}

	public void setSetNo(String setNo) {
		this.setNo = setNo;
	}

	public String getQuestionName() {
		try {
			questionName = EncryptionDecryption.decrypt(questionName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionName;
	}

	public void setQuestionName(String questionName) {
		try {
			this.questionName = EncryptionDecryption.encrypt(questionName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
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

	public String getQuestionHindi() {
		try {
			questionHindi = EncryptionDecryption.decrypt(questionHindi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionHindi;
	}

	public void setQuestionHindi(String questionHindi) {
		try {
			this.questionHindi = EncryptionDecryption.encrypt(questionHindi);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
