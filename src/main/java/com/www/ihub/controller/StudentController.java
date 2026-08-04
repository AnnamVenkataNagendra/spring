package com.www.ihub.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.www.ihub.entity.StudentEntity;
import com.www.ihub.service.StudentService;
import com.www.ihub.token.StudentToken;

@RestController
@RequestMapping("/stu")
@CrossOrigin(origins = "https://student-ohvy.onrender.com/")
public class StudentController 
{
	@Autowired
	private StudentService service;
	
	@Autowired
	private StudentToken studentToken;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/post")
	public ResponseEntity<?> entity(@RequestBody StudentEntity entity){
		
		service.studentDetails(entity);
		
		return ResponseEntity.status(HttpStatus.OK).body("Student data stored");
		
	}
	
	@GetMapping("/fetch")
	public List<StudentEntity> entities()
	{
		return service.entities();
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> responseEntity(@RequestBody StudentEntity entity){
		
		UsernamePasswordAuthenticationToken token=
				new UsernamePasswordAuthenticationToken(entity.getStuName(), entity.getStuPass());
 
       org.springframework.security.core.Authentication authentication=manager.authenticate(token);
		
		if(authentication.isAuthenticated()){
			
			return new ResponseEntity<String>(studentToken.generateToken(entity.getStuName()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("User Login Falied",HttpStatus.UNAUTHORIZED);
		}
		
	}
	
}
