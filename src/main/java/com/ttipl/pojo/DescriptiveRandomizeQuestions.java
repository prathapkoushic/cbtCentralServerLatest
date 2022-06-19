package com.ttipl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DescriptiveRandomizeQuestions 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    
    @Column(columnDefinition = "Longtext")
    private String englishQuestion;
    @Column(columnDefinition = "Longtext")
    private String hindiQuestion;
	private Integer exam_id;
	private Integer exam_loc_session_id;
	@Column(name = "desquestion_id")
	private Integer desquestion_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEnglishQuestion() {
		return englishQuestion;
	}
	public void setEnglishQuestion(String englishQuestion) {
		this.englishQuestion = englishQuestion;
	}
	public String getHindiQuestion() {
		return hindiQuestion;
	}
	public void setHindiQuestion(String hindiQuestion) {
		this.hindiQuestion = hindiQuestion;
	}
	public Integer getExam_id() {
		return exam_id;
	}
	public void setExam_id(Integer exam_id) {
		this.exam_id = exam_id;
	}
	public Integer getExam_loc_session_id() {
		return exam_loc_session_id;
	}
	public void setExam_loc_session_id(Integer exam_loc_session_id) {
		this.exam_loc_session_id = exam_loc_session_id;
	}
	public Integer getDesquestion_id() {
		return desquestion_id;
	}
	public void setDesquestion_id(Integer desquestion_id) {
		this.desquestion_id = desquestion_id;
	}
	
	
	
}
