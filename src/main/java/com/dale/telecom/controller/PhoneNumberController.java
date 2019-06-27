package com.dale.telecom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dale.telecom.entity.PhoneNumber;
import com.dale.telecom.service.PhoneNumberService;

@RestController
@RequestMapping(path = "/api/phoneNumber")
public class PhoneNumberController {

	@Autowired
	private PhoneNumberService phoneNumberService;
	
	@GetMapping(path = "")
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumberService.getPhoneNumbers();
	}
	
	@GetMapping(path = "/customer/{customerId}")
	public List<PhoneNumber> getCustomerPhoneNumbers(@PathVariable Integer customerId) {
		return phoneNumberService.getCustomerPhoneNumbers(customerId);
	}
	
	
	/**
	 * Some would argue the whole row should be updated (put) and some would prefer
	 * the 2nd function below (not quite a patch). The difference is that
	 * - 'savePhoneNumber' will save new or update the whole object 
	 * - 'activatePhoneNumber' updates a single field with the id
	 * 
	 * Would be interesting to test the latency and memory difference
	 * 
	 */
	@PutMapping(path = "")
	public PhoneNumber savePhoneNumber(@Valid @RequestBody PhoneNumber phoneNumber) {
		return phoneNumberService.savePhoneNumber(phoneNumber);
	}
	
	@PutMapping(path = "{phoneNumberId}/activate")
	public int activatePhoneNumber(@PathVariable int phoneNumberId) {
		return phoneNumberService.activePhoneNumber(phoneNumberId);
	}
}
