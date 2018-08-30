package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class InnerProduct {
	
	public static void main(String[] args) {

		
		

		
		double[] dataA = {2.0, 1.0, 5.0};
		RealVector vectorA = new ArrayRealVector(dataA);
		
		double[] dataB = {1.0, 0.0, -1.0};
		RealVector vectorB = new ArrayRealVector(dataB);
		
		double innerProductAB = vectorA.dotProduct(vectorB);
		double innerProductBA = vectorB.dotProduct(vectorA);
		
		System.out.println(innerProductAB);
		System.out.println(innerProductBA);

	}

}
