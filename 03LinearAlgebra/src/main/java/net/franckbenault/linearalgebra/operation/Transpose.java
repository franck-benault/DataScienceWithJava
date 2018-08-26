package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class Transpose {
	
	public static void main(String[] args) {
		double[] data = {1.0, 6.0, 2.5, 2.3};
		RealMatrix columnVector = new Array2DRowRealMatrix(data);
		
		RealMatrix rowVector = columnVector.transpose();
		
		System.out.println(columnVector);
		System.out.println(rowVector);
		
		
		
		double [][] data2 = {{1.0, 2.3}, {2.2, 2.5}, {2.6,5.9}};
		RealMatrix matrix = new Array2DRowRealMatrix(data2);
		RealMatrix transposedMatrix = matrix.transpose();
		
		System.out.println(matrix);
		System.out.println(transposedMatrix);

	}

}
