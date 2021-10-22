package com.sportyshoes.webservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.annotation.Rollback;

import com.sportyshoes.webservice.entity.Login;
import com.sportyshoes.webservice.entity.User;
import com.sportyshoes.webservice.repository.UserRepository;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Authentication Tests :Auth Test")
public class AuthControllerTest {
	
	@LocalServerPort
	private int randomPort;
	
	@Autowired
	TestRestTemplate template;
	
	@Autowired
	UserRepository userRepo;
	
	@Test
	@DisplayName("Test: Login Test")
	public void loginTest() {
		String url = "http://localhost:"+randomPort+"/api/login/";
		Login login = new Login("admin@gmail.com","admin");
		HttpEntity<Login> requestObj = new HttpEntity<>(login);
		ResponseEntity<String> resp = template.postForEntity(url, requestObj, String.class);
		assertNotNull(resp.getBody());
		assertEquals("Login Success",resp.getBody());
		assertEquals(HttpStatus.OK , resp.getStatusCode());
	}
	
	@Test
	@DisplayName("Test: Logout Test")
	public void logoutTest() {
		String url = "http://localhost:"+randomPort+"/api/logout/";
		ResponseEntity<String> resp = template.getForEntity(url, String.class);
		
		assertNotNull(resp.getBody());
		assertEquals("Logged Out",resp.getBody());
		assertEquals(HttpStatus.OK , resp.getStatusCode());
	}
	
	@Test
	@DirtiesContext
	@IgnoreForBinding
	public void signup() {
		System.out.println("1");
		User expected = new User("Sachin","sachin@gmail.com","sachin","Jaipur");	
		User actual= userRepo.save(expected);
		assertEquals(expected,actual);
	}
}
