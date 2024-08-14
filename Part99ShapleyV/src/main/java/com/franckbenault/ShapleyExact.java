package com.franckbenault;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShapleyExact {
	
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
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValueDescending(Map<K, V> map) {
	    return map.entrySet()
	      .stream()
	      .sorted(Map.Entry.<K, V>comparingByValue().reversed())
	      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
	
	public void showAllSortedValue() {
		
        Map<String, Double> sortedMap = sortMapByValueDescending(res);
		logger.debug(""+sortedMap);
		for(Entry<String, Double> entry : sortedMap.entrySet())
			logger.info("{} value= {}", entry.getKey(), entry.getValue());
		
	}
	

	

}
