package com.franckbenault;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.OpenMapRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.junit.jupiter.api.Test;

public class Test01Vectors {

	@Test
	public void createVector01Test() {
		
		//Create an empty vector with a defined size
		int size = 4;
		RealVector vector1 = new ArrayRealVector(size);
		System.out.println(vector1);
		
		//Create a vector from a tab
		double[] data = {1.8, 9.0, 3.14};
		RealVector vector2 = new ArrayRealVector(data);
		System.out.println(vector2);
		
		//Create a vector from ... a vector
		RealVector vector3 = new ArrayRealVector(vector2);
		System.out.println(vector3);
		
		//Create a vector defining the size and the default value
		size = 3;
		double defaultValue = 3.14;
		RealVector vector4 = new ArrayRealVector(size,defaultValue);
		System.out.println(vector4);
	}
	
	@Test
	public void spareVector01Test() {
		//spare vector = large vector with a lot of 0
		RealVector vector1 = new ArrayRealVector(71_000_000);
		RealVector vector2 = vector1.copy();
		vector2.mapMultiply(2);
		
		assertEquals(vector1, vector2);
		
	}
	
	@Test
	public void spareVector02Test() {
		//spare vector = large vector with a lot of 0
		RealVector vector1 = new OpenMapRealVector(71_000_000);
		RealVector vector2 = vector1.copy();
		vector2.mapMultiply(2);
		
		assertEquals(vector1, vector2);
		
	}
	
	@Test
	public void spareVector03Test() {
		//spare vector = large vector with a lot of 0
		RealVector vector1 = new ArrayRealVector(71_000_000);
		RealVector vector2 = vector1.copy();
		vector2.mapMultiply(2);
		
		assertEquals(vector1, vector2);
		
	}
	
	@Test
	public void accessingValue04Test() {
		RealVector vector1 = new ArrayRealVector(4);
		
		//set all value to 5
		vector1.set(5.0);
		vector1.setEntry(3, 7.1);
		
		System.out.println(vector1);
		
		assertEquals(vector1.getEntry(2), 5);
		assertEquals(vector1.getEntry(3), 7.1);
		
	}

}
