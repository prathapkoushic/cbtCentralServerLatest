package com.ttipl.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "descriptive_question_languages")
public class DescriptiveQuestionLanguages implements Serializable {

	private static final long serialVersionUID = -5466869496753242418L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_lang_id", unique = true, nullable = false)
	private Integer questionLangId;
	@Column(name = "language")
	private String language;
	@Column(name = "desquestion_id")
	private Integer desquestion_id;
	@Column(name = "question_image_id")
	private Integer question_image_id =null;
	@Column(name = "question",columnDefinition = "LONGTEXT")
	private String question;
}
