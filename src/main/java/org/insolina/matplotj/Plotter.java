/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

/**
 * A utility to mimic the Matlab / Matplotlib plotting interface. 
 * 
 * A plot can be displayed in 3 lines:
 * 
 * <pre>
 * {@code
 * Plotter plt = new Plotter();
 * plt.plot(new double[] {1, 2, 3});
 * plt.show();
 * }
 * </pre>
 */
public class Plotter {
    private String title = "Title";
    private String xAxisLabel = "X";
    private String yAxisLabel = "Y";
    private int width = 600;
    private int height = 320;
    private ChartStyle chartStyle;
    private List<Series> seriesList = new ArrayList<>();
    
    private XYChart chart;
    
    /**
     * Default constructor. Sets title = "Title", xLabel = "X", and 
     * yLabel = "Y".
     */   
    public Plotter() {
    }
    
    /**
     * Constructor to set the title of the chart explicitly.
     * 
     * @param title - the title of the chart
     */
    public Plotter(final String title) {
        this();
        this.title = title;
    }
    
    /**
     * Constructor to set the title, xLabel and yLabel of the chart explicitly.
     * 
     * @param title - the title of the chart
     * @param xLabel - the x-axis label
     * @param yLabel - the y-axis label
     */
    public Plotter(final String title, final String xLabel, final String yLabel) {
        this();
        this.title = title;
        this.xAxisLabel = xLabel;
        this.yAxisLabel = yLabel;
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData - the xy data
     */
    public void plot(final double[] xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData - the x data
     * @param yData - the y data
     */
    public void plot(final double[] xData, final double[] yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data. This method will define a default format. 
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     */
    public void plot(final double[] xData, final double[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data.
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     * @param fmt - the format for the plot as a Matlab format string
     */
    public void plot(final double[] xData, final double[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData - the xy data
     */
    public void plot(final int[] xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData - the x data
     * @param yData - the y data
     */
    public void plot(final int[] xData, final int[] yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data. This method will define a default format. 
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     */
    public void plot(final int[] xData, final int[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data.
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     * @param fmt - the format for the plot as a Matlab format string
     */
    public void plot(final int[] xData, final int[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData - the xy data
     */
    public void plot(final float[] xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData - the x data
     * @param yData - the y data
     */
    public void plot(final float[] xData, final float[] yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data. This method will define a default format. 
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     */
    public void plot(final float[] xData, final float[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data.
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     * @param fmt - the format for the plot as a Matlab format string
     */
    public void plot(final float[] xData, final float[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData - the xy data
     */
    public void plot(final List<? extends Number> xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData - the x data
     * @param yData - the y data
     */
    public void plot(final List<?> xData, final List<? extends Number> yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data. This method will define a default format. 
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     */
    public void plot(final List<?> xData, final List<? extends Number> yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data.
     * 
     * @param xData - the x data
     * @param yData - the y data
     * @param seriesLegend - the series legend
     * @param fmt - the format for the plot as a Matlab format string
     */
    public void plot(final List<?> xData, final List<? extends Number> yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (seriesList.size() + 1) : seriesLegend;
        seriesList.add(new Series(xData, yData, fmt, legend));
        this.chartStyle = ChartStyle.LineChart;      
    }
    
    /**
     * Set the title of the chart
     * 
     * @param title - the title 
     */
    public void title(final String title) {
        this.title = title;
    }
    
    /**
     * Set the x-axis label
     * 
     * @param xLabel - the x-axis label
     */
    public void xLabel(final String xLabel) {
        this.xAxisLabel = xLabel;
    }
    
    /**
     * Set the y-axis label
     * 
     * @param yLabel - the y-axis label
     */
    public void yLabel(final String yLabel) {
        this.yAxisLabel = yLabel;
    }
    
    /**
     * Set the width of the chart
     * 
     * @param width - the width of the chart
     */
    public void width(final int width) {
        this.width = width;
    }
    
    /**
     * Set the height of the chart
     * 
     * @param height - the height of the chart
     */
    public void height(final int height) {
        this.height = height;
    }
    
    /**
     * Save the chart to a file. The chart needs to be displayed on screen 
     * when this method is called, i.e. you need to call Plotter::show() 
     * before you call Plotter::savefig(). 
     * 
     * @param filename - the file name
     */
    public void savefig(final String filename) {
        try {
            ChartImage.saveImage(chart, filename);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
     
    private void initialiseChart() {
        if (chartStyle == ChartStyle.LineChart) {
            chart = new XYChartBuilder().width(width).height(height).theme(Styler.ChartTheme.Matlab).title(title).xAxisTitle(xAxisLabel).yAxisTitle(yAxisLabel).build();
            chart.getStyler().setXAxisTickMarkSpacingHint(100);
            
            int len = seriesList.size();
            for (int i = 0; i < len; i++) {
                Series s = seriesList.get(i);
                XYSeries series = s.addSeriesToChart(chart);

                if (series != null) {
                    StyleFormat fmt = StyleFormat.getStyleFormat(i, s.styleFormatStr);
                    series.setLineStyle(fmt.lineStyle);
                    series.setMarker(fmt.markerStyle);
                    series.setMarkerColor(fmt.colour);
                    series.setLineColor(fmt.colour);
                }
            }
            return;
        }
    }
    
    /**
     * Display the plot.
     */
    public void show() {
        initialiseChart();
        
        if (chart != null) {
            new XChartSwingWrapper<>(chart).displayChart();
        }
    }
    
    enum ChartStyle {
        LineChart, BarChart
    }
    
    static class Series {
        public Object xData;
        public Object yData;
        public String styleFormatStr;
        public String legend;
        public SeriesType seriesType;
        
        enum SeriesType {
            Double, Float, Int, List, None
        }
        
        public Series(final String styleFormatStr, final String legend) {
            seriesType = SeriesType.None;
            this.styleFormatStr = styleFormatStr;
            this.legend = legend;
        }
        
        public Series(final double[] xData, final double[] yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.Double;
            this.xData = xData;
            this.yData = yData;           
        }
 
        public Series(final int[] xData, final int[] yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.Int;
            this.xData = xData;
            this.yData = yData;
        }
        
        public Series(final float[] xData, final float[] yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.Float;
            this.xData = xData;
            this.yData = yData;
        }
        
        public Series(final List<?> xData, final List<? extends Number> yData, final String styleFormatStr, final String legend) {
            this(styleFormatStr, legend);
            seriesType = SeriesType.List;
            this.xData = xData;
            this.yData = yData;
        }
        
        public XYSeries addSeriesToChart(final XYChart chart) {
            switch(seriesType) {
                case Double:
                    return chart.addSeries(legend, (double[]) xData, (double[]) yData);

                case Float:
                    return chart.addSeries(legend, (float[]) xData, (float[]) yData);
                    
                case Int:
                    return chart.addSeries(legend, (int[]) xData, (int[]) yData);
                    
                case List:
                    return chart.addSeries(legend, (List<?>) xData, (List<? extends Number>) yData);
            }
            
            return null;
        }
    }
}
