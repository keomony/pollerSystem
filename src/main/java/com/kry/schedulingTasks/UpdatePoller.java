package com.kry.schedulingTasks;

import com.kry.clients.PollerCall;
import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.services.PollerService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class UpdatePoller {

    private final PollerService pollerService;
    private final PollerCall client;

    //todo : fixedRate should be configurable
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void updateStatus() throws PollerException {

        Iterable<Poller> pollers = pollerService.retrieveAllPollers();

        if (pollers != null) {

            for (Poller poller : pollers) {

                Optional<Poller> pollerToBeUpdated = pollerService.findById(poller.getId());

                if (pollerToBeUpdated.isPresent()) {

                    String updatedStatus = client.getStatus(poller.getUrl()).name();
                    pollerToBeUpdated.get().setResponseStatus(updatedStatus);

                    pollerService.save(pollerToBeUpdated.get());

                }
            }
        }

    }

}
