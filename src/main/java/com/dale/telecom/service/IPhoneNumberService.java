package com.dale.telecom.service;

import java.util.List;

import com.dale.telecom.entity.PhoneNumber;

public interface IPhoneNumberService {

	public List<PhoneNumber> getPhoneNumbers();
	public List<PhoneNumber> getCustomerPhoneNumbers(int customerId);
	public PhoneNumber savePhoneNumber(PhoneNumber phoneNumber);

}
