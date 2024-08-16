package com.franckbenault.application.frauddetection;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.franckbenault.ShapleyI;
import com.franckbenault.exact.ShapleyExact;
import com.franckbenault.randomsampling.ShapleyApproximationRandomSampling;

public class FraudDetection {
	
	private Logger logger = LoggerFactory.getLogger(FraudDetection.class);
	
	private Map<String,Boolean> transactions = null;
	private Map<String, Set<String>> fraudDetectionRules = null;
	private ShapleyI shapley =null;
	private Set<String> rejectedRules;

	public FraudDetection(Map<String,Boolean> transactions,
			Map<String,Set<String>> fraudDetectionRules, boolean exact, int sampleSize) {
		this.transactions = new HashMap<>(transactions);
		this.fraudDetectionRules =new HashMap<>();
		this.fraudDetectionRules.putAll(fraudDetectionRules);
		rejectedRules =new HashSet<String>();
		
		Set<String> n= new HashSet<>();
		for(String rule: fraudDetectionRules.keySet()) {
			ConfusionMatrix matrix = 
					new ConfusionMatrix(this.transactions,fraudDetectionRules.get(rule) ,rule);
			if(!Double.isNaN(matrix.f1score())) {
				n.add(rule);
			} else 
				rejectedRules.add(rule);
		}
		if(exact) 
			shapley = new ShapleyExact(getF1Score,n);
		else
			shapley = new ShapleyApproximationRandomSampling(getF1Score,n,sampleSize);
	    shapley.calculateAllShapleyValues();
	}
	
	private Set<String> detectedFraudulentTs(Set<String> rules) {
		Set<String> detectedFraudulentTs =new HashSet<>();
		for(String rule : rules)
			detectedFraudulentTs.addAll(fraudDetectionRules.get(rule));
		return detectedFraudulentTs;
	}
	
	private Function<Set<String>, Double> getF1Score = rules ->  {

		if(rules.size()==0)
			return 0.0;
		
		Set<String> detectedFraudulentTs =detectedFraudulentTs(rules) ;
		logger.debug("rule(s) "+rules+" "+detectedFraudulentTs);
		ConfusionMatrix matrix = new ConfusionMatrix(this.transactions, detectedFraudulentTs,rules.toString());
		//matrix.draw();
		return matrix.f1score();
		
	};
	
	public double calculate(String rule) {
		return shapley.getShapleyValue(rule);
	}
	
	public List<String> getAllSortedRules() {
		Map<String, Double> sorted = shapley.getAllSortedValue();
		logger.info("Rejected rules {}",rejectedRules);
		List<String> firstRules = new ArrayList<>();
		for(String rule : sorted.keySet()) {
			firstRules.add(rule);
		}
		return firstRules;

	}
	
	public void showAllSortedRules() {
        Map<String, Double> sortedMap = shapley.getAllSortedValue();
		logger.debug(""+sortedMap);
		for(Entry<String, Double> entry : sortedMap.entrySet())
			logger.info("{} Shapley value= {}", entry.getKey(), entry.getValue());
	}
	
	public void showScoreFirstRules() {
		Map<String, Double> sorted = shapley.getAllSortedValue();
		logger.info("Rejected rules {}",rejectedRules);
		Set<String> firstRules = new HashSet<>();
		for(String rule : sorted.keySet()) {
			firstRules.add(rule);
			logger.info("score {} transactions {} rules {}",
					String.format("%,.3f",getF1Score.apply(firstRules)),
					detectedFraudulentTs(firstRules), 
					firstRules);
		}
			
	}
}
