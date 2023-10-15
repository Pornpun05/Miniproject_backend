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

import com.example.demo.model.Location;
import com.example.demo.repository.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin(origins = "*")
public class LocationController {
	
	@Autowired
	LocationRepository locationRepository;
	
	@PostMapping(value = "/location", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createPhoto(@RequestParam("body") String LocationIdjson,
			@RequestParam("photo") MultipartFile photo) throws IOException {

		try {
			Location body = new ObjectMapper().readValue(LocationIdjson, Location.class);

				body.setLocationPicture(photo.getBytes());
		
				Location newLocation = locationRepository.save(body);
			return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error processing the photo.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/location")
	public ResponseEntity<Object> getLocation() {

		try {
			List<Location> LocationList = locationRepository.findAllLocation();
			List<Location> Location = new ArrayList<>();

			for (Location row : LocationList) {
				Integer locationId = row.getLocationId();
				String locationName = row.getLocationName();
				String locationDescription = row.getLocationDescription();
				byte[] locationPicture = row.getLocationPicture();
				
				Location newLocation = new Location(locationId, locationName, locationDescription,locationPicture,null);

				Location.add(newLocation);
			}
			return new ResponseEntity<>(Location, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping("/location/{locationId}")
	public ResponseEntity<Object> getLocationById(@PathVariable("locationId") Integer locationId) {

		try {

			Optional<Location> locationFind = locationRepository.findById(locationId);
			if (locationFind.isPresent()) {
				Location location = locationFind.get();


				return new ResponseEntity<>( location, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Location Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/location/{locationId}")
	public ResponseEntity<Object> deleteLocationById(@PathVariable("locationId") Integer locationId) {

		try {

			Optional<Location> locationFind = locationRepository.findById(locationId);

			if (locationFind.isPresent()) {


				locationRepository.delete(locationFind.get());

				return new ResponseEntity<>("Delete Location Success.", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Location not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping(value = "/location/{locationId}", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> updateLocation(@PathVariable("locationId") Integer locationId,
			@RequestParam("body") String LocationIdjson, @RequestParam("photo") MultipartFile photo) throws IOException {

		try {

			Optional<Location>locationFind = locationRepository.findById(locationId);

			if (locationFind.isPresent()) {
				Location body = new ObjectMapper().readValue(LocationIdjson, Location.class);
				Location locationUpdate = locationFind.get();

				if (!photo.isEmpty()) {
					body.setLocationPicture(photo.getBytes());
				}

				locationUpdate.setLocationName(body.getLocationName());
				locationUpdate.setLocationDescription(body.getLocationDescription());
				locationUpdate.setLocationPicture(body.getLocationPicture());
				locationUpdate.setFaculty(body.getFaculty());

				locationRepository.save(locationUpdate);

				return new ResponseEntity<>(locationUpdate, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Faculty Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

		
	
}
