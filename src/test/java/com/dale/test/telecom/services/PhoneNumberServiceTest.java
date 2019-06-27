package com.dale.test.telecom.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dale.telecom.service.PhoneNumberService;
import com.dale.test.telecom.AbstractTest;
import com.dale.telecom.entity.PhoneNumber;

@Transactional
public class PhoneNumberServiceTest extends AbstractTest {

	@Autowired
	private PhoneNumberService phoneNumberService;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	private PhoneNumber createTestNumber() {
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setCountryCode("+44");
		phoneNumber.setNumber("0123456789");
		phoneNumber.setCustomerId(1);
		
		return phoneNumber;
	}
	
	@Test
	public void testAddNumber() {
		PhoneNumber phoneNumber = createTestNumber();
		PhoneNumber newPhoneNumber = phoneNumberService.savePhoneNumber(phoneNumber);
		
		assertThat(phoneNumber.getId()).isGreaterThan(0);
		assertEquals(phoneNumber.getNumber(), newPhoneNumber.getNumber());
		assertEquals(phoneNumber.getCountryCode(), newPhoneNumber.getCountryCode());
		/// etc
	}
	
	
	@Test
	public void testPhoneNumberHasActivatedFalseAsDefault() {
		PhoneNumber phoneNumber = createTestNumber();
		PhoneNumber newPhoneNumber = phoneNumberService.savePhoneNumber(phoneNumber);
		
		assertEquals(false, newPhoneNumber.isActive());
	}
	
	@Test
	public void testPhoneNumberGetsActivated() throws Exception {
		PhoneNumber testSubject = phoneNumberService.savePhoneNumber(createTestNumber());
		
		int count = phoneNumberService.getPhoneNumbers().size();
		assertEquals(1, count);
		
		int updated = phoneNumberService.activePhoneNumber(testSubject.getId());
		assertEquals(1, updated);
		
		List<PhoneNumber> listOfNumbers = phoneNumberService.getPhoneNumbers();
		
		assertEquals(true, listOfNumbers.get(0).isActive());
	}
	
	@Test
	public void testPhoneNumberGetsActivatedBySave() throws Exception {
		PhoneNumber testSubject = phoneNumberService.savePhoneNumber(createTestNumber());
		
		testSubject.setActive(true);
		
		PhoneNumber updatedNumber = phoneNumberService.savePhoneNumber(testSubject);
		
		assertEquals(true, updatedNumber.isActive());
	}
	
	@Test
	public void testGetAllNumber() throws Exception {
		phoneNumberService.savePhoneNumber(createTestNumber());
		phoneNumberService.savePhoneNumber(createTestNumber());
		phoneNumberService.savePhoneNumber(createTestNumber());
		
		int count = phoneNumberService.getPhoneNumbers().size();
		assertEquals(3, count);
	}
	
	@Test
	public void testTheCorrectCustomerNumbersAreReturned() throws Exception {
		// 2 customers with id 1
		PhoneNumber testSubject = phoneNumberService.savePhoneNumber(createTestNumber());
		phoneNumberService.savePhoneNumber(createTestNumber());

		// 1 customer with id 2
		PhoneNumber customer2 = createTestNumber();
		customer2.setCustomerId(2);
		phoneNumberService.savePhoneNumber(customer2);

		// customer 1
		int count = phoneNumberService.getCustomerPhoneNumbers(testSubject.getCustomerId()).size();
		assertEquals(2, count);
		
		// customer 2
		count = phoneNumberService.getCustomerPhoneNumbers(customer2.getCustomerId()).size();
		assertEquals(1, count);
	}
	
	/**
	 * TODO
	 * 
	 * Go into a more granular level eg
	 * - max lengths
	 * - special chars
	 * - expected runtime and checked exceptions
	 */

}
