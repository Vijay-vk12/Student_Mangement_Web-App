package com.sms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import com.sms.demo.entity.Student;
import com.sms.demo.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentservice;
	
//	
//	public StudentController(StudentService studentservice) {
//		super();
//		this.studentservice = studentservice;
//	}


	@GetMapping("/allStudents")
	public String listStudents(Model model)
	{
		model.addAttribute("students", studentservice.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model)
	{
		Student student=new Student();
		model.addAttribute("student", student);
		return "create-student";
	}
	
	@PostMapping("/students")
	public String saveStudent( @ModelAttribute("student") Student student)
	{
		studentservice.saveStudent(student);
		return "redirect:/allStudents";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model)
	{
		model.addAttribute("student", studentservice.getStudentById(id));
		return "edit-student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id , @ModelAttribute("student") Student student, Model model)
	{
		Student exstudent=studentservice.getStudentById(id);
		exstudent.setId(id);
		exstudent.setName(student.getName());
		exstudent.setBranch(student.getBranch());
		exstudent.setCollege(student.getCollege());
		
		studentservice.updateStudent(exstudent);
		return "redirect:/allStudents";
		
	}
	
	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentservice.deleteStudentById(id);
		 return "redirect:/allStudents";
	}
}
