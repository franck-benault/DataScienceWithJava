package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class AdditionSubtraction {
	
	public static void main(String[] args) {
		double[] dataA = {1.0, 6.0, 2.5, 2.3};
		RealVector vectorA = new ArrayRealVector(dataA);
		
		double[] dataB = {1.0, 6.0, 2.5, 2.2};
		RealVector vectorB = new ArrayRealVector(dataB);
		
		RealVector vAPlusB = vectorA.add(vectorB);
		System.out.println(vAPlusB);
		
		RealVector vAMinusB = vectorA.subtract(vectorB);
		System.out.println(vAMinusB);
		
		
		double [][] data2a = {{1.1, 2.3}, {2.3, 2.5}, {2.6,5.9}};
		RealMatrix matrixA = new Array2DRowRealMatrix(data2a);
		
		double [][] data2b = {{1.0, 2.3}, {2.2, 2.5}, {2.6,5.9}};
		RealMatrix matrixB = new Array2DRowRealMatrix(data2b);
		
		RealMatrix mAPlusB = matrixA.add(matrixB);
		System.out.println(mAPlusB);
		
		RealMatrix mAMinusB = matrixA.subtract(matrixB);
		System.out.println(mAMinusB);

	}

}
