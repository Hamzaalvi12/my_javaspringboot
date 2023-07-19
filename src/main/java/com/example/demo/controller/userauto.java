package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;
import com.example.demo.entity.authentaction;
import com.example.demo.repository.authenctaction;

@Controller
@RequestMapping(path="/auto")
public class userauto {
	
	@Autowired
	private authenctaction a_repository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addProduct(@RequestParam String email, @RequestParam String password) {
		 authentaction a = new authentaction();
		    a.setEmail(email);
		    a.setPassword(password);
		    a_repository.save(a);
		    return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody List<authentaction> getAlldata(){
		return a_repository.findAll();
	}

}

