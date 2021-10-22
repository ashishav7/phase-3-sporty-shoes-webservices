package com.sportyshoes.webservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.sportyshoes.webservice.controller.AuthController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Application Tests :Main Test")
class ProjectPhase3ApplicationTests {

	@Autowired
	private AuthController authController;
	
	@LocalServerPort
	private int randomPort;
	
	@Test
	@DisplayName("Test: Load Application Context")
	void contextLoads() {
		assertNotNull(authController);
	}
	
}
