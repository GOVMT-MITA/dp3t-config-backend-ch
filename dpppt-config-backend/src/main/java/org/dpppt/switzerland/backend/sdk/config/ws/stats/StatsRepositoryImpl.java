package org.dpppt.switzerland.backend.sdk.config.ws.stats;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

public class StatsRepositoryImpl implements StatsRepository {

	private static final Logger logger = LoggerFactory.getLogger(StatsRepositoryImpl.class);
	
	private ExpiringMap<String, ActiveDevice> repository;
	private RestTemplate template;
	
	public StatsRepositoryImpl() {
		super();
		repository = ExpiringMap.builder()
				.expirationPolicy(ExpirationPolicy.CREATED)
				.expiration(7, TimeUnit.HOURS)
				.asyncExpirationListener((k,v) -> {
					if (logger.isDebugEnabled()) {
						ActiveDevice ad = (ActiveDevice) v;
						logger.debug(k + " expired. It checked in " + ad.getCheckins() + " times.");
					}
				})
				.build();
		template = new RestTemplate();
	}

	@Override
	public long getNumberOfActiveDevices() {
		return repository.keySet().size();
	}
	
	@Override
	public Map<String, Object> getActiveDevicesByOsVersion() {
		 
		Map<String, List<ActiveDevice>> collect = repository.entrySet().stream()
			.map(e -> e.getValue())
			.collect(Collectors.groupingBy(ActiveDevice::getOsversion));
		
		Map<String, Object> result = collect.entrySet()
		.stream()
		.collect(
	            Collectors.toMap(
	                Map.Entry::getKey,
	                e ->  e.getValue().size()		                
	             )
	    );			
		
		return result;
			
	}

	@Override
	public Map<String, Object> getActiveDevicesByCountry() {
		 
		Map<String, List<ActiveDevice>> collect = repository.entrySet().stream()
			.map(e -> e.getValue())
			.collect(Collectors.groupingBy(ActiveDevice::getCountry));
		
		Map<String, Object> result = collect.entrySet()
		.stream()
		.collect(
	            Collectors.toMap(
	                Map.Entry::getKey,
	                e ->  e.getValue().size()		                
	             )
	    );			
		
		return result;
			
	}
	
	@Override
	public void collect(String address, String userAgent, String appversion, String osversion, String buildnr) {		
		String sha256hex = DigestUtils.sha256Hex(address + userAgent + appversion + osversion + buildnr);
		if (repository.containsKey(sha256hex)) {
			repository.resetExpiration(sha256hex);
			repository.get(sha256hex).checkin();
		} else {
			repository.put(sha256hex, new ActiveDevice(appversion, osversion, buildnr, geoLocate(address)));			
		}
		if (logger.isDebugEnabled())
			logger.debug("Collected " + address + " using " + userAgent + " with parameters " + appversion + "/" + osversion + "/" + buildnr);
		
	}
	
	private String geoLocate(String ip) {
		String c = "xx";
		try {
			GeolocationResponse res = template.getForObject("https://api.ipgeolocationapi.com/geolocate/" + ip, GeolocationResponse.class);
			if (null != res && null != res.getAlpha2() && res.getAlpha2().length() == 2)
				c = res.getAlpha2();
		} catch (Exception e) {
			logger.warn("geoLocated failed with message ", e.getMessage());
		}
		return c;
	}

	@Override
	public CheckinsStats getCheckinsStats() {
		List<Integer> checkins = repository.entrySet().stream()
				.map(e -> e.getValue().getCheckins())
				.collect(Collectors.toList());

		Integer smallest = checkins.stream()
				.reduce(Integer.MAX_VALUE, (s, r) -> r < s ? r : s);

		Integer biggest = checkins.stream()
				.reduce(0, (s, r) -> r > s ? r : s);

		Integer mean = checkins.stream()
				.reduce(0, (s, r) -> s + r) / checkins.size();
		
		return new CheckinsStats(smallest, biggest, mean);
	}
	

}
