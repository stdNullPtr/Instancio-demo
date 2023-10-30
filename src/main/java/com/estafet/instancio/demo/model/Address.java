package com.estafet.instancio.demo.model;

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
public class Address {

	private String street;
	private String city;
	private String state;
	private String postalCode;
	private String country;
}
