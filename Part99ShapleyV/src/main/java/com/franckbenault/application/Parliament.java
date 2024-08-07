package com.franckbenault.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.franckbenault.ShapleySimple;

public class Parliament {
	
	private Map<String,Integer> npMPPerParty = null;
	private ShapleySimple shapley =null;
	
	public Parliament(Map<String,Integer> npMPPerParty) {
		this.npMPPerParty = new HashMap<>();
		this.npMPPerParty.putAll(npMPPerParty);
		
		Set<String> n= this.npMPPerParty.keySet();
		int totalMp = this.npMPPerParty.values().stream().reduce(0, Integer::sum);
		int majority = totalMp/2+1;
		System.out.println("####### Majority ="+majority);
				
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
		
		
	    shapley = new ShapleySimple(v,n);
	    shapley.calculateAll();
	}
	
	public double calculate(String party) {
		return shapley.calculate(party);
	}

}
