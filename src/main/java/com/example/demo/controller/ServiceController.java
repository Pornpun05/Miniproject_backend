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
import com.example.demo.model.Service;
import com.example.demo.repository.ServiceRepository;


@RestController
public class ServiceController {
	
	@Autowired
	ServiceRepository serviceRepository;
	private Object service;
	
	
	
	@GetMapping("/service")
	public ResponseEntity<Object>  getLocation(){
		try {
			List<Service> services = serviceRepository.findAll();
			return new ResponseEntity<>(services ,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/service")
	public ResponseEntity<Object> addService(@RequestBody Service body) {
		try {

			
			Service service =  serviceRepository.save(body);
			

			return new ResponseEntity<>(service, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/service/{numFac}")
	public ResponseEntity<Object> getServiceDetail(@PathVariable Integer numFac) {
		
		try {
			Optional<Service> service = serviceRepository.findById(numFac);
			if(service.isPresent()) {
				return new ResponseEntity<>(service,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Service Not Found.",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("service/{numFac}")
	public ResponseEntity<Object> updateService(@PathVariable Integer numFac,@RequestBody Service body) {
		try {Optional<Service> service= serviceRepository.findById(numFac);
		
		if (service.isPresent()) {
			Service serviceEdit = service.get();
//			serviceEdit.setNumFac(body.getNumFac());
			
			
			serviceRepository.save(serviceEdit);
			
			return new ResponseEntity<>(serviceEdit,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Service Not Found.",HttpStatus.BAD_REQUEST);
		}
	
		}catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
		
	
	@DeleteMapping("service/{numFac}")
	public ResponseEntity<Object> deleteservice(@PathVariable Integer numFac) {
		try {
			Optional<Service> service =  serviceRepository.findById(numFac);
		
			if (service.isPresent()) {
			
				serviceRepository.delete(service.get());
				return new ResponseEntity<>("Delete Service Success.",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Service Not Found.",HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
