package com.franckbenault;


import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.junit.jupiter.api.Test;

public class Test01Vectors {

	@Test
	public void FirstVector01Test() {
		
		RealVector vector1 = new ArrayRealVector(4);
		System.out.println(vector1);
		
		double[] data = {1.8, 9.0, 3.14};
		RealVector vector2 = new ArrayRealVector(data);
		System.out.println(vector2);
		
		RealVector vector3 = new ArrayRealVector(vector2);
		System.out.println(vector3);
	}

}
