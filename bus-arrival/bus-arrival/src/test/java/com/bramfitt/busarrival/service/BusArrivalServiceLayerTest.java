package com.bramfitt.busarrival.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bramfitt.busarrival.model.BusArrival;
import com.bramfitt.busarrival.repo.BusArrivalRepo;
import com.bramfitt.busarrival.service.BusArrivalServiceImpl;

@ExtendWith(SpringExtension.class)
public class BusArrivalServiceLayerTest {
	
	@MockBean
	private BusArrivalRepo busArrivalRepo;
	
//	@Autowired
	private BusArrivalServiceImpl busArrivalServiceImpl;
	
	private BusArrival ba1;
	private BusArrival ba2;
	
	private String time = "2022-09-09T15:00:00Z";

	
    @BeforeEach
    public void setUp() {
    	
    	busArrivalServiceImpl = new BusArrivalServiceImpl(busArrivalRepo);
    	
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
    }
    
    @AfterEach
    public void tearDown() {
        ba1 = null;
    }
    
    @Test
    public void saveBusArrivalsServiceImplTest() {

    	when(busArrivalRepo.save(ba1)).thenReturn(ba1);
    
    	assertEquals("Tolworth", busArrivalServiceImpl.addBusArrival(ba1).getTowards());
    }
    
    @Test
    public void searchBusArrivalsServiceTest() {

    	when(busArrivalRepo.findAll()).thenReturn(Stream.of(ba1, ba2).collect(Collectors.toList()));
    
    	assertEquals(2, busArrivalServiceImpl.searchHistory().size());
    	assertEquals("Lower Marsh Lane", busArrivalServiceImpl.searchHistory().get(0).getStationName());
    } 
    
    @Test
    public void pageableSearchBusArrivalsRepoTest() {
    	Pageable paging = PageRequest.of(0, 1);
    	List<BusArrival> list = new ArrayList<BusArrival>();
    	list.add(ba2);
    	Page<BusArrival> busArrivalPage = new PageImpl<BusArrival>(list);

    	when(busArrivalRepo.findAll(paging)).thenReturn(busArrivalPage);
    
    	assertEquals(1, busArrivalServiceImpl.pageSearchHistory(paging).size());
    	assertNotEquals("Lower Marsh Lane", busArrivalServiceImpl.pageSearchHistory(paging).get(0).getStationName());
    }
}
