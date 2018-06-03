/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 *
 * @author Nigel Currie
 * 
 * see https://matplotlib.org/users/pyplot_tutorial.html for introduction to PyPlot
 * see https://knowm.org/open-source/xchart/xchart-example-code/ for XChart example code
 * see https://github.com/eseifert/gral/wiki/tutorial for tutorials on Gral
 * see http://www.jzy3d.org/ for a 3D plotting library
 * 
 * The different charts supported by XChart:
 * 
 *      Bubble Chart (no equivalent)
 *      Category Chart (hist, bar)
 *      Dial Chart (no equivalent)
 *      OHLC Chart (a chart showing error ranges)
 *      Pie Chart (pie)
 *      Radar Chart (no equivalent)
 *      XY Chart (scatter, plot)
 * 
 *      Implement bar, hist, pie, plot and scatter.
 * 
 *      Histograms are used to show distributions of variables while bar charts are used to compare
 *      variables. Histograms plot quantitative data with ranges of the data grouped into bins or
 *      intervals while bar charts plot categorical data.
 * 
 * Next things to do:
 * 
 * 1. Support for multiple series
 * Would be best if we returned the XChart series object from the call to plot, etc., and 
 * provide support for getting the XChart chart object from the Plotter object, so that the user
 * can customise their charts.
 * 2. Support for the other chart types
 * 3. Support for multiple charts on the same window
 * 4. Methods to set the variables of the chart
 * 5. Overrides for setting the data, int arrays, Lists, etc.
 */
public class Plotter {
    private String title = "Title";
    private String xAxisLabel = "X";
    private String yAxisLabel = "Y";
    private int width = 600;
    private int height = 320;
    private String styleFormatStr;
    private ChartStyle chartStyle;
    private String legend;
    private double[] xData;
    private double[] yData;
    private List<Series> seriesList = new ArrayList<>();
    
    private XYChart chart;
    private CategoryChart histChart;
    
    
    
    public Plotter() {
    }
    
    public Plotter(final String title) {
        this();
        this.title = title;
    }
    
    public Plotter(final String title, final String xAxisLabel, final String yAxisLabel) {
        this();
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }
    
    public void plot(final double[] xyData) {
        plot(xyData, xyData);
    }
    
    public void plot(final double[] xData, final double[] yData) {
        plot(xData, yData, null);
    }
    
    public void plot(final double[] xData, final double[] yData, final String fmt) {
        plot(xData, yData, fmt, null);
    }
    
    public void plot(final double[] xData, final double[] yData, final String fmt, final String seriesLegend) {
        seriesList.add(new Series(xData, yData, fmt, (seriesLegend == null || seriesLegend.equals("")) ? " " : seriesLegend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    public void hist(final double[] xData, final double[] yData, final String fmt, final String seriesLegend) {
        this.xData = xData;
        this.yData = yData;
        this.styleFormatStr = fmt;
        this.legend = (seriesLegend == null || seriesLegend.equals("")) ? " " : seriesLegend;
        this.chartStyle = ChartStyle.BarChart;       
    }
    
    public void initialiseChart() {
        if (chartStyle == ChartStyle.LineChart) {
            chart = new XYChartBuilder().width(width).height(height).theme(Styler.ChartTheme.Matlab).title(title).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
            chart.getStyler().setXAxisTickMarkSpacingHint(100);
            
            for (Series s : seriesList) {
                XYSeries series = chart.addSeries(s.legend, s.xData, s.yData);

                StyleFormat fmt = StyleFormat.parseStyleFormatString(s.styleFormatStr);
                series.setLineStyle(fmt.lineStyle);
                series.setMarker(fmt.markerStyle);
                series.setMarkerColor(fmt.colour);
                series.setLineColor(fmt.colour);
            }
            return;
        }
        
        if (chartStyle == ChartStyle.BarChart) {
            histChart = new CategoryChartBuilder().width(width).height(height).theme(Styler.ChartTheme.Matlab).title(title).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
            histChart.getStyler().setHasAnnotations(true);
            CategorySeries series = histChart.addSeries(legend, xData, yData);
            
            StyleFormat fmt = StyleFormat.parseStyleFormatString(styleFormatStr);
            series.setFillColor(fmt.colour); 
            return;
        }
    }
    
    public void show() {
        initialiseChart();
        
        if (chart != null) {
            new XChartSwingWrapper<>(chart).displayChart();
        }
        
        if (histChart != null) {
            new XChartSwingWrapper<>(histChart).displayChart();
        }
    }
    
    enum ChartStyle {
        LineChart, BarChart
    }
    
    static class Series {
        public double[] xData;
        public double[] yData;
        public String styleFormatStr;
        public String legend;
        
        public Series(final double[] xData, final double[] yData, final String styleFormatStr, final String legend) {
            this.xData = xData;
            this.yData = yData;
            this.styleFormatStr = styleFormatStr;
            this.legend = legend;
        }
    }
}
