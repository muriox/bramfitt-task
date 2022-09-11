package com.bramfitt.busarrival.controller;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bramfitt.busarrival.logic.BusArrivalsLogic;
import com.bramfitt.busarrival.model.BusArrival;
import com.bramfitt.busarrival.service.BusArrivalServiceImpl;


@RestController
public class BusArrivalAPIController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private final String busArrivalUrl = "https://api.tfl.gov.uk/StopPoint/490009333W/arrivals";
	
	private BusArrivalsLogic busArrivalsLogic = BusArrivalsLogic.getInstance();
	
	@Autowired
	private BusArrivalServiceImpl busArrivalServiceImpl;
	
	@RequestMapping(value="/arrivals", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String arrivals() throws JSONException {
		LOG.info("Get TFL Bus arrival info");
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(busArrivalUrl, String.class);
		
		LOG.info("Request content extract");	
		
		// Extract and set relevant BusArrival object
		List<BusArrival> busArrivalList = busArrivalsLogic.extractBusArrivalInfo(result);
		
		// Save bus arrival info
		for (BusArrival ba : busArrivalList) {
			busArrivalServiceImpl.addBusArrival(ba);
		}
		
		// Convert POJO to JSON
		String jsonStr = busArrivalsLogic.pojoToJsonString(busArrivalList);
		
		return jsonStr;
	}
	
	@RequestMapping(value="/history", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public String history(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "0") int size) {
		LOG.info("Get Stored search history");
		//System.out.println(" - page: " + page + " - Size: " + size);
		
		List<BusArrival> busArrivalList = new ArrayList<BusArrival>();
		
		try {		
			
			if(page <= 0 && size <= 0) {
				LOG.info("No pagination search");
				// Fetch all result from service impl
				busArrivalList = busArrivalServiceImpl.searchHistory();

			} else {
				LOG.info("Pagination search");
				// Use paging
				Pageable paging = PageRequest.of(page, size);
				busArrivalList = busArrivalServiceImpl.pageSearchHistory(paging);
			}	
			
			return busArrivalsLogic.pojoToJsonString(busArrivalList);

		} catch (Exception e) {
			LOG.error("ERROR: ", e);
			return "Retry or report issue. ERROR: " + e.getMessage();
		}		
	}
	
}
