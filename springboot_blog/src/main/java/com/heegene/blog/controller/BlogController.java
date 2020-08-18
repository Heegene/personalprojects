package com.heegene.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {
	@GetMapping("/test")
	public String test() {
		return "Hello, world!";
	}
}
