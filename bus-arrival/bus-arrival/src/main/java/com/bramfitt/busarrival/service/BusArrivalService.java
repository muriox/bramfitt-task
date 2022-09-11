package com.bramfitt.busarrival.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bramfitt.busarrival.model.BusArrival;


public interface BusArrivalService {
	
	BusArrival addBusArrival(BusArrival ba);
	
	List<BusArrival> searchHistory();
	
	List<BusArrival> pageSearchHistory(Pageable pageable);
}
