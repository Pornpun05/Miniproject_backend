package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Faculty;
import com.example.demo.repository.FacultyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
public class FacultyController {
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@PostMapping(value = "/faculty", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createPhoto(@RequestParam("body") String Facultyjson,
			@RequestParam("photo") MultipartFile photo) throws IOException {

		try {
			Faculty body = new ObjectMapper().readValue(Facultyjson, Faculty.class);

				body.setFacultyPicture(photo.getBytes());
		
				Faculty newFaculty = facultyRepository.save(body);
			return new ResponseEntity<>(newFaculty, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error processing the photo.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/faculty")
	public ResponseEntity<Object> getFaculty() {

		try {
			List<Faculty> FacultyList = facultyRepository.findAllFaculty();
			List<Faculty> Faculty = new ArrayList<>();

			for (Faculty row : FacultyList) {
				Integer facultyId = row.getFacultyId();
				String facultyName = row.getFacultyName();
				byte[] facultyPicture = row.getFacultyPicture();
				
				Faculty newFaculty = new Faculty(facultyId, facultyName, facultyPicture);

				Faculty.add(newFaculty);
			}
			return new ResponseEntity<>(Faculty, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping("/faculty/{facultyId}")
	public ResponseEntity<Object> getFacultyById(@PathVariable("facultyId") Integer facultyId) {

		try {

			Optional<Faculty> facultyFind = facultyRepository.findById(facultyId);
			if (facultyFind.isPresent()) {
				Faculty faculty = facultyFind.get();


				return new ResponseEntity<>( faculty, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Faculty Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/faculty/{facultyId}")
	public ResponseEntity<Object> deleteFacultyById(@PathVariable("facultyId") Integer facultyId) {

		try {

			Optional<Faculty> facultyFind = facultyRepository.findById(facultyId);

			if (facultyFind.isPresent()) {


				facultyRepository.delete(facultyFind.get());

				return new ResponseEntity<>("Delete Faculty Success.", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Faculty not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping(value = "/faculty/{facultyId}", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> updateFaculty(@PathVariable("facultyId") Integer facultyId,
			@RequestParam("body") String Facultyjson, @RequestParam("photo") MultipartFile photo) throws IOException {

		try {

			Optional<Faculty>facultyFind = facultyRepository.findById(facultyId);

			if (facultyFind.isPresent()) {
				Faculty body = new ObjectMapper().readValue(Facultyjson, Faculty.class);
				Faculty facultyUpdate = facultyFind.get();

				if (!photo.isEmpty()) {
					body.setFacultyPicture(photo.getBytes());
				}

				facultyUpdate.setFacultyName(body.getFacultyName());
				facultyUpdate.setFacultyPicture(body.getFacultyPicture());

				facultyRepository.save(facultyUpdate);

				return new ResponseEntity<>(facultyUpdate, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Faculty Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
}
	
	