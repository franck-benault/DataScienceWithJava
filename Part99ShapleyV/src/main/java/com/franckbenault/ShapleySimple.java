package com.franckbenault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ShapleySimple {
	
	private Function<Set<String>, Double> v;
	private Set<String> n;
	private Set<Set<String>> powerSet;
	
	private Map<String, Double> res = null;
	
	ShapleySimple(Function<Set<String>, Double> v, Set<String> n) {
		this.v=v;
		this.n=n;
		res = new HashMap<String , Double>();
		powerSet = powerSet(n);
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
				int weight = factorial(sMinusI.size())*(factorial(n.size()-sMinusI.size()-1));
				System.out.println("weight="+weight);
				
				Double v2 =v.apply(sMinusI);
				Double marginal = v1-v2;
				System.out.println("marginal "+marginal);
				temp+=weight*marginal;
			}
		}
		double res= temp/factorial(n.size());	
		System.out.println(res);
		return res;
	}
	
	private int factorial(int i) {
		//System.out.println("factorial "+i);
		if(i<=1)
			return 1;
		else {
			return i*factorial(i-1);
		}
	}
	
	private Set<Set<String>> powerSet(Set<String> originalSet) {
		//powerSet = set of all subset
        Set<Set<String>> sets = new HashSet<Set<String>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<String>());
            return sets;
        }
        List<String> list = new ArrayList<String>(originalSet);
        String head = list.get(0);
        Set<String> rest = new HashSet<String>(list.subList(1, list.size()));
        for (Set<String> set : powerSet(rest)) {
            Set<String> newSet = new HashSet<String>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
		
	}
}
