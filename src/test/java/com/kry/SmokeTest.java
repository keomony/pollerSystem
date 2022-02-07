package com.kry;

import com.kry.controllers.PollerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private PollerController controller;

    //an assertion to check if the context really creates the controller
    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
