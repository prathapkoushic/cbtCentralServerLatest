package com.ttipl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.DescriptiveImages;

@Repository
public interface DesImageRepo extends CrudRepository<DescriptiveImages, Integer>
{

	@Transactional
	@Modifying
	@Query(value="delete from DescriptiveImages where image_id=?1" , nativeQuery = true)
	void deleteByImageId(Integer question_image_id);

	@Transactional
	@Modifying
	@Query(value="delete from DescriptiveImages where image_id=?1" , nativeQuery = true)
	void deleteByImageId2(Integer question_image_id);

}
