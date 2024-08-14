package com.franckbenault;

import java.util.Map;

public interface ShapleyI {
	
	Double getShapleyValue(String key);
	
	Map<String, Double> getAllSortedValue();
	
	void calculateAllShapleyValues();
	

}
