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
	
	@Test
	public void example3BelgiumParliamentTest() {
		Map<String,Integer> nbMpPerParty =
				new HashMap<>();
		
		nbMpPerParty.put("N-VA",24);
		nbMpPerParty.put("VB",20);
		nbMpPerParty.put("MR",19);
		nbMpPerParty.put("PS",16);
		nbMpPerParty.put("PTB/PVDA",15);
		nbMpPerParty.put("LE",14);
		nbMpPerParty.put("Vooruit",13);
		nbMpPerParty.put("CD&V",11);
		nbMpPerParty.put("Ecolo/Groen",9);
		nbMpPerParty.put("Open VLD",8);
		nbMpPerParty.put("DeFI",1);
		
		Parliament p = new Parliament(nbMpPerParty);
		for(String party : nbMpPerParty.keySet())		
			System.out.println("party "+party+" "+p.calculate(party));

	}
	

}

