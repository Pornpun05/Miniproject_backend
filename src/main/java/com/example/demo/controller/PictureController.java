package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Location;
import com.example.demo.model.Picture;
import com.example.demo.repository.PictureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PictureController {
	
	@Autowired
	PictureRepository pictureRepository;
	
	@GetMapping("/picture")
	public ResponseEntity<Object> getBoardGames() {

		try {
			List<Picture> PictureList = pictureRepository.findAllPicture();
			List<Picture>Picture = new ArrayList<>();

			for (Picture row : PictureList) {
				Integer PictureId = row.getPictureId();
				String PictureName = row.getPictureName();
				
				
				byte[] picture = row.getPicture();
				Location location =row.getLocation();

				
 
				Picture newPicture = new Picture(PictureId, PictureName, picture ,location);

				Picture.add(newPicture);
			}
			return new ResponseEntity<>(Picture, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping(value = "/picture", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createPicture(@RequestParam("body") String pictureJson,
			@RequestParam("photo") MultipartFile photo) throws IOException {

		try {
			
			Picture body = new ObjectMapper().readValue(pictureJson, Picture.class);

				body.setPicture(photo.getBytes());

			Picture newPicture = pictureRepository.save(body);
			return new ResponseEntity<>(newPicture, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error processing the photo.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/Picture/{pictureId}")
	public ResponseEntity<Object> getPictureById(@PathVariable("pictureId") Integer pictureId) {

		try {

			Optional<Picture> pictureFind = pictureRepository.findById(pictureId);
			if (pictureFind.isPresent()) {
				Picture picture = pictureFind.get();

				return new ResponseEntity<>( picture, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Picture Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@PutMapping(value = "/Picture/{productid}", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> updatePicture(@PathVariable("pictureId") Integer pictureId,
			@RequestParam("body") String pictureJson, @RequestParam("photo") MultipartFile photo) throws IOException {

		try {

			Optional<Picture>pictureFind = pictureRepository.findById(pictureId);

			if (pictureFind.isPresent()) {
				Picture body = new ObjectMapper().readValue(pictureJson, Picture.class);
				Picture pictureUpdate = pictureFind.get();

				if (!photo.isEmpty()) {
					body.setPicture(photo.getBytes());
				}

				
				pictureUpdate.setPictureId(body.getPictureId());
				pictureUpdate.setPictureName(body.getPictureName());
				pictureUpdate.setLocation(body.getLocation());

				pictureRepository.save(pictureUpdate);

				return new ResponseEntity<>(pictureUpdate, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Picture Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@DeleteMapping("/Picture/{pictureId}")
	public ResponseEntity<Object> deletePictureById(@PathVariable("pictureId") Integer pictureId) {

		try {

			Optional<Picture> pictureFind = pictureRepository.findById(pictureId);

			if (pictureFind.isPresent()) {


				pictureRepository.delete(pictureFind.get());

				return new ResponseEntity<>("Delete Picture Success.", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Picture Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
