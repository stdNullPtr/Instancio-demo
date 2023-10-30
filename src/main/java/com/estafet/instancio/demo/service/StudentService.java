package com.estafet.instancio.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.estafet.instancio.demo.model.Grade;
import com.estafet.instancio.demo.model.Student;
import com.estafet.instancio.demo.model.Subject;

public class StudentService {

	// Using a map to simulate a database. The key is studentId and the value is the Student object.
	private final Map<String, Student> studentDatabase = new HashMap<>();

	public void saveStudent(Student student) {
		studentDatabase.put(student.getStudentId(), student);
	}

	public void updateStudent(Student student) {
		if (studentDatabase.containsKey(student.getStudentId())) {
			studentDatabase.put(student.getStudentId(), student);
		}
	}

	public void deleteStudent(String studentId) {
		studentDatabase.remove(studentId);
	}

	public boolean setGradeForStudent(String studentId, Subject subject, double score) {
		Student student = studentDatabase.get(studentId);

		if (student == null) {
			return false;
		}

		Grade grade = new Grade(subject, score);
		student.getGrades().add(grade);
		return true;
	}

	public Optional<Student> getStudentById(String studentId) {
		return Optional.ofNullable(studentDatabase.get(studentId));
	}
}
