package com.www.ihub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.www.ihub.entity.StudentEntity;

@ResponseBody
public interface StudentRepo extends JpaRepository<StudentEntity, Integer>
{
	public StudentEntity findByStuName(String name); 
	

}
