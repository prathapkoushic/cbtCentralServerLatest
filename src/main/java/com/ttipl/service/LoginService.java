package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.pojo.LoginBean;
import com.ttipl.repository.LoginRepo;

@Service
public class LoginService {
	@Autowired
	private LoginRepo loginRepository;

	public void insert(LoginBean login) {
		loginRepository.save(login);

	}

	public List<LoginBean> get() {
		return (List<LoginBean>) loginRepository.findAll();
	}

	public List<LoginBean> getdata(String username, String password) {
		return loginRepository.findByUsernameAndPassword(username, password);
	}

}
