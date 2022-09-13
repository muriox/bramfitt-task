package com.bramfitt.busarrival.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bramfitt.busarrival.model.BusArrival;
import com.bramfitt.busarrival.repo.BusArrivalRepo;
import com.bramfitt.busarrival.service.BusArrivalServiceImpl;

@ExtendWith(SpringExtension.class)
public class BusArrivalServiceLayerTest {
	
	@Autowired
	private BusArrivalRepo busArrivalRepo;
	
	private BusArrivalServiceImpl busArrivalServiceImpl;
	private BusArrival ba1;
	
    @BeforeEach
    public void setUp() {
    	
    	this.busArrivalRepo = Mockito.mock(BusArrivalRepo.class);    	
    	this.busArrivalServiceImpl = Mockito.mock(BusArrivalServiceImpl.class);
    	
    	String time = "2022-09-09T15:00:00Z";

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
        ba1 = null;
    }
    
    @Test
    public void addBusArrival() {
    	
    	this.busArrivalServiceImpl = new BusArrivalServiceImpl(this.busArrivalRepo);
    	
    	Mockito.doReturn(ba1).when(this.busArrivalServiceImpl).addBusArrival(ba1);
    	    	
    	verify(this.busArrivalServiceImpl, timeout(1)).addBusArrival(any());    	
    }

}
