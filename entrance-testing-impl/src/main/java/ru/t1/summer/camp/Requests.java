package ru.t1.summer.camp;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class Requests {
    private static final String BASE_URL = "http://193.19.100.32:7000";
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getRoles() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/api/get-roles")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String signUp(String lastName, String firstName, String email, String role) throws IOException {
        String json = objectMapper.writeValueAsString(new SignUpRequest(lastName, firstName, email, role));
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(BASE_URL + "/api/sign-up")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String getCode(String email) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/api/get-code").newBuilder();
        urlBuilder.addQueryParameter("email", email);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String setStatus(String token, String status) throws IOException {
        String json = objectMapper.writeValueAsString(new SetStatusRequest(token, status));
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(BASE_URL + "/api/set-status")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private static class SignUpRequest {
        public String last_name;
        public String first_name;
        public String email;
        public String role;

        public SignUpRequest(String lastName, String firstName, String email, String role) {
            this.last_name = lastName;
            this.first_name = firstName;
            this.email = email;
            this.role = role;
        }
    }

    private static class SetStatusRequest {
        public String token;
        public String status;

        public SetStatusRequest(String token, String status) {
            this.token = token;
            this.status = status;
        }
    }
}