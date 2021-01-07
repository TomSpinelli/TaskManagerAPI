package com.example.TaskManager.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskListService {
	
	@Autowired
	private TaskListRepository rep;
	
	/*
	 * 
	 */
	public List<TaskList> getTaskLists(){
		return StreamSupport.stream(rep.findAll().spliterator(), false) 
	            .collect(Collectors.toList()); 
	}
	
	/*
	 * 
	 */
	public TaskList save(TaskList pTaskList) {
		return rep.save(pTaskList);
	}
	
	/*
	 * 
	 */
	public void delete(Long pId) {
		Optional<TaskList> taskList = getTaskListById(pId);
		if(taskList.isPresent()) {
			rep.deleteById(pId);
		}else {
			throw new RuntimeException("Task not be found");
		}
	}
	
	/*
	 * 
	 */
	public Optional<TaskList> getTaskListById(Long pId) {
		return rep.findById(pId);
	}
	
}
