package com.franckbenault.application.parliament;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.franckbenault.Util;

class TestParliamentRandomSampling {

	@Test
	public void parliamentWithALotOfPartiesUsingExactTest() {
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
		
		nbMpPerParty.put("petit11",1);
		nbMpPerParty.put("petit12",1);
		nbMpPerParty.put("petit13",1);
		nbMpPerParty.put("petit14",1);
		nbMpPerParty.put("petit15",1);
		nbMpPerParty.put("petit16",1);	
		nbMpPerParty.put("petit17",1);	
		nbMpPerParty.put("petit18",1);	
		nbMpPerParty.put("petit19",1);	
		nbMpPerParty.put("petit20",1);
		
		Parliament p = new Parliament(nbMpPerParty,true,0);
		double sum =0.0;
		for(String party : nbMpPerParty.keySet()) {		
			System.out.println("party "+party+" "+p.calculate(party));
			sum+=p.calculate(party);
		}
		assertEquals(sum,1.0, 0.1);

	}
	
	@Test
	public void parliamentWithALotOfPartiesUsingRandomSamplingTest() {
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
		
		nbMpPerParty.put("petit11",1);
		nbMpPerParty.put("petit12",1);
		nbMpPerParty.put("petit13",1);
		nbMpPerParty.put("petit14",1);
		nbMpPerParty.put("petit15",1);
		nbMpPerParty.put("petit16",1);	
		nbMpPerParty.put("petit17",1);	
		nbMpPerParty.put("petit18",1);	
		nbMpPerParty.put("petit19",1);	
		nbMpPerParty.put("petit20",1);
		
		for(int size =10000 ; size<=1000000; size*=10) {
			Parliament p = new Parliament(nbMpPerParty,false,size);
			System.out.println("###### size ="+size);
			System.out.println("######  size/Util.factorial(nbMpPerParty.size()) "+
					(0.0+size)/Util.factorial(nbMpPerParty.size()));
			System.out.println("petit11  "+p.calculate("petit11"));
			System.out.println("petit20  "+p.calculate("petit20"));
		
		}


	}

}
