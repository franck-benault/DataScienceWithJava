package net.franckbenault.linearalgebra.random;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Main {

	public static void main(String[] args) {
		
		int numRows = 3;
		int numCols = 4;
		long seed = 0L;
		
		RandomizedMatrix rndMatrix = new RandomizedMatrix(new NormalDistribution(0.0,0.5), seed);
		RealMatrix matrix = rndMatrix.getMatrix(numRows, numCols);
		RealVector vector = rndMatrix.getVector(numRows);
		System.out.println(matrix);
		System.out.println(vector);

		rndMatrix = new RandomizedMatrix(new NormalDistribution(0.0,0.5), seed);
		matrix = rndMatrix.getMatrix(numRows, numCols);
		vector = rndMatrix.getVector(numRows);
		System.out.println(matrix);
		System.out.println(vector);
	}

}
