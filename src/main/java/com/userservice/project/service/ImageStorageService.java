
package com.userservice.project.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.userservice.project.model.UserData;
import com.userservice.project.repository.ImageStorageRepo;
import com.userservice.project.util.UserImgUtils;

@Service
public class ImageStorageService {

    @Autowired
    private ImageStorageRepo repo;

    public String uploadImg(MultipartFile file) {
        try {
            UserData userData = repo
                    .save(UserData.builder().name(file.getOriginalFilename()).type(file.getContentType())
                            .ImageData(UserImgUtils.compressImage(file.getBytes())).build());
            if (userData != null) {
                return "File Uploaded" + file.getOriginalFilename();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] downloadImage(String FileName) {
        Optional<UserData> userOptional = repo.findByName(FileName);
        byte[] Image = UserImgUtils.decompressImage(userOptional.get().getImageData());
        return Image;
    }
}
