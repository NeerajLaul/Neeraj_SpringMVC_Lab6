package com.StudentAuth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.StudentAuth.service.StudentService;
import com.gl.StudentAuth.entity.Student;


@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	StudentService studentserv;
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student", theStudent);
		return "add-Form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {
		Student theStudent = studentserv.findById(theId);
		theModel.addAttribute("Student",theStudent);
		return "update-Form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int Id, 
			                  @RequestParam("fname") String fname,
			                  @RequestParam("lname") String lname,
			                  @RequestParam("course") String course,
			                  @RequestParam("country") String country) {
		System.out.println(Id);
		Student theStudent;
		if(Id!=0) {
			theStudent = studentserv.findById(Id);
			theStudent.setFname(fname);
			theStudent.setLname(lname);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		} else {
			theStudent = new Student(Id, fname,lname, course, country);
					studentserv.save(theStudent);
		}
		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {
		studentserv.deleteById(theId);
		return "redirect:/students/list";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("fname") String fname, @RequestParam("lname") String lname, Model theModel) {
		if(fname.trim().isEmpty() && lname.trim().isEmpty()) {
			return "redirect:/students/list";
		} else {
			List<Student> theStudents = studentserv.searchBy(fname, lname);
			theModel.addAttribute("Student", theStudents);
		}
		return "list-Students";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
	
	@RequestMapping("/list")
	public String listStudents(Model theModel)
	{
		List<Student> listStudents = studentserv.findAll();
		theModel.addAttribute("tickets", listStudents);
		return "list-Students";
	}

}
