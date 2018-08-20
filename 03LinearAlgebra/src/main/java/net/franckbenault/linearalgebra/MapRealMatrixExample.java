package net.franckbenault.linearalgebra;

import org.apache.commons.math3.linear.OpenMapRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class MapRealMatrixExample {

	public static void main(String[] args) {
		
		
		//constructor with row and col size
		int rowDimension = 40_000;
		int colDimension = 40_000;
		//OpenMapRealMatrix for big size matrix management with mainly zeros
		RealMatrix matrix = new OpenMapRealMatrix(rowDimension, colDimension);
		//add default value
		matrix.setEntry(1, 2, 7.0);
		double norm = matrix.getNorm();
		System.out.println("norm ="+norm);


	}

}
