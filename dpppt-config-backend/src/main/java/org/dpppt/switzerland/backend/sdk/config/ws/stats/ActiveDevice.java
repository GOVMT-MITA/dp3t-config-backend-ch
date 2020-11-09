package org.dpppt.switzerland.backend.sdk.config.ws.stats;

import java.time.Instant;

public class ActiveDevice {

	private String appverson;
	private String osversion;
	private String buildnr;
	private String country;
	private int checkins;
	
	public ActiveDevice(String appverson, String osversion, String buildnr, String country) {
		super();
		this.appverson = appverson;
		this.osversion = osversion;
		this.buildnr = buildnr;
		this.country = country;
		this.checkins = 1;
	}
	
	public String getAppverson() {
		return appverson;
	}
	public void setAppverson(String appverson) {
		this.appverson = appverson;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public String getBuildnr() {
		return buildnr;
	}
	public void setBuildnr(String buildnr) {
		this.buildnr = buildnr;
	}

	public int getCheckins() {
		return checkins;
	}
	
	public void checkin() {
		this.checkins++;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
}
