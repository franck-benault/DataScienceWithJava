package com.franckbenault.randomsampling;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParliamentRandomSampling {
	private Logger logger = LoggerFactory.getLogger(ParliamentRandomSampling.class);
	
	private Map<String,Integer> npMPPerParty = null;
	private ShapleyApproximationRandomSampling shapley =null;
	
	public ParliamentRandomSampling(Map<String,Integer> npMPPerParty, int randomSampleSize) {
		this.npMPPerParty = new HashMap<>();
		this.npMPPerParty.putAll(npMPPerParty);
		
		Set<String> n= this.npMPPerParty.keySet();
		int totalMp = this.npMPPerParty.values().stream().reduce(0, Integer::sum);
		int majority = totalMp/2+1;
		logger.debug("Majority = {}",majority);
				
		Function<Set<String>, Double> v=  s -> {
			int nbMp =0;
			for(String party : s) {
				nbMp+=this.npMPPerParty.get(party);
			}
			if(nbMp>=majority)
				return 1.0;
			else
				return 0.0;
	    };
		
		
	    shapley = new ShapleyApproximationRandomSampling(v,n, randomSampleSize);
	    shapley.calculateAllShapleyValues();
	}
	
	public double calculate(String party) {
		return shapley.getShapleyValue(party);
	}

}
