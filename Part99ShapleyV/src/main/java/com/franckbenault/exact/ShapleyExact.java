package com.franckbenault.exact;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.franckbenault.ShapleyI;
import com.franckbenault.Util;

public class ShapleyExact implements ShapleyI {
	
	private Logger logger = LoggerFactory.getLogger(ShapleyExact.class);
    	
	private Function<Set<String>, Double> v;
	private Set<String> n;
	private Set<Set<String>> powerSet;
	
	private Map<String, Double> res = null;
	
	public ShapleyExact(Function<Set<String>, Double> v, Set<String> n) {
		this.v=v;
		this.n=n;
		res = new HashMap<String , Double>();
		powerSet = Util.powerSet(n);
	}

	@Override
	public void calculateAllShapleyValues() {
		
		for(String i : n) {
			res.put(i,calculateShapleyValue(i));
		}
		
		logger.info("calculate all res {}",res);
	}
	
	private Double calculateShapleyValue(String i) {
		logger.debug("calculate for i {}",i);
		double res=0.0;
		for(Set<String> s : powerSet) {
			if(s.contains(i)) {
				logger.debug("set involved {}",s);		
				Double v1 = v.apply(s);
				Set<String> sMinusI = new HashSet<String>();
				sMinusI.addAll(s);
				sMinusI.remove(i);			
				long weight = Util.factorial(sMinusI.size())*(Util.factorial(n.size()-sMinusI.size()-1));
				logger.debug("weight={}",weight);
				
				Double v2 =v.apply(sMinusI);
				Double marginal = v1-v2;
				logger.debug("marginal={}",marginal);
				res+=weight*marginal/Util.factorial(n.size());
			}
		}
		
		logger.debug("res={}",res);
		return res;
	}

	@Override
	public Double getShapleyValue(String key) {
		Double output = 0.0;
		if(res.containsKey(key))
			output=res.get(key);
		else {
			output= calculateShapleyValue(key);
			res.put(key,output);
		}
			
		
		logger.debug("res={}",res);
		return output;
	}
	

	@Override
	public Map<String, Double> getAllSortedValue() {
		
        Map<String, Double> sortedMap = Util.sortMapByValueDescending(res);
        return sortedMap;
		
	}

	

}
