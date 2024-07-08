package ru.t1.summer.camp.roles;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RolesController {

    public List<String> getRoles() throws IOException {
        final HttpUriRequest httpGet = new HttpGet("http://193.19.100.32:7000/api/get-roles");
        try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(httpGet)) {
            final HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new ClientProtocolException("Unexpected response status: " + response.getStatusLine().getStatusCode());
            }
            String res = EntityUtils.toString(entity);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
            JsonArray rolesArray = jsonObject.getAsJsonArray("roles");
            List<String> rolesList = new ArrayList<>();
            for (int i = 0; i < rolesArray.size(); i++) {
                rolesList.add(rolesArray.get(i).getAsString());
            }
            return rolesList;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
