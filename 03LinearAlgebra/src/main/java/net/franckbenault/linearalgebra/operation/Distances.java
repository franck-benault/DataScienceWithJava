package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class Distances {
	
	public static void main(String[] args) {
		double[] dataA = {1.0, 1.0, -1.0, 0.0};
		RealVector vectorA = new ArrayRealVector(dataA);
		
		double[] dataB = {0.0, 0.0, 0.0, 1.0};
		RealVector vectorB = new ArrayRealVector(dataB);
		
		double l1distance = vectorA.getL1Distance(vectorB);
		System.out.println("l1distance="+l1distance);
		
		double l2distance = vectorA.getDistance(vectorB);
		System.out.println("l2distance="+l2distance);
		
		double cosineDistance = vectorA.cosine(vectorB);
		System.out.println("cosineDistance="+cosineDistance);
		
		vectorA.unitize();
		vectorB.unitize();
		
		System.out.println(vectorA);
		System.out.println(vectorB);

	}

}
