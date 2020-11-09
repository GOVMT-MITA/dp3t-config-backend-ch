package org.dpppt.switzerland.backend.sdk.config.ws.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocationResponse {

	private String alpha2;

	public String getAlpha2() {
		return alpha2;
	}

	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}
	
	
}
