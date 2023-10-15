package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer facultyId;	
	private String facultyName;
	@Lob
	@Column(length = 3048576)
	private byte[] facultyPicture;
	
	public Faculty() {
		super();
	}

	public Faculty(Integer facultyId, String facultyName, byte[] facultyPicture) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.facultyPicture = facultyPicture;
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

	public byte[] getFacultyPicture() {
		return facultyPicture;
	}

	public void setFacultyPicture(byte[] facultyPicture) {
		this.facultyPicture = facultyPicture;
	}

	
	
}
