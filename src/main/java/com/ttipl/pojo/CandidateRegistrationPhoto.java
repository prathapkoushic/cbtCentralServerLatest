package com.ttipl.pojo;

import javax.persistence.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CandidateRegistrationPhoto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "candidate_id", unique = true, nullable = false)
	private String candidateId;
	@Column(name = "image", columnDefinition = "LONGTEXT")
	private String image;
	@Column(name = "is_matched")
	private boolean Matched;
	private String remarks;
	private LocalDateTime login_time;

	public LocalDateTime getLogin_time() {
		return login_time;
	}

	public void setLogin_time(LocalDateTime login_time) {
		this.login_time = login_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isMatched() {
		return Matched;
	}

	public void setMatched(boolean matched) {
		Matched = matched;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
