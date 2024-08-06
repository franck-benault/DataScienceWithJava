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
		    
		    ShapleySimple shapley = new ShapleySimple(v,n);
			assertEquals(shapley.calculate("P1"),39.16, 2);
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
		    
		    ShapleySimple shapley = new ShapleySimple(v,n);
			assertEquals(shapley.calculate("P1"),1.0/6);
			assertEquals(shapley.calculate("P2"),1.0/6);
			assertEquals(shapley.calculate("P3"),4.0/6);
		}
	}
	
	@Nested
	public class ParlementTests {

		@Test
		public void example1Test() {
			Set<String> n = new HashSet<>(Arrays.asList(
					"P1", "P2","P3"));
			
			Function<String, Integer> nbMPF=  s -> {
				if(s.equals("P1"))
					return 49;
				if(s.equals("P2"))
					return 4;				
				if(s.equals("P3"))
					return 48;	
				return 0;
			};
					
			Function<Set<String>, Double> v=  s -> {
				int nbMP=0;
				for(String p : s) {
					nbMP+=nbMPF.apply(p);
				}
				if(nbMP>50)
					return 1.0;
				else
					return 0.0;
		    };
			
			
		    ShapleySimple shapley = new ShapleySimple(v,n);
			assertEquals(shapley.calculate("P1"),1.0/3);
			assertEquals(shapley.calculate("P2"),1.0/3);
			assertEquals(shapley.calculate("P3"),1.0/3);
			
		}
		
		@Test
		public void example2Test() {
			Set<String> n = new HashSet<>(Arrays.asList(
					"P1", "P2","P3","P4","P5","P6"));
			
			Function<String, Integer> nbMPF=  s -> {
				if(s.equals("P1"))
					return 33;
				if(s.equals("P2"))
					return 30;				
				if(s.equals("P3"))
					return 16;	
				if(s.equals("P4"))
					return 13;		
				if(s.equals("P5"))
					return 6;	
				if(s.equals("P6"))
					return 3;	
				return 0;
			};
					
			Function<Set<String>, Double> v=  s -> {
				int nbMP=0;
				for(String p : s) {
					nbMP+=nbMPF.apply(p);
				}
				if(nbMP>50)
					return 1.0;
				else
					return 0.0;
		    };
			
			
		    ShapleySimple shapley = new ShapleySimple(v,n);
		    shapley.calculateAll();
			System.out.println(shapley.calculate("P1"));
			System.out.println(shapley.calculate("P2"));
			System.out.println(shapley.calculate("P3"));
			System.out.println(shapley.calculate("P4"));
			System.out.println(shapley.calculate("P5"));
			System.out.println(shapley.calculate("P6"));
		}
	}

}
