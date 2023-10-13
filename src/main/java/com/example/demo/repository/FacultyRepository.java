package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.demo.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    @Query("SELECT f FROM Faculty f")
    List<Faculty> findAllFaculty();
}
