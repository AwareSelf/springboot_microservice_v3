package com.javainuse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class SecondController {

	@GetMapping("/message")
	public String test(@RequestHeader("second-request") String header) {
		System.out.println(header);
		return "Hello JavaInUse Called in second Service:header:"+header;
	}
	
	@GetMapping("/message1/{msg}")
	public String test1(@PathVariable String msg) {
		System.out.println(msg);
		return "Hello JavaInUse Called in second Service:msg:"+msg;
	}
	
	@GetMapping("/message2")
	public String test1() {
		
		return "Hello JavaInUse message2 Called in second Service";
	}

}
