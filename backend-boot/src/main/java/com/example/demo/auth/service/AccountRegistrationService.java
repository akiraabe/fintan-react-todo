package com.example.demo.auth.service;

import com.example.demo.auth.entity.AccountEntity;
import com.example.demo.auth.entity.UserProfileEntity;
import com.example.demo.auth.repository.AccountRepository;
import com.example.demo.auth.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class AccountRegistrationService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public AccountRegistrationResult register(String userName, String password) {
        if (existsAccount(userName)) {
            return AccountRegistrationResult.NAME_CONFLICT;
        }
        String userId = generateUserId();
        insertAccount(userId,password);
        insertUserProfile(userId, userName);
        return AccountRegistrationResult.SUCCESS;
    }

    private boolean existsAccount(String userName) {
        Iterable<UserProfileEntity> results = userProfileRepository.findByName(userName);
        return results.iterator().hasNext();
    }

    private String generateUserId() {
        return UUID.randomUUID().toString();
    }

    private void insertAccount(String userId, String password) {
        AccountEntity entity = new AccountEntity();
        entity.setUserId(userId);
        entity.setPassword(password);
        accountRepository.save(entity);
    }

    private void insertUserProfile(String userId, String userName) {
        UserProfileEntity entity = new UserProfileEntity();
        entity.setUserId(userId);
        entity.setName(userName);
        userProfileRepository.save(entity);
    }
}
