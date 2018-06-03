/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 *
 * @author Nigel Currie
 */
public class StyleFormat {
    private static final Map<String, Color> COLOURS = new HashMap<>();
    private static final Map<String, BasicStroke> LINE_STYLES = new HashMap<>();
    private static final Map<String, Marker> MARKER_STYLES = new HashMap<>();
    
    static {
        // Shortcut format string is [colour][marker][line]
        COLOURS.put("b", XChartSeriesColors.BLUE);
        COLOURS.put("g", XChartSeriesColors.GREEN);
        COLOURS.put("r", XChartSeriesColors.RED);
        COLOURS.put("c", XChartSeriesColors.CYAN);
        COLOURS.put("m", XChartSeriesColors.MAGENTA);
        COLOURS.put("y", XChartSeriesColors.YELLOW);
        COLOURS.put("k", XChartSeriesColors.BLACK);
        COLOURS.put("w", Color.WHITE);
        
        LINE_STYLES.put("-", SeriesLines.SOLID);
        LINE_STYLES.put("--", SeriesLines.DASH_DASH);
        LINE_STYLES.put("-.", SeriesLines.DASH_DOT);
        LINE_STYLES.put(":", SeriesLines.DOT_DOT);
        
        MARKER_STYLES.put("o", SeriesMarkers.CIRCLE);
        MARKER_STYLES.put("d", SeriesMarkers.DIAMOND);
        MARKER_STYLES.put("s", SeriesMarkers.SQUARE);
        MARKER_STYLES.put("v", SeriesMarkers.TRIANGLE_DOWN);
        MARKER_STYLES.put("^", SeriesMarkers.TRIANGLE_UP);
        MARKER_STYLES.put("x", SeriesMarkers.CROSS);
        MARKER_STYLES.put("+", SeriesMarkers.PLUS);
    }
    
    public Color colour = XChartSeriesColors.BLUE;
    public BasicStroke lineStyle = SeriesLines.SOLID;
    public Marker markerStyle = SeriesMarkers.CIRCLE;
    
    public static StyleFormat parseStyleFormatString(final String fmt) {
        StyleFormat styleFormat = new StyleFormat();
        if (fmt == null) {
            return styleFormat;
        }
        
        for (String key : COLOURS.keySet()) {
            if (fmt.contains(key)) {
                styleFormat.colour = COLOURS.get(key);
                break;
            }
        }
        
        for (String key : LINE_STYLES.keySet()) {
            if (fmt.contains(key)) {
                styleFormat.lineStyle = LINE_STYLES.get(key);
                break;
            }
        }
        
        for (String key : MARKER_STYLES.keySet()) {
            if (fmt.contains(key)) {
                styleFormat.markerStyle = MARKER_STYLES.get(key);
                break;
            }
        }
        
        return styleFormat;
    }
}
