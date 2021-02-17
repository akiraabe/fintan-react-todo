package com.example.demo.controller;

import com.example.demo.auth.service.AccountRegistrationResult;
import com.example.demo.auth.service.AccountRegistrationService;
import com.example.demo.auth.service.AuthenticationResult;
import com.example.demo.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

//@RequestMapping("/")
@RestController
public class AuthController {

    @Autowired
    private UserSession userSession;

    @Autowired
    private AccountRegistrationService accountRegistrationService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/api/signup")
    @CrossOrigin
    public void signup(@RequestBody SignupRequest requestBody) {
        //ValidatorUtil.validate(requestBody);
        AccountRegistrationResult result = accountRegistrationService.register(requestBody.userName, requestBody.password);
        if (result == AccountRegistrationResult.NAME_CONFLICT) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "userId is conflicted");
        }
    }

    @PostMapping(value = "/api/login")
    @CrossOrigin
    public void login(@RequestBody LoginRequest loginBody) {
        AuthenticationResult result = authenticationService.authenticate(loginBody.userName, loginBody.password);
        if (result.isFailed()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized");
        }
        userSession.setUserId(result.userId());
        System.out.println("**** " + userSession.getUserId());
    }

    @PostMapping(value = "/api/logout")
    @CrossOrigin
    public void logout() {
        userSession.setUserId(null);
    }

    public static class SignupRequest {
        @NotNull
        public String userName;

        @NotNull
        public String password;
    }

    public static class LoginRequest {
        @NotNull
        public String userName;
        @NotNull
        public String password;
    }
}
