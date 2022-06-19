package com.ttipl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ttipl.pojo.CandidateRegistrationPhoto;

public interface CandidateRegistrationPhotoRepo extends CrudRepository<CandidateRegistrationPhoto, Integer>
{
	@Query(value = "Select * from candidate_registration_photo  where candidate_id=?1", nativeQuery = true)
	List<CandidateRegistrationPhoto> findPhotoByCandidateId(String candidateId);

	@Query(value = "\r\n" + 
			"select c.candidate_id  ,concat(c.candidate_first_name,c.candidate_last_name) as fullName,  c.candidate_photo,cgp.image,cgp.is_matched,cgp.remarks,count(cgp.candidate_id) as count from candidate c inner join candidate_registration_photo cgp on c.candidate_id=cgp.candidate_id where cgp.is_matched=false group by cgp.candidate_id;;", nativeQuery = true)
	List<Object[]> findAllPhotos();
	
	@Query(value = "select c.candidate_id  ,concat(c.candidate_first_name,c.candidate_last_name) as fullName,  c.candidate_photo,cgp.image,cgp.is_matched,cgp.remarks from candidate c inner join candidate_registration_photo cgp on c.candidate_id=cgp.candidate_id where c.candidate_id=?1 order by is_matched;", nativeQuery = true)
	List<Object[]> findCandidatePhotos(String id);
	
	@Query(value="SELECT candidate_id ,login_time FROM candidate_registration_photo  group by candidate_id order by login_time",nativeQuery=true)
     List<Object[]> findCandidatesLogintime();
	
	//@Query(value="SELECT candidate_id ,loginTime FROM iplogin  group by candidate_id order by loginTime",nativeQuery=true)
	//List<Object[]> findCandidatesLogintime();
	
	

}
