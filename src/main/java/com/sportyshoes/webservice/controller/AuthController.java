package com.sportyshoes.webservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.webservice.entity.Login;
import com.sportyshoes.webservice.entity.User;
import com.sportyshoes.webservice.exceptions.AuthenticationFailedException;
import com.sportyshoes.webservice.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class AuthController {
	HttpSession session;

	@Autowired
	UserRepository userRepo;

	@PostMapping("/login")
	public String login(@RequestBody Login login, HttpServletRequest request) {
		if (session == null) {

			if (login.getEmail().equals("admin@gmail.com") && login.getPassword().equals("admin")) {
				session = request.getSession(true);
				session.setAttribute("role", "admin");
				if (session != null) {
					return "Login Success";
				}
				else {
					throw new AuthenticationFailedException("Admin Login Failed");
				}
			}
			else {
				String username = login.getEmail();
				String password = login.getPassword();
				List<User> users = userRepo.findAll();
				for (User u : users) {
					if (u.getEmail().equals(username)) {
						if (u.getPassword().equals(password)) {
							session = request.getSession(true);
							session.setAttribute("role", "user");
							session.setAttribute("id", u.getId());
							return "User Login Success";
						}
						else {
							throw new AuthenticationFailedException("Invalid User Creds");
						}
					}
				}
			}
			throw new AuthenticationFailedException("Invalid User Creds");
		}
		else {
			return "Already Logged In";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
			session = null;
			return "Logged Out";
		}
		else {
			return "Login First";
		}
		
	}

	@PostMapping("/signup")
	public User signUp(@RequestBody User user) {
		return userRepo.save(user);
	}
}
