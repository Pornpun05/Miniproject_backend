package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Location;



@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{
	 @Query("SELECT l FROM Location l")
	    List<Location> findAllLocation();

}
