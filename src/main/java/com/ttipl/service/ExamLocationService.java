package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.repository.ExamLocaRepository;

@Service
public class ExamLocationService {

	@Autowired
	private ExamLocaRepository examLocaRepository;

	public List<ExamLocationSess> findByExamId(int examId) {
		return examLocaRepository.findByExamId(examId);
	}

	public ExamLocationSess findById(int examId) {
		return examLocaRepository.findById(examId);
	}

	public void saveData(ExamLocationSess examLocationSess) {

		examLocaRepository.save(examLocationSess);
	}

	public List<ExamLocationSess> getData() {

		return (List<ExamLocationSess>) examLocaRepository.findAll();

	}

	public ExamLocationSess getDataForUpdate(int givenID) {
		return examLocaRepository.finland(givenID);
	}

	public void delete(int givenID) {
		examLocaRepository.deleteById(givenID);
	}

	public List<Object[]> getListOfLocations(int id) {
		return examLocaRepository.findLocs(id);

	}

	public void deleteByExamId(int examId) {
		examLocaRepository.deleteByExamId(examId);
	}

	public List<Object[]> findSessionWithExamName() {
		return examLocaRepository.findSessionWithExamName();
	}

	public ExamLocationSess findByUserNameAndPassword(String username, String password) {
		return examLocaRepository.findByUserNameAndPassword(username, password);
	}
}
