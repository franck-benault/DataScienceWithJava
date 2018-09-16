package net.franckbenault.linearalgebra.operation.function;

import org.apache.commons.math3.linear.RealMatrixChangingVisitor;

public class PowerMappingFunction implements RealMatrixChangingVisitor {
	
	private double power;
	
	public PowerMappingFunction(double power) {
		this.power = power;
	}
	
	

	@Override
	public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double visit(int row, int column, double value) {
		return Math.pow(value,power);
	}

	@Override
	public double end() {
		// TODO Auto-generated method stub
		return 0;
	}

}
