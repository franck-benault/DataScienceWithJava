package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class Scaling {
	
	public static void main(String[] args) {
		double[] data = {1.0, 6.0, 2.5, 2.3};
		RealVector vector = new ArrayRealVector(data);
		
		RealVector scaledVector = vector.mapMultiply(1.2);
		vector.mapMultiplyToSelf(1.3);
		
		System.out.println(vector);
		System.out.println(scaledVector);
		
		scaledVector = scaledVector.mapDivide(1.2);
		vector.mapDivideToSelf(1.3);
		
		System.out.println(vector);
		System.out.println(scaledVector);
		
		
	}

}
