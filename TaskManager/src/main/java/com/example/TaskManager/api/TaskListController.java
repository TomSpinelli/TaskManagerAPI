package com.example.TaskManager.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskManager.domain.TaskList;
import com.example.TaskManager.domain.TaskListService;

@RestController
@RequestMapping("/api/v1/tasklist")
public class TaskListController {
	
	@Autowired //Dependency Injection
	private TaskListService service;
	
	/*
	 * 
	 */
	@GetMapping("/all")
	public ResponseEntity<List<TaskList>> getTaskLists() {
		
		List<TaskList> taskLists = service.getTaskLists();
		return taskLists.isEmpty()?
				ResponseEntity.noContent().build():
				ResponseEntity.ok(taskLists);
	}

	/*
	 * 
	 */
	@PostMapping("/insert")
	public String save(@RequestBody TaskList tasklist) {
		
		TaskList tl = service.save(tasklist);
		return tl.listValid()?
				"Task List id:" + tl.getId()+ " inserted":
				"Error: Task List not inserted";
	}
	
	/*
	 * 
	 */
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long  id) {
		
		try {
			service.delete(id);
		}catch(Exception e) {
			return "Car id:" + id + "not deleted!";
		}
		return "Error: Car id:" + id + " deleted!";
	}
		
}
