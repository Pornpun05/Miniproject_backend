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
@Table(name = "picture")
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pictureId;
	private String pictureName;
	
	
	@Lob
	@Column(length = 3048576)
	private byte[] picture;

	
	@ManyToOne
	@JoinColumn(name = "locationId")
	private Location location;

	public Picture() {
		super();
	}

	public Picture(Integer pictureId, String pictureName, byte[] picture, Location location) {
		super();
		this.pictureId = pictureId;
		this.pictureName = pictureName;
		this.picture = picture;
		this.location = location;
	}

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	 
	

	
}
