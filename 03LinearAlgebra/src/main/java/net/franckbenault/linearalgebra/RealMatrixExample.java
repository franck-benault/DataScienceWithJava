package net.franckbenault.linearalgebra;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class RealMatrixExample {

	public static void main(String[] args) {
		
		
		//constructor with row and col size
		int rowDimension =2;
		int colDimension = 3;
		RealMatrix matrix = new Array2DRowRealMatrix(rowDimension, colDimension);
		System.out.println(matrix);
		//add default value
		matrix = matrix.scalarAdd(2.3);
		System.out.println(matrix);
		
		//constructor with data
		double [][] data = {{1.0, 2.3}, {2.2, 2.5}, {2.6,5.9}};
		matrix = new Array2DRowRealMatrix(data);
		System.out.println(matrix);
		
		//copy
		RealMatrix anotherMatrix = matrix.copy();
		System.out.println(anotherMatrix);

	}

}
