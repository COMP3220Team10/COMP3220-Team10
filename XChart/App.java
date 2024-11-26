package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class App 
{
    public static void main( String[] args )
    {
        // Create CategoryChart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Service Requests Count by Ward").xAxisTitle("Ward#").yAxisTitle("Ward Count").build();
    
        // Customize Chart
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeriesRenderStyle.Stick);
    
        // Series
        List<Integer> xData = new ArrayList<Integer>();
        List<Integer> yData = new ArrayList<Integer>(Arrays.asList(28, 0, 0, 0, 1, 1, 3, 6, 28, 19));
        for (int i = 1; i <= 10; i++) {
            xData.add(i);
        }
        chart.addSeries("data", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();



        // Create XYChart
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Monthly Service Requests (2024)").xAxisTitle("# of Requests").yAxisTitle("Months").build();
    
        // Customize Chart
        //chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart2.getStyler().setChartTitleVisible(false);
        chart2.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart2.getStyler().setMarkerSize(16);
    
        // Series
        List<Integer> xData2 = new ArrayList<Integer>(Arrays.asList(3, 3, 1, 5, 7, 6, 16, 15, 8, 4, 0, 0));
        List<Integer> yData2 = new ArrayList<Integer>();
        for (int i = 1; i < 13; i++) {
            yData2.add(i);
        }
        chart2.addSeries("Monthly Requests", xData2, yData2);

        // Show it
        new SwingWrapper(chart2).displayChart();
    }
}
