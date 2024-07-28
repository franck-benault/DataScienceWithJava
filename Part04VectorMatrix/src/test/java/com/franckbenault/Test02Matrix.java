package com.franckbenault;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.jupiter.api.Test;

public class Test02Matrix {

	@Test
	public void FirstMatrix01Test() {
		
		RealMatrix matrix1 = new Array2DRowRealMatrix(3,4);
		System.out.println(matrix1);
		

	}
}
