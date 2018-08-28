package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class Norme {
	
	public static void main(String[] args) {
		double[] data = {2.0, 1.0, -2.0, 0.0};
		RealVector vector = new ArrayRealVector(data);
		
		double normL1 = vector.getL1Norm();
		System.out.println("normL1="+normL1);
		
		double norm = vector.getNorm();
		System.out.println("norm="+norm);
		
		RealVector  unitVector = vector.unitVector();
		System.out.println("unitVector="+unitVector);
		System.out.println("unitVector norm="+unitVector.getNorm());
		
		vector.unitize();
		System.out.println("vector="+vector);

	}

}
