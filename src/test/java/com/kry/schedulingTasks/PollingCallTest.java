package com.kry.schedulingTasks;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.services.PollerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PollingCallTest {

    @InjectMocks
    private PollerCall underTest;

    @Mock
    private PollerService pollerService;

    @BeforeEach
    public void setup() {
        underTest = new PollerCall(pollerService);
    }

    @Test
    public void updateStatus_shouldUpdateStatus() throws PollerException {

        //given
        Poller poller = new Poller(1, "google", "https://www.google.com");
        Iterable<Poller> pollers = Collections.singleton(poller);
        Optional<Poller> pollerToBeUpdated = Optional.of(poller);

        when(pollerService.retrieveAllPollers()).thenReturn(pollers);
        when(pollerService.findById(poller.getId())).thenReturn(pollerToBeUpdated);


        //when
        underTest.updateStatus();

        //then
        verify(pollerService, times(1)).save(pollerToBeUpdated.get());
    }


}
