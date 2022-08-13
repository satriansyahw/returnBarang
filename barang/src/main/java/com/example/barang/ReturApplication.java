package com.example.barang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
public class ReturApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReturApplication.class, args);
	}
	@RestController
	static class GreetingController {

		@RequestMapping("/")
		String welcome() {
			return "Welcome hello world!";
		}
	}
}
