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
		System.out.println("Accuraty  "+matrix.accuraty());
		System.out.println("Recall    "+matrix.recall());
		System.out.println("Precision "+matrix.precision());
		System.out.println("F1 score  "+matrix.f1score());
	}

}
