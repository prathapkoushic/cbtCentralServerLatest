package com.ttipl.repository;

import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.CandidateQueAns;

@Repository
public interface CandidateQueAnsRepo extends CrudRepository<CandidateQueAns, Integer> {
	@Query(value = "select concat(c.candidate_first_name,c.candidate_last_name)as candidate_name,c.dob,c.candidate_id,q.question, "
			+ " IFNULL(ao.answer_option,'N.A')as answer_option,case when ao.is_answer =0 then '0'  when ao.is_answer =1 then '1' else 'N.A' end as is_answer,IFNULL(ao1.ANSWER_OPTION,'N.A') as correctanswer "
			+ " from candidate_qa cqa " + " left join question q on q.question_id=cqa.question_id "
			+ " left join answer_option ao1 on ao1.question_id=cqa.question_id and ao1.is_answer=1 "
			+ " left join answer_option ao on ao.answer_option_id=cqa.answer_option_id "
			+ " inner join candidate c on c.candidate_id=cqa.candidate_id "
			+ " where c.candidate_id=?1", nativeQuery = true)
	public List<Object[]> findcandidateQuestionsReport(int id);

	@Query(value = "select concat(c.candidate_first_name,c.candidate_last_name)as candidate_name,c.dob,c.candidate_id,q.question,cqa.remaining_time,cqa.date_time\r\n"
			+ "			 " + "			from candidate_qa cqa  left join question q on q.question_id=cqa.question_id "
			+ "			" + "			 inner join candidate c on c.candidate_id=cqa.candidate_id"
			+ "			where c.candidate_id=?1", nativeQuery = true)
	public List<Object[]> findStatisticsReport(int id);

	@Query(value = " select cqa.question_id,IFNULL(cqa.answer_option_id,'N.A')as answer_option_id,\r\n"
			+ "cqa.candidate_id,case when ao.is_answer =0 then '0'  when ao.is_answer =1 then '1' else 'N.A' end as is_answer,\r\n"
			+ "concat(c.candidate_first_name,'',c.candidate_last_name)as candidate_name,concat(els.start_time,'-',els.end_time) as session, "
			+ "e.marks_per_question,e.negativemarks_per_question,IFNULL(els.location_name,'N.A') as location_name ,c.community,c.dob, ifnull(ao.option_order ,'N.A') as option_answer ,c.exam_id   from candidate_qa cqa  "
			+ "left join answer_option ao on cqa.answer_option_id=ao.answer_option_id "
			+ "inner join candidate c on c.candidate_id=cqa.candidate_id "
			+ "left join question q on q.question_id=cqa.question_id "
			+ "inner join exam_loc_session els on els.exam_loc_session_id=c.exam_loc_session_id "
			+ "inner join exam e on e.exam_id=els.exam_id ", nativeQuery = true)
	public List<Object[]> findCandidateReports();

	@Query(value = " select cqa.question_id,IFNULL(cqa.answer_option_id,'N.A')as answer_option_id,"
			+ "cqa.candidate_id,case when ao.is_answer =0 then '0'  when ao.is_answer =1 then '1' else 'N.A' end as is_answer,"
			+ "concat(c.candidate_first_name,'',c.candidate_last_name)as candidate_name,concat(els.start_time,'-',els.end_time) as session,  "
			+ "e.marks_per_question,e.negativemarks_per_question,IFNULL(els.location_name,'N.A') as location_name  ,c.community,c.dob, ifnull(ao.option_order ,'N.A') as option_answer ,c.exam_id    from candidate_qa cqa "
			+ "left join answer_option ao on cqa.answer_option_id=ao.answer_option_id "
			+ "inner join candidate c on c.candidate_id=cqa.candidate_id "
			+ "left join question q on q.question_id=cqa.question_id "
			+ "inner join exam_loc_session els on els.exam_loc_session_id=c.exam_loc_session_id "
			+ "inner join exam e on e.exam_id=els.exam_id where cqa.candidate_id= ?1", nativeQuery = true)
	public List<Object[]> findCandidateReportsById(String id);

	@Query(value = "select c.candidate_id,c.candidate_first_name as candidate_name,p.post_name, \r\n"
			+ "c.dob,ifnull(c.community,'N.A') as community,e.exam_code,concat(els.location_name,'-',els.session_date_time) as loaction_session\r\n"
			+ "from candidate c, exam e, exam_loc_session els,post p where e.post_id=p.post_id and c.exam_loc_session_id= els.exam_loc_session_id\r\n"
			+ "and e.exam_id=els.exam_id and els.exam_loc_session_id=?1 and ifnull(c.community,'N.A') like ?2 "
			+ "order by (c.candidate_id+0)", nativeQuery = true)
	public List<Object[]> findCandidatesByExamSessionIdAndComminuty(int exam_session_id, String community);

	@Query(value = "select cq.candidate_id,cq.question_id,ifNULL(ap.option_order,0) as answer_option,case when is_answer =0 then '0'  when is_answer =1 then '1' else 'N.A' end as is_answer,\r\n"
			+ " e.marks_per_question, e.negativemarks_per_question \r\n"
			+ " from candidate_qa cq inner join question q on q.question_id=cq.question_id \r\n"
			+ " inner join exam_loc_session els on els.exam_loc_session_id=q.exam_loc_session_id \r\n"
			+ " inner join exam e on e.exam_id= els.exam_id \r\n"
			+ " inner join candidate c on c.candidate_id= cq.candidate_id \r\n"
			+ " left join answer_option as ap on ap.answer_option_id=cq.answer_option_id and els.exam_loc_session_id=?1 and ifnull(c.community,'N.A') like ?2", nativeQuery = true)
	public List<Object[]> findCandidateResultByExamSessionIdAndComminuty(int exam_session_id, String community);

	@Transactional
	@Modifying
	@Query(value = " delete from candidate_qa where candidate_id=?", nativeQuery = true)
	public int deleteByCandidateId(String id);

	@Query(value = "SELECT count(*) FROM candidate_qa where answer_option_id is not null and  candidate_id=?1", nativeQuery = true)
	public int findAnsweredCount(String candidate_id);

	@Query(value = "SELECT min(remaining_time) FROM candidate_qa where candidate_id=?1", nativeQuery = true)
	public Time findRemainingTime(String candidate_id);

	//@Query(value = "SELECT ipaddress FROM candidate_seat_details where candidate_id=?1", nativeQuery = true)
	//public String findAssignedIpAddress(String candidate_id);
	
	@Query(value = "SELECT ipAddress , max(loginTime) FROM iplogin where candidate_id=?1", nativeQuery = true)
	public String[] findAssignedIpAddress(String candidate_id);

	@Query(value = "SELECT count(*) FROM candidate_qa where candidate_id=?1", nativeQuery = true)
	public int findByCandidateQA(String candidate_id);

	@Query(value = "SELECT count(DISTINCT candidate_id) FROM candidate_qa WHERE candidate_id in(select candidate_id from candidate where exam_id=?1)", nativeQuery = true)
	public int getCanidateCount(int examId);

	@Transactional
	@Modifying
	@Query(value = " update candidate_qa set flag = ?1 where candidate_id=?2", nativeQuery = true)
	public void updateFlagBasedOnCandidateId(boolean b, String cd_id);
	@Query(value = "SELECT count(*) from candidate_qa where candidate_id in (SELECT candidate_id from candidate where exam_id=? AND exam_loc_session_id=?) ", nativeQuery = true)
	Integer getRecord(int examId, int locationSessionId);
}
