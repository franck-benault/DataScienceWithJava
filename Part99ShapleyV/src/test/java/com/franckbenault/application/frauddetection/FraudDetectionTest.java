package com.franckbenault.application.frauddetection;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FraudDetectionTest {

	@Test
	public void test() {

		List<Boolean> transactions = 
				Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE
						);
		
		Map<String,Set<Integer>> fraudDetectionRules =new HashMap<>();
		
		fraudDetectionRules.put("r1",new HashSet<Integer>(Arrays.asList(1)));	
		fraudDetectionRules.put("r2",new HashSet<Integer>(Arrays.asList(8)));	
		fraudDetectionRules.put("r3",new HashSet<Integer>(Arrays.asList(0,1,8,9)));
		fraudDetectionRules.put("r4",new HashSet<Integer>(Arrays.asList(7,8,9)));
		fraudDetectionRules.put("r5",new HashSet<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules);	
		f.showAllSortedRules();

	}
	
	@Test
	public void test2() {

		List<Boolean> transactions = 
				Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE
						);
		
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
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules);	
		f.showAllSortedRules();

	}

}
