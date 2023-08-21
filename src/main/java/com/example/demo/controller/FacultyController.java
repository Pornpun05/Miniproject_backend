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
import com.example.demo.model.Faculty;
import com.example.demo.repository.FacultyRepository;


@RestController
public class FacultyController {
	
	@Autowired
	FacultyRepository facultyRepository;
	private Object faculty;
	
	
	
	@GetMapping("/faculty")
	public ResponseEntity<Object>  getLocation(){
		try {
			List<Faculty> facultys = facultyRepository.findAll();
			return new ResponseEntity<>(facultys ,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/faculty")
	public ResponseEntity<Object> addFaculty(@RequestBody Faculty body) {
		try {

			
			Faculty faculty =  facultyRepository.save(body);
			

			return new ResponseEntity<>(faculty, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/faculty/{facultyId}")
	public ResponseEntity<Object> getFacultyDetail(@PathVariable Integer facultyId) {
		
		try {
			Optional<Faculty> faculty = facultyRepository.findById(facultyId);
			if(faculty.isPresent()) {
				return new ResponseEntity<>(faculty,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("faculty not found",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("faculty/{facultyId}")
	public ResponseEntity<Object> updateService(@PathVariable Integer facultyId,@RequestBody Faculty body) {
		try {Optional<Faculty> faculty= facultyRepository.findById(facultyId);
		
		if (faculty.isPresent()) {
			Faculty facultyEdit = faculty.get();
			facultyEdit.setFacultyName(body.getFacultyName());
			facultyEdit.setFacultyId(body.getFacultyId());
			
			
			facultyRepository.save(facultyEdit);
			
			return new ResponseEntity<>(facultyEdit,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("faculty not found",HttpStatus.BAD_REQUEST);
		}
	
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
		
	
	@DeleteMapping("faculty/{facultyId}")
	public ResponseEntity<Object> deletefaculty(@PathVariable Integer facultyId) {
		try {
			Optional<Faculty> faculty =  facultyRepository.findById(facultyId);
		
			if (faculty.isPresent()) {
			
				facultyRepository.delete(faculty.get());
				return new ResponseEntity<>("Delete Success",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("faculty not found",HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
