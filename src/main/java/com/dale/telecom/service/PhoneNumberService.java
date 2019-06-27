package com.dale.telecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dale.telecom.entity.PhoneNumber;
import com.dale.telecom.repository.PhoneNumberRepository;

@Service
public class PhoneNumberService implements IPhoneNumberService {

	@Autowired
	private PhoneNumberRepository phoneNumberRepository;

	@Override
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumberRepository.findAll();
	}

	@Override
	public PhoneNumber savePhoneNumber(PhoneNumber newPhoneNumber) {
		/**
		 * Creates or updates based on the id
		 */
		return phoneNumberRepository.save(newPhoneNumber);
	}

	@Override
	public List<PhoneNumber> getCustomerPhoneNumbers(int customerId) {
		return phoneNumberRepository.findAllBycustomerId(customerId);
	}

	
	public int activePhoneNumber(long phoneNumberId) {
		return phoneNumberRepository.setActiveForPhoneNumber(true, phoneNumberId);
	}
	 
}
