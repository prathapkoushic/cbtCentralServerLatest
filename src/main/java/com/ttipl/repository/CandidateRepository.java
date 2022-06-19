package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Candidate;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
	@Query(value = "select c from Candidate c where c.exam_loc_session_id=?1")
	List<Candidate> findBylocationName(int id);
	
	

	@Query(value = "select c from Candidate c where c.candidate_id=?1")
	Candidate findBycandidateId(String id);

	@Query("SELECT c.candidate_id , c.candidate_first_name , e.exam_name , s.location_name , p.post_name FROM Candidate c INNER JOIN com.ttipl.pojo.ExamLocationSess s ON c.exam_loc_session_id = s.id INNER JOIN com.ttipl.pojo.ExamBean e ON c.exam_id = e.id INNER JOIN com.ttipl.pojo.Post p ON e.postId = p.postId")
	List<Object[]> getSomething();

	@Query("SELECT c FROM Candidate c where c.candidate_id=?1")
	Candidate findById(int id);

	@Modifying
	@Transactional
	@Query("DELETE  FROM Candidate c where c.candidate_id=?1")
	void deleteCandRecord(int id);

	@Modifying
	@Transactional
	@Query("DELETE  FROM Candidate c where c.exam_loc_session_id=?1")
	int deleteCandRecordsByExamLoc_session(int id);

	@Modifying
	@Transactional
	@Query(value = "update candidate set exam_is_end=?1 , login_status=?2 where candidate_id=?3 and end_status="+false+"", nativeQuery = true)
	int updateExamIsEnd(boolean b,boolean login, String id);

	@Query(value = "SELECT * FROM candidate where exam_id=?1 and exam_loc_session_id=?2", nativeQuery = true)
	List<Candidate> findByExamIdAndLocationSession(int examId, int examLocationSessionId);

	@Query("SELECT c.candidate_id , c.candidate_first_name , e.exam_name , s.location_name , p.post_name FROM Candidate c INNER JOIN com.ttipl.pojo.ExamLocationSess s ON c.exam_loc_session_id = s.id INNER JOIN com.ttipl.pojo.ExamBean e ON c.exam_id = e.id INNER JOIN com.ttipl.pojo.Post p ON e.postId = p.postId where e.id=?1 and s.id=?2")
	List<Object[]> getCandidateByExam_idAndExamSession_Id(int examId, int sessionId);

	@Query(value = "SELECT * FROM candidate where exam_id=?1 and exam_loc_session_id=?2", nativeQuery = true)
	List<Candidate> findByExamIdandLocationId(int examId, int locationSessionId);

	@Query(value = "SELECT * FROM candidate where exam_id=?1 and exam_loc_session_id=?2", nativeQuery = true)
	List<Candidate> getCounPulledCandidates(int examId, int exam_loc_session_id);

	@Query(value = "SELECT count(*) FROM candidate where exam_id=?1 and exam_loc_session_id=?2", nativeQuery = true)
	int LocalCanidateCount(int examId, int exam_loc_session_id);


	@Query(value = "SELECT * FROM candidate where  candidate_id=?1", nativeQuery = true)
	Candidate findIsExamEnd(String candidate_id);
}