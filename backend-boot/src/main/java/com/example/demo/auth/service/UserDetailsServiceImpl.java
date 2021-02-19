package com.example.demo.auth.service;

import com.example.demo.auth.entity.AccountEntity;
import com.example.demo.auth.security.AppUserDetails;
import com.example.demo.auth.entity.UserProfileEntity;
import com.example.demo.auth.repository.AccountRepository;
import com.example.demo.auth.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AccountEntity accountEntity = accountRepository.findByUserName(userName);
        UserProfileEntity userProfileEntity = userProfileRepository.findByName(userName).iterator().next();

        UserDetails user = new AppUserDetails() //
                .builder() //
                .userId(userName) //
                .password(accountEntity.getPassword()) //
                .appUserName(accountEntity.getUserId()) //
                .build(); //
        return user;
    }
}
