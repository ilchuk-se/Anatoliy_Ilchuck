package com.epam.homework4.cinemawebapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CinemaWebAppApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		HttpStatus statusCode = restTemplate.getForEntity("http://localhost:" + port + "/swagger-ui.html", String.class).getStatusCode();
		assertEquals(statusCode, HttpStatus.OK);
	}
}
