package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Multiplication {
	
	public static void main(String[] args) {

		
		
		
		double [][] dataA = {{1.0, 1.0}, {2.0, 2.0}, {3.0,3.0}};
		RealMatrix matrixA = new Array2DRowRealMatrix(dataA);
		
		double [][] dataB = {{1.0, 1.0, 1.0}, {3.0,3.0, 3.0}};
		RealMatrix matrixB = new Array2DRowRealMatrix(dataB);
		
		System.out.println(matrixA);
		System.out.println(matrixB);
		
		RealMatrix matrixAB = matrixA.multiply(matrixB);
		RealMatrix matrixAB2 = matrixB.preMultiply(matrixA);
		
		RealMatrix matrixBA = matrixB.multiply(matrixA);
		RealMatrix matrixBA2 = matrixA.preMultiply(matrixB);
		
		System.out.println(matrixAB);
		System.out.println(matrixAB2);
		System.out.println(matrixBA);
		System.out.println(matrixBA2);
		
		double[] dataV = {2.0, 1.0};
		RealVector vector = new ArrayRealVector(dataV);
		
		RealVector vectorAv = matrixA.operate(vector);
		
		System.out.println(vectorAv);

	}

}
