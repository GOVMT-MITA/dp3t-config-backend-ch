package org.dpppt.switzerland.backend.sdk.config.ws.stats;

public class CheckinsStats {

	private Integer smallest;
	private Integer biggest;
	private Integer mean;
		
	public CheckinsStats(Integer smallest, Integer biggest, Integer mean) {
		super();
		this.smallest = smallest;
		this.biggest = biggest;
		this.mean = mean;
	}
	
	public Integer getSmallest() {
		return smallest;
	}
	public Integer getBiggest() {
		return biggest;
	}
	public Integer getMean() {
		return mean;
	}
	
	
}
