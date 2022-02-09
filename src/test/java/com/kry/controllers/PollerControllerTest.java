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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PollerController.class)
@AutoConfigureMockMvc
public class PollerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PollerService pollerService;

    @Test
    public void addPoller_shouldRedirectToIndexPage() throws Exception {

        //Given

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/addpoller")
                        .param("id", "1")
                        .param("name", "test")
                        .param("url", "www.test.com")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                //Then
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/index"));

    }

    @Test
    public void getAllPollers_shouldReturnListOfPollers() throws Exception {

        //Given
        Poller pollerStub = new Poller(1, "kr", "www.kr.com");
        when(pollerService.retrieveAllPollers()).thenReturn(List.of(pollerStub));

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/index"))
                //Then
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("pollers", equalTo(List.of(pollerStub))))
                .andExpect(content().string(containsString("www.kr.com")));
    }

    @Test
    public void deletePoller_shouldRedirectToIndexPage() throws Exception, PollerException {

        //Given
        Poller pollerStub = new Poller(1, "kr", "www.kr.com");
        when(pollerService.findById(1)).thenReturn(Optional.of(pollerStub));

        //When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/delete/{id}", pollerStub.getId()))
                //Then
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/index"))
                .andExpect(model().attribute("pollers", equalTo(null)));

        //Then
        verify(pollerService, times(1)).delete(pollerStub.getId());

    }
}
