package com.kry.schedulingTasks;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.services.PollerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class PollerCall {

    private final PollerService pollerService;

    //todo : fixedRate should be configurable
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void updateStatus() throws PollerException {

        Iterable<Poller> pollers = pollerService.retrieveAllPollers();

        if (pollers != null) {

            for (Poller poller : pollers) {

                Optional<Poller> pollerToBeUpdated = pollerService.findById(poller.getId());

                if (pollerToBeUpdated.isPresent()) {

                    pollerToBeUpdated.get().setResponseStatus(getStatus(poller.getUrl()));

                    pollerService.save(pollerToBeUpdated.get());

                }
            }
        }

    }

    private String getStatus(String uri) {

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                if (response.statusCode() == HttpStatus.OK.value()) {
                    return "OK";
                }
            }
        } catch (Exception e) {
            return "FAIL";
        }

        return "FAIL";
    }
}
