package com.ttipl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.DescriptiveQuestionLanguages;

@Repository
public interface QuestionLangRepo extends CrudRepository<DescriptiveQuestionLanguages, Integer>
{

	@Query(value="SELECT * from descriptive_question_languages where desquestion_id=?1" , nativeQuery = true)
	DescriptiveQuestionLanguages findAllByDesquestionId(Integer desquestion_id);

	@Query(value="SELECT * from descriptive_question_languages where desquestion_id=?1" , nativeQuery = true)
	DescriptiveQuestionLanguages findByDesId(Integer desquestion_id);

	@Transactional
	@Modifying
	@Query(value="DELETE  from descriptive_question_languages where desquestion_id=?1" , nativeQuery = true)
	void deleteByDesquestionId(Integer desquestion_id);
}
