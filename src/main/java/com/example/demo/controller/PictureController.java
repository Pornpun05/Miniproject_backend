package com.example.demo.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.Picture;
import com.example.demo.repository.PictureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PictureController {
	
	@Autowired
	PictureRepository pictureRepository;
	
	@PostMapping(value = "/picture", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createBoardGame(@RequestParam("body") String pictureJson,
			@RequestParam("photo") MultipartFile photo) throws IOException {

		try {
			Picture body = new ObjectMapper().readValue(pictureJson, Picture.class);

			
				String photoName = UUID.randomUUID().toString() + ".png";
				body.setPictureName(photoName);
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

}
