package com.spring.orm.dao;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {

	private HibernateTemplate hibernateTemplate;

	// save student
	@Transactional
	public int insert(Student student) {
		// insert
		Integer i = (Integer) this.hibernateTemplate.save(student);
		return i;
	}
	
	// Get The Single Data Object
	public Student getStudent(int StudentId)
	{
		Student student=this.hibernateTemplate.get(Student.class, StudentId);
		return student;
	}
	// Get All Students (Rows)
	public List<Student> getAllStudents() {
		List<Student> students=this.hibernateTemplate.loadAll(Student.class);
		return students;
	}
	
	// Deleting The Data
	@Transactional
	public void deleteStudent(int studentId) {
		Student student=this.hibernateTemplate.get(Student.class, studentId);
		this.hibernateTemplate.delete(student);
	}
	
	// Updating Data
	@Transactional
	public void updateStudent(Student student) {
		this.hibernateTemplate.update(student);
		
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
