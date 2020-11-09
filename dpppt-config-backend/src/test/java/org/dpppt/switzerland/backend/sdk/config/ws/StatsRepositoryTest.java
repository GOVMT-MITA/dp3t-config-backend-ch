package org.dpppt.switzerland.backend.sdk.config.ws;

import static org.junit.Assert.assertEquals;

import org.dpppt.switzerland.backend.sdk.config.ws.semver.Version;
import org.dpppt.switzerland.backend.sdk.config.ws.stats.StatsRepository;
import org.dpppt.switzerland.backend.sdk.config.ws.stats.StatsRepositoryImpl;
import org.junit.Test;

public class StatsRepositoryTest {

    @Test
    public void testGetActiveDevicesByOsVersion() throws Exception {
    	StatsRepository repo = new StatsRepositoryImpl();
    	repo.collect("1.1.1.1", "test", "1", "1", "1");
    	repo.collect("1.1.1.2", "test", "1", "1", "1");
    	repo.collect("1.1.1.3", "test", "1", "1", "1");
    	repo.collect("1.1.1.4", "test", "1", "1", "1");
    	repo.collect("1.1.1.5", "test", "1", "2", "1");
    	repo.collect("1.1.1.6", "test", "1", "2", "1");
    	System.out.println("NumberOfActiveDevices: " + repo.getNumberOfActiveDevices());
    	System.out.println("ActiveDevicesByOsVersion: " + repo.getActiveDevicesByOsVersion());
    }

}
