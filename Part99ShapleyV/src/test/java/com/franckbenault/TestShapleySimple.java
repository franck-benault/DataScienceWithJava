package com.franckbenault;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
 
public class TestShapleySimple {
	
	@Test
	public void oneActorTest() {
		
		Set<String> n = new HashSet<String>();
		n.add("One");
				
		
		Function<Set<String>, Double> v=  s -> {
			//System.out.println("v(s) "+s);
	        if(s.isEmpty())
	        	return 0.0;
	        else
	        	return 100.0;
	    };
	    
	    ShapleySimple shapley = new ShapleySimple(v,n);
		assertEquals(shapley.calculate("One"),100.0);		
		
	}
	
	@Test
	public void twoEquivalentActorsTest() {
		
		Set<String> n = new HashSet<String>();
		n.add("One");
		n.add("Two");
		
		
		Function<Set<String>, Double> v=  s -> {
			//System.out.println("v(s) "+s);
	        if(s.isEmpty())
	        	return 0.0;
	        else
	        	return 100.0;
	    };
	    
	    ShapleySimple shapley = new ShapleySimple(v,n);
		assertEquals(shapley.calculate("One"),50.0);
		assertEquals(shapley.calculate("Two"),50.0);
		
		
	}
	
	@Test
	public void twoActorsWithNonParticipantActorTest() {
		
		Set<String> n = new HashSet<String>();
		n.add("One");
		n.add("DoNothing");
		
		
		Function<Set<String>, Double> v=  s -> {
			
			double res=0.0;
	        if(s.isEmpty())
	        	res= 0.0;
	        else if(s.size()==1 && s.contains("DoNothing")) {
	        	res=0.0;
	        } else {
	        	res= 100.0;
	        }
	        System.out.println("v(s) ("+s+")="+res);
	        return res;
	    };
	    
	    ShapleySimple shapley = new ShapleySimple(v,n);
		assertEquals(shapley.calculate("One"),100.0);
		assertEquals(shapley.calculate("DoNothing"),0.0);
		
		
	}
	
	@Test
	public void threeActorsTest() {
		
		Set<String> n = new HashSet<String>();
		n.add("One");
		n.add("Two");
		n.add("Three");
		
		Function<Set<String>, Double> v=  s -> {
			
			double res=0.0;
	        if(s.isEmpty())
	        	res= 0.0;
	        else if(s.size()==1 && !s.contains("Three")) {
	        	res=10.0;
	        } else if(s.size()==1 && s.contains("Three")) {
	        	res=90.0;
	        } else {
	        	res= 100.0;
	        }
	        System.out.println("v(s) ("+s+")="+res);
	        return res;
	    };
	    
	    ShapleySimple shapley = new ShapleySimple(v,n);
		assertEquals(shapley.calculate("One"),shapley.calculate("Two"));
		
		
	}

}
