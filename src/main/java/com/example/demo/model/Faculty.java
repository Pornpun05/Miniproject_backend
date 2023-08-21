package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer facultyId;	
	private String facultyName;
	
	
	
	
	public Faculty(Integer facultyId, String facultyName) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
	}
	
	
	public Faculty() {
		super();
	}




	public Integer getFacultyId() {
		return facultyId;
	}




	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}




	public String getFacultyName() {
		return facultyName;
	}




	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	
	
	
	
	
	

}
