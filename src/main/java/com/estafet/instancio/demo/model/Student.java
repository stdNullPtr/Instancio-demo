package com.estafet.instancio.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

	private String studentId;
	private String name;
	private Address address;
	private Phone phone;
	private String email;
	private LocalDate dateOfBirth;
	private List<Grade> grades = new ArrayList<>();
}

