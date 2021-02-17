package com.example.demo.auth.service;

public class AuthenticationResult {
    private final Status status;

    private final String userId;

    private AuthenticationResult(Status status, String userId) {
        this.status = status;
        this.userId = userId;
    }

    public static AuthenticationResult success(String userId) {
        return new AuthenticationResult(Status.SUCCESS, userId);
    }

    public static AuthenticationResult passwordMismatch() {
        return new AuthenticationResult(Status.PASSWORD_MISMATCH, null);
    }

    public boolean isFailed() {
        return status != Status.SUCCESS;
    }

    public String userId() {
        if (isFailed()) {
            throw new UnsupportedOperationException();
        }
        return userId;
    }

    private enum Status {
        SUCCESS, PASSWORD_MISMATCH
    }
}
