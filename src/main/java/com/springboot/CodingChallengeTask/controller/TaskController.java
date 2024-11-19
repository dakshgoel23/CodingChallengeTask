package com.springboot.CodingChallengeTask.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.CodingChallengeTask.dto.ResponseMessageDto;
import com.springboot.CodingChallengeTask.exception.TaskNotFoundException;
import com.springboot.CodingChallengeTask.model.Task;
import com.springboot.CodingChallengeTask.service.TaskService;


@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping("/task/add")
	public Task addTask(@RequestBody Task task) {
		
		return taskService.insert(task);
	}
	
	@GetMapping("/task/all")
	public List<Task> viewAllTask()
	{
		return taskService.viewAll();
	}
	
	@GetMapping("/task/byId")
	public ResponseEntity<?> viewTaskById(@RequestParam int task_id,ResponseMessageDto dto) throws TaskNotFoundException
	{
		Task task=null;
		task=taskService.validate(task_id);
		return ResponseEntity.ok(task);
	}
	
	@DeleteMapping("/task/delete")
	public ResponseEntity<?> deletebyId(@RequestParam int task_id,ResponseMessageDto dto) throws TaskNotFoundException
	{
		
	    taskService.validate(task_id);
		taskService.delete(task_id);
		dto.setMsg("Task is Deleted");
		return ResponseEntity.ok(dto);
		
	}
	
	@PutMapping("/task/update")
	public ResponseEntity<?> updateTask(@RequestParam int task_id,
			@RequestBody Task  newTask,ResponseMessageDto dto) throws TaskNotFoundException
	{
		    Task oldTask=null;
			oldTask=taskService.validate(task_id);
			if(newTask.getTitle()!=null)
			{
				oldTask.setTitle(newTask.getTitle());
			}
			if(newTask.getDescription()!=null)
			{
				oldTask.setDescription(newTask.getDescription());
			}
			if(newTask.getDueDate()!=null)
			{
				oldTask.setDueDate(newTask.getDueDate());
			}
			if(newTask.getPriority()!=null)
			{
				oldTask.setPriority(newTask.getPriority());
			}
			if(newTask.getStatus()!=null)
			{
				oldTask.setStatus(newTask.getStatus());
			}
			
			oldTask=taskService.insert(oldTask);
			return ResponseEntity.ok(oldTask);
	}
	


}
