package com.franckbenault.application.frauddetection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
		List<Boolean> transactions = 
				Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE
						);
		Set<Integer> detectedFraudulentTs =new HashSet<>(Arrays.asList(1));	
		ConfusionMatrix r1 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r1");		
		r1.draw();
		r1.printResult();
		
		detectedFraudulentTs =new HashSet<>(Arrays.asList(8));	
		ConfusionMatrix r2 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r2");		
		r2.draw();
		r2.printResult();
		
		detectedFraudulentTs =new HashSet<>(Arrays.asList(0,1,8,9));	
		ConfusionMatrix r3 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r3");		
		r3.draw();
		r3.printResult();

		detectedFraudulentTs =new HashSet<>(Arrays.asList(7,8,9));	
		ConfusionMatrix r4 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r4");		
		r4.draw();
		r4.printResult();
	
		detectedFraudulentTs =new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));	
		ConfusionMatrix r5 = new ConfusionMatrix(transactions, detectedFraudulentTs,"r5");		
		r5.draw();
		r5.printResult();

	}

}
