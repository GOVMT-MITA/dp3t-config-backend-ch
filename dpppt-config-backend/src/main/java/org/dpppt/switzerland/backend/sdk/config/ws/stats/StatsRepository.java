package org.dpppt.switzerland.backend.sdk.config.ws.stats;

import java.util.Map;

public interface StatsRepository {

	void collect(String address, String userAgent, String appversion, String osversion, String buildnr);
	
	long getNumberOfActiveDevices();

	Map<String, Object> getActiveDevicesByOsVersion();
	
	Map<String, Object> getActiveDevicesByCountry();
	
	CheckinsStats getCheckinsStats();
	
}
