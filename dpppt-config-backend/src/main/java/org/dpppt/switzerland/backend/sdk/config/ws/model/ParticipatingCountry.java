package org.dpppt.switzerland.backend.sdk.config.ws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipatingCountry {

	private String countryCode;
	private String countryName;
	
	
	
	public ParticipatingCountry() {
		super();
	}

	public ParticipatingCountry(String countryCode, String countryName) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
	}
	
	public static ParticipatingCountry build(String countryCode, String countryName) {
		return new ParticipatingCountry(countryCode, countryName);
		
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}
