package com.ttipl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ttipl.pojo.LoginBean;

public interface LoginRepo extends CrudRepository<LoginBean, Integer> {

	@Override
	List<LoginBean> findAll();

	@Query(value="select * from login where username=?1 and password=?2" , nativeQuery = true)
	List<LoginBean> findByUsernameAndPassword(String username, String password);
}
