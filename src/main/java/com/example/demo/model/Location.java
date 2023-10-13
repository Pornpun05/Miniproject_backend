package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
	
	@Lob
	@Column(length = 3048576)
	private byte[] locationPicture;
	
	
	@ManyToOne
	@JoinColumn(name = "facultyId")
	private Faculty faculty;

	
	public Location() {
		super();
	}


	public Location(Integer locationId, String locationName, String locationDescription, byte[] locationPicture,
			Faculty faculty) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationDescription = locationDescription;
		this.locationPicture = locationPicture;
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


	public byte[] getLocationPicture() {
		return locationPicture;
	}


	public void setLocationPicture(byte[] locationPicture) {
		this.locationPicture = locationPicture;
	}


	public Faculty getFaculty() {
		return faculty;
	}


	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	

}