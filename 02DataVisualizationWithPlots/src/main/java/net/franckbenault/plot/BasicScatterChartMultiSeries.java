package net.franckbenault.plot;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.Scene;

public class BasicScatterChartMultiSeries extends Application {

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		int[] xData = {1, 2, 3, 4, 5};
		double[] yData = {1.3, 2.1, 3.3, 4.0, 4.8};
		double[] yData2 = {1.5, 2.6, 3.7, 4.5, 5.2};
		double[] yData3 = {1.1, 1.7, 2.9, 3.5, 4.1};

		Series<Integer,Double> series1 = new Series<>();
		for(int i=0 ; i<xData.length; i++) {
			series1.getData().add(new Data(xData[i], yData[i]));
			series1.setName("team1");
		}
		Series<Integer,Double> series2 = new Series<>();
		for(int i=0 ; i<xData.length; i++) {
			series2.getData().add(new Data(xData[i], yData2[i]));
			series2.setName("team2");
		}
		Series<Integer,Double> series3 = new Series<>();
		for(int i=0 ; i<xData.length; i++) {
			series3.getData().add(new Data(xData[i], yData3[i]));
			series3.setName("team3");
		}
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("x");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("y");
		
		ScatterChart<Integer,Double> chart = new ScatterChart(xAxis,yAxis);
		chart.getData().addAll(series1,series2, series3);
		
		Scene scene = new Scene(chart, 800, 600);
		stage.setScene(scene);
		
		stage.show();
	}

}
