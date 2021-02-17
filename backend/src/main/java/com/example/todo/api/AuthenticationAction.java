package com.example.todo.api;

import nablarch.common.web.session.SessionUtil;
import nablarch.core.validation.ee.ValidatorUtil;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpErrorResponse;
import nablarch.fw.web.HttpResponse;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

public class AuthenticationAction {

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void login(ExecutionContext executionContext, LoginRequest requestBody) {
        ValidatorUtil.validate(requestBody);

//        AuthenticationResult result = authenticationService.authenticate(requestBody.userName, requestBody.password);
//        if (result.isFailed()) {
//            throw new HttpErrorResponse(HttpResponse.Status.UNAUTHORIZED.getStatusCode());
//        }
        SessionUtil.invalidate(executionContext);
        SessionUtil.put(executionContext, "user.id", "test");//result.userId());
    }

    public static class LoginRequest {
        @NotNull
        public String userName;

        @NotNull
        public String password;
    }
}
