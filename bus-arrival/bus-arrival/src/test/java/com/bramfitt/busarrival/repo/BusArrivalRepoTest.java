package com.bramfitt.busarrival.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.bramfitt.busarrival.model.BusArrival;

@ExtendWith(SpringExtension.class)
public class BusArrivalRepoTest {

	@Autowired
	private BusArrivalRepo busArrivalRepo;
	private BusArrival ba1;
	private BusArrival ba2;

	private String time = "2022-09-09T15:00:00Z";
	
    @BeforeEach
    public void setUp() { 	

    	ba1 = new BusArrival();    	
    	ba1.setStationName("Lower Marsh Lane");
    	ba1.setDestinationName("New Malden");
    	ba1.setTowards("Tolworth");
    	ba1.setExpectedArrival("2022-09-09T15:37:09Z");
    	ba1.setTimeToLive("2022-09-09T15:37:39Z");
    	ba1.setTimeCreated(time);
    }
    
    @AfterEach
    public void tearDown() {
    	busArrivalRepo.deleteAll();
        ba1 = null;
    }
    
    @Test
    public void getAllBusArrivals() {
    	ba1 = new BusArrival();    	
    	ba1.setStationName("Lower Marsh Lane");
    	ba1.setDestinationName("New Malden");
    	ba1.setTowards("Tolworth");
    	ba1.setExpectedArrival("2022-09-09T15:37:09Z");
    	ba1.setTimeToLive("2022-09-09T15:37:39Z");
    	ba1.setTimeCreated(time);
    	
    	ba2 = new BusArrival();    	
    	ba2.setStationName("B-Lower Marsh Lane");
    	ba2.setDestinationName("B-New Malden");
    	ba2.setTowards("B-Tolworth");
    	ba2.setExpectedArrival("2022-09-10T15:37:09Z");
    	ba2.setTimeToLive("2022-09-10T15:37:39Z");
    	ba2.setTimeCreated(time);
    	
    	//
    	busArrivalRepo.save(ba1);
    	busArrivalRepo.save(ba2);
    	
    	
    	List<BusArrival> list = busArrivalRepo.findAll();
    	
    	assertEquals(ba1.getStationName(), list.get(0).getStationName());
    	
    }
    
}
