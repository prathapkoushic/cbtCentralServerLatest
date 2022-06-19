package com.ttipl.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ttil.encryption.EncryptionDecryption;

@Entity
@Table(name = "answer_option")
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_option_id")
	private int optionId;
	@Column(name = "answer_option", length = 65535, columnDefinition = "LONGTEXT")
	private String option;
	@Column(name = "answer_option_hindi", length = 65535, columnDefinition = "LONGTEXT")
	private String optionHindi;
	@JoinColumn(name = "question_id")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Question.class)
	private Question Question;
	@Column(name = "option_order")
	private int optionOrder;
	@Column(name = "is_answer")
	private boolean answer;
	@Column(name = "english_image_id")
	private Integer englishImageId=null;
	@Column(name = "hindi_image_id")
	private Integer hindiImageId =null;

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

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOption() {
		try {
			option = EncryptionDecryption.decrypt(option);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public void setOption(String option) {
		try {
			this.option = EncryptionDecryption.encrypt(option);
		} catch (Exception e) {
			this.option = option;
			e.printStackTrace();
		}
	}

	public Question getQuestion() {
		return Question;
	}

	public void setQuestion(Question question) {
		Question = question;
	}

	public int getOptionOrder() {
		return optionOrder;
	}

	public void setOptionOrder(int optionOrder) {
		this.optionOrder = optionOrder;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public String getOptionHindi() {
		try {
			optionHindi = EncryptionDecryption.decrypt(optionHindi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return optionHindi;
	}

	public void setOptionHindi(String optionHindi) {

		try {
			this.optionHindi = EncryptionDecryption.encrypt(optionHindi);
		} catch (Exception e) {
			this.optionHindi = optionHindi;
			e.printStackTrace();
		}

	}

}
