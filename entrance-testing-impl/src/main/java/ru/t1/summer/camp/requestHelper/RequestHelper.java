package ru.t1.summer.camp.requestHelper;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface RequestHelper {

    void jsonStreamOutput(HttpURLConnection connection, String jsonInputString) throws IOException;
}
