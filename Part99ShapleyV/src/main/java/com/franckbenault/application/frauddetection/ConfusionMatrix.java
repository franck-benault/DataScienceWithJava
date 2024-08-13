package com.franckbenault.application.frauddetection;

public class ConfusionMatrix {
	
	private int nbTruePositive;
	private int nbFalsePositive;
	private int nbFalseNegative;
	private int nbTrueNegative;
	private int total;
	
	public ConfusionMatrix(
			int nbTruePositive,
			int nbFalsePositive,
			int nbFalseNegative,
			int nbTrueNegative
			) {
		
		this.nbTruePositive= nbTruePositive;
		this.nbFalsePositive= nbFalsePositive;
		this.nbFalseNegative= nbFalseNegative;
		this.nbTrueNegative=nbTrueNegative;	
		total =nbTruePositive+nbFalsePositive+nbFalseNegative+nbTrueNegative;
	}
	
	public void draw() {
		System.out.println(nbTruePositive+" | "+nbFalsePositive);
		System.out.println("-------- ");
		System.out.println(nbFalseNegative+" | "+nbTrueNegative);
	}
	
	public void printResult() {
		System.out.println("Accuraty  "+accuraty());
		System.out.println("Recall    "+recall());
		System.out.println("Precision "+precision());
		System.out.println("F1 score  "+f1score());
		System.out.println();
	}
	
	public double accuraty() {
		return (0.0+nbTruePositive+ nbTrueNegative)/total;
	}
	
	public double recall() {
		return (0.0+nbTruePositive)/(nbTruePositive+nbFalseNegative);
	}
	
	public double precision() {
		return (0.0+nbTruePositive)/(nbTruePositive+nbFalsePositive);
	}
	
	public double f1score() {
		return 2*precision()*recall()/(precision()+recall());
	}

}
