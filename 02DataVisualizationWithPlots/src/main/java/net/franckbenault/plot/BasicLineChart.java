package net.franckbenault.plot;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.Scene;

public class BasicLineChart extends Application {

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		int[] xData = {1, 2, 3, 4, 5};
		double[] yData = {1.3, 2.1, 3.3, 4.0, 4.8};

		Series<Integer,Double> series = new Series<>();
		for(int i=0 ; i<xData.length; i++) {
			series.getData().add(new Data(xData[i], yData[i]));
		}
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("x");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("y");
		
		LineChart<Integer,Double> chart = new LineChart(xAxis,yAxis);
		chart.getData().add(series);
		
		Scene scene = new Scene(chart, 800, 600);
		stage.setScene(scene);
		
		stage.show();
	}

}
