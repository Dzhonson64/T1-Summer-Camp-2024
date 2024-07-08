package ru.t1.summer.camp.responseHelper;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface ResponseHelper {

    public String jsonStreamReader(HttpURLConnection connection) throws IOException;
}
