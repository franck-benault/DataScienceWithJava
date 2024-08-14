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
	
	private List<Boolean> transactions = null;
	private Map<String, Set<Integer>> fraudDetectionRules = null;
	private ShapleyI shapley =null;
	private Set<String> rejectedRules;

	public FraudDetection(List<Boolean> transactions,
			Map<String,Set<Integer>> fraudDetectionRules, boolean exact, int sampleSize) {
		this.transactions = new ArrayList<>(transactions);
		this.fraudDetectionRules =new HashMap<>();
		this.fraudDetectionRules.putAll(fraudDetectionRules);
		rejectedRules =new HashSet<String>();
		
		Set<String> n= new HashSet<>();
		for(String rule: fraudDetectionRules.keySet()) {
			ConfusionMatrix matrix = 
					new ConfusionMatrix(this.transactions,fraudDetectionRules.get(rule) ,rule);
			if(matrix.accuraty()!=0.0 && matrix.precision()!=0.0)
				n.add(rule);
			else 
				rejectedRules.add(rule);
		}
		if(exact) 
			shapley = new ShapleyExact(getF1Score,n);
		else
			shapley = new ShapleyApproximationRandomSampling(getF1Score,n,sampleSize);
	    shapley.calculateAllShapleyValues();
	}
	
	private Function<Set<String>, Double> getF1Score = rules ->  {
		
		Set<Integer> detectedFraudulentTs =new HashSet<>();
		if(rules.size()==0)
			return 0.0;
		
		for(String rule : rules)
			detectedFraudulentTs.addAll(fraudDetectionRules.get(rule));
		logger.debug("rule(s) "+rules+" "+detectedFraudulentTs);
		ConfusionMatrix matrix = new ConfusionMatrix(this.transactions, detectedFraudulentTs,rules.toString());
		//matrix.draw();
		return matrix.f1score();
		
	};
	
	public double calculate(String rule) {
		return shapley.getShapleyValue(rule);
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
			logger.info("score {} rules {}",String.format("%,.3f",getF1Score.apply(firstRules)),firstRules);
		}
			
	}
}
