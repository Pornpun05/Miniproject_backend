package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Facilities;
import com.example.demo.repository.FacilitiesRepository;

@RestController
public class facilitiesController {
	
	@Autowired
	FacilitiesRepository facilitiesRepository;
	private Object facilities;
	
	
	
	@GetMapping("/facilities")
	public ResponseEntity<Object>  getLocation(){
		try {
			List<Facilities> facilitiess = facilitiesRepository.findAll();
			return new ResponseEntity<>(facilitiess ,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/facilities")
	public ResponseEntity<Object> addFacilities(@RequestBody Facilities body) {
		try {

			
			Facilities facilities =  facilitiesRepository.save(body);
			

			return new ResponseEntity<>(facilities, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/facilities/{facilitiesId}")
	public ResponseEntity<Object> getFacilitiesDetail(@PathVariable Integer facilitiesId) {
		
		try {
			Optional<Facilities> faculty = facilitiesRepository.findById(facilitiesId);
			if(faculty.isPresent()) {
				return new ResponseEntity<>(facilities,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Facilities Not Found.",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("facilities/{facilitiesId}")
	public ResponseEntity<Object> updateService(@PathVariable Integer facilitiesId,@RequestBody Facilities body) {
		try {Optional<Facilities> facilities= facilitiesRepository.findById(facilitiesId);
		
		if (facilities.isPresent()) {
			Facilities facilitiesEdit = facilities.get();
			facilitiesEdit.setFacilitiesName(body.getFacilitiesName());
			facilitiesEdit.setFacilitiesId(body.getFacilitiesId());
			
			
			facilitiesRepository.save(facilitiesEdit);
			
			return new ResponseEntity<>(facilitiesEdit,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Facilities Not Found.",HttpStatus.BAD_REQUEST);
		}
	
		}catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
		
	
	@DeleteMapping("facilities/{facilitiesId}")
	public ResponseEntity<Object> deletefaculty(@PathVariable Integer facilitiesId) {
		try {
			Optional<Facilities> facilities =  facilitiesRepository.findById(facilitiesId);
		
			if (facilities.isPresent()) {
			
				facilitiesRepository.delete(facilities.get());
				return new ResponseEntity<>("Delete Facilities Success",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Facilities Not Found",HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
