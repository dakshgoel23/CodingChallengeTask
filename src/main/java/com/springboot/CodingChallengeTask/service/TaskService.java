package com.springboot.CodingChallengeTask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.CodingChallengeTask.exception.TaskNotFoundException;
import com.springboot.CodingChallengeTask.model.Task;
import com.springboot.CodingChallengeTask.repository.TaskRepository;


@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task insert(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> viewAll() {
		return taskRepository.findAll();
	}

	
    public Task validate(int id) throws TaskNotFoundException {
		
		Optional< Task> optional=taskRepository.findById(id);
		if(optional.isEmpty())
			throw new TaskNotFoundException("Task id invalid");
		
		 Task task = optional.get();
		return task;  
		
		
	}

	public void delete(int task_id) {
		taskRepository.deleteById(task_id);
		
	}

}
