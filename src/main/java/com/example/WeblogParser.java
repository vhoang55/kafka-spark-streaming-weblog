package com.example;


import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class WeblogParser {

    private static final String uri = "http://localhost:8080/weblogs";
    private static String fileName = "./clickstreamweblogs.txt";
    private static final RestTemplate restTemplate = new RestTemplate();

    private void generateTraffic() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(l -> restTemplate.postForObject(uri, l, String.class));
        }
    }

    public static void main(String args[]) throws IOException {
        new WeblogParser().generateTraffic();
    }
}
