package com.ttipl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ttipl.pojo.PullCountOfLocationServer;

public interface PullCountOfLocationServerRepo extends CrudRepository<PullCountOfLocationServer, Integer> {
	@Query(value = "SELECT * FROM pull_count_of_location_server where exam_id=?1 and session_id=?2", nativeQuery = true)
	PullCountOfLocationServer findPullCountBasedOnExamIdAndLocationSessionId(int examId, int locationSessionId);

	@Modifying
	@Transactional
	@Query(value = "update pull_count_of_location_server set candidate_count=?1 where exam_id=?2 and session_id=?3", nativeQuery = true)
	void updateCountOfPull(int count, int examId, int locationSessionId);

	@Modifying
	@Transactional
	@Query(value = "update pull_count_of_location_server set questions_count=?1 where exam_id=?2 and session_id=?3", nativeQuery = true)

	void updateCountOfQuestions(int count1, int examId, int locationSessionId);

}
