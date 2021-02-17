package com.example.demo.auth.service;

import com.example.demo.auth.entity.AccountEntity;
import com.example.demo.auth.entity.AppUserDetails;
import com.example.demo.auth.entity.UserProfileEntity;
import com.example.demo.auth.repository.AccountRepository;
import com.example.demo.auth.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountEntity accountEntity = accountRepository.findByUserName(username);
        UserProfileEntity userProfileEntity = userProfileRepository.findByName(username).iterator().next();

        UserDetails user = new AppUserDetails() //
                .builder() //
                .userId(username) //
                .password(accountEntity.getPassword()) //
                .appUserName(username + accountEntity.getUserId()) //
                .build(); //
        return user;
    }
}
