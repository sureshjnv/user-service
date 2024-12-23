package com.automation.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automation.app.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	public User findByName(String username);

}
