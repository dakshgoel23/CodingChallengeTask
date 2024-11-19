package com.springboot.CodingChallengeTask.model;

import java.time.LocalDate;

import com.springboot.CodingChallengeTask.enums.Priority;
import com.springboot.CodingChallengeTask.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 
	 private String title;
	 
	 private String description;
	 
	 private LocalDate date;
	 
	 @Enumerated(EnumType.STRING)
	 private Priority priority;
	 
	 @Enumerated(EnumType.STRING)
	 private Status status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	 public Task(int id, String title, String description, LocalDate date, Priority priority, Status status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.priority = priority;
		this.status = status;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	 
	 
	 
	 

}
