package net.franckbenault.linearalgebra;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class BlockRealMatrixExample {

	public static void main(String[] args) {
		
		
		//constructor with row and col size
		int rowDimension = 50;
		int colDimension = 60;
		//blockRealMatrix for big size matrix management
		RealMatrix matrix = new BlockRealMatrix(rowDimension, colDimension);
		//add default value
		matrix = matrix.scalarAdd(2.3);
	
		


	}

}
