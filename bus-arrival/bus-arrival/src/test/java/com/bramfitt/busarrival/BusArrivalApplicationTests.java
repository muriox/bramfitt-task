package com.bramfitt.busarrival;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bramfitt.busarrival.repo.BusArrivalRepo;

@SpringBootTest
class BusArrivalApplicationTests {
	
	@Autowired
	BusArrivalRepo busArrivalRepo;
	
	@Autowired
	private MockMvc mockMvc;
	
	private String data = "[{\r\n"
			+ "        \"$type\": \"Tfl.Api.Presentation.Entities.Prediction, Tfl.Api.Presentation.Entities\",\r\n"
			+ "        \"id\": \"-1163906419\",\r\n"
			+ "        \"operationType\": 1,\r\n"
			+ "        \"vehicleId\": \"YX20OLC\",\r\n"
			+ "        \"naptanId\": \"490009333W\",\r\n"
			+ "        \"stationName\": \"Lower Marsh Lane\",\r\n"
			+ "        \"lineId\": \"k1\",\r\n"
			+ "        \"lineName\": \"K1\",\r\n"
			+ "        \"platformName\": \"null\",\r\n"
			+ "        \"direction\": \"inbound\",\r\n"
			+ "        \"bearing\": \"246\",\r\n"
			+ "        \"destinationNaptanId\": \"\",\r\n"
			+ "        \"destinationName\": \"New Malden\",\r\n"
			+ "        \"timestamp\": \"2022-09-09T15:16:09.6153193Z\",\r\n"
			+ "        \"timeToStation\": 1260,\r\n"
			+ "        \"currentLocation\": \"\",\r\n"
			+ "        \"towards\": \"Tolworth\",\r\n"
			+ "        \"expectedArrival\": \"2022-09-09T15:37:09Z\",\r\n"
			+ "        \"timeToLive\": \"2022-09-09T15:37:39Z\",\r\n"
			+ "        \"modeName\": \"bus\",\r\n"
			+ "        \"timing\": {\r\n"
			+ "            \"$type\": \"Tfl.Api.Presentation.Entities.PredictionTiming, Tfl.Api.Presentation.Entities\",\r\n"
			+ "            \"countdownServerAdjustment\": \"00:00:33.3303944\",\r\n"
			+ "            \"source\": \"2022-09-09T08:31:25.163Z\",\r\n"
			+ "            \"insert\": \"2022-09-09T15:16:07.925Z\",\r\n"
			+ "            \"read\": \"2022-09-09T15:16:41.271Z\",\r\n"
			+ "            \"sent\": \"2022-09-09T15:16:09Z\",\r\n"
			+ "            \"received\": \"0001-01-01T00:00:00Z\"\r\n"
			+ "        }\r\n"
			+ "    }, {\r\n"
			+ "        \"$type\": \"Tfl.Api.Presentation.Entities.Prediction, Tfl.Api.Presentation.Entities\",\r\n"
			+ "        \"id\": \"-278574204\",\r\n"
			+ "        \"operationType\": 1,\r\n"
			+ "        \"vehicleId\": \"YX20OLH\",\r\n"
			+ "        \"naptanId\": \"490009333W\",\r\n"
			+ "        \"stationName\": \"Lower Marsh Lane\",\r\n"
			+ "        \"lineId\": \"k1\",\r\n"
			+ "        \"lineName\": \"K1\",\r\n"
			+ "        \"platformName\": \"null\",\r\n"
			+ "        \"direction\": \"inbound\",\r\n"
			+ "        \"bearing\": \"246\",\r\n"
			+ "        \"destinationNaptanId\": \"\",\r\n"
			+ "        \"destinationName\": \"New Malden\",\r\n"
			+ "        \"timestamp\": \"2022-09-09T15:16:09.6153193Z\",\r\n"
			+ "        \"timeToStation\": 326,\r\n"
			+ "        \"currentLocation\": \"\",\r\n"
			+ "        \"towards\": \"Tolworth\",\r\n"
			+ "        \"expectedArrival\": \"2022-09-09T15:21:35Z\",\r\n"
			+ "        \"timeToLive\": \"2022-09-09T15:22:05Z\",\r\n"
			+ "        \"modeName\": \"bus\",\r\n"
			+ "        \"timing\": {\r\n"
			+ "            \"$type\": \"Tfl.Api.Presentation.Entities.PredictionTiming, Tfl.Api.Presentation.Entities\",\r\n"
			+ "            \"countdownServerAdjustment\": \"00:00:33.3195108\",\r\n"
			+ "            \"source\": \"2022-09-09T08:31:25.163Z\",\r\n"
			+ "            \"insert\": \"2022-09-09T15:16:38.467Z\",\r\n"
			+ "            \"read\": \"2022-09-09T15:17:11.834Z\",\r\n"
			+ "            \"sent\": \"2022-09-09T15:16:09Z\",\r\n"
			+ "            \"received\": \"0001-01-01T00:00:00Z\"\r\n"
			+ "        }\r\n"
			+ "    }, {\r\n"
			+ "        \"$type\": \"Tfl.Api.Presentation.Entities.Prediction, Tfl.Api.Presentation.Entities\",\r\n"
			+ "        \"id\": \"806214881\",\r\n"
			+ "        \"operationType\": 1,\r\n"
			+ "        \"vehicleId\": \"YX20OLO\",\r\n"
			+ "        \"naptanId\": \"490009333W\",\r\n"
			+ "        \"stationName\": \"Lower Marsh Lane\",\r\n"
			+ "        \"lineId\": \"k1\",\r\n"
			+ "        \"lineName\": \"K1\",\r\n"
			+ "        \"platformName\": \"null\",\r\n"
			+ "        \"direction\": \"inbound\",\r\n"
			+ "        \"bearing\": \"246\",\r\n"
			+ "        \"destinationNaptanId\": \"\",\r\n"
			+ "        \"destinationName\": \"New Malden\",\r\n"
			+ "        \"timestamp\": \"2022-09-09T15:16:09.6153193Z\",\r\n"
			+ "        \"timeToStation\": 1389,\r\n"
			+ "        \"currentLocation\": \"\",\r\n"
			+ "        \"towards\": \"Tolworth\",\r\n"
			+ "        \"expectedArrival\": \"2022-09-09T15:39:18Z\",\r\n"
			+ "        \"timeToLive\": \"2022-09-09T15:39:48Z\",\r\n"
			+ "        \"modeName\": \"bus\",\r\n"
			+ "        \"timing\": {\r\n"
			+ "            \"$type\": \"Tfl.Api.Presentation.Entities.PredictionTiming, Tfl.Api.Presentation.Entities\",\r\n"
			+ "            \"countdownServerAdjustment\": \"00:00:33.3224103\",\r\n"
			+ "            \"source\": \"2022-09-09T08:31:25.163Z\",\r\n"
			+ "            \"insert\": \"2022-09-09T15:16:16.128Z\",\r\n"
			+ "            \"read\": \"2022-09-09T15:16:49.459Z\",\r\n"
			+ "            \"sent\": \"2022-09-09T15:16:09Z\",\r\n"
			+ "            \"received\": \"0001-01-01T00:00:00Z\"\r\n"
			+ "        }\r\n"
			+ "    }\r\n"
			+ "]";
	
	@Test
	void contextLoads() {
	}

}
