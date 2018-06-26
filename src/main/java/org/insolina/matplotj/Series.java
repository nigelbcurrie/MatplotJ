/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.util.List;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;

/**
 *
 * @author Nigel Currie
 */
public class Series {
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
