package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class Utils {
    public static String getURL(String NasaURL) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        HttpGet httpGet = new HttpGet(NasaURL);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            NASAAnswer answer = mapper.readValue(response.getEntity().getContent(), NASAAnswer.class);
            return answer.url;
        } catch (IOException e) {
            System.out.println("Сервер NASA недоступен");
        }
        return "";
    }
}
