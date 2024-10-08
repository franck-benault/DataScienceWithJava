package com.franckbenault.application.parliament;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.franckbenault.ShapleyI;
import com.franckbenault.exact.ShapleyExact;
import com.franckbenault.randomsampling.ShapleyApproximationRandomSampling;

public class Parliament {
	private Logger logger = LoggerFactory.getLogger(Parliament.class);
	
	private Map<String,Integer> npMPPerParty = null;
	private ShapleyI shapley =null;
	
	public Parliament(Map<String,Integer> npMPPerParty, boolean exact,int samplingSize) {
		this.npMPPerParty = new HashMap<>();
		this.npMPPerParty.putAll(npMPPerParty);
		
		Set<String> n= this.npMPPerParty.keySet();
		int totalMp = this.npMPPerParty.values().stream().reduce(0, Integer::sum);
		int majority = totalMp/2+1;
		logger.info("Majority = {}",majority);
				
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
		
	    if(exact)
		    shapley = new ShapleyExact(v,n);
	    else
	    	 shapley = new ShapleyApproximationRandomSampling(v,n,samplingSize);
	    shapley.calculateAllShapleyValues();
	}
	
	public double calculate(String party) {
		return shapley.getShapleyValue(party);
	}

}
