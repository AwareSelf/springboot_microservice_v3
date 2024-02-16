package com.javainuse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class FirstController {

	@GetMapping("/message")
	public String test(@RequestHeader("myname") String header) {
		System.out.println(header);
		return "Hello JavaInUse Called in First Service"+header;
	}
	
	@GetMapping("/message1")
	public String test() {
	
		return "Hi ha ha";
	}
	
	@GetMapping("/messagex")
	public String testx(@RequestParam String empname) {
	
		return "Hi "+empname;
	}
}
