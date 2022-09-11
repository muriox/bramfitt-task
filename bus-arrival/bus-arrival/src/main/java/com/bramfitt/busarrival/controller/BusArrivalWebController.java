package com.bramfitt.busarrival.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BusArrivalWebController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@RequestMapping("")
	public String index() {
		LOG.info("Home Page");
		
		return "index";
	}

	@RequestMapping("/history")
	public String history() {
		LOG.info("History Page");
		
		return "history";
	}
}

