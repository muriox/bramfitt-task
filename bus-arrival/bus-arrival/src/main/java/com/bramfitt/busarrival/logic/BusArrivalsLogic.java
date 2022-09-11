package com.bramfitt.busarrival.logic;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bramfitt.busarrival.model.BusArrival;

public class BusArrivalsLogic {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private static BusArrivalsLogic logic = null;
	
	private BusArrivalsLogic() {
		
	}
	
	/**
	 * Extract relevant data.
	 * @param jsonString
	 * @return busArrivalList
	 * @throws JSONException
	 */
	public List<BusArrival> extractBusArrivalInfo(final String jsonString) throws JSONException {
		LOG.info("Extracting Bus arrival Info");
		
		List<BusArrival> busArrivalList = new ArrayList<BusArrival>();
		JSONArray array = new JSONArray(jsonString); 
		
		for(int i=0; i < array.length(); i++) {			
			JSONObject object = array.getJSONObject(i);  
			
			BusArrival ba = new BusArrival();
			ba.setStationName(object.getString("stationName"));
			ba.setDestinationName(object.getString("destinationName"));
			ba.setTowards(object.getString("towards"));
			ba.setExpectedArrival(object.getString("expectedArrival"));
			ba.setTimeToLive(object.getString("timeToLive"));
			ba.setTimeCreated(LocalDateTime.now().toString());

			busArrivalList.add(ba);			
		}  
		LOG.info("List collection created");
		
		return busArrivalList;
		
	}
	
	/**
	 * Convert list of BusArrival POJO to JSON String
	 * @param busArrivalList
	 * @return jsonStr
	 * @throws JSONException
	 */

	public String pojoToJsonString(List<BusArrival> busArrivalList) throws JSONException {
		LOG.info("Converting BusArrival POJO to JSON String");
		String jsonString = new JSONArray(busArrivalList.toArray()).toString();
		
		return jsonString;
	}
	
	/**
	 * Class singleton instance
	 * @return
	 */
	public static BusArrivalsLogic getInstance()
    {
        if (logic == null)
            logic = new BusArrivalsLogic();
        
        return logic;
    } 
}
