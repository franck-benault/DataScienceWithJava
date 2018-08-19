package net.franckbenault.linearalgebra;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class RealVectorExample {

	public static void main(String[] args) {
		
		//constructor with size
		int size = 3;
		RealVector vector = new ArrayRealVector(size);
		System.out.println(vector);
		
		//constructor with data
		double[] data = {1.0, 6.0, 2.5, 2.3};
		vector = new ArrayRealVector(data);
		System.out.println(vector);
		
		//constructor with size and default value
		size = 4;
		double defaultValue = 5.3;
		vector = new ArrayRealVector(size, defaultValue);
		System.out.println(vector);
		
		//constructor with another vector
		RealVector anotherVector = new ArrayRealVector(vector);
		System.out.println(anotherVector);

	}

}
