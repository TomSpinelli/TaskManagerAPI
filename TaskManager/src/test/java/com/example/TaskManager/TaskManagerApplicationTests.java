package com.example.TaskManager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TaskManager.domain.TaskElement;
import com.example.TaskManager.domain.TaskElementService;
import com.example.TaskManager.domain.TaskList;
import com.example.TaskManager.domain.TaskListService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerApplicationTests {
	
	//Tests use H2 instead of mySQL
	//Check out file data.sql. H2 Database populated(2 Task List , 8 Task Elements)
	
	//------------------------------------  TaskList  TESTS  -----------------------------------------//	
	@Autowired
	private TaskListService listService;
	
	
	 /*
     * 
     */
	/*Tests TaskList Insertion*/
	@Test
	public void testInsertDeleteTaskList() {
		
		TaskList taskList = new TaskList();
		taskList.setName("ListaTeste");
		TaskList taskListInsert = listService.save(taskList);
		
		assertNotNull(taskListInsert);
		assertEquals("ListaTeste",taskListInsert.getname());
		
		listService.delete(taskListInsert.getId());
		Optional<TaskList> taskListDelete = listService.getTaskListById(taskListInsert.getId());
	    
		assert(!taskListDelete.isPresent());
    }

	
	 /*
     * 
     */
	/*Tests number of TaskList inserted by the 'data.sql' script as the server starts*/
	 @Test
     public void testListSizeTaskList() {
         Iterable<TaskList> taskList = listService.getTaskLists();
         assertEquals(2, taskList.spliterator().estimateSize());
     }
	 
	 
	 /*
	  * 
	  */
	 /*Tests TaskList FindById*/
	 @Test
     public void testGetByIdTaskList() {
       
		Optional<TaskList> taskList = listService.getTaskListById(1L);
        assert(taskList.isPresent());
       
        assertNotNull(taskList);
        assertEquals("janeiro", taskList.get().getname());
     }
	 
	//-------------------------------------  TaskElements  TESTS  -------------------------------------//	
	
	@Autowired
	private TaskElementService elementService;
	
	 
	 /*
     * 
     */
	/*Tests TaskElement INSERT DELETE*/
	@Test
	public void testInsertDeleteTaskElement() {
		
		TaskElement taskElement = new TaskElement();
		taskElement.setName("ElementTeste");
		taskElement.setTaskListid(1L);
		TaskElement taskElementInsert = elementService.save(taskElement);
		
		assertNotNull(taskElementInsert);
		assertEquals("ElementTeste",taskElementInsert.getName());
	
		elementService.delete(taskElementInsert.getId());
		Optional<TaskElement> taskElementDelete = elementService.getTaskElementById(taskElementInsert.getId());
	   
		assert(!taskElementDelete.isPresent());
    }

	
	 /*
     * 
     */
	/*Tests number of TaskElement inserted by the 'data.sql' script as the server starts*/
	 @Test
     public void testListSizeTaskElement() {
         Iterable<TaskElement> taskElement = elementService.getTaskElements();
         assertEquals(8, taskElement.spliterator().estimateSize());
     }
	 	
	 
	 /*
	  * 
	  */    	 
	 /*Tests TaskElement FindById*/
	 @Test
     public void testGetByIdTaskElement() {
        
		Optional<TaskElement> taskElement = elementService.getTaskElementById(8L);
        assert(taskElement.isPresent());
        
        assertNotNull(taskElement);
        assertEquals("Viagem", taskElement.get().getName());
      }
	 
	
	 /*
	  * 
	  */
	 /*Tests getTaskElemensByTaskList. The Tasks inside a TaskList*/
	 @Test
     public void testGetTaskElementsByTaskList() {
         Iterable<TaskElement> taskElements = elementService.getTaskElemensByTaskList("1");
         assertEquals(4, taskElements.spliterator().estimateSize());
     }
	 
	 /*
	  * 
	  */
	 @Test
     public void testSwitchStatusTask() {
		 elementService.switchTaskStatus(1L);
		 Optional<TaskElement> taskElement = elementService.getTaskElementById(1L);
	       
		 assertEquals("YES", taskElement.get().getTaskDone());
     }
	 
	 @Test
	 public void contextLoads() {
	 }

}
