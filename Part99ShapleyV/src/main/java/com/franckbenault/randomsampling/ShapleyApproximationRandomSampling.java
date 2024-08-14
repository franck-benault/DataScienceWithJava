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

import com.franckbenault.ShapleyI;
import com.franckbenault.Util;

public class ShapleyApproximationRandomSampling implements ShapleyI {
	
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

	@Override
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
	


	@Override
	public Double getShapleyValue(String key) {
		Double output = 0.0;
		if(!res.containsKey(key))
			calculateAllShapleyValues();
		
		output=res.get(key);
		
		logger.debug("output={}",output);
		return output;
	}

	@Override
	public Map<String, Double> getAllSortedValue() {
		
        Map<String, Double> sortedMap = Util.sortMapByValueDescending(res);
        return sortedMap;
		
	}
	

	

}
