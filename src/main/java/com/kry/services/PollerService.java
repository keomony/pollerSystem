package com.kry.services;

import com.kry.exceptions.PollerException;
import com.kry.models.Poller;
import com.kry.repositories.PollerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PollerService {

    private final PollerRepository pollerRepository;

    public void save(Poller poller) throws PollerException {

        if (poller.getUrl() == null || poller.getName() == null) {
            throw new PollerException("Something is wrong. The data is missing! ");
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

    public void delete(Integer id) throws PollerException {

        if(id !=null ) {
            pollerRepository.deleteById(id);

        } else {
            throw new PollerException("Something is wrong. The data can not be deleted! The id is empty");
        }
    }

    public Optional<Poller> findById(Integer id) {
        return pollerRepository.findById(id);
    }
}
