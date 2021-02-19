package com.example.demo.auth.service;

import com.example.demo.auth.entity.AccountEntity;
import com.example.demo.auth.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Deprecated
public class AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    public AuthenticationResult authenticate(String userName, String password) {
        AccountEntity accountEntity = findAccount(userName);
        if (!password.equals(accountEntity.getPassword())) {
            return AuthenticationResult.passwordMismatch();
        }
        return AuthenticationResult.success((accountEntity.getUserId()));
    }

    private AccountEntity findAccount(String userName) {
        return accountRepository.findByUserName(userName);

    }
}
