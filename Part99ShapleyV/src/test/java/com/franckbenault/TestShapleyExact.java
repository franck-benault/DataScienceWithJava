package com.franckbenault;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
 
public class TestShapleyExact{
	
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
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(shapley.getShapleyValue("One"),100.0);		
			
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
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(shapley.getShapleyValue("One"),50.0);
			assertEquals(shapley.getShapleyValue("Two"),50.0);
			
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
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(shapley.getShapleyValue("One"),shapley.getShapleyValue("Two"));
			
			
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
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(
					shapley.getShapleyValue("One")+shapley.getShapleyValue("Two"),
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
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(shapley.getShapleyValue("One"),shapley.getShapleyValue("Two"));
			assertEquals(shapley.getShapleyValue("One"),shapley.getShapleyValue("Three"));			
			assertEquals(shapley.getShapleyValue("One"),shapley.getShapleyValue("Four"));			
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
		    
		    ShapleyExact shapley1 = new ShapleyExact(v1,n);
		    ShapleyExact shapley2 = new ShapleyExact(v2,n);
		    ShapleyExact shapley12 = new ShapleyExact(v12,n);
			assertEquals(shapley1.getShapleyValue("One")+shapley2.getShapleyValue("One"), shapley12.getShapleyValue("One"), 0.1);
			assertEquals(shapley1.getShapleyValue("Two")+shapley2.getShapleyValue("Two"), shapley12.getShapleyValue("Two"), 0.1);
			assertEquals(shapley1.getShapleyValue("Three")+shapley2.getShapleyValue("Three"), shapley12.getShapleyValue("Three"),0.1);
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
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(shapley.getShapleyValue("OneDoNothing"),0.0);
			
			
		}
		
		
	}
	
	@Nested
	public class MediumOCTests {
		//O Caelen
		//example coming from https://medium.com/the-modern-scientist/what-is-the-shapley-value-8ca624274d5a
		@Test
		public void example1Test() {
			Set<String> n = new HashSet<>(Arrays.asList(
					"P1", "P2","P3"));
			
			Function<Set<String>, Double> v=  s -> {;
				double res=0.0;
		        if(s.isEmpty())
		        	res= 0.0;
		        else if(s.size()==1) {
		        	if(s.contains("P1"))
		        		res=80;
		        	if(s.contains("P2"))
		        		res=56;
		        	if(s.contains("P3"))
		        		res=70;
		        } else if(s.size()==2) {
			       	if(s.contains("P1")&&s.contains("P2"))
			       		res=80;
			       	if(s.contains("P1")&& s.contains("P3"))
			       		res=85;
			       	if(s.contains("P2")&&s.contains("P3"))
			       		res=72;
		        } else {
		        	res= 90;
		        }
		        //System.out.println("v(s) ("+s+")="+res);
		        return res;
		    };
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(shapley.getShapleyValue("P1"),39.16, 2);
		}
		
		@Test
		public void example2GloveTest() {
			Set<String> n = new HashSet<>(Arrays.asList(
					"P1", "P2","P3"));
					
			Function<Set<String>, Double> v=  s -> {
				double res=0.0;
		        if(s.isEmpty())
		        	res= 0.0;
		        else if(s.size()==1) {
		        	res=0;
		        } else if(s.size()==2) {
			       	if(s.contains("P1")&&s.contains("P2"))
			       		res=0;
			       	if(s.contains("P1")&& s.contains("P3"))
			       		res=1;
			       	if(s.contains("P2")&&s.contains("P3"))
			       		res=1;
		        } else {
		        	res= 1;
		        }
		        //System.out.println("v(s) ("+s+")="+res);
		        return res;
		    };
		    
		    ShapleyExact shapley = new ShapleyExact(v,n);
			assertEquals(shapley.getShapleyValue("P1"),1.0/6);
			assertEquals(shapley.getShapleyValue("P2"),1.0/6);
			assertEquals(shapley.getShapleyValue("P3"),4.0/6);
		}
	}
	

}
