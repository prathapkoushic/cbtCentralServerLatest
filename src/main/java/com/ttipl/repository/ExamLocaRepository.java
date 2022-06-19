package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.ExamLocationSess;

@Repository
public interface ExamLocaRepository extends CrudRepository<ExamLocationSess, Integer> {

	List<ExamLocationSess> findAll();

	@Query(value = "SELECT e  FROM ExamLocationSess e where e.examId=?1")

	List<ExamLocationSess> findByExamId(Integer examId);

	ExamLocationSess findById(int id);

	@Query(value = "SELECT ipaddress FROM exam_loc_session where exam_loc_session_id=?", nativeQuery = true)
	String getIdAddressBasedOnLocationSessionId(int locationSessionId);

	@Query("SELECT e FROM ExamLocationSess e where e.id=?1")
	ExamLocationSess finland(int givenID);

	@Query("SELECT e FROM ExamLocationSess e where e.examId=?1")
	List<Object[]> findLocs(int id);

	@Transactional
	int deleteByExamId(int id);

	@Query("SELECT e.id, e.location_name,e.address, e.start_time,e.end_time ,e.contactName,eb.exam_name,eb.exam_code FROM ExamLocationSess e inner join ExamBean eb on e.examId=eb.id")
	List<Object[]> findSessionWithExamName();

	@Query(value = "SELECT * FROM exam_loc_session where exam_id=?1 and start_time=?2 and end_time=?3", nativeQuery = true)
	List<ExamLocationSess> getDataBasedOnExamIdAndTimings(int examId, String startTime, String endTime);

	@Query(value = "SELECT * FROM exam_loc_session where user_name=?1 and password=?2", nativeQuery = true)
	ExamLocationSess findByUserNameAndPassword(String username, String password);

	@Transactional
	@Modifying
	@Query(value = "update exam_loc_session set exam_start_flag=?1,exam_start_flag_updated_time=?2 where exam_id=?3 and exam_loc_session_id=?4", nativeQuery = true)
	int updateExamStartFlag(boolean exam_start, String exam_start_Updated_time, int examId, int locationSessionId);

}
