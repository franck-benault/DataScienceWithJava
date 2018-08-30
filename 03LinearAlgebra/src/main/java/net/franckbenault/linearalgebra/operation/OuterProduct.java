package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class OuterProduct {
	
	public static void main(String[] args) {

	
		
		double[] dataA = {2.0, 1.0, 5.0};
		RealVector vectorA = new ArrayRealVector(dataA);
		
		double[] dataB = {1.0, 0.0};
		RealVector vectorB = new ArrayRealVector(dataB);
		
		RealMatrix outerProductAB = vectorA.outerProduct(vectorB);
		RealMatrix outerProductBA = vectorB.outerProduct(vectorA);
		
		System.out.println(outerProductAB);
		System.out.println(outerProductBA);

	}

}
