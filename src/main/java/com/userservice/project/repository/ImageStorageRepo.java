package com.userservice.project.repository;

import com.userservice.project.model.UserData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageStorageRepo extends JpaRepository<UserData, Long> {

    Optional<UserData> findByName(String FileName);
}
