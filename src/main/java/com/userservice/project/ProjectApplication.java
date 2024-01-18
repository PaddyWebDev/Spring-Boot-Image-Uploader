package com.userservice.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class ProjectApplication {
	@GetMapping("/")
	public String Main() {
		return "Welcome to Spring Boot Image Uploader.... <br/> This project is for Image Upload and download functionality";
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
