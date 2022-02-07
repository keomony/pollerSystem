package com.kry.controllers;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.services.PollerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pollers")
public class PollerController {

    private final PollerService pollerService;

    public PollerController(PollerService pollerService) {
        this.pollerService = pollerService;
    }

    @PostMapping
    public ResponseEntity addNewPoller(@RequestBody Poller poller) {

        try {
            pollerService.storeAPoller(poller);

            return new ResponseEntity(HttpStatus.CREATED);

        } catch (PollerException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public @ResponseBody
    Iterable<Poller> getAllPollers() {
        // This returns a JSON  with the pollers
        return pollerService.retrieveAllPollers();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePoller(@PathVariable("id") String id) throws PollerException {
        pollerService.deletePoller(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
