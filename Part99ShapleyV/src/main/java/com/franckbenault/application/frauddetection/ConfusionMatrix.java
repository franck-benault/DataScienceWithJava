package com.franckbenault.application.frauddetection;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfusionMatrix {
	
	private Logger logger = LoggerFactory.getLogger(ConfusionMatrix.class);
	
	private int nbTruePositive;
	private int nbFalsePositive;
	private int nbFalseNegative;
	private int nbTrueNegative;
	private int total;
	private String matrixName;
	
	public ConfusionMatrix(
			int nbTruePositive,
			int nbFalsePositive,
			int nbFalseNegative,
			int nbTrueNegative,
			String matrixName
			) {
		
		this.nbTruePositive= nbTruePositive;
		this.nbFalsePositive= nbFalsePositive;
		this.nbFalseNegative= nbFalseNegative;
		this.nbTrueNegative=nbTrueNegative;	
		this.matrixName=matrixName;
		total =nbTruePositive+nbFalsePositive+nbFalseNegative+nbTrueNegative;
	}
	
	public ConfusionMatrix(
			Map<String,Boolean> transactions,
			Set<String> detectedFraudulentTs,
			String matrixName
			) {
		
		nbTruePositive=0;
		nbFalsePositive= 0;
		nbFalseNegative= 0;
		nbTrueNegative=0;
		this.matrixName=matrixName;
		
		
		total= transactions.size();
		for(String detectedFraudulentT: detectedFraudulentTs) {
			if(transactions.get(detectedFraudulentT)!=null)
				if(transactions.get(detectedFraudulentT))
					nbTruePositive++;
				else
					nbFalsePositive++;
			
		}
		
		for(String key: transactions.keySet()) {
			if(transactions.get(key) && !detectedFraudulentTs.contains(key))
				nbFalseNegative++;

		}
		nbTrueNegative=total -nbTruePositive-nbFalsePositive-nbFalseNegative;
		
		
	}
	
	
	public void draw() {
		if(matrixName!=null)
			logger.info("---{}-----", matrixName);
		logger.info("{} | {} ",nbTruePositive, nbFalsePositive);
		logger.info("-------- ");
		logger.info("{} | {} ",nbFalseNegative,nbTrueNegative);
		logger.info("");
	}
	
	public void printResult() {
		if(matrixName!=null)
			logger.info("figure for {}",matrixName);
		logger.info("Accuraty  {}",accuraty());
		logger.info("Recall    {}",recall());
		logger.info("Precision {}",precision());
		logger.info("F1 score  {}",f1score());
		logger.info("");
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
