package com.userservice.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.userservice.project.service.ImageStorageService;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/Image")

public class ImageController {
    @Autowired
    private ImageStorageService service;

    @PostMapping("/UploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("Image") MultipartFile file) {
        String uploadImage = service.uploadImg(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/GetImage/{FileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String FileName) {
        if (FileName == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Name is Required for the Image to be Deleted");

        byte[] ImgData = service.downloadImage(FileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(ImgData);
    }

    @DeleteMapping("/DeleteImage/{Name}")
    public ResponseEntity<?> deleteImage(@PathVariable String Name) {
        if (Name != null) {
            String removeImage = service.deleteImage(Name);
            return ResponseEntity.status(HttpStatus.OK).body(removeImage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Name is Required for the Image to be Deleted");
        }
    }

}
