package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer locationId;	
	private String locationName;
	private String locationDescription;
	
	@ManyToOne
	@JoinColumn(name = "adminId")
	 private Admin admin;
	
	@ManyToOne
	@JoinColumn(name = "facultyId")
	private Faculty faculty;

	
	public Location() {
		super();
	}


	public Location(Integer locationId, String locationName, String locationDescription, Admin admin, Faculty faculty) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationDescription = locationDescription;
		this.admin = admin;
		this.faculty = faculty;
	}


	public Integer getLocationId() {
		return locationId;
	}


	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public String getLocationDescription() {
		return locationDescription;
	}


	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public Faculty getFaculty() {
		return faculty;
	}


	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	


	

}
