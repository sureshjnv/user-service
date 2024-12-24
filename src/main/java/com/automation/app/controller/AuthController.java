package com.automation.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automation.app.entity.LoginRequest;
import com.automation.app.entity.UserRegisterDto;
import com.automation.app.service.UserPriciple;
import com.automation.app.service.UserService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User authenticated successfully";
    }
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRegisterDto dto){
		return userService.register(dto);
	}
	  @GetMapping("user/details")
	    public UserPriciple getUserDetails() {
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        System.out.println(principal);
	        if (principal instanceof UserPriciple) {
	            return (UserPriciple) principal;
	        }
	        throw new RuntimeException("User is not authenticated");
	 }
	
	
	

}
