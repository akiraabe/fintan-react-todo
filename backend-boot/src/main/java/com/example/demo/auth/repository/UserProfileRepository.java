package com.example.demo.auth.repository;

import com.example.demo.auth.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String> {
}
