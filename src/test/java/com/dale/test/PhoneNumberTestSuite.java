package com.dale.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.dale.test.telecom.controller.PhoneNumberControllerTest;
import com.dale.test.telecom.services.PhoneNumberServiceTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	PhoneNumberControllerTest.class,
	PhoneNumberServiceTest.class
})
public class PhoneNumberTestSuite {

}
