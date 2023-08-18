package com.example.demo.controller;


import java.util.Optional;
import com.example.demo.service.jwtService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
//Controller class to handle requests related to products and authentication
//Rest controller
@Controller
@RequestMapping(path="/demo")
public class MainController {
	//repository for product operations
	@Autowired
	private ProductRepository p_repository;
	//JWT service for authentication and token generation
	@Autowired
    private jwtService jwtService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	// @RequestBody Product product
	@PostMapping(path="/add")
	public @ResponseBody String addProduct(@RequestParam String name, @RequestParam Integer quantity, @RequestParam Double price ) {
		Product p=new Product();
		p.setName(name);
		p.setPrice(price);
		p.setQuantity(quantity);
		p_repository.save(p);
		return "saved";
	}
	
	
	@GetMapping(path="/all")
	public @ResponseBody  Iterable<Product> getAllProducts(){
		return p_repository.findAll();
	}
	
	
	@PutMapping(path="/update/{id}")
	public @ResponseBody String updateProduct(@PathVariable Integer id, @RequestParam String name, @RequestParam Integer quantity, @RequestParam Double price) {
	    Optional<Product> optionalProduct = p_repository.findById(id);
	    if (optionalProduct.isPresent()) {
	        Product p = optionalProduct.get();
	        p.setName(name);
	        p.setPrice(price);
	        p.setQuantity(quantity);
	        p_repository.save(p);
	        return "Product updated";
	    } else {
	        return "Product not found";
	    }
	}
	
	
	@DeleteMapping(path="/delete/{id}")
	public @ResponseBody String deleteProduct(@PathVariable Integer id) {
	    Optional<Product> optionalProduct = p_repository.findById(id);
	    if (optionalProduct.isPresent()) {
	        p_repository.deleteById(id);
	        return "Product deleted";
	    } else {
	        return "Product not found";
	    }
	}
	
	 @PostMapping("/authenticate")
	    public String authenticateAndGetToken(@RequestBody User authRequest) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(authRequest.getEmail());
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	 }
 

}