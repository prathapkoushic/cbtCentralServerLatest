package com.ttipl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false, unique = true)
	private Integer postId;
	
	@Column(name = "post_name ")
	private String post_name;

	@Column(name = "post_description", columnDefinition = "longtext")
	private String post_description;

	@Column(name = "post_qualifications", columnDefinition = "longtext")
	private String post_qualifications;

	@Column(name = "no_of_vacancies")
	private Integer no_of_vacancies;

	@Column(name = "post_category_id", nullable = false)
	private Integer postCategoryId;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getPost_description() {
		return post_description;
	}

	public void setPost_description(String post_description) {
		this.post_description = post_description;
	}

	public String getPost_qualifications() {
		return post_qualifications;
	}

	public void setPost_qualifications(String post_qualifications) {
		this.post_qualifications = post_qualifications;
	}

	public Integer getNo_of_vacancies() {
		return no_of_vacancies;
	}

	public void setNo_of_vacancies(Integer no_of_vacancies) {
		this.no_of_vacancies = no_of_vacancies;
	}

	public Integer getPostCategoryId() {
		return postCategoryId;
	}

	public void setPostCategoryId(Integer postCategoryId) {
		this.postCategoryId = postCategoryId;
	}

}
