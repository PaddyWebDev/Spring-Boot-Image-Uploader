package com.userservice.project.repository;
import com.userservice.project.model.Image;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageStorageRepo extends JpaRepository<Image, Long> {

    Optional<Image> findByName(String FileName);
}
