package com.example.demo.controller;

import com.example.demo.auth.service.AccountRegistrationResult;
import com.example.demo.auth.service.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AccountRegistrationService accountRegistrationService;

    @GetMapping(value = "/api/csrf_token")
    @CrossOrigin
    public Map<String,String> getCsrf(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        result.put("csrfTokenParameterName", "csrf-token");
        result.put("csrfTokenHeaderName", "X-XSRF-TOKEN");
        DefaultCsrfToken token = (DefaultCsrfToken) request.getAttribute("_csrf");
        result.put("csrfTokenValue", token.getToken());

        return result;
    }

    @RequestMapping(value = "/api/signup", method = {RequestMethod.GET, RequestMethod.POST})
    @CrossOrigin
    public void signup(@RequestBody SignupRequest requestBody) {
        System.out.println("signup is called");
        //ValidatorUtil.validate(requestBody);
        String password = new BCryptPasswordEncoder().encode(requestBody.password);
        AccountRegistrationResult result = accountRegistrationService.register(requestBody.userName, password);
        if (result == AccountRegistrationResult.NAME_CONFLICT) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "userId is conflicted");
        }
    }

    public static class SignupRequest {
        @NotNull
        public String userName;

        @NotNull
        public String password;
    }
}
