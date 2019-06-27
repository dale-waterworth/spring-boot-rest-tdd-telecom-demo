package com.dale.test.telecom.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.dale.telecom.entity.PhoneNumber;
import com.dale.test.telecom.AbstractTest;

@Transactional
public class PhoneNumberControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * Perfect world tests where all fields exist and are valid / or
	 * that we get a 200 response when expected
	 */
	
	@Test
	public void canAddAPhoneNumber() throws Exception {
		String uri = "/api/phoneNumber";

		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setCountryCode("+44");
		phoneNumber.setNumber("0123456789");
		phoneNumber.setCustomerId(1);

		String inputJson = super.mapToJson(phoneNumber);

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void canGetPhoneNumbers() throws Exception {
		String uri = "/api/phoneNumber";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void canGetCustomerPhoneNumbers() throws Exception {
		int customerId = 1;

		StringBuilder sb = new StringBuilder();
		sb.append("/api/phoneNumber/customer/").append(customerId);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(sb.toString()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void activatePhoneNumber() throws Exception {
		int phoneNumberId = 1;

		StringBuilder sb = new StringBuilder();
		sb.append("/api/phoneNumber/").append(phoneNumberId).append("/activate");

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.put(sb.toString()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void cantAddAPhoneNumber_empty() throws Exception {
		String uri = "/api/phoneNumber";

		PhoneNumber phoneNumber = new PhoneNumber();

		String inputJson = super.mapToJson(phoneNumber);

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		
		// expects 400 error code
		// ideally would have a custom exception as well
		assertEquals(400, status);
	}
	
	/**
	 * More granular test such as catching missing params eg.
	 * - no customer ID
	 * - no phone number
	 * - no country code
	 * 
	 * etc.
	 * etc.
	 */
	
}
