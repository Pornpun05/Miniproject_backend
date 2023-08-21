package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer numfacId;
	
	
	@ManyToOne
	@JoinColumn(name = "locationId")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "facilitiesId")
	private Facilities facilities;
	
	
	public Service() {
		super();
	}


	public Service(Integer numfacId, Location location, Facilities facilities) {
		super();
		this.numfacId = numfacId;
		this.location = location;
		this.facilities = facilities;
	}


	public Integer getNumfacId() {
		return numfacId;
	}


	public void setNumfacId(Integer numfacId) {
		this.numfacId = numfacId;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public Facilities getFacilities() {
		return facilities;
	}


	public void setFacilities(Facilities facilities) {
		this.facilities = facilities;
	}


	
	
}
