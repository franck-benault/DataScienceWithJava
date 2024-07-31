package com.franckbenault;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.OpenMapRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.jupiter.api.Test;

public class Test02Matrix {

	@Test
	public void firstMatrix01Test() {
		
		//Create an empty matrix defining its size
		int rowDimension =3;
		int colDimension =4;
		RealMatrix matrix1 = new Array2DRowRealMatrix(rowDimension, colDimension);
		System.out.println(matrix1);
		
		//Create a matrix from a java tab
		double [][] data = {
				{1.3, 1.5, 1.7, 1.8},
				{2.3, 1.5, 1.7, 2.9},
				{3.3, 1.5, 1.7, 3.9}
		};
		
		RealMatrix matrix2 = new Array2DRowRealMatrix(data);
		System.out.println(matrix2);
		
		//create a matrix from another matrix
		RealMatrix matrix3 = matrix2.copy();
		System.out.println(matrix3);
	}
	
	@Test
	public void spareMatrix01Test() {
		
		//Spare matrix = large matrix with a lot of 0
		//In French language matrice creuse
		RealMatrix spareMatrix = new Array2DRowRealMatrix(10_000, 10_000);
		
		RealMatrix spareMatrix2 = spareMatrix.transpose();
		assertEquals(spareMatrix, spareMatrix2);
		

	}
	
	@Test
	public void spareMatrix02Test() {
		
		//Spare matrix = large matrix with a lot of 0
		//In French language matrice creuse
		RealMatrix spareMatrix = new OpenMapRealMatrix(10_000, 10_000);
		
		RealMatrix spareMatrix2 = spareMatrix.transpose();
		assertEquals(spareMatrix, spareMatrix2);
		
	}
	
	@Test
	public void spareMatrix03Test() {
		
		//Spare matrix = large matrix with a lot of 0
		//In French language matrice creuse
		RealMatrix spareMatrix = new Array2DRowRealMatrix(10_000, 10_000);
		
		RealMatrix spareMatrix2 = spareMatrix.transpose();
		assertEquals(spareMatrix, spareMatrix2);
		

	}
	
	@Test
	public void accessingValue04Test() {

		int rowDimension =3;
		int colDimension =4;
		RealMatrix matrix1 = new Array2DRowRealMatrix(rowDimension, colDimension);
		
		//set all value to 5
		matrix1.setEntry(1,2, 7.1);
		
		System.out.println(matrix1);
		
		assertEquals(matrix1.getEntry(1,2), 7.1);
		assertEquals(matrix1.getEntry(2,3), 0.0);
		
		//go back to tab[] with toArray()
		assertEquals(matrix1.getData()[1][2],7.1);
		
	}
}
