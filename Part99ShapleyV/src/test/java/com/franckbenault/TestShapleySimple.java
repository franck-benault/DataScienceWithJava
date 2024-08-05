package com.franckbenault;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
 
public class TestShapleySimple {
	
	@Nested
	public class SimpleTests {
		@Test
		public void oneActorTest() {
			
			Set<String> n = new HashSet<>(Arrays.asList("One"));
					
			
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
		public void twoActorsTest() {
			
			Set<String> n = new HashSet<>(Arrays.asList("One", "Two"));	
			
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
		public void threeActorsTest() {
			
			Set<String> n = new HashSet<>(Arrays.asList("One", "Two", "Three"));
			
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
	
	@Nested
	public class AxiomTests {
		
		@Test
		public void axiomOneEfficiencyTest() {
			
			Set<String> n = new HashSet<>(Arrays.asList("One", "Two"));
	
			Function<Set<String>, Double> v=  s -> {
				//System.out.println("v(s) "+s);
		        if(s.isEmpty()) {
		        	return 0.0;
	        	} else if(s.size()==1 && s.contains("One")) {
	        		return 55.0;
	        	} else if(s.size()==1 && s.contains("Two")) {
	        		return 90.0;
	        	} else
		        	return 100.0;
		    };
		    
		    ShapleySimple shapley = new ShapleySimple(v,n);
			assertEquals(
					shapley.calculate("One")+shapley.calculate("Two"),
					v.apply(n), 
					"Efficiency some of all shapley value equals the value of the great coalition");
			
		}
		
		@Test
		public void axiomSymetryTest() {
			
			Set<String> n = new HashSet<>(Arrays.asList("One", "Two","Three", "Four"));
			
			
			Function<Set<String>, Double> v=  s -> {
				//System.out.println("v(s) "+s);
		        if(s.isEmpty())
		        	return 0.0;
		        else if(s.size()==1)
		        	return 10.0;
		        else if(s.size()==2)
		        	return 25.0;
		        else if(s.size()==3)
		        	return 55.0;
		        else
		        	return 100.0;
		    };
		    
		    ShapleySimple shapley = new ShapleySimple(v,n);
			assertEquals(shapley.calculate("One"),shapley.calculate("Two"));
			assertEquals(shapley.calculate("One"),shapley.calculate("Three"));			
			assertEquals(shapley.calculate("One"),shapley.calculate("Four"));			
		}
		
		
		@Test
		public void axiomLinearityTest() {
			
			Set<String> n = new HashSet<>(Arrays.asList("One", "Two", "Three"));
			
			
			Function<Set<String>, Double> v1=  s -> {
		        if(s.isEmpty())
		        	return 0.0;
		        else if(s.size()==1 && s.contains("One")) {
		        	return 10.0;
		        } else if(s.size()==1 && !s.contains("One")) {
		        	return 15.0;
		        } else if(s.size()==2) {
		        	return 45.0;
		        } else
		        	return 100.0;
		    };
		    
			Function<Set<String>, Double> v2=  s -> {
		        if(s.isEmpty())
		        	return 0.0;
		        else if(s.size()==1 && s.contains("One"))
		        	return 5.0;
		        else if(s.size()==1 && !s.contains("One"))
		        	return 16.0;
	        	else if(s.size()==2) 
	        		return 35.0;
		        else
		        	return 100.0;
		    };
		    
		    Function<Set<String>, Double> v12=  s -> v1.apply(s)+v2.apply(s);
		    
		    ShapleySimple shapley1 = new ShapleySimple(v1,n);
		    ShapleySimple shapley2 = new ShapleySimple(v2,n);
		    ShapleySimple shapley12 = new ShapleySimple(v12,n);
			assertEquals(shapley1.calculate("One")+shapley2.calculate("One"), shapley12.calculate("One"));
			assertEquals(shapley1.calculate("Two")+shapley2.calculate("Two"), shapley12.calculate("Two"));
			assertEquals(shapley1.calculate("Three")+shapley2.calculate("Three"), shapley12.calculate("Three"));
		}
		
		
		@Test
		public void axiomDummyPlayerTest() {
			
			Set<String> n = new HashSet<>(Arrays.asList("OneDoNothing", "Two","Three","Four"));
			
			
			Function<Set<String>, Double> v=  s -> {
				Set<String> s2 = new HashSet<String>();
				s2.addAll(s);
				s2.remove("OneDoNothing");
				double res=0.0;
		        if(s.isEmpty())
		        	res= 0.0;
		        else {
		        	res= 100.0*s2.size()/n.size();
		        }
		        System.out.println("v(s) ("+s+")="+res);
		        return res;
		    };
		    
		    ShapleySimple shapley = new ShapleySimple(v,n);
			assertEquals(shapley.calculate("OneDoNothing"),0.0);
			
			
		}
		
		
	}

}
