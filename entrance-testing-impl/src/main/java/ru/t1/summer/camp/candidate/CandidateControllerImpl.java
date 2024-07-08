package ru.t1.summer.camp.candidate;

import ru.t1.summer.camp.connectionSetter.ConnectionSetter;
import ru.t1.summer.camp.connectionSetter.ConnectionSetterImpl;
import ru.t1.summer.camp.encoder.Encoder;
import ru.t1.summer.camp.encoder.EncoderImpl;
import ru.t1.summer.camp.requestHelper.RequestHelper;
import ru.t1.summer.camp.requestHelper.RequestHelperImpl;
import ru.t1.summer.camp.responseHelper.ResponseHelper;
import ru.t1.summer.camp.responseHelper.ResponseHelperImpl;

import java.io.IOException;
import java.net.HttpURLConnection;

public class CandidateControllerImpl implements CandidateController {
    
    private final Encoder encoder = new EncoderImpl();

    private final RequestHelper request = new RequestHelperImpl();

    private final ResponseHelper response = new ResponseHelperImpl();

    private final ConnectionSetter connectionSetter = new ConnectionSetterImpl();
    
    @Override
    public String signUp(String lastName, String firstName, String email, String role) {
        try {
            HttpURLConnection connection = connectionSetter.setConnection("http://193.19.100.32:7000/api/sign-up", "POST");
            String jsonInputString = "{\"last_name\":\"" + lastName + "\",\"first_name\":\"" + firstName + "\",\"email\":\"" + email + "\",\"role\":\"" + role + "\"}";
            request.jsonStreamOutput(connection, jsonInputString);
            return response.jsonStreamReader(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCode(String email) {
        try {
            HttpURLConnection connection = connectionSetter.setConnection("http://193.19.100.32:7000/api/get-code?email=" + email, "GET");
            return response.jsonStreamReader(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setStatus(String email, String code) {
        try {
            HttpURLConnection connection = connectionSetter.setConnection("http://193.19.100.32:7000/api/set-status", "POST");
            String token = encoder.encodeToken(email, code);
            String jsonInputString = "{\"token\":\"" + token + "\", \"status\":\"increased\"}";
            request.jsonStreamOutput(connection, jsonInputString);
            return response.jsonStreamReader(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
