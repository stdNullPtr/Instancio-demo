package com.estafet.instancio.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.estafet.instancio.demo.model.Grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

	private String studentId;
	private String name;
	private Address address;
	private Phone phone;
	private String email;
	private Date dateOfBirth;
	private List<Grade> grades = new ArrayList<>();
}

