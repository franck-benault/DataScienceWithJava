package com.franckbenault.application.frauddetection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FraudDetectionTest {

	@Test
	public void test() {

		List<Boolean> transactions = new ArrayList<Boolean>(Arrays.asList(new Boolean[10]));
		Collections.fill(transactions, Boolean.FALSE);
		transactions.set(1,Boolean.TRUE);
		transactions.set(8,Boolean.TRUE);
		
		Map<String,Set<Integer>> fraudDetectionRules =new HashMap<>();
		
		fraudDetectionRules.put("r1",new HashSet<Integer>(Arrays.asList(1)));	
		fraudDetectionRules.put("r2",new HashSet<Integer>(Arrays.asList(8)));	
		fraudDetectionRules.put("r3",new HashSet<Integer>(Arrays.asList(0,1,8,9)));
		fraudDetectionRules.put("r4",new HashSet<Integer>(Arrays.asList(7,8,9)));
		fraudDetectionRules.put("r5",new HashSet<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules,true,0);	
		f.showAllSortedRules();
		f.showScoreFirstRules();

	}
	
	@Test
	public void test2() {

		List<Boolean> transactions = new ArrayList<Boolean>(Arrays.asList(new Boolean[10]));
		Collections.fill(transactions, Boolean.FALSE);
		transactions.set(1,Boolean.TRUE);
		transactions.set(8,Boolean.TRUE);
		
		Map<String,Set<Integer>> fraudDetectionRules =new HashMap<>();
		
		fraudDetectionRules.put("r1",new HashSet<Integer>(Arrays.asList(1)));	
		fraudDetectionRules.put("r2",new HashSet<Integer>(Arrays.asList(8)));	
		fraudDetectionRules.put("r3",new HashSet<Integer>(Arrays.asList(0,1,8,9)));
		fraudDetectionRules.put("r4",new HashSet<Integer>(Arrays.asList(7,8,9)));
		fraudDetectionRules.put("r5",new HashSet<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
		fraudDetectionRules.put("r6",new HashSet<Integer>(Arrays.asList(2,3,7,8,9)));
		fraudDetectionRules.put("r7",new HashSet<Integer>(Arrays.asList(0,1,2,3,4,5,6,7)));
		fraudDetectionRules.put("r8",new HashSet<Integer>(Arrays.asList(3,7,8,9)));
		fraudDetectionRules.put("r9",new HashSet<Integer>(Arrays.asList(1,2,3,7,8,9)));
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules,true,0);	
		f.showAllSortedRules();
		f.showScoreFirstRules();

	}
	
	@Test
	public void test3() {

		List<Boolean> transactions = new ArrayList<Boolean>(Arrays.asList(new Boolean[100]));
		Collections.fill(transactions, Boolean.FALSE);
		transactions.set(1,Boolean.TRUE);
		transactions.set(11,Boolean.TRUE);
		transactions.set(21,Boolean.TRUE);
		transactions.set(31,Boolean.TRUE);
		transactions.set(41,Boolean.TRUE);
		
		Map<String,Set<Integer>> fraudDetectionRules =new HashMap<>();
		
		fraudDetectionRules.put("r01",new HashSet<Integer>(Arrays.asList(1,2,7,34)));	
		fraudDetectionRules.put("r02",new HashSet<Integer>(Arrays.asList(8,11,22,33)));	
		fraudDetectionRules.put("r03",new HashSet<Integer>(Arrays.asList(0,1,8,9,37)));
		fraudDetectionRules.put("r04",new HashSet<Integer>(Arrays.asList(7,8,9,11,31,49)));
		fraudDetectionRules.put("r05",new HashSet<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,22,23)));
		fraudDetectionRules.put("r06",new HashSet<Integer>(Arrays.asList(2,3,7,8,9,11,22)));
		fraudDetectionRules.put("r07",new HashSet<Integer>(Arrays.asList(0,1,3,4,5,6,7,21,41)));
		fraudDetectionRules.put("r08",new HashSet<Integer>(Arrays.asList(3,7,8,9,11,27,33)));
		fraudDetectionRules.put("r09",new HashSet<Integer>(Arrays.asList(1,2,3,7,8,9,44,48)));
		fraudDetectionRules.put("r10",new HashSet<Integer>(Arrays.asList(31,48)));
		fraudDetectionRules.put("r11",new HashSet<Integer>(Arrays.asList(48)));
		fraudDetectionRules.put("r12",new HashSet<Integer>(Arrays.asList(48,49)));
		fraudDetectionRules.put("r13",new HashSet<Integer>(Arrays.asList(1,45,48,49)));
		fraudDetectionRules.put("r14",new HashSet<Integer>(Arrays.asList(1,2,7,22,45,48,49)));
		fraudDetectionRules.put("r15",new HashSet<Integer>(Arrays.asList(1,2,8,23,45,48)));
		fraudDetectionRules.put("r16",new HashSet<Integer>(Arrays.asList(1,2,8,21,40,48)));
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules,true,0);	
		f.showAllSortedRules();
		f.showScoreFirstRules();
		System.out.println("----------");
			
		f = new FraudDetection(transactions, fraudDetectionRules,false,1_000);	
		f.showAllSortedRules();
		System.out.println("----------");
		
		f = new FraudDetection(transactions, fraudDetectionRules,false,10_000);	
		f.showAllSortedRules();


	}

}
