package com.franckbenault.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


public class TestParliament {
	@Test
	public void example1Test() {
		Map<String,Integer> nbMpPerParty =
				new HashMap<>();
		nbMpPerParty.put("P1", 49);
		nbMpPerParty.put("P2", 4);
		nbMpPerParty.put("P3", 48);
		
		Parliament p = new Parliament(nbMpPerParty);
		  
		assertEquals(p.calculate("P1"),1.0/3);
		assertEquals(p.calculate("P2"),1.0/3);
		assertEquals(p.calculate("P3"),1.0/3);
		
	}
	
	@Test
	public void example2Test() {
		Map<String,Integer> nbMpPerParty =
				new HashMap<>();
		nbMpPerParty.put("P1", 33);
		nbMpPerParty.put("P2", 30);
		nbMpPerParty.put("P3", 16);
		nbMpPerParty.put("P4", 13);
		nbMpPerParty.put("P5", 6);
		nbMpPerParty.put("P6", 3);
		
		Parliament p = new Parliament(nbMpPerParty);

		System.out.println(p.calculate("P1"));
		System.out.println(p.calculate("P2"));
		System.out.println(p.calculate("P3"));
		System.out.println(p.calculate("P4"));
		System.out.println(p.calculate("P5"));
		System.out.println(p.calculate("P6"));
	}
}

