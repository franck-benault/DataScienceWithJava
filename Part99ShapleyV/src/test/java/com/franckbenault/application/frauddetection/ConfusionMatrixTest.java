package com.franckbenault.application.frauddetection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class ConfusionMatrixTest {

	@Test
	public void simpleMatrixTest() {
		ConfusionMatrix matrix = new ConfusionMatrix(4,2,1,93,"Simple sample");
		
		assertTrue(matrix.accuraty()>=0);
		assertTrue(matrix.accuraty()<=1);
		assertEquals(matrix.accuraty(),0.97);
		
		assertTrue(matrix.recall()>=0);
		assertTrue(matrix.recall()<=1);
		assertEquals(matrix.recall(),0.8);
		
		
		assertTrue(matrix.precision()>=0);
		assertTrue(matrix.precision()<=1);
		assertEquals(matrix.precision(),0.66,0.01);
		
		assertTrue(matrix.f1score()>=0);
		assertTrue(matrix.f1score()<=1);
		
		matrix.draw();
		matrix.printResult();
	}
	
	
	@Test
	public void r1Test() {
		Map<String,Boolean> transactions = new HashMap<>();
		for(int  i=0; i<10 ; i++)
			transactions.put(String.format("%02d",i),Boolean.FALSE);
		transactions.put("01",Boolean.TRUE);
		transactions.put("08",Boolean.TRUE);
		
		Set<String> detectedFraudulentTs =new HashSet<>(Arrays.asList("01"));	
		ConfusionMatrix r1 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r1");		
		r1.draw();
		r1.printResult();
		
		detectedFraudulentTs =new HashSet<>(Arrays.asList("08"));	
		ConfusionMatrix r2 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r2");		
		r2.draw();
		r2.printResult();
		
		detectedFraudulentTs =new HashSet<>(Arrays.asList("00","01","08","09"));	
		ConfusionMatrix r3 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r3");		
		r3.draw();
		r3.printResult();

		detectedFraudulentTs =new HashSet<>(Arrays.asList("07","08","09"));	
		ConfusionMatrix r4 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r4");		
		r4.draw();
		r4.printResult();
	
		detectedFraudulentTs =new HashSet<>(Arrays.asList("00","01","02","03","04","05","06","07","08","09"));	
		ConfusionMatrix r5 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r5");		
		r5.draw();
		r5.printResult();

	}

}
