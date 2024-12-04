package com.automation.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.automation.app.dao.UserDao;
import com.automation.app.entity.User;
import com.automation.app.entity.UserRegisterDto;

@Service
public class UserService {
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	UserDao userDao;

	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userDao.findAll();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}

	public ResponseEntity<User> getUser(Integer id) {
		User user = userDao.findById(id).get();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	public ResponseEntity<String> updateUser(User user) {
		userDao.save(user);
		return new ResponseEntity<>("User Details Updated",HttpStatus.OK);
	}

	public ResponseEntity<String> deleteUser(Integer id) {
		userDao.deleteById(id);
		return new ResponseEntity<>("User Deleted",HttpStatus.OK);
	}

	public ResponseEntity<String> register(UserRegisterDto dto) {
		
        String hashedPassword = encoder.encode(dto.getPassword());

		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(hashedPassword);
		user.setRole("Customer");
		user.setStatus("Active");
		userDao.save(user);
		return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
	}

	public String getUserStatus(Integer id) {
		
		return userDao.findById(id).get().getStatus();
		
	}

}
