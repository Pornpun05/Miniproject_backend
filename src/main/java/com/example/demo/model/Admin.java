package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;	
	private String adminfirstName;
	private String adminlastName;
	private Integer adminpassword;
	
	
	
	public Admin(Integer adminId, String adminfirstName, String adminlastName, Integer adminpassword) {
		super();
		this.adminId = adminId;
		this.adminfirstName = adminfirstName;
		this.adminlastName = adminlastName;
		this.adminpassword = adminpassword;
	}
	
	public Admin() {
		super();
	}



	public Integer getAdminId() {
		return adminId;
	}



	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}



	public String getAdminfirstName() {
		return adminfirstName;
	}



	public void setAdminfirstName(String adminfirstName) {
		this.adminfirstName = adminfirstName;
	}



	public String getAdminlastName() {
		return adminlastName;
	}



	public void setAdminlastName(String adminlastName) {
		this.adminlastName = adminlastName;
	}



	public Integer getAdminpassword() {
		return adminpassword;
	}



	public void setAdminpassword(Integer adminpassword) {
		this.adminpassword = adminpassword;
	}
	
	
	
	
	
	
	

}
