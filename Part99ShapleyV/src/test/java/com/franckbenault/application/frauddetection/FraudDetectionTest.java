package com.franckbenault.application.frauddetection;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FraudDetectionTest {
	
	private Logger logger = LoggerFactory.getLogger(FraudDetectionTest.class);
	
	private Map<String,Boolean> getSmallTransactionSample() {
		Map<String,Boolean> transactions = new HashMap<>();
		for(int  i=0; i<10 ; i++)
			transactions.put(String.format("%02d",i),Boolean.FALSE);
		transactions.put("01",Boolean.TRUE);
		transactions.put("08",Boolean.TRUE);
		//System.out.println(transactions);
		return transactions;
	}

	@Test
	public void smallSetOfTransactionFewRulesTest() {

		Map<String,Boolean> transactions = getSmallTransactionSample();
		
		Map<String,Set<String>> fraudDetectionRules =new HashMap<>();
		
		fraudDetectionRules.put("r1",new HashSet<String>(Arrays.asList("01")));	
		fraudDetectionRules.put("r2",new HashSet<String>(Arrays.asList("08")));	
		fraudDetectionRules.put("r3",new HashSet<String>(Arrays.asList("00","01","08","09")));
		fraudDetectionRules.put("r4",new HashSet<String>(Arrays.asList("07","08","09")));
		fraudDetectionRules.put("r5",new HashSet<String>(Arrays.asList("00","01","02","03","04","05","06","07","08","09")));
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules,true,0);	
		f.showAllSortedRules();
		f.showScoreFirstRules();

	}
	
	@Test
	public void smallSetOfTransactionMoreRulesTest2(){

		Map<String,Boolean> transactions = getSmallTransactionSample();
		
		Map<String,Set<String>> fraudDetectionRules =new HashMap<>();
		
		fraudDetectionRules.put("r1",new HashSet<String>(Arrays.asList("01")));	
		fraudDetectionRules.put("r2",new HashSet<String>(Arrays.asList("08")));	
		fraudDetectionRules.put("r3",new HashSet<String>(Arrays.asList("00","01","08","09")));
		fraudDetectionRules.put("r4",new HashSet<String>(Arrays.asList("07","08","09")));
		fraudDetectionRules.put("r5",new HashSet<String>(Arrays.asList("00","01","02","03","04","05","06","07","08","09")));
		fraudDetectionRules.put("r6",new HashSet<String>(Arrays.asList("02","03","06","07","08","09")));
		fraudDetectionRules.put("r7",new HashSet<String>(Arrays.asList("00","01","02","03","04","05","06","07")));
		fraudDetectionRules.put("r8",new HashSet<String>(Arrays.asList("03","07","08","09")));
		fraudDetectionRules.put("r9",new HashSet<String>(Arrays.asList("01","02","03","08","09")));
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules,true,0);	
		f.showAllSortedRules();
		f.showScoreFirstRules();

	}
	
	private Map<String,Boolean> getBigTransactionSample() {
		Map<String,Boolean> transactions = new HashMap<>();
		for(int  i=0; i<50 ; i++)
			if(i%10==1)
				transactions.put(String.format("%02d",i),Boolean.TRUE);				
			else 
				transactions.put(String.format("%02d",i),Boolean.FALSE);
		//System.out.println(transactions);
		return transactions;
	}
	
	@Test
	public void bigSetOfTransactionHardCodedRulesTest3() {

		Map<String,Boolean> transactions =  getBigTransactionSample();
		
		Map<String,Set<String>> fraudDetectionRules =new HashMap<>();
		
		fraudDetectionRules.put("r01",new HashSet<String>(Arrays.asList("01","02","07","34")));	
		fraudDetectionRules.put("r02",new HashSet<String>(Arrays.asList("08","11","22","33")));	
		fraudDetectionRules.put("r03",new HashSet<String>(Arrays.asList("00","01","08","09","37")));
		fraudDetectionRules.put("r04",new HashSet<String>(Arrays.asList("07","08","09","11","31","49")));
		fraudDetectionRules.put("r05",new HashSet<String>(Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","22","23")));
		fraudDetectionRules.put("r06",new HashSet<String>(Arrays.asList("02","03","07","08","09","11","22")));
		fraudDetectionRules.put("r07",new HashSet<String>(Arrays.asList("00","01","03","04","05","06","07","21","41")));
		fraudDetectionRules.put("r08",new HashSet<String>(Arrays.asList("03","07","08","09","11","27","33")));
		fraudDetectionRules.put("r09",new HashSet<String>(Arrays.asList("01","02","03","07","08","09","44","48")));
		fraudDetectionRules.put("r10",new HashSet<String>(Arrays.asList("31","48")));
		fraudDetectionRules.put("r11",new HashSet<String>(Arrays.asList("48")));
		fraudDetectionRules.put("r12",new HashSet<String>(Arrays.asList("48","49")));
		fraudDetectionRules.put("r13",new HashSet<String>(Arrays.asList("01","45","48","49")));
		fraudDetectionRules.put("r14",new HashSet<String>(Arrays.asList("01","02","07","22","45","48","49")));
		fraudDetectionRules.put("r15",new HashSet<String>(Arrays.asList("01","02","08","23","45","48")));
		fraudDetectionRules.put("r16",new HashSet<String>(Arrays.asList("01","02","08","21","40","48")));
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules,true,0);	
		f.showAllSortedRules();
		f.showScoreFirstRules();
		logger.info("----------");
			
		f = new FraudDetection(transactions, fraudDetectionRules,false,1_000);	
		f.showAllSortedRules();
		logger.info("----------");
		
		f = new FraudDetection(transactions, fraudDetectionRules,false,10_000);	
		f.showAllSortedRules();
		logger.info("----------");
		
		f = new FraudDetection(transactions, fraudDetectionRules,false,20_000);	
		f.showAllSortedRules();
		logger.info("----------");


	}
	
	private Set<String> getRandomRule() {
		int n=50;
		
		Set<String> rule =new HashSet<>();
		
		for(int count=0; count<3; count++) {
			int index = (int)(Math.random() * n); 
			rule.add(String.format("%02d", index));
		}
		return rule;
	}
	
	
	
	@Test
	public void bigSetOfTransactionRandomRulesTest4() {

		Map<String,Boolean> transactions =  getBigTransactionSample();
		
		Map<String,Set<String>> fraudDetectionRules =new HashMap<>();
		
		for(int i=1; i<=100; i++)
			fraudDetectionRules.put(String.format("R%03d", i), getRandomRule());
		
		FraudDetection f = new FraudDetection(transactions, fraudDetectionRules,false,50_000);	
		f.showAllSortedRules();
		for(String rule : f.getAllSortedRules())
			logger.info(rule+""+fraudDetectionRules.get(rule));
		f.showScoreFirstRules();


	}
	


}
