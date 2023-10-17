package com.StudentAuth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentAuth.repository.StudentRepository;
import com.gl.StudentAuth.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository studentrepo;

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		List<Student> students = studentrepo.findAll();
		return students;
	}

	@Override
	public Student findById(int theId) {
		// TODO Auto-generated method stub
		return studentrepo.findById(theId).get();
	}

	@Override
	public void save(Student theStudent) {
		// TODO Auto-generated method stub
		studentrepo.save(theStudent);
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		studentrepo.deleteById(theId);
		
	}

	@Override
	public List<Student> searchBy(String fname, String country) {
		// TODO Auto-generated method stub
		List<Student> students = studentrepo.findByNameContainsAndCountryContainsAllIgnoreCase(fname, country);
		return students;
	}
	
	
	

}
