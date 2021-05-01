package com.QuasarFireOperationMS.repositories;

import com.QuasarFireOperationMS.domain.Satellite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jiz10
 * 1/5/21
 */
public interface SatelliteRepository extends CrudRepository<Satellite, UUID> {

    @Override
    List<Satellite> findAll();

    Optional<Satellite> findByName(String name);
}
