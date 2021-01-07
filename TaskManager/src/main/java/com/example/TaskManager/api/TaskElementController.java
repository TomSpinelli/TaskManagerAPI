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

import com.example.TaskManager.domain.TaskElement;
import com.example.TaskManager.domain.TaskElementService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskElementController {
	
	@Autowired //Dependency Injection
	private TaskElementService service;
	
	/*
	 * Retrieves all TASKS from a taskList
	 */
	@GetMapping("/tasklist/{tasklist}")
	public ResponseEntity<List<TaskElement>> getTaskElementsByTaskList(@PathVariable("tasklist") String tasklist) {
		
		List<TaskElement> taskElements = service.getTaskElemensByTaskList(tasklist);
		return taskElements.isEmpty()?
				ResponseEntity.noContent().build():
				ResponseEntity.ok(taskElements);
	}
	
	/*
	 * 
	 */
	@GetMapping("/all")
	public ResponseEntity<List<TaskElement>> getTaskElements() {
		
		List<TaskElement> taskElement = service.getTaskElements();
		return taskElement.isEmpty()?
				ResponseEntity.noContent().build():
				ResponseEntity.ok(taskElement);
	}

	
	/*
	 * 
	 */
	@PostMapping("/insert/{idTaskList}")
	public String save(@RequestBody TaskElement pTaskElement, @PathVariable("idTaskList") Long  idTaskList) {
		
		pTaskElement.setTaskListid(idTaskList);
		TaskElement taskElement = service.save(pTaskElement);
		
		return taskElement.isTaskElementValid()?
				"TaskElement id:" + taskElement.getId()+ " inserted":
				"Error: TaskElement not inserted";
	}

	/*
	 * 
	 */
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long  id) {
		
		try {
			service.delete(id);
		}catch(Exception e) {
			return "Task id:" + id + "not deleted!";
		}
		return "Error: Task id:" + id + " deleted!";
	}
	
	/*
	 *  method used to Open or Finish a TASK 
	 */
	@GetMapping("/switchtaskstatus/{id}")
	public String switchTaskStatus( @PathVariable("id") Long  id) {
	
		try {
			service.switchTaskStatus(id);
		}catch(Exception e) {
			return "Error: Task id:" + id + " was not Att!";
		}
		return "Task id:" + id + "Att!";
	}
			
}
