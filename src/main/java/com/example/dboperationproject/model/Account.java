package com.example.dboperationproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(generator = "account_generator")
	@SequenceGenerator(name = "account_generator", sequenceName = "account_sequence", initialValue = 1000)
	private Long AccountId;

	public Account() {
	}

	@Column(name = "CustomerId", columnDefinition = "bigint")
	private Long CustomerId;
	@Column(name = "AccountNumber", columnDefinition = "bigint")
	private int CountryCode;

	@Column(name = "OwnerBranchCode", columnDefinition = "integer")
	private int OwnerBranchCode;

	@Column(name = "OwnerBranchName", columnDefinition = "text")
	private String OwnerBranchName;

	public Long getAccountId() {
		return AccountId;
	}

	public void setAccountId(Long accountId) {
		AccountId = accountId;
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

	public int getOwnerBranchCode() {
		return OwnerBranchCode;
	}

	public void setOwnerBranchCode(int ownerBranchCode) {
		OwnerBranchCode = ownerBranchCode;
	}

	public String getOwnerBranchName() {
		return OwnerBranchName;
	}

	public void setOwnerBranchName(String ownerBranchName) {
		OwnerBranchName = ownerBranchName;
	}
}
