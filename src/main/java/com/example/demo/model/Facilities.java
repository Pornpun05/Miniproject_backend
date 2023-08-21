package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "facilities")
public class Facilities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer facilitiesId;	
	private String facilitiesName;
	
	

	public Facilities() {
		super();
	}



	public Facilities(Integer facilitiesId, String facilitiesName) {
		super();
		this.facilitiesId = facilitiesId;
		this.facilitiesName = facilitiesName;
	}



	public Integer getFacilitiesId() {
		return facilitiesId;
	}



	public void setFacilitiesId(Integer facilitiesId) {
		this.facilitiesId = facilitiesId;
	}



	public String getFacilitiesName() {
		return facilitiesName;
	}



	public void setFacilitiesName(String facilitiesName) {
		this.facilitiesName = facilitiesName;
	}
	
	
	



	
	
	

}
