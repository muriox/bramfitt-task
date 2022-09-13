package com.bramfitt.busarrival.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bramfitt.busarrival.exception.ResourceNotFoundException;
import com.bramfitt.busarrival.model.BusArrival;
import com.bramfitt.busarrival.service.BusArrivalServiceImpl;


@RestController
@RequestMapping(value="/api/v1", produces=MediaType.APPLICATION_JSON_VALUE)
public class BusArrivalAPIController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private final String busArrivalUrl = "https://api.tfl.gov.uk/StopPoint/490009333W/arrivals";
	
	private BusArrivalServiceImpl busArrivalServiceImpl;

	public BusArrivalAPIController(BusArrivalServiceImpl busArrivalServiceImpl) {
		this.busArrivalServiceImpl = busArrivalServiceImpl;
	}
	
	@GetMapping(value="/arrivals")
	public ResponseEntity<List<BusArrival>> arrivals() {
		LOG.info("Get TFL Bus arrival info");
		
		RestTemplate restTemplate = new RestTemplate();
		List<BusArrival> busArrivalList = new ArrayList<>();

		LOG.info("Request content extract");		
		ResponseEntity<BusArrival[]> response = restTemplate.getForEntity(busArrivalUrl, BusArrival[].class);
		busArrivalList = Arrays.asList(response.getBody());
		
		if(busArrivalList.isEmpty() || busArrivalList == null) {
			System.out.println("Here 1");
			throw new ResourceNotFoundException("Empty. No data found");
		}

		// Save bus arrival info
		for (BusArrival ba : busArrivalList) {
			ba.setTimeCreated(LocalDateTime.now().toString());
			busArrivalServiceImpl.addBusArrival(ba);
		}

		// Check for data
		if (busArrivalList.isEmpty()) {			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		} else {			
			return new ResponseEntity<>(busArrivalList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/history")
	public  ResponseEntity<List<BusArrival>> history(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "0") int size) {
		LOG.info("Get Stored search history");
		//System.out.println(" - page: " + page + " - Size: " + size);
		
		List<BusArrival> busArrivalList = new ArrayList<BusArrival>();
		if(page <= 0 && size <= 0) {
			LOG.info("No pagination search");
			// Fetch all result from service impl
			busArrivalList = busArrivalServiceImpl.searchHistory();

		} else {
			LOG.info("Pagination search");
			// Use pagination
			Pageable paging = PageRequest.of(page, size);
			busArrivalList = busArrivalServiceImpl.pageSearchHistory(paging);
		}	
		
		//
		if (busArrivalList.isEmpty()) {			
			throw new ResourceNotFoundException("Empty. No data found");
		} else {			
			return new ResponseEntity<>(busArrivalList, HttpStatus.OK);
		}
	}
	
}
