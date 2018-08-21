package net.franckbenault.linearalgebra.random;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class RandomizedMatrix {
	
	private AbstractRealDistribution distribution;

	public RandomizedMatrix(AbstractRealDistribution distribution, long seed) {
		this.distribution = distribution;
		distribution.reseedRandomGenerator(seed);
	}
	
	public RandomizedMatrix() {
		this(new UniformRealDistribution(-1,1), 0L);
	}

	public RealMatrix getMatrix(int numRows, int numCols) {
		RealMatrix output = new BlockRealMatrix(numRows, numCols);
		for(int i=0; i<numRows; i++)
			output.setRow(i, distribution.sample(numCols));
		return output;
	}

	public RealVector getVector(int dim) {
		
		return new ArrayRealVector(distribution.sample(dim));
	}

}
