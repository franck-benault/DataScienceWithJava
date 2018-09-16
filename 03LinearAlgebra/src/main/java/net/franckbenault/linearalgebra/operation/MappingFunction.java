package net.franckbenault.linearalgebra.operation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import net.franckbenault.linearalgebra.operation.function.PowerMappingFunction;
import net.franckbenault.linearalgebra.operation.function.PowerMappingFunctionV;

public class MappingFunction {
	
	public static void main(String[] args) {

		double[] dataV = {1.0, 1.0, -2.0, 0.0};
		RealVector vector = new ArrayRealVector(dataV);
		
		System.out.println(vector);
		
		vector.mapToSelf(new PowerMappingFunctionV(2.0));
		System.out.println(vector);
		
		RealVector vector2 =vector.map(new PowerMappingFunctionV(0.5));
		System.out.println(vector2);
		
		double [][] data = {{1.0, 2.0}, {2.0, 3.0}, {3.0, 4.0}};
		RealMatrix matrix = new Array2DRowRealMatrix(data);

		System.out.println(matrix);
		matrix.walkInOptimizedOrder(new PowerMappingFunction(2.0));
		System.out.println(matrix);
	}

}
