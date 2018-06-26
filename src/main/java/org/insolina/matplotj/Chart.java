/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author Nigel Currie
 */
public class Chart {
    public enum ChartStyle {
        LineChart, BarChart
    }
    
    private String title = "Title";
    private String xAxisLabel = "X";
    private String yAxisLabel = "Y";
    private int width = 600;
    private int height = 320;
    private ChartStyle chartStyle = ChartStyle.LineChart;
    private List<Series> seriesList = new ArrayList<>();
    
    private XYChart chart;
      
    public void initialiseChart() {
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
        }
        
    }
    
    public XYChart getXYChart() {
        return chart;
    }
     
    void setTitle(final String title) {
        this.title = title;
    }

    void setXAxisLabel(final String xLabel) {
        this.xAxisLabel = xLabel;
    }

    void setYAxisLabel(final String yLabel) {
        this.yAxisLabel = yLabel;
    }

    void setWidth(final int width) {
        this.width = width;
    }

    void setHeight(final int height) {
        this.height = height;
    }

    int seriesCount() {
        return seriesList.size();
    }

    void addSeries(final Series series) {
        seriesList.add(series);
    }
}
