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

	public void calculateAll() {
		
		for(String i : n) {
			res.put(i,calculate(i));
		}
		
		logger.info("calculate all res {}",res);
	}
	
	public Double calculate(String i) {
		if(res.containsKey(i))
			return res.get(i);
		logger.info("calculate for i {}",i);
		double res=0.0;
		for(Set<String> s : powerSet) {
			if(s.contains(i)) {
				logger.info("set involved {}",s);		
				Double v1 = v.apply(s);
				Set<String> sMinusI = new HashSet<String>();
				sMinusI.addAll(s);
				sMinusI.remove(i);			
				long weight = Util.factorial(sMinusI.size())*(Util.factorial(n.size()-sMinusI.size()-1));
				logger.info("weight={}",weight);
				
				Double v2 =v.apply(sMinusI);
				Double marginal = v1-v2;
				logger.info("marginal={}",marginal);
				res+=weight*marginal/Util.factorial(n.size());
			}
		}
		
		logger.info("res={}",res);
		return res;
	}
	

	

}
