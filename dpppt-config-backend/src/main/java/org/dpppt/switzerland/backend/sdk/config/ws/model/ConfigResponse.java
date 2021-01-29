/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.switzerland.backend.sdk.config.ws.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ch.ubique.openapi.docannotations.Documentation;

@JsonIgnoreProperties(ignoreUnknown = true)

@Documentation(description = "ConfigResponse description")
public class ConfigResponse {

	@Documentation(description = "Blocks the app and shows a link to the app-store. The user can only continue once "
			+ "she updated the app")
	private boolean forceUpdate = false;

	@Documentation(description = "Holds a message translated in different languages")
	private InfoBoxCollection infoBox = null;
	private WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts;

	@Documentation(description = "Holds a url for test locations for each canton and Liechtenstein")
	private TestLocationCollection testLocations;

	@Documentation(description = "GAEN epidemiological parameters for iOS")
	private GAENSDKConfig iOSGaenSdkConfig = new GAENSDKConfig();
	@Documentation(description = "GAEN epidemiological parameters for Android")
	private GAENSDKConfig androidGaenSdkConfig = new GAENSDKConfig();
	
	@Documentation(description = "Version number of the configuration")
	private int configVersion = 1;
	
	@Documentation(description = "Indicated whether sharing of keys with other EU member states is enabled")
	private boolean euSharingEnabled = true;
		
	@Documentation(description = "List of countries participating in the sharing of infected keys")
	private List<ParticipatingCountry> euSharingCountries;	

	public int getConfigVersion() {
		return configVersion;
	}

	public void setConfigVersion(int configVersion) {
		this.configVersion = configVersion;
	}

	public List<ParticipatingCountry> getEuSharingCountries() {
		return euSharingCountries;
	}

	public void setEuSharingCountries(List<ParticipatingCountry> euSharingCountries) {
		this.euSharingCountries = euSharingCountries;
	}

	public boolean isEuSharingEnabled() {
		return euSharingEnabled;
	}

	public void setEuSharingEnabled(boolean euSharingEnabled) {
		this.euSharingEnabled = euSharingEnabled;
	}

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public InfoBoxCollection getInfoBox() {
		return infoBox;
	}

	public void setInfoBox(InfoBoxCollection infoBox) {
		this.infoBox = infoBox;
	}

	public WhatToDoPositiveTestTextsCollection getWhatToDoPositiveTestTexts() {
		return whatToDoPositiveTestTexts;
	}

	public void setWhatToDoPositiveTestTexts(WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts) {
		this.whatToDoPositiveTestTexts = whatToDoPositiveTestTexts;
	}

	public GAENSDKConfig getiOSGaenSdkConfig() {
		return iOSGaenSdkConfig;
	}

	public void setiOSGaenSdkConfig(GAENSDKConfig iOSGaenSdkConfig) {
		this.iOSGaenSdkConfig = iOSGaenSdkConfig;
	}

	public GAENSDKConfig getAndroidGaenSdkConfig() {
		return androidGaenSdkConfig;
	}

	public void setAndroidGaenSdkConfig(GAENSDKConfig androidGaenSdkConfig) {
		this.androidGaenSdkConfig = androidGaenSdkConfig;
	}

	public TestLocationCollection getTestLocations() {
		return testLocations;
	}

	public void setTestLocations(TestLocationCollection testLocations) {
		this.testLocations = testLocations;
	}

}
