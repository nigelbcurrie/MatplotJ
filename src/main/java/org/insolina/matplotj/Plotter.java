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
 * <p>
 * A shameless copy of the <a href="https://matplotlib.org/users/pyplot_tutorial.html">Matplotlib</a> 
 * plotting interface, using Tim Molter's <a href="https://knowm.org/open-source/xchart">XChart library</a>
 * to do the heavy lifting.
 * </p>
 * <p>
 * I've really written this for my own benefit, because I like the simple interface of Matplotlib. To show
 * how concise it can be, here's how to display a simple XY plot in 3 lines of code (in this example the single array 
 * of doubles is used for both the x and y data):
 * </p>
 * <pre>
 * {@code
 * Plotter plt = new Plotter();
 * plt.plot(new double[] {1, 2, 3});
 * plt.show();
 * }
 * </pre>
 * <p>
 * This being Java rather than Python, I've provided a constructor that allows you to define the title, the x-axis label
 * and the y-axis label:
 * </p>
 * <pre>
 * {@code
 * Plotter plt = new Plotter("My Chart Title", "My X-Axis Label", "My Y-Axis Label");
 * ...
 * }
 * </pre>
 * <p>
 * But if you want to do it the Python way, you can do that too:
 * </p>
 * <pre>
 * {@code
 * Plotter plt = new Plotter();
 * plt.title("My Chart Title");
 * plt.xlabel("My X-Axis Label");
 * plt.ylabel("My Y-Axis Label");
 * ...
 * }
 * </pre>
 * <strong>Series Legends</strong>
 * <p>
 * Each call to <i>plot</i> creates a new series on the chart, with a default legend of 'Series 1', 'Series 2', etc.
 * If you want to specify the legend yourself, just pass that as a third parameter in the call to <i>plot</i>.
 * </p>
 * <pre>
 * {@code
 * Plotter plt = new Plotter();
 * plt.plot(new double[] {1, 2, 3}, new double[] {1, 2, 3}, "My Series Legend");
 * plt.show();
 * }
 * </pre>
 * <strong>Plot Style</strong>
 * <p>
 * The colour, line-style and marker-style of the plot will default to something sensible, and each time you call 
 * <i>plot</i> you'll get a new plot style by default. But if you want to set the style yourself, you can define it 
 * using the standard Matlab format string, where the first character defines the colour, the second character
 * defines the marker style, and the final character(s) define the line-style. For example, the following 
 * code will create a red plot with upright triangular markers and a solid line:
 * </p>
 * <pre>
 * {@code
 * Plotter plt = new Plotter();
 * plt.plot(new double[] {1, 2, 3}, new double[] {1, 2, 3}, "My Series Legend", "r^-");
 * plt.show();
 * }
 * </pre>
 * 
 * <strong>Supported Plot Styles</strong>
 * <p>&nbsp;</p>
 * <table summary="Colours" style="border-collapse: collapse;">
 *      <caption>Colours</caption>
 *      <tr><th style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Character</th><th style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Colour</th></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">b</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Blue</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">r</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Red</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">g</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Green</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">c</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Cyan</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">m</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Magenta</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">y</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Yellow</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">x</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Black</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">w</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">White</td></tr>
 * </table>
 *  
 * <p>&nbsp;</p>
 * <table summary="Marker Styles" style="border-collapse: collapse;">
 *      <caption>Marker Styles</caption>
 *      <tr><th style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Character</th><th style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Marker Style</th></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">o</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Circle</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">d</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Diamond</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">s</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Square</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">v</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Upside-down triangle</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">^</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Upright triangle</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">x</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Cross</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">+</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Plus</td></tr>
 * </table>
 * 
 * <p>&nbsp;</p>
 * <table summary="Line Styles" style="border-collapse: collapse;">
 *      <caption>Line Styles</caption>
 *      <tr><th style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Characters</th><th style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Line Style</th></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">-</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Solid</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">--</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Dash Dash</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">-.</td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">Dash Dot</td></tr>
 *      <tr><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;"> </td><td style="border: 1px solid #999; padding: 0.5rem; text-align: left;">No line</td></tr>
 * </table>
 * <p>&nbsp;</p> 
 * <strong>Saving the Chart</strong>
 * <p>
 * The chart can be saved to an image file by calling <i>savefig</i>. The supported file formats are bmp, jpg, 
 * png and gif. Note that you need to call <i>show</i> before you call <i>savefig</i> because the image file 
 * is generated from the displayed chart.
 * </p>
 * <pre>
 * {@code
 * Plotter plt = new Plotter();
 * plt.plot(new double[] {1, 2, 3}, new double[] {1, 2, 3}, "My Series Legend", "r^-");
 * plt.show();
 * plt.savefig("MySampleChart.jpg");
 * }
 * </pre>
 * <strong>An Example Chart with Multiple Series</strong>
 * <pre>
 * {@code
 * int size = 30;
 * double[] xData1 = new double[size];
 * double[] xData2 = new double[size];
 * double[] yData1 = new double[size];
 * double[] yData2 = new double[size]; 
 * for (int i = 0; i < size; i++) {
 *     double radians = (Math.PI / (size / 2) * i);
 *     xData1[i] = xData2[i] = i - size / 2;
 *     yData1[i] = -.000001 * Math.sin(radians);
 *     yData2[i] = -.000001 * Math.cos(radians);
 * }
 *       
 * Plotter plt = new Plotter("Example sin and cos plots", "x", "f(x)");
 * plt.plot(xData1, yData1, "sin(x)");
 * plt.plot(xData2, yData2, "cos(x)");
 * plt.show();
 * }
 * </pre>
 * 
 * <strong>Multiple Charts (subplots)</strong>
 * 
 * To create multiple charts on a single figure, use subplots(rows, cols), to set up the 
 * matrix of charts, then call subplot(index) to position the plotter on the chart you want 
 * to add data to. Note that indexing starts at 1, and the matrix is traversed from left to right
 * and top to bottom. 
 * 
 * Those familiar will matlab/matplotlib will notice that I've diverged from the interface here. But I 
 * find the matlab api for subplots overly complex, so I've decided not to copy it slavishly.
 * 
 * <pre>
 * {@code
 * Plotter plt = new Plotter();
 * plt.subplots(2, 2);
 *       
 * for (int i = 1; i <= 4; i++) {
 *      double[] x = getConsecutiveValues(200);
 *      double[] y1 = getRandomWalk(200);
 *      double[] y2 = getRandomWalk(200);
 *           
 *      plt.subplot(i);
 *      plt.title("Title " + i).xlabel("X Label " + i).ylabel("YLabel " + i);
 *      plt.plot(x, y1, "Series A" + i, "b-");
 *      plt.plot(x, y2, "Series B" + i, "r-");
 * }
 *       
 * plt.show();
 * }
 * </pre>
 * 
 */
public class Plotter {    
    private List<Figure> figures = new ArrayList<>();
    private Figure currentFigure;
    private Chart currentChart;
    
    /**
     * Default constructor. Sets title = "Title", xLabel = "X", and 
     * yLabel = "Y".
     */   
    public Plotter() {
        currentFigure = new Figure();
        currentChart = new Chart();
        figures.add(currentFigure);
        currentFigure.addChart(currentChart);
    }
    
    /**
     * Constructor to set the title of the chart explicitly.
     * 
     * @param title the title of the chart
     */
    public Plotter(final String title) {
        this();
        currentChart.setTitle(title);
    }
    
    /**
     * Constructor to set the title, xLabel and yLabel of the chart explicitly.
     * 
     * @param title the title of the chart
     * @param xLabel the x-axis label
     * @param yLabel the y-axis label
     */
    public Plotter(final String title, final String xLabel, final String yLabel) {
        this();
        
        currentChart.setTitle(title);
        currentChart.setXAxisLabel(xLabel);
        currentChart.setYAxisLabel(yLabel);
    }
    
    /**
     * Create a matrix of charts with the given number of rows and columns. Clears 
     * any current matrix and initialises the Plotter at the first chart.
     * 
     * @param numRows - the number of rows to create
     * @param numCols - the number of columns to create
     */
    public void subplots(final int numRows, final int numCols) {
        currentFigure.rows(numRows);
        currentFigure.cols(numCols);
        
        int numCharts = numRows * numCols;
        currentFigure.clear();
        for (int i = 0; i < numCharts; i++) {
            Chart chart = new Chart();
            currentFigure.addChart(chart);
        }
        
        if (currentFigure.getCharts().size() > 0) {
            currentChart = currentFigure.getCharts().get(0);
        }
    }
    
    /**
     * Set the Plotter so that the chart at index 'index' in the matrix of charts
     * becomes the current chart. Indexing starts at 1, and the matrix is read from left
     * to right and top to bottom.
     * 
     * @param index - the index into the current matrix of charts
     */
    public void subplot(final int index) {
        int actualIndex = index - 1;
        
        if (actualIndex >= 0 && actualIndex < currentFigure.getCharts().size()) {
            currentChart = currentFigure.getCharts().get(actualIndex);
        }
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData the xy data
     */
    public void plot(final double[] xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData the x data
     * @param yData the y data
     */
    public void plot(final double[] xData, final double[] yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data. This method will define a default format. 
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     */
    public void plot(final double[] xData, final double[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using arrays of doubles for the x and y data.
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     * @param fmt the format for the plot as a Matlab format string
     */
    public void plot(final double[] xData, final double[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (currentChart.seriesCount() + 1) : seriesLegend;
        currentChart.addSeries(new Series(xData, yData, fmt, legend));      
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData the xy data
     */
    public void plot(final int[] xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData the x data
     * @param yData the y data
     */
    public void plot(final int[] xData, final int[] yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data. This method will define a default format. 
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     */
    public void plot(final int[] xData, final int[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using arrays of ints for the x and y data.
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     * @param fmt the format for the plot as a Matlab format string
     */
    public void plot(final int[] xData, final int[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (currentChart.seriesCount() + 1) : seriesLegend;
        currentChart.addSeries(new Series(xData, yData, fmt, legend));      
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData the xy data
     */
    public void plot(final float[] xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData the x data
     * @param yData the y data
     */
    public void plot(final float[] xData, final float[] yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data. This method will define a default format. 
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     */
    public void plot(final float[] xData, final float[] yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using arrays of floats for the x and y data.
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     * @param fmt the format for the plot as a Matlab format string
     */
    public void plot(final float[] xData, final float[] yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (currentChart.seriesCount() + 1) : seriesLegend;
        currentChart.addSeries(new Series(xData, yData, fmt, legend));     
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data. This method will define a default series legend 
     * and a default format. The same data will be used for the x and y axis.
     * 
     * @param xyData the xy data
     */
    public void plot(final List<? extends Number> xyData) {
        plot(xyData, xyData);
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data. This method will define a default series legend 
     * and a default format.
     * 
     * @param xData the x data
     * @param yData the y data
     */
    public void plot(final List<?> xData, final List<? extends Number> yData) {
        plot(xData, yData, null);
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data. This method will define a default format. 
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     */
    public void plot(final List<?> xData, final List<? extends Number> yData, final String seriesLegend) {
        plot(xData, yData, seriesLegend, null);
    }
    
    /**
     * Plot an XYSeries using lists for the x and y data.
     * 
     * @param xData the x data
     * @param yData the y data
     * @param seriesLegend the series legend
     * @param fmt the format for the plot as a Matlab format string
     */
    public void plot(final List<?> xData, final List<? extends Number> yData, final String seriesLegend, final String fmt) {
        String legend = (seriesLegend == null || seriesLegend.equals("")) ? "Series " + (currentChart.seriesCount() + 1) : seriesLegend;
        currentChart.addSeries(new Series(xData, yData, fmt, legend));      
    }
    
    /**
     * Set the title of the chart
     * 
     * @param title the title 
     * @return this Plotter
     */
    public Plotter title(final String title) {
        currentChart.setTitle(title);
        return this;
    }
    
    /**
     * Set the x-axis label
     * 
     * @param xLabel the x-axis label
     * @return this Plotter
     */
    public Plotter xlabel(final String xLabel) {
        currentChart.setXAxisLabel(xLabel);
        return this;
    }
    
    /**
     * Set the y-axis label
     * 
     * @param yLabel the y-axis label
     * @return this Plotter
     */
    public Plotter ylabel(final String yLabel) {
        currentChart.setYAxisLabel(yLabel);
        return this;
    }
    
    /**
     * Set the width of the chart
     * 
     * @param width the width of the chart
     * @return this Plotter
     */
    public Plotter width(final int width) {
        currentChart.setWidth(width);
        return this;
    }
    
    /**
     * Set the height of the chart
     * 
     * @param height the height of the chart
     * @return this Plotter
     */
    public Plotter height(final int height) {
        currentChart.setHeight(height);
        return this;
    }
    
    /**
     * Save the chart to a file. The chart needs to be displayed on screen 
     * when this method is called, i.e. you need to call Plotter::show() 
     * before you call Plotter::savefig(). 
     * 
     * @param filename the file name
     */
    public void savefig(final String filename) {
        currentFigure.saveFigure(filename);
    }
    
    /**
     * Display the plot.
     */
    public void show() {
        // TODO: Iterate through all figures, displaying each one in turn
        currentFigure.show();
    }
}
