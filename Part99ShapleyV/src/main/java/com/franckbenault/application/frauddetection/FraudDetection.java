package com.franckbenault.application.frauddetection;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.franckbenault.ShapleyExact;

public class FraudDetection {
	
	private Logger logger = LoggerFactory.getLogger(FraudDetection.class);
	
	private List<Boolean> transactions = null;
	private Map<String, Set<Integer>> fraudDetectionRules = null;
	private ShapleyExact shapley =null;

	public FraudDetection(List<Boolean> transactions,
			Map<String,Set<Integer>> fraudDetectionRules) {
		this.transactions = new ArrayList<>(transactions);
		this.fraudDetectionRules =new HashMap<>();
		this.fraudDetectionRules.putAll(fraudDetectionRules);
		
		Set<String> n = fraudDetectionRules.keySet();
		

				
		Function<Set<String>, Double> v=  s -> {
			
			Set<Integer> detectedFraudulentTs =new HashSet<>();
			if(s.size()==0)
				return 0.0;
			
			for(String rule : s)
				detectedFraudulentTs.addAll(fraudDetectionRules.get(rule));
			logger.info("rule(s) "+s+" "+detectedFraudulentTs);
			ConfusionMatrix matrix = new ConfusionMatrix(this.transactions, detectedFraudulentTs,null);
			matrix.draw();
			return matrix.f1score();
	    };
		
		 
	    shapley = new ShapleyExact(v,n);
	    shapley.calculateAllShapleyValues();
	}
	
	public double calculate(String rule) {
		return shapley.getShapleyValue(rule);
	}
	
	public void showAllSortedRules() {
		shapley.showAllSortedValue();
	}
}
