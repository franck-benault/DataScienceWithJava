package com.franckbenault;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShapleySimple {
	
	private Logger logger = LoggerFactory.getLogger(ShapleySimple.class);
    	
	private Function<Set<String>, Double> v;
	private Set<String> n;
	private Set<Set<String>> powerSet;
	
	private Map<String, Double> res = null;
	
	public ShapleySimple(Function<Set<String>, Double> v, Set<String> n) {
		this.v=v;
		this.n=n;
		res = new HashMap<String , Double>();
		powerSet = Util.powerSet(n);
	}

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

	
	public Double getShapleyValue(String i) {
		Double output = 0.0;
		if(res.containsKey(i))
			output=res.get(i);
		else {
			output= calculateShapleyValue(i);
			res.put(i,output);
		}
			
		
		logger.debug("res={}",res);
		return output;
	}
	

	

}
