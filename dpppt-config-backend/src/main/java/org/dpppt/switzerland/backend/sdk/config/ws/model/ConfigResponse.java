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

public class ConfigResponse {

	private boolean forceUpdate = true;
	private boolean forceTraceShutdown = false;

	private InfoBoxCollection infoBox = null;

	private SDKConfig sdkConfig = new SDKConfig();

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public SDKConfig getSdkConfig() {
		return sdkConfig;
	}

	public void setSdkConfig(SDKConfig sdkConfig) {
		this.sdkConfig = sdkConfig;
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

	public boolean isForceTraceShutdown() {
		return forceTraceShutdown;
	}

	public void setForceTraceShutdown(boolean forceTraceShutdown) {
		this.forceTraceShutdown = forceTraceShutdown;
	}

}
