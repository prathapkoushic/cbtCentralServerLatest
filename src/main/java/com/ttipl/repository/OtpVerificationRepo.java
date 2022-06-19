package com.ttipl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ttipl.pojo.OtpVerification;

public interface OtpVerificationRepo extends CrudRepository<OtpVerification, Integer> {
	@Query(value = "SELECT * FROM otp_verification where exam_id=?1 and exam_loc_session_id=?2 and otp=?3", nativeQuery = true)
	OtpVerification findByExamStartFlag(int examId, int locationSessionId, String otp);

}
