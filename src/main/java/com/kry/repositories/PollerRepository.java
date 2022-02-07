package com.kry.repositories;

import com.kry.models.Poller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollerRepository extends CrudRepository<Poller, Integer> {

}
