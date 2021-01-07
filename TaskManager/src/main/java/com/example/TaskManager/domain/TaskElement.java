	package com.example.TaskManager.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//TaskElement represents a TASK

@Entity
@EqualsAndHashCode
@AllArgsConstructor
public class TaskElement {
	//*********************************************Atributos*******************************************//	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String taskDone;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tasklist_id", nullable = false)
    private TaskList tasklist;

	private String  idTaskList;
	
	
	//***************************************Constructors**********************************************//	
		
	public TaskElement() {}
	
	public TaskElement(String pName, Long pId, String pIdTaskList) {
		super();
		this.name = pName;
		this.id = pId;
		this.taskDone = "NO";
		this.idTaskList = pIdTaskList;
	}	
	
	
	
	public Boolean isTaskElementValid() {
		return (!this.name.isEmpty());
	}
	
	//******************************************Getters************************************************//	
	
	public String getIdTaskList() {
		return idTaskList;
	}
	
	public String getName() {
		return name;
	}

	public String getTaskDone() {
		return taskDone;
	}
	
	public Long getId() {
		return id;
	}
	
	//********************************************Setters**********************************************//	
	
	public void setIdTaskList(String pIdTaskList) {
		this.idTaskList = pIdTaskList;
	}
		
	public void setTaskDone(String pTaskDone) {
		this.taskDone = pTaskDone;
	}
		
	public void setName(String pName) {
		this.name = pName;
	}
	
	public void setId(Long pId) {
		this.id = pId;
	}
	
	public void setTaskListid(Long pId) {
		tasklist = new TaskList();
		tasklist.setId(pId);
	}
	
}