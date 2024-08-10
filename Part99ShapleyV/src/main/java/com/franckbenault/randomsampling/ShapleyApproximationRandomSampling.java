package com.franckbenault.randomsampling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShapleyApproximationRandomSampling {
	
	private Logger logger = LoggerFactory.getLogger(ShapleyApproximationRandomSampling.class);
    	
	private Function<Set<String>, Double> v;
	private Set<String> n;
	private int samplingSize;
	
	private Map<String, Double> res = null;
	
	public ShapleyApproximationRandomSampling(Function<Set<String>, Double> v, Set<String> n, int samplingSize) {
		this.v=v;
		this.n=n;
		this.samplingSize= samplingSize;
		res = new HashMap<String , Double>();
	}

	public void calculateAllShapleyValues() {
		
    	List<String> temp = new ArrayList<>(n);
		
		for(int loop=1; loop<=samplingSize; loop++) {
			List<String> permutation= RandomPermutationUtil.generateRandomPermutation(temp);
			Set<String> tempSet = new HashSet<>();
			double before = 0.0;
			for(String element: permutation) {
				tempSet.add(element);
				double after = v.apply(tempSet);
				double marginal = after-before;
				before=after;
				res.merge(element, marginal/samplingSize, Double::sum);
				//res.put(element,res.get(element)+(marginal/samplingSize));
			}
		}
		
		logger.debug("calculate all res {}",res);
	}
	


	
	public Double getShapleyValue(String i) {
		Double output = 0.0;
		if(!res.containsKey(i))
			calculateAllShapleyValues();
		
		output=res.get(i);
		
		logger.debug("output={}",output);
		return output;
	}
	

	

}
