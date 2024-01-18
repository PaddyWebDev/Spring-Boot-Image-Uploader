package com.userservice.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;	
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.userservice.project.service.ImageStorageService;

@SpringBootApplication
@RestController



@RequestMapping("/Image")
public class ProjectApplication {

	@Autowired
	private ImageStorageService service;

	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("Image") MultipartFile file) {
		String uploadImage = service.uploadImg(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("/{FileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String FileName) {
		byte[] ImgData = service.downloadImage(FileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(ImgData);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
