package com.sportyshoes.webservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.webservice.entity.User;
import com.sportyshoes.webservice.exceptions.AuthenticationFailedException;
import com.sportyshoes.webservice.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class UserController {
	HttpSession session;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/users")
	public List<User> getUsers(HttpServletRequest request){
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("admin")) {
			return userRepo.findAll();
		}
		else {
			throw new AuthenticationFailedException("Unable to authenticate admin");
		}
	}
	
	@PostMapping("/users")
	public User signUp(@RequestBody User user,HttpServletRequest request) {
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("admin")) {
			return userRepo.save(user);
		}
		else {
			throw new AuthenticationFailedException("Unable to authenticate admin");
		}
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user,HttpServletRequest request) {
		session = request.getSession(false);
		
		if(session!=null && session.getAttribute("role").equals("admin")) {
			User u = userRepo.findById(user.getId()).get();
			if(u!=null) {
				
				u.setUsername(user.getUsername());
				u.setPassword(user.getPassword());
				u.setEmail(user.getEmail());
				u.setAddress(user.getAddress());
				
				return userRepo.save(u);
			}
			else {
				return null; // add exception here
			}
			
		}
		else {
			throw new AuthenticationFailedException("Unable to authenticate admin");
		}
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable long id,HttpServletRequest request) {
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("admin")) {
			User u = userRepo.findById(id).get();
			userRepo.delete(u);
			return "User Deleted Successfully";
		}
		else {
			throw new AuthenticationFailedException("Unable to authenticate admin");
		}
	}
}
