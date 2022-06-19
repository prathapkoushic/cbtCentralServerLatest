package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttipl.pojo.CandidateSeatDetails;
import com.ttipl.pojo.SeatingPlan;
import com.ttipl.repository.CandidateSeatDetailsRepository;
import com.ttipl.repository.SeatingPlanRepository;

@Service
public class SeatingPlanService {

	@Autowired
	private SeatingPlanRepository seatingPlanRepository;

	@Autowired
	private CandidateSeatDetailsRepository candidateSeatDetailsRepository;

	@Transactional
	public void save(List<SeatingPlan> seatingPlans) {
		seatingPlanRepository.saveAll(seatingPlans);
	}

	@Transactional
	public void saveList(List<CandidateSeatDetails> candidateSeatDetails) {
		candidateSeatDetailsRepository.saveAll(candidateSeatDetails);
	}

	@Transactional
	public void save(CandidateSeatDetails candidateSeatDetails) {
		candidateSeatDetailsRepository.save(candidateSeatDetails);
	}

	public CandidateSeatDetails findById(String candidateId) {
		return candidateSeatDetailsRepository.findSeatNumberBasedOnCandidateId(candidateId);
	}

	@Transactional
	public void deleteAll() {
		candidateSeatDetailsRepository.deleteAll();
		seatingPlanRepository.deleteAll();
	}

	public List<CandidateSeatDetails> findAllCandidateSeatDetails() {
		return candidateSeatDetailsRepository.findAll();
	}
	public List<CandidateSeatDetails> findAllCandidateSeatDetailsByRoomName(String roomName) {
		return candidateSeatDetailsRepository.findByRoomNameOrderByCandidateIdAsc(roomName);
	}
	public String[] findDistinctRoomName() {
		return candidateSeatDetailsRepository.findDistinctRoomName();}

	public Iterable<SeatingPlan> findAllSeatingPlans() 
	{
		return seatingPlanRepository.findAll();		
	}

	public List<SeatingPlan> findSeatPlanGroup() 
	{
		return seatingPlanRepository.findAllByGroup();
		
	}

	public List<SeatingPlan> findSeatsByRoom(String roomName) 
	{
		return seatingPlanRepository.findByRoom(roomName);		
	}

	public List<CandidateSeatDetails> findAllCandidateS()
	{
		return candidateSeatDetailsRepository.findAll();
	}
	
}
