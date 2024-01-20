
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
            Image imageData = repo
                    .save(Image.builder().name(file.getOriginalFilename()).type(file.getContentType())
                            .ImageData(UserImgUtils.compressImage(file.getBytes())).build());

            if (imageData != null) {
                return "File Uploaded" + file.getOriginalFilename();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] downloadImage(String FileName) {
        Optional<Image> imageOptional = repo.findByName(FileName);
        byte[] Image = UserImgUtils.decompressImage(imageOptional.get().getImageData());
        return Image;
    }

    public String deleteImage(String Name) {
        Optional<Image> image = repo.findByName(Name);
        if (image.isPresent()) {
            Long Id = image.get().getId();
            repo.deleteById(Id);
            return "Deleted Successfully";
        }
        return "Failed";
    }
}
