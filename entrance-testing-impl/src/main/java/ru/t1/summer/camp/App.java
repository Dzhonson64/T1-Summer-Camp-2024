package ru.t1.summer.camp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import ru.t1.summer.camp.pojo.Roles;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class App {
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final String emailToRegister;
    private final URI[] methodURIs;

    public App(String emailToRegister) {
        this.emailToRegister = emailToRegister;
        methodURIs = buildMethodURIs();
    }

    public void Register() {
        try {
            String role = doFirstRequest();
            doSecondRequest(role);
            String code = doThirdRequest();
            doFourthRequest(code);

            System.out.println("Registration successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String doFirstRequest() throws IOException {
        String jsonBody = Request.get(methodURIs[0])
                .execute()
                .returnContent()
                .asString(StandardCharsets.UTF_8);

        Roles roles = jsonMapper.readValue(jsonBody, Roles.class);

        return Arrays.stream(roles.getRoles())
                .filter(role -> role.contains("Java") && !role.contains("script"))
                .findFirst()
                .orElseThrow();
    }

    private void doSecondRequest(String role) throws Exception {
        String jsonBody = buildSecondRequestBody(role);

        Request.post(methodURIs[1])
                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                .execute();
    }

    private String doThirdRequest() throws IOException {
        String code = Request.get(methodURIs[2])
                .execute()
                .returnContent()
                .asString(StandardCharsets.UTF_8);

        // Removing redundant " from both sides of code
        return code.substring(1, code.length() - 1);
    }

    private void doFourthRequest(String code) throws IOException {
        String jsonBody = buildFourthRequestBody(code);

        Request.post(methodURIs[3])
                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                .execute();
    }

    private URI[] buildMethodURIs() {
        String apiPath = "http://193.19.100.32:7000/api/";
        final String queryForThirdMethod = "?email=" + emailToRegister;

        final String firstMethod = "get-roles";
        final String secondMethod = "sign-up";
        final String thirdMethod = "get-code" + queryForThirdMethod;
        final String fourthMethod = "set-status";

        return new URI[]{
                URI.create(apiPath + firstMethod),
                URI.create(apiPath + secondMethod),
                URI.create(apiPath + thirdMethod),
                URI.create(apiPath + fourthMethod),
        };
    }

    private String buildSecondRequestBody(String role) {
        return jsonMapper.createObjectNode()
                .put("last_name", "Фамилия")
                .put("first_name", "Имя")
                .put("email", emailToRegister)
                .put("role", role)
                .toString();
    }

    private String buildFourthRequestBody(String code) {
        TokenEncoder encoder = new TokenEncoder();
        String encodedToken = encoder.encodeToken(emailToRegister, code);

        return jsonMapper.createObjectNode()
                .put("token", encodedToken)
                .put("status", "increased")
                .toString();
    }
}
