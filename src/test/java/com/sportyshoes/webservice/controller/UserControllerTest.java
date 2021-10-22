package com.sportyshoes.webservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sportyshoes.webservice.entity.Login;
import com.sportyshoes.webservice.entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Admin User Tests :User Contoller Test")
@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {
	@LocalServerPort
	private int randomPort;
	
	@Autowired
	TestRestTemplate template;
	
	@Test
	@DisplayName("Test: User Login Test")
	@BeforeAll
	public void loginTest() {
		String url = "http://localhost:"+randomPort+"/api/login/";
		Login login = new Login("admin@gmail.com","admin");
		HttpEntity<Login> requestObj = new HttpEntity<>(login);
		ResponseEntity<String> resp = template.postForEntity(url, requestObj, String.class);
		assertNotNull(resp.getBody());
		assertEquals(HttpStatus.OK , resp.getStatusCode());
	}
	
	@Test
	@DisplayName("Test: User List Test")
	public void userListTest() {
		String url = "http://localhost:"+randomPort+"/api/admin/users";
		ResponseEntity<User> resp = template.getForEntity(url, User.class);
		assertNotNull(resp.getBody());
		assertEquals(HttpStatus.OK , resp.getStatusCode());
	}
	
	@Test
	@DisplayName("Test: Logout Test")
	@AfterAll
	public void logoutTest() {
		String url = "http://localhost:"+randomPort+"/api/logout/";
		ResponseEntity<String> resp = template.getForEntity(url, String.class);
		
		assertNotNull(resp.getBody());
		assertEquals("Logged Out",resp.getBody());
		assertEquals(HttpStatus.OK , resp.getStatusCode());
	}
	
}
