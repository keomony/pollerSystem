package com.kry.schedulingTasks;

import com.kry.clients.PollerCall;
import com.kry.clients.Status;
import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.services.PollerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdatePollerTest {

    @InjectMocks
    private UpdatePoller underTest;

    @Mock
    private PollerService pollerService;

    @Mock
    private PollerCall client;

    @Test
    public void updateStatus_shouldUpdateStatus() throws PollerException {

        //given
        Poller poller = new Poller(1, "google", "https://www.google.com");
        Iterable<Poller> pollers = Collections.singleton(poller);

        when(pollerService.retrieveAllPollers()).thenReturn(pollers);
        when(client.getStatus(any())).thenReturn(Status.OK);

        Optional<Poller> pollerToBeUpdated = Optional.of(pollers.iterator().next());
        when(pollerService.findById(poller.getId())).thenReturn(pollerToBeUpdated);


        //when
        underTest.updateStatus();

        //then
        assertEquals(Status.OK.name(), pollerToBeUpdated.get().getResponseStatus());
        verify(pollerService, times(1)).save(pollerToBeUpdated.get());
    }


}
