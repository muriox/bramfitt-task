package com.bramfitt.busarrival.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bramfitt.busarrival.model.BusArrival;
import com.bramfitt.busarrival.repo.BusArrivalRepo;

@Service
public class BusArrivalServiceImpl implements BusArrivalService {
	
	private BusArrivalRepo busArrivalRepo;
	
	public BusArrivalServiceImpl(BusArrivalRepo busArrivalRepo) {
		
		this.busArrivalRepo = busArrivalRepo;		
	}
	
	@Override
	public BusArrival addBusArrival(BusArrival ba) {
		busArrivalRepo.save(ba);
		
		return ba;
	}

	@Override
	public List<BusArrival> searchHistory() {
		
		return busArrivalRepo.findAll();
	}

	@Override
	public List<BusArrival> pageSearchHistory(Pageable pageable) {
		
		List<BusArrival> list = new ArrayList<BusArrival>();
		Page<BusArrival> busArrivalPage;

		busArrivalPage = busArrivalRepo.findAll(pageable);
		list = busArrivalPage.getContent();
		
		return list;
	}

}
