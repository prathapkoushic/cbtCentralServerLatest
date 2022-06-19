package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.DescriptiveQuestion;

@Repository
public interface DescriptiveQuestionRepo extends CrudRepository<DescriptiveQuestion, Integer>
{
	 @Query(value = "SELECT count(*) FROM descriptive_question where exam_id=?1 and exam_loc_session_id=?2", nativeQuery = true)
	 int LocalQuestionCount(int examId, int exam_loc_session_id);

	 @Query(value = "SELECT DISTINCT * FROM descriptive_question where exam_id=?1 and exam_loc_session_id=?2 and level='EASY' ORDER BY RAND() LIMIT ?3", nativeQuery = true)
	 Iterable<DescriptiveQuestion> findAllByExamIdAndLocationId1(int examId, int locationSessionId, int i);
	 
	 @Query(value = "SELECT DISTINCT * FROM descriptive_question where exam_id=?1 and exam_loc_session_id=?2 and level='MEDIUM' ORDER BY RAND() LIMIT ?3", nativeQuery = true)
	 Iterable<DescriptiveQuestion> findAllByExamIdAndLocationId2(int examId, int locationSessionId, int i);
	 
	 @Query(value = "SELECT DISTINCT * FROM descriptive_question where exam_id=?1 and exam_loc_session_id=?2 and level='DIFFICULT' ORDER BY RAND() LIMIT ?3", nativeQuery = true)
	 Iterable<DescriptiveQuestion> findAllByExamIdAndLocationId3(int examId, int locationSessionId, int i);

	 @Query(value = "SELECT * FROM descriptive_question where exam_id=?1", nativeQuery = true)
	 List<DescriptiveQuestion> findAllByExam(int examId);
 
	 @Transactional
	 @Modifying
	 @Query(value = "DELETE FROM descriptive_question where exam_id=?1", nativeQuery = true)
	  void deleteByExamId(int examId);

		/*
		 * @Transactional
		 * 
		 * @Modifying
		 * 
		 * @Query(value =
		 * "DELETE FROM descriptive_question where exam_id=?1 and exam_loc_session_id=?2"
		 * , nativeQuery = true) void deleteAllByExam(int examId, int
		 * locationSessionId);
		 */
	 
		/*
		 * @Transactional
		 * 
		 * @Modifying
		 * 
		 * @Query(value =
		 * "INSERT into descriptive_question(`desquestion_id`,`exam_id`,`exam_loc_session_id`,`question`,`question_no`,`question_type`,"
		 * + "`question_paper_code`,`status`) values(?1,?2,?3,?4,?5,?6,?7,?8)",
		 * nativeQuery = true) void saveAllWithx(Integer integer, Integer integer2,
		 * Integer integer4, String string, Integer integer3, String string2, String
		 * string3, Boolean boolean1);
		 */
}
