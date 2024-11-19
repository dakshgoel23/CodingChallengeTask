package com.springboot.CodingChallengeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.CodingChallengeTask.dto.ResponseMessageDto;
import com.springboot.CodingChallengeTask.exception.TaskNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	private ResponseMessageDto dto; 
	
	
	@ExceptionHandler(TaskNotFoundException.class)
	ResponseEntity<?> handleResourceNotFoundException(Exception e){
		 dto.setMsg(e.getMessage());
		 return ResponseEntity.badRequest().body(dto);
	}
}