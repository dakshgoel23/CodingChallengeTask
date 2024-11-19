package com.springboot.CodingChallengeTask;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.CodingChallengeTask.enums.Priority;
import com.springboot.CodingChallengeTask.enums.Status;
import com.springboot.CodingChallengeTask.exception.TaskNotFoundException;
import com.springboot.CodingChallengeTask.model.Task;
import com.springboot.CodingChallengeTask.repository.TaskRepository;
import com.springboot.CodingChallengeTask.service.TaskService;

@SpringBootTest
public class TaskServiceTest {
	
	@InjectMocks
	private TaskService taskService;
	
	@Mock
	private TaskRepository taskRepository;
	
	private Task task1;
	private Task task2;
	List<Task> list;
	
	@BeforeEach
	public void initsetup()
	{
		task1=new Task(55,"Update Website","Resolve the reported bugs in the latest software version.",LocalDate.parse("2024-11-30"),Priority.MEDIUM,Status.INPROGRESS);
		task2=new Task(53,"Team Meeting","Schedule and conduct a team meeting to discuss project",LocalDate.parse("2024-11-22"),Priority.HIGH,Status.COMPLETED);
	}
	
	@Test
	public void postTaskTest()
	{
				when(taskRepository.save(task1)).thenReturn(task1);
				
			
				Task newTask =   taskService.insert(task1); 
				
				//test and compare 
				assertNotNull(newTask);
				//assertEquals(task.getName(), newTask.getName());
				verify(taskRepository, times(1)).save(task1);
	}
	
	@Test
	public void viewTaskByIdTest() throws TaskNotFoundException
	{
		when(taskRepository.findById(53)).thenReturn(Optional.of(task2));
		
		Task task=taskService.validate(53);
		assertNotNull(task);
		
		assertThrows(TaskNotFoundException.class, () -> {
	        taskService.validate(56);  
	    });
		
	}
	

	
	

}
