package com.example.authentication;

import com.example.openapi.OpenApiValidator;
import nablarch.common.web.session.SessionUtil;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpResponse;
import nablarch.fw.web.RestMockHttpRequest;
import nablarch.test.core.http.SimpleRestTestSupport;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.nio.file.Paths;
import java.util.Map;

public class AuthenticationRestApiTest extends SimpleRestTestSupport {

    public static OpenApiValidator openApiValidator = new OpenApiValidator(Paths.get("rest-api-specification/openapi.yaml"));

    @Test
    public void RESTAPIでサインアップできる() throws Exception {
        RestMockHttpRequest request = post("/api/signup")
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(Map.of(
                        "userName", "signup-test",
                        "password", "pass"));
        HttpResponse response = sendRequest(request);

        assertStatusCode("サインアップ", HttpResponse.Status.NO_CONTENT, response);

        openApiValidator.validate("signup", request, response);
    }

    @Test
    public void 名前が登録済みの場合_サインアップに失敗して409になる() throws Exception {
        RestMockHttpRequest firstRequest = post("/api/signup")
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(Map.of(
                        "userName", "signup-conflict-test",
                        "password", "pass"));
        sendRequest(firstRequest);

        RestMockHttpRequest secondRequest = post("/api/signup")
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(Map.of(
                        "userName", "signup-conflict-test",
                        "password", "pass"));
        HttpResponse response = sendRequest(secondRequest);

        assertStatusCode("サインアップ", HttpResponse.Status.CONFLICT, response);

        openApiValidator.validate("signup", secondRequest, response);
    }

    @Test
    public void RESTAPIでログインできる() throws Exception {
        RestMockHttpRequest request = post("/api/login")
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(Map.of(
                        "userName", "login-test",
                        "password", "pass"));
        HttpResponse response = sendRequest(request);

        assertStatusCode("ログイン", HttpResponse.Status.NO_CONTENT, response);

        openApiValidator.validate("login", request, response);
    }

    @Test
    public void パスワードが不一致の場合_ログインに失敗して401になる() throws Exception {
        RestMockHttpRequest request = post("/api/login")
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(Map.of(
                        "userName", "login-test",
                        "password", "fail"));
        HttpResponse response = sendRequest(request);

        assertStatusCode("ログイン", HttpResponse.Status.UNAUTHORIZED, response);

        openApiValidator.validate("login", request, response);
    }

    @Test
    public void 名前が不一致の場合_ログインに失敗して401になる() throws Exception {
        RestMockHttpRequest request = post("/api/login")
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(Map.of(
                        "userName", "fail-test",
                        "password", "pass"));
        HttpResponse response = sendRequest(request);

        assertStatusCode("ログイン", HttpResponse.Status.UNAUTHORIZED, response);

        openApiValidator.validate("login", request, response);
    }

    @Test
    public void RESTAPIでログアウトできる() throws Exception {

        ExecutionContext executionContext = new ExecutionContext();
        SessionUtil.put(executionContext, "user.id", "1010");

        RestMockHttpRequest request = post("/api/logout");
        HttpResponse response = sendRequestWithContext(request, executionContext);

        assertStatusCode("ログアウト", HttpResponse.Status.NO_CONTENT, response);

        openApiValidator.validate("logout", request, response);
    }
}