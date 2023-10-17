package com.StudentAuth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.StudentAuth.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	List<Student> findByNameContainsAndCountryContainsAllIgnoreCase(String fname, String country);

}
