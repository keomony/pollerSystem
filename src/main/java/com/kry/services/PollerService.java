package com.kry.services;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.repositories.PollerRepository;
import org.springframework.stereotype.Service;

@Service
public class PollerService {

    private final PollerRepository pollerRepository;

    public PollerService(PollerRepository pollerRepository) {
        this.pollerRepository = pollerRepository;
    }

    public void storeAPoller(Poller poller) throws PollerException {

        if (poller.getUrl() == null || poller.getName() == null) {
            throw new PollerException("Something is wrong. The data is missing!");
        }

        try {
            pollerRepository.save(poller);

        } catch (Exception e) {
            throw new PollerException("Something is wrong. The data can not be saved!");
        }

    }

    public Iterable<Poller> retrieveAllPollers() {
        return pollerRepository.findAll();
    }

    public void deletePoller(String id) throws PollerException {

        if(id !=null ) {
            pollerRepository.deleteById(Integer.valueOf(id));

        } else {
            throw new PollerException("Something is wrong. The data can not be deleted! The id is empty");
        }
    }
}
