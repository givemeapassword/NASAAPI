package org.example;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://api.nasa.gov/planetary/apod?api_key=0EQ5jMdJas2pdA2jSc6oTM87wIF99gfh1WjfqRQL";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);

//        Scanner scanner = new Scanner(response.getEntity().getContent());
//        System.out.println(scanner.nextLine());

        NASAAnswer answer = mapper.readValue(response.getEntity().getContent(), NASAAnswer.class);
        String[] answerSeparate = answer.url.split("/");
        String title = answerSeparate[answerSeparate.length - 1];
        System.out.println(title);

        HttpGet httpGetImage = new HttpGet(answer.url);
        CloseableHttpResponse image = httpClient.execute(httpGetImage);

        FileOutputStream fileOutputStream = new FileOutputStream("Image.jpg");
        image.getEntity().writeTo(fileOutputStream);
    }
}