package com.kry.clients;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PollerCallTest {

    private PollerCall underTest;

    private MockWebServer mockWebServer;

    @BeforeEach
    public void setup() throws IOException {

        mockWebServer = new MockWebServer();
        underTest = new PollerCall();
        mockWebServer.start();

    }

    @Test
    public void getStatus_shouldReturnOK() {

        //given
        String uri = mockWebServer.url("/").toString();
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200);

        mockWebServer.enqueue(mockResponse);

        //when
        Status actual = underTest.getStatus(uri);

        //then
        assertEquals(Status.OK, actual);
    }

    @Test
    public void getStatus_shouldReturnFAIL() {

        //given
        String uri = mockWebServer.url("/").toString();
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(300);

        mockWebServer.enqueue(mockResponse);

        //when
        Status actual = underTest.getStatus(uri);

        //then
        assertEquals(Status.FAIL, actual);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }


}