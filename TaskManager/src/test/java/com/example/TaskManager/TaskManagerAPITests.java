package com.example.TaskManager;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.TaskManager.domain.TaskElement;
import com.example.TaskManager.domain.TaskElementService;
import com.example.TaskManager.domain.TaskList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskManagerAPITests {
  
	@Autowired
    protected TestRestTemplate rest;

    @Autowired
    private TaskElementService service;

  //--------------------------------  TaskList  TESTS  ------------------------------------------//	
    
    /*
     * 
     */
    private ResponseEntity<TaskList> getTaskList(String url) {
        return
                rest.getForEntity(url, TaskList.class);
    }

    /*
     * 
     */
    @Test
    public void testSave() {

    	TaskList taskList = new TaskList();
    	taskList.setName("ElementTeste");
    	taskList.setId(9L);
		
    	ResponseEntity response = rest.postForEntity("/api/v1/tasklist/insert", taskList, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        rest.delete("/api/v1/tasklist/delete/9");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
  //--------------------------------  TaskElements  TESTS  ---------------------------------------//	
    
    /*
     * 
     */
    private ResponseEntity<List<TaskElement>> getTaskElements(String url) {
        return rest.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TaskElement>>() { });
    }
    
    /*
     * 
     */
    @Test
    public void testElementsAll() {
        List<TaskElement> taskElement = getTaskElements("/api/v1/task/all").getBody();
        assertNotNull(taskElement);
        assertEquals(8, taskElement.size());
    }
    
    /*
     * 
     */
    @Test
    public void testListaPorTipo() {

        assertEquals(4, getTaskElements("/api/v1/task/tasklist/1").getBody().size());
        assertEquals(4, getTaskElements("/api/v1/task/tasklist/2").getBody().size());
        assertEquals(HttpStatus.NO_CONTENT, getTaskElements("/api/v1/task/tasklist/15").getStatusCode());
    }
  
}