package com.sportyshoes.webservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.webservice.entity.Shoe;
import com.sportyshoes.webservice.exceptions.AuthenticationFailedException;
import com.sportyshoes.webservice.repository.ShoeRepository;

@RestController
@RequestMapping("/api/admin")
public class ShoeController {
	HttpSession session;
	
	@Autowired
	ShoeRepository shoeRepository;
	
	@GetMapping("/shoes")
	public List<Shoe> shoes(HttpServletRequest request){
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("admin")) {
			return shoeRepository.findAll();
		}
		else {
			throw new AuthenticationFailedException("Unable to authenticate admin");
		}
	}
	
	@PostMapping("/shoes")
	public Shoe createShoe(@RequestBody Shoe shoe,HttpServletRequest request) {
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("admin")) {
			return shoeRepository.save(shoe);
		}
		else {
			throw new AuthenticationFailedException("Unable to authenticate admin");
		}
	}
	
	@DeleteMapping("/shoes/{id}")
	public String deleteShoe(@PathVariable long id,HttpServletRequest request) {
		session = request.getSession(false);
		
		if(session!=null && session.getAttribute("role").equals("admin")) {
			Shoe s = shoeRepository.findById(id).get();
			shoeRepository.delete(s);
			return "Delete successfull";
			//else throw exception
		}
		else {
			throw new AuthenticationFailedException("Unable to authenticate admin");
		}
	}
}
