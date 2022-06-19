package com.ttipl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.ttipl.pojo.DescriptiveRandomizeQuestions;

@Repository
public interface DescriptiveRandomRepo extends CrudRepository<DescriptiveRandomizeQuestions, Integer>
{
	@Query(value="select count(*) from DescriptiveRandomizeQuestions where exam_id=?1")
    int findAll(int examId);

	@Modifying
	@Transactional
	@Query(value="delete from DescriptiveRandomizeQuestions where exam_id=?1")
	void deleteByExam(int examId);
}
