package com.springboot.CodingChallengeTask.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.CodingChallengeTask.JwtUtil;
import com.springboot.CodingChallengeTask.dto.JwtDto;
import com.springboot.CodingChallengeTask.dto.ResponseMessageDto;
import com.springboot.CodingChallengeTask.exception.InvalidUsernameException;
import com.springboot.CodingChallengeTask.model.User;
import com.springboot.CodingChallengeTask.service.UserSecurityService;
import com.springboot.CodingChallengeTask.service.UserService;



@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/api/token")
	public ResponseEntity<?> getToken(@RequestBody User user, JwtDto dto ) {
		try {
		Authentication auth 
				= new UsernamePasswordAuthenticationToken
							(user.getUsername(), user.getPassword());
		
		authenticationManager.authenticate(auth);
		
		/*Check if username is in DB */
		user = (User) userSecurityService.loadUserByUsername(user.getUsername());
		
		String jwt = jwtUtil.generateToken(user.getUsername());
		dto.setUsername(user.getUsername());
		dto.setToken(jwt);
		return ResponseEntity.ok(dto);
		}
		catch(AuthenticationException ae) {
			return ResponseEntity.badRequest().body(ae.getMessage());
		}

	}

	
	@PostMapping("/auth/sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user,ResponseMessageDto dto){
		try {
			return ResponseEntity.ok(userService.signUp(user));
		} catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
	}
	

}