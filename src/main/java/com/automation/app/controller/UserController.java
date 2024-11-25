package com.automation.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.automation.app.entity.User;
import com.automation.app.entity.UserRegisterDto;
import com.automation.app.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("users/register")
	public ResponseEntity<String> register(@RequestBody UserRegisterDto dto){
		return userService.register(dto);
	}
	
	
	@GetMapping("users")
	public ResponseEntity<List<User>> getUsers(){
		
		return userService.getAllUsers();
		
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id){
		
		return userService.getUser(id);
		
	}
	
	@PutMapping("users")
	public ResponseEntity<String> updateUser(@RequestBody User User){
		
		return userService.updateUser(User);
		
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id){
		
		return userService.deleteUser(id);
		
	}


}
