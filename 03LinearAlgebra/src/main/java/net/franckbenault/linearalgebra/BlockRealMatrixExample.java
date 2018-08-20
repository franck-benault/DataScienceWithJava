package net.franckbenault.linearalgebra;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class BlockRealMatrixExample {

	public static void main(String[] args) {
		
		
		//constructor with row and col size
		int rowDimension = 9_950;
		int colDimension = 9_950;
		//blockRealMatrix for big size matrix management
		RealMatrix matrix = new BlockRealMatrix(rowDimension, colDimension);
		
		matrix.setEntry(1, 2, 7.0);
		double norm = matrix.getNorm();
		System.out.println("norm ="+norm);
		

	}

}
