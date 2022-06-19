package com.ttipl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Candidate_login;

@Repository
public interface Candidate_loginRepo extends CrudRepository<Candidate_login, Integer> {

	@Query(value = "SELECT count(distinct cl.candidate_id) FROM candidate_login as cl inner join candidate c on c.candidate_id=cl.candidate_id where c.exam_id=?1 and c.exam_loc_session_id=?2", nativeQuery = true)
	int candidateLoginCount(int examId, int locationSessionId);

}
