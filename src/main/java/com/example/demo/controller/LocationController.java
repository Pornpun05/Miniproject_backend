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
import com.example.demo.model.Location;
import com.example.demo.repository.LocationRepository;


@RestController
public class LocationController {
	
	@Autowired
	LocationRepository locationRepository;
	
	
	
	@GetMapping("/location")
	public ResponseEntity<Object>  getLocation(){
		try {
			List<Location> locations = locationRepository.findAll();
			return new ResponseEntity<>(locations ,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/location")
	public ResponseEntity<Object> addAdmin(@RequestBody Location body) {
		try {

			
			Location location =  locationRepository.save(body);
			

			return new ResponseEntity<>(location, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/location/{locationId}")
	public ResponseEntity<Object> getLocationDetail(@PathVariable Integer locationId) {
		
		try {
			Optional<Location> location = locationRepository.findById(locationId);
			if(location.isPresent()) {
				return new ResponseEntity<>(location,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("location not found",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("location/{locationId}")
	public ResponseEntity<Object> updateLocation(@PathVariable Integer locationId,@RequestBody Location body) {
		try {Optional<Location> location= locationRepository.findById(locationId);
		
		if (location.isPresent()) {
			Location locationEdit = location.get();
			locationEdit.setLocationId(body.getLocationId());
			locationEdit.setLocationDescription(body.getLocationDescription());
			locationEdit.setLocationId(body.getLocationId());
			
			
			 locationRepository.save(locationEdit);
			
			return new ResponseEntity<>(locationEdit,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Location not found",HttpStatus.BAD_REQUEST);
		}
	
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
		
	
	@DeleteMapping("location/{locationId}")
	public ResponseEntity<Object> deletelocation(@PathVariable Integer locationId) {
		try {
			Optional<Location> location =  locationRepository.findById(locationId);
		
			if (location.isPresent()) {
			
				locationRepository.delete(location.get());
				return new ResponseEntity<>("Delete Success",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("location not found",HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
