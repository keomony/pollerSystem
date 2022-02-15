package com.kry.services;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.repositories.PollerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PollerServiceTest {

    @InjectMocks
    private PollerService underTest;

    @Mock
    private PollerRepository pollerRepository;

    @BeforeEach
    public void setup() {
        underTest = new PollerService(pollerRepository);
    }

    @Test
    public void storeAPoller_shouldBeSavedInTheRepository() throws PollerException {

        //Given
        Poller pollerStub = new Poller(1, "better", "www.good.com");

        //When
        underTest.save(pollerStub);

        //Then
        verify(pollerRepository, times(1)).save(pollerStub);

    }

    @Test
    public void storeAPoller_whenDataIsMissing_shouldThrowPullerException() {

        //Given
        Poller poller = new Poller(1, null, "www.good.com");

        //When
        PollerException result = assertThrows(PollerException.class,
                () -> underTest.save(poller)
        );

        //Then
        verify(pollerRepository, times(0)).save(poller);
        assertEquals("Name or Url is needed.", result.getMessage());
    }


    @Test
    public void retrieveAllPollers_shouldReturnAllPollers() {

        //Given
        Poller pollerStub2 = new Poller(2, "go", "www.go.com");
        Poller pollerStub1 = new Poller(1, "kr", "www.kr.com");
        when(pollerRepository.findAll()).thenReturn(List.of(pollerStub1, pollerStub2));

        //When
        Iterable<Poller> result = underTest.retrieveAllPollers();

        //Then

        assertEquals(List.of(pollerStub1, pollerStub2), result);

    }

    @Test
    public void deletePoller_shouldDeletePoller() {

        //Given
        Poller poller = new Poller(2, "go", "www.go.com");

        //When
        underTest.delete(poller.getId());

        //Then
        verify(pollerRepository, times(1)).deleteById(poller.getId());
    }


}
