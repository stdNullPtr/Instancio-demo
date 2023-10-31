package com.estafet.instancio.demo.service;

import static org.instancio.Select.all;
import static org.instancio.Select.allStrings;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.internal.util.StringUtils;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.estafet.instancio.demo.model.Address;
import com.estafet.instancio.demo.model.Phone;
import com.estafet.instancio.demo.model.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(InstancioExtension.class)
public class ServiceTest {

	private static final String COUNTRY_CODE = "+359";
	private StudentService service;

	@BeforeEach
	public void resetService() {
		service = new StudentService();
	}

	private Model<Student> getStudentModel() {
		return Instancio.of(Student.class)
				.set(field(Phone::getCountryCode), COUNTRY_CODE)
				.supply(all(LocalDate.class), () -> LocalDate.now().minusYears(18))
				.ignore(field(Student::getEmail))
				.generate(field(Phone::getNumber), gen -> gen.text().pattern("#d#d#d-#d#d#d-#d#d#d"))
				.supply(all(Address.class), random -> Address.builder()
						.country(random.oneOf("Bulgaria", "UK", "Germany"))
						.postalCode(random.digits(5))
						.build())
				.withNullable(all(allStrings()))
				.toModel();
	}

	@Test
	void saveStudent_success() {
		Student studentToSave = Instancio.of(getStudentModel()).create();

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
