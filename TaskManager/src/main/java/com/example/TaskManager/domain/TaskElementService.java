package com.example.TaskManager.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskElementService {
	
	@Autowired
	private TaskElementRepository rep;
	
	/*
	 * 
	 */
	public List<TaskElement> getTaskElements(){
		return StreamSupport.stream(rep.findAll().spliterator(), false) 
	            .collect(Collectors.toList()); 
	}
	
	/*
	 * 
	 */
	public TaskElement save(TaskElement pTaskElement) {
		pTaskElement.setTaskDone("NO");
		return rep.save(pTaskElement);
	}
	
	/*
	 * 
	 */
	public Optional<TaskElement> getTaskElementById(Long id) {
		return rep.findById(id);
	}
	
	/*
	 * 
	 */
	public void delete(Long pId) {
		Optional<TaskElement> taskElement = getTaskElementById(pId);
		if(taskElement.isPresent()) {
			rep.deleteById(pId);
		}else {
			throw new RuntimeException("TaskElement not be found");
		}
	}
	
	/*
	 * 
	 */
	public List<TaskElement> getTaskElemensByTaskList(String pIdTaskList) {
		if(pIdTaskList.equals("undefined")) {
			return this.getTaskElements();
		}else {
			TaskList tl = new TaskList();
			tl.setId(Long.parseLong(pIdTaskList));
			return rep.findByTasklist(tl);
		}
	}

	/*
	 *  This method Switchs the TASK complete status between 'YES' and 'NO'
	 *  meaning its complete or not
	 */
	public void switchTaskStatus(Long pId) {
		Optional<TaskElement> taskElement = getTaskElementById(pId);
		if(taskElement.isPresent()) {
			
			String status = taskElement.get().getTaskDone();
			
			//For some reason i couldn't get boolean to show on FrontEnd's GRID in time...
			//So i used String =/
			
			taskElement.get().setTaskDone(status.equals("YES") ? "NO" : "YES");
			rep.save(taskElement.get());
		}else {
			throw new RuntimeException("Task could not be found");
		}
	}
	
}
