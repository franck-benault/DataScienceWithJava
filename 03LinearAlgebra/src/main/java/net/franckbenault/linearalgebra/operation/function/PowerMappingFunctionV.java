package net.franckbenault.linearalgebra.operation.function;

import org.apache.commons.math3.analysis.UnivariateFunction;

public class PowerMappingFunctionV implements UnivariateFunction {
	
	private double power;
	
	public PowerMappingFunctionV(double power) {
		this.power = power;
	}

	@Override
	public double value(double value) {
		return Math.pow(value, power);
	}
	


}
