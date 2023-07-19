package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	@RequestMapping(value="/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/signup")
	public String signup() {
		return "signup";
	}
		
	@RequestMapping(value="/interface")
	public String index() {
		return "interface";
	}
	@RequestMapping(value="/addUser")
	public String add() {
		return "addUser";
	}
	@RequestMapping(value="/deletUser")
	public String delet() {
		return "deletUser";
	}
	@RequestMapping(value="/updateUser")
	public String update() {
		return "updateUser";
	}
}


