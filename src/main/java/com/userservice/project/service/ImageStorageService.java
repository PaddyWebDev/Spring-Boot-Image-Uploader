
package com.userservice.project.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.userservice.project.model.Image;
import com.userservice.project.repository.ImageStorageRepo;
import com.userservice.project.util.UserImgUtils;

@Service
public class ImageStorageService {

    @Autowired
    private ImageStorageRepo repo;

    public String uploadImg(MultipartFile file) {
        try {
            Image image = repo
                    .save(Image.builder().name(file.getOriginalFilename()).type(file.getContentType())
                            .ImageData(UserImgUtils.compressImage(file.getBytes())).build());
            if (image != null) {
                return "File Uploaded" + file.getOriginalFilename();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteImage(String FileName) {
        try {
            Optional<Image> image = repo.findByName(FileName);
            if (image.isEmpty()) {
                return "The Image Doesn't Exist";
            } else {
                Long Id = image.get().getId();
                repo.deleteById(Id);
                return "Deleted Successfully";
            }
        } catch (Exception e) {
            return "Error Occured" + e.getMessage();
        }
    }

    public byte[] downloadImage(String FileName) {
        Optional<Image> imageData = repo.findByName(FileName);
        if (imageData.isEmpty()) {
            return null;
        }
        byte[] Image = UserImgUtils.decompressImage(imageData.get().getImageData());
        return Image;

    }
}
