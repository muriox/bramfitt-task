package com.bramfitt.busarrival.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "BusArrival")
public class BusArrival {
	
	private String stationName;
	private String destinationName;
	private String towards;
	private String expectedArrival;
	private String timeToLive;
	private String timeCreated;
	
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getTowards() {
		return towards;
	}
	public void setTowards(String towards) {
		this.towards = towards;
	}
	public String getExpectedArrival() {
		return expectedArrival;
	}
	public void setExpectedArrival(String expectedArrival) {
		this.expectedArrival = expectedArrival;
	}
	public String getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(String timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	
}
