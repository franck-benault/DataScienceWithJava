package com.franckbenault.application.frauddetection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfusionMatrixTest {

	@Test
	void simpleMatrixTest() {
		ConfusionMatrix matrix = new ConfusionMatrix(4,2,1,93);
		
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
	void r1Test() {
		System.out.println("--r1--");
		ConfusionMatrix r1 = new ConfusionMatrix(1,0,1,8);		
		r1.draw();
		r1.printResult();
		
		System.out.println("--r3--");
		ConfusionMatrix r3 = new ConfusionMatrix(2,2,0,6);		
		r3.draw();
		r3.printResult();
		
		System.out.println("--r4--");
		ConfusionMatrix r4 = new ConfusionMatrix(1,2,1,6);		
		r4.draw();
		r4.printResult();
		
		System.out.println("--r5--");
		ConfusionMatrix r5 = new ConfusionMatrix(2,8,0,0);		
		r5.draw();
		r5.printResult();
	}

}
