package com.example.demo.controller;

import com.example.demo.auth.service.AccountRegistrationResult;
import com.example.demo.auth.service.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

@RestController
public class AuthController {

    @Autowired
    private AccountRegistrationService accountRegistrationService;

//    @Autowired
//    private AuthenticationService authenticationService;

    @RequestMapping(value = "/api/signup", method = {RequestMethod.GET, RequestMethod.POST})
    @CrossOrigin
    public void signup(@RequestBody SignupRequest requestBody) {
        //ValidatorUtil.validate(requestBody);
        String password = new BCryptPasswordEncoder().encode(requestBody.password);
        AccountRegistrationResult result = accountRegistrationService.register(requestBody.userName, password);
        if (result == AccountRegistrationResult.NAME_CONFLICT) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "userId is conflicted");
        }
    }

//    @PostMapping(value = "/api/login")
//    //@RequestMapping(value = "/api/login", method = {RequestMethod.GET, RequestMethod.PUT})
//    @CrossOrigin(origins = {"http://localhost:300"}, methods = RequestMethod.POST)
//    public void login(@RequestBody LoginRequest loginBody) {
//        String password = new BCryptPasswordEncoder().encode(loginBody.password);
//        AuthenticationResult result = authenticationService.authenticate(loginBody.userName, password);
//        if (result.isFailed()) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized");
//        }
//    }
//    @GetMapping(value = "/api/login")
//    @CrossOrigin
//    public String loginGet() {
//        return "login";
//    }
//
//    @PostMapping(value = "/api/logout")
//    @CrossOrigin
//    public void logout() {
//        return;
//    }
//
    public static class SignupRequest {
        @NotNull
        public String userName;

        @NotNull
        public String password;
    }
//
//    public static class LoginRequest {
//        @NotNull
//        public String userName;
//        @NotNull
//        public String password;
//    }
}
