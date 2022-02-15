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
            throw new PollerException("Name or Url is needed.");
        }

            pollerRepository.save(poller);

    }

    public Iterable<Poller> retrieveAllPollers() {
        return pollerRepository.findAll();
    }

    public void delete(Integer id) { pollerRepository.deleteById(id); }

    public Optional<Poller> findById(Integer id) { return pollerRepository.findById(id); }
}
