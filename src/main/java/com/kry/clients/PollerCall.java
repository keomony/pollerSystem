package com.kry.clients;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PollerCall {

    public Status getStatus(String uri) {

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                if (response.statusCode() == HttpStatus.OK.value()) {
                    return Status.OK;
                }
            }
        } catch (Exception e) {
            return Status.FAIL;
        }

        return Status.FAIL;
    }
}
