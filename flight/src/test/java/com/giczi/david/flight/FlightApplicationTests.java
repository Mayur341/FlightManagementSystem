package com.giczi.david.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.giczi.david.flight.service.FlightService;

@SpringBootTest
class FlightApplicationTests {

	private FlightService service;
	
	@Autowired
	public void setService(FlightService service) {
		this.service = service;
	}
	
	@Test
	void contextLoads() {
		assertNotNull(service);
	}
	
	@Test
	void encode() {
		String encoded = service.encodePassword("Tündérgalambocska79");
		System.out.println(encoded);
		assertEquals("VMO8bmTDqXJnYWxhbWJvY3NrYTc5", encoded);
	}


	@Test
	void decode() {
		String decoded = service.decodePassword("VMO8bmTDqXJnYWxhbWJvY3NrYTc5");
		System.out.println(decoded);
		assertEquals("Tündérgalambocska79", decoded);
	}
	
	@Test
	void encodeNegativeTest() {
		String encoded = service.encodePassword("Tündérgalambocska79");
		assertNotEquals("VMO8bmTDqXJnYWxhbWJvY3NrYTc9", encoded);
	}


	@Test
	void decodeNegativTest() {
		String decoded = service.decodePassword("VMO8bmTDqXJnYWxhbWJvY3NrYTc5");
		assertNotEquals("Tündérgalambocska78", decoded);
	}
	
	@Test
	void userNameValidationTest1() {
		assertFalse(service.isValidUsername(""), "e-mail address should not be empty");
	}
	
	@Test
	void userNameValidationTest2() {
		assertFalse(service.isValidUsername("emailAdresswithoutAt"), "e-mail address should contain @");
	}
	
	@Test
	void userNameValidationTest3() {
		assertTrue(service.isValidUsername("abc@noname.hu"), "it is a correct e-mail address");
	}
	
	@Test
	void userNameValidationTest4() {
		assertFalse(service.isValidUsername("a@noname.com"), "e-mail address should be at least 3 length before @ ");
	}
	
	@Test
	void userNameValidationTest5() {
		assertFalse(service.isValidUsername("abc@no.com"), "e-mail address should be at least 3 length after @ ");
	}
	
	@Test
	void userNameValidationTest6() {
		assertFalse(service.isValidUsername("abc@noname"), "e-mail address should contain . after @");
	}
	
	@Test
	void userNameValidationTest7() {
		assertFalse(service.isValidUsername("abc@noname.c"), "e-mail address should be at least 2 length after .");
	}
	
	@Test
	void userNameValidationTest8() {
		assertFalse(service.isValidUsername("abc@non@ame.com"), "e-mail address should contain one @");
	}
	
	@Test
	void userNameValidationTest9() {
		assertFalse(service.isValidUsername("abc@non.ame.com"), "e-mail address should contain one . after @");
	}
	
	@Test
	void userNameValidationTest10() {
		assertTrue(service.isValidUsername("abc.efg@noname.com"), "it is a correct e-mail address");
	}
	
	@Test
	void passwordNameValidationTest1() {
		assertTrue(service.isValidPassword("LoCalhero8$"), "it is a correct password");
	}
	
	@Test
	void passwordNameValidationTest2() {
		assertFalse(service.isValidPassword("Localhero8$"), "password should contain at least 2 uppercase chars");
	}
	
	@Test
	void passwordNameValidationTest3() {
		assertFalse(service.isValidPassword("LoCalhero$"), "password should contain at least 1 digit char");
	}
	
	@Test
	void passwordNameValidationTest4() {
		assertFalse(service.isValidPassword("LoCalhero6"), "password should contain at least 1 non-char");
	}
	
	
}
