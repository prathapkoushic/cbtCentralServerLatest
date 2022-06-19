package com.ttipl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Options;


@Repository
public interface AnswerOptionRepo extends CrudRepository<Options, Integer> {
	@Query(value="SELECT * FROM answer_option u where u.questionId=?1",nativeQuery=true)
	List<Options> findRow(int questionId);
}
