package com.bramfitt.busarrival.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bramfitt.busarrival.model.BusArrival;

@Repository
public interface BusArrivalRepo extends MongoRepository<BusArrival, String> {

}
