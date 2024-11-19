package com.springboot.CodingChallengeTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.CodingChallengeTask.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
