package com.example.TaskManager.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
public class TaskList {
	//*********************************************Atributos*******************************************//	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
		
	 @OneToMany(mappedBy = "tasklist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 private Set<TaskElement> taskElements;
		
	//***************************************Constructors**********************************************//	
		
	public TaskList() {}
	
	public TaskList(String pName, Long pId) {
		super();
		this.name = pName;
		this.id = pId;
	}	
	
	public Boolean listValid() {
		return (!this.name.isEmpty());
	}
	
	//******************************************Getters************************************************//	
	
	public String getname() {
		return name;
	}
	
	public Long getId() {
		return id;
	}
	
	//********************************************Setters**********************************************//		
	
	public void setName(String pName) {
		this.name = pName;
	}
	
	public void setId(Long pId) {
		this.id = pId;
	}
		
}