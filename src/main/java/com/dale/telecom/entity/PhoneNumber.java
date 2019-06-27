package com.dale.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "phone_number")
public class PhoneNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Country code is required")
	private String countryCode;
	
	/**
	 * TODO - Add other validations / table conditions etc.
	 */
	private String number;
	private int customerId;
	
	/**
	 * We'll assume that a number is not active as default
	 */
	private boolean active = false;

	public Long getId() {
		return id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String code) {
		this.countryCode = code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
