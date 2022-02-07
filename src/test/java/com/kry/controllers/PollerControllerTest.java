package com.kry.controllers;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.services.PollerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PollerController.class)
@AutoConfigureMockMvc
public class PollerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PollerService pollerService;

    @Test
    public void addNewPoller_shouldReturnCreated_whenSuccessful() throws Exception {

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/pollers")
                        .content("{\"name\":\"test\",\"url\":\"www.test.com\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void addNewPoller_shouldThrowError_whenUnsuccessful() throws PollerException, Exception {

        //Given
        doThrow(new PollerException("Something is wrong. The data is missing!")).when(pollerService).storeAPoller(any());

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/pollers")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }


    @Test
    public void getAllPollers_shouldReturnListOfPollers() throws Exception {

        //Given
        Poller pollerStub = new Poller(1, "kr", "www.kr.com");
        when(pollerService.retrieveAllPollers()).thenReturn(List.of(pollerStub));

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/pollers"))
                //Then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"name\": \"kr\", \"url\":\"www.kr.com\"}]"));
    }

    @Test
    public void deletePoller_shouldReturnOKStatus() throws Exception, PollerException {

        //Given

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/pollers/delete/1"))
                //Then
                .andExpect(status().isNoContent());

        //Then
        verify(pollerService, times(1)).deletePoller("1");

    }
}
