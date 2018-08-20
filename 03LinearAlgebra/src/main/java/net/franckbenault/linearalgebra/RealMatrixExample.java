package net.franckbenault.linearalgebra;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class RealMatrixExample {

	public static void main(String[] args) {
		
		
		//constructor with row and col size
		int rowDimension = 9_950;
		int colDimension = 9_950;
		RealMatrix matrix = new Array2DRowRealMatrix(rowDimension, colDimension);

		matrix.setEntry(1, 2, 7.0);
		double norm = matrix.getNorm();
		System.out.println("norm ="+norm);
			
		//constructor with data
		double [][] data = {{1.0, 2.3}, {2.2, 2.5}, {2.6,5.9}};
		matrix = new Array2DRowRealMatrix(data);
		System.out.println(matrix);
		
		//copy
		RealMatrix anotherMatrix = matrix.copy();
		System.out.println(anotherMatrix);

	}

}
