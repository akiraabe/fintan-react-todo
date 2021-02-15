package com.example.demo.auth.repository;

import com.example.demo.auth.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
