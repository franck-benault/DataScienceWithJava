package com.franckbenault;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Diagram01 {

	public static void main(String[] args) {

		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "Sales", "January");
		dataset.addValue(120, "Sales", "February");
		dataset.addValue(180, "Sales", "March");
		dataset.addValue(250, "Sales", "April");
		dataset.addValue(240, "Sales", "May");
		
		JFreeChart chart = ChartFactory.createLineChart(
			    "Monthly Sales",
			    "Month",
			    "Sales",
			    dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setContentPane(chartPanel);
		frame.setLocationRelativeTo(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
