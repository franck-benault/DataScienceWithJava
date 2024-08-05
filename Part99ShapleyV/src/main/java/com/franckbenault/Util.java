package com.franckbenault;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {
	
	public static Set<Set<String>> powerSet(Set<String> originalSet) {
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
	
	public static int factorial(int i) {
		if(i<=1)
			return 1;
		else {
			return i*factorial(i-1);
		}
	}

}
