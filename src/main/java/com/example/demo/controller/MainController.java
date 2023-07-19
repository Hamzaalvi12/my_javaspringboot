package com.example.demo.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private UserRepository u_repository;
	
	
	// @RequestBody Product product
	@PostMapping(path="/add")
	public @ResponseBody String addProduct(@RequestParam String name, @RequestParam String email, @RequestParam String Password) {
		 User n = new User();
		    n.setName(name);
		    n.setEmail(email);
		    n.setpassword(Password);
		    u_repository.save(n);
		    return "Saved";
	}
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllProducts(){
		return u_repository.findAll();
	}

   
    @DeleteMapping(path="/delete/{id}")
    public@ResponseBody String deleteProduct(@PathVariable Integer id) {
    Optional<User> optionalProduct = u_repository.findById(id);
    if (optionalProduct.isPresent()) {
        u_repository.deleteById(id);
        return "Product deleted";
    } else {
        return "Product not found";
    }
}
    @PutMapping(path="/update/{id}")
	public @ResponseBody String updateProduct(@PathVariable Integer id, @RequestParam String name, @RequestParam String email) {
	    Optional<User> optionalProduct = u_repository.findById(id);
	    if (optionalProduct.isPresent()) {
	        User u = optionalProduct.get();
	        u.setName(name);
	        u.setEmail(email);
	        u_repository.save(u);
	        return "Product updated";
	    } else {
	        return "Product not found";
	    }
	}
}