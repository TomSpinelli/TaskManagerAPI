package com.example.TaskManager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskElementRepository extends CrudRepository<TaskElement, Long>{
	List<TaskElement> findByTasklist(Object object);

 }
