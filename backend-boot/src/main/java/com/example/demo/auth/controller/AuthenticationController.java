package com.example.demo.auth.controller;

import com.example.demo.auth.service.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("/")
@RestController
public class AuthenticationController {

    @Autowired
    private AccountRegistrationService service;

    @PostMapping(name = "/signup")
    @CrossOrigin
    public void signup(@RequestBody SignupRequest requestbody) {
        //ValidatorUtil.validate(requestBody);
        service.register(requestbody.userName, requestbody.password);
    }


    public static class SignupRequest {
        @NotNull
        public String userName;

        @NotNull
        public String password;
    }
}
