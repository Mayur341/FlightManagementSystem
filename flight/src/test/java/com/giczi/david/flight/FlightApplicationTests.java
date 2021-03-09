package com.giczi.david.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
	
	
}
