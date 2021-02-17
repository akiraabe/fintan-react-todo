package com.example.demo.auth.repository;

import com.example.demo.auth.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @Query(value = "SELECT a.* FROM account a JOIN user_profile u ON a.user_id = u.user_id WHERE u.name = :name", nativeQuery = true)
    AccountEntity findByUserName(@Param("name") String userName);
}
