package ru.t1.summer.camp.connectionSetter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionSetterImpl implements ConnectionSetter {

    @Override
    public HttpURLConnection setConnection(String urlString, String requestMethod) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Content-Type", "application/json");
        if (requestMethod.equals("POST")) {
            connection.setDoOutput(true);
        }
        return connection;
    }
}
