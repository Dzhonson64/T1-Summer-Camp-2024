package ru.t1.summer.camp.connectionSetter;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface ConnectionSetter {

    HttpURLConnection setConnection(String urlString, String requestMethod) throws IOException;
}
