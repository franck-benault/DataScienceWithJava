package com.franckbenault;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ShapleySimple {
	
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
		
		
		System.out.println("powerSet "+powerSet);
		System.out.println("calculate all res "+res);
	}
	
	public Double calculate(String i) {
		System.out.println("calculate for i "+i);
		double temp=0.0;
		for(Set<String> s : powerSet) {
			if(s.contains(i)) {
				System.out.println("set involved "+s);		
				Double v1 = v.apply(s);
				Set<String> sMinusI = new HashSet<String>();
				sMinusI.addAll(s);
				sMinusI.remove(i);			
				int weight = Util.factorial(sMinusI.size())*(Util.factorial(n.size()-sMinusI.size()-1));
				System.out.println("weight="+weight);
				
				Double v2 =v.apply(sMinusI);
				Double marginal = v1-v2;
				System.out.println("marginal "+marginal);
				temp+=weight*marginal;
			}
		}
		double res= temp/Util.factorial(n.size());	
		System.out.println(res);
		return res;
	}
	

	

}
