package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.CandidateSeatDetails;

@Repository
public interface CandidateSeatDetailsRepository extends CrudRepository<CandidateSeatDetails, Integer> {

	List<CandidateSeatDetails> findAll();

	@Modifying
	@Transactional
	@Query(value = "update candidate_seat_details set ipaddress=?1 where candidate_id=?2", nativeQuery = true)
	int updateNewIpToCandidate(String newIp, String candidateID);
	
	//@Modifying
	//@Transactional
	//@Query(value = "update iplogin set ipaddress=?1 where candidate_id=?2", nativeQuery = true)
	//int updateNewIpToCandidate(String newIp, String candidateID);

	@Query(value = "SELECT * FROM candidate_seat_details where candidate_id=?1", nativeQuery = true)
	CandidateSeatDetails findSeatNumberBasedOnCandidateId(String candidateID);

	@Query(value = "SELECT Distinct csd.roomName  FROM CandidateSeatDetails csd ")
	String[] findDistinctRoomName();

	List<CandidateSeatDetails> findByRoomNameOrderByCandidateIdAsc(String roomName);
}
