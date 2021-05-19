package com.nikgri.demospringpizzaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoSpringPizzaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringPizzaApiApplication.class, args);
	}

	@GetMapping("/")
	public String hello(){
		return "Hello world!";
	}

}
