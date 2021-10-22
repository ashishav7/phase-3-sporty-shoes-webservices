package com.sportyshoes.webservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.webservice.entity.Shoe;
import com.sportyshoes.webservice.entity.User;
import com.sportyshoes.webservice.exceptions.AuthenticationFailedException;
import com.sportyshoes.webservice.exceptions.ShoeNotFoundException;
import com.sportyshoes.webservice.repository.ShoeRepository;
import com.sportyshoes.webservice.repository.UserRepository;


@RestController
@RequestMapping("/api/user")
public class CartController {
	
	HttpSession session;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ShoeRepository shoeRepository;
	
	@GetMapping("/list")
	public List<Shoe> getShoes(HttpServletRequest request){
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("user")) {
			return shoeRepository.findAll();
		}
		else {
			return null;
		}
	}
	
	@GetMapping("/cart")
	public List<Shoe> getCartShoes(HttpServletRequest request){
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("user")) {
			long userId = (long) session.getAttribute("id");
			User user = userRepo.findById(userId).get();
			return user.getCart();
		}
		else {
			throw new AuthenticationFailedException("User is not authenticated");
		}
	}
	
	@PostMapping("/cart/{productId}")
	public User addToCart(@PathVariable long productId, HttpServletRequest request) {
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("user")) {
			long userId = (long) session.getAttribute("id");
			User user = userRepo.findById(userId).get();
			Shoe shoe = shoeRepository.findById(productId).get();
			if(shoe!=null) {
				List<Shoe> shoes = user.getCart();
				shoes.add(shoe);
				user.setCart(shoes);
				return userRepo.save(user);
			}
			else {
				throw new ShoeNotFoundException("Shoe with the id does not exist");
			}
		}
		else {
			throw new AuthenticationFailedException("User is not authenticated");
		}
	}
	
	@DeleteMapping("/cart/{id}")
	public User removeFromCart(@PathVariable long id,HttpServletRequest request) {
		session = request.getSession(false);
		if(session!=null && session.getAttribute("role").equals("user")) {
			Shoe shoe = shoeRepository.findById(id).get();
			if(shoe!=null) {
				long userId = (long) session.getAttribute("id");
				User user = userRepo.findById(userId).get();
				List<Shoe> shoes = user.getCart();
				shoes.remove(shoe);
				user.setCart(shoes);
				return userRepo.save(user);
			}
			else {
				throw new ShoeNotFoundException("Shoe with the id does not exist");
			}
		}
		else {
			throw new AuthenticationFailedException("User is not authenticated");
		}
	}
}
