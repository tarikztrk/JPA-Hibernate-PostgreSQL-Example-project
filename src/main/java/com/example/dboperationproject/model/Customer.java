package com.example.dboperationproject.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(generator = "customer_generator")
	@SequenceGenerator(name = "customer_generator", sequenceName = "customer_sequence", initialValue = 1000)
	private Long id;

	public Customer() {
	}

	@Column(name = "CustomerName", columnDefinition = "text")
	private String CustomerName;
	@Column(name = "BirthDate",columnDefinition = "text")
	private Date BirthDate;
	@Column(name = "BirthPlace",columnDefinition = "text")
	private String BirthPlace;
	@Column(name = "Email",columnDefinition = "text")
	private String Email;

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

	public String getBirthPlace() {
		return BirthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		BirthPlace = birthPlace;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
}
