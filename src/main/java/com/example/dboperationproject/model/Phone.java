package com.example.dboperationproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {
	@Id
	@GeneratedValue(generator = "phone_generator")
	@SequenceGenerator(name = "phone_generator", sequenceName = "phone_sequence", initialValue = 1000)
	private Long PhoneId;

	public Phone() {
	}

	@Column(name = "CustomerId", columnDefinition = "bigint")
	private Long CustomerId;
	@Column(name = "CountryCode",columnDefinition = "integer")
	private int CountryCode;
	public Long getPhoneId() {
		return PhoneId;
	}
	public void setPhoneId(Long phoneId) {
		PhoneId = phoneId;
	}
	public Long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}
	public int getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(int countryCode) {
		CountryCode = countryCode;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}

	@Column(name = "Number",columnDefinition = "integer")
	private int Number;
	
}
