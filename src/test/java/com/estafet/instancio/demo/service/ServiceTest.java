package com.estafet.instancio.demo.service;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.instancio.Instancio;
import org.instancio.internal.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.estafet.instancio.demo.model.Phone;
import com.estafet.instancio.demo.model.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceTest {

	private static final String COUNTRY_CODE = "+359";
	private StudentService service;

	@BeforeEach
	public void resetService() {
		service = new StudentService();
	}

	@Test
	void saveStudent_success() {
		Student studentToSave = Instancio.of(Student.class)
				.set(field(Phone::getCountryCode), COUNTRY_CODE)
				.set(field(Student::getDateOfBirth), Date.valueOf(LocalDate.now().minusYears(18)))
				.create();

		log.info(studentToSave.toString());

		log.info("Saving student...");
		service.saveStudent(studentToSave);

		log.info("Retrieving student from DB...");
		Student studentFromDb = service.getStudentById(studentToSave.getStudentId())
				.orElseThrow(() -> new RuntimeException("Failed to retrieve student"));

		log.info("Validating retrieved student against expected...");
		assertEquals(studentToSave, studentFromDb);
		assertTrue(StringUtils.startsWithAny(studentFromDb.getPhone().getCountryCode(), COUNTRY_CODE));
	}
}
