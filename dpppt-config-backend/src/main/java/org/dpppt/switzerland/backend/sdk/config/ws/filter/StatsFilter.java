/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.switzerland.backend.sdk.config.ws.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.dpppt.switzerland.backend.sdk.config.ws.stats.StatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatsFilter implements Filter {

	private StatsRepository statsRepository;
	
	
	public StatsFilter(StatsRepository statsRepository) {
		super();
		this.statsRepository = statsRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(StatsFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			if ("/v1/config".equals(httpRequest.getRequestURI())) {
				String address = httpRequest.getHeader("X-Forwarded-For");
				String userAgent = httpRequest.getHeader("User-Agent");
				String osversion = httpRequest.getParameter("osversion");
				String appversion = httpRequest.getParameter("appversion");
				String buildnr = httpRequest.getParameter("buildnr");

				if (null != address && address.length() > 0) {
					Pattern pattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
					Matcher matcher = pattern.matcher(address);
					if (matcher.find())
					{
						statsRepository.collect(matcher.group(), userAgent, appversion, osversion, buildnr);
					}
				}
				
			}
			
		} catch (Exception e) {
			// don't sweat it
			logger.error("StatusFilter failed",e);
		}
		chain.doFilter(request, response);
	}

}