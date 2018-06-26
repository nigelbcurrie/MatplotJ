/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 *
 * @author Nigel Currie
 */
public class Figure {
    private List<Chart> charts = new ArrayList<>();
    private int numRows;
    private int numCols;
    
    public Figure() {
        numRows = 1;
        numCols = 1;
    }
    
    public void addChart(final Chart chart) {
        charts.add(chart);
    }
    
    public void rows(final int numRows) {
        this.numRows = numRows;       
    }
    
    public void cols(final int numCols) {
        this.numCols = numCols;
    }
    
    public void saveFigure(final String filename) {
        // TODO: How do we save multiple figures. I guess the user needs to select which figure to print.
        if (charts.size() == 1) {
            Chart chart = charts.get(0);
            XYChart xyChart = chart.getXYChart();
            if (xyChart != null) {
                try {
                    ChartImage.saveImage(xyChart, filename);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
    
    public void show() {
        if (charts.size() == 1) {
            Chart chart = charts.get(0);
            chart.initialiseChart();
            
            XYChart xyChart = chart.getXYChart();
            if (xyChart != null) {
                new XChartSwingWrapper<>(xyChart).displayChart();
            }
        } else {
            List<XYChart> xyCharts = new ArrayList<>();
            for (Chart chart : charts) {
                chart.initialiseChart();
                xyCharts.add(chart.getXYChart());
            }
            
            new XChartSwingWrapper<>(xyCharts, numRows, numCols).displayChartMatrix();
        }
        
        // TODO: If we have multiple charts, use the swing wrapper displayChartMatrix. It
        // has support for setting number of rows and columns.
    }

    public List<Chart> getCharts() {
        return charts;
    }

    void clear() {
        charts = new ArrayList<>();
    }
}
