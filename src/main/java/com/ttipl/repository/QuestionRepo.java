package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Question;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer> {

	Question findByQuestionId(int id);

	List<Question> findAll();

	@Query(value = "SELECT question_id FROM question where exam_id=?", nativeQuery = true)
	int[] findQuestionIdBasedOnExamIdAndLocationSessionId(int examId);

	@Modifying
	@Transactional
	@Query(value = "update question set exam_loc_session_id=?1 where exam_id=?2 and question_paper_code=?3", nativeQuery = true)
	int updateExamLocationSession(int locationSessionId, int examId, String setNo);

	@Modifying
	@Transactional
	@Query(value = "delete from question where exam_loc_session_id=?1 ", nativeQuery = true)
	int deleteQuestionByexamLocSessionId(int locationSessionId);

	 @Query(value = "SELECT * FROM question where exam_id=?1 and exam_loc_session_id=?2", nativeQuery = true)
		List<Question> getCounPulledCandidates(int examId, int exam_loc_session_id);
	 @Query(value = "SELECT count(*) FROM question where exam_id=?1 and exam_loc_session_id=?2", nativeQuery = true)
		int LocalQuestionCount(int examId, int exam_loc_session_id);
}
