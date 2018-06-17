/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    private static final Map<String, Color> COLOURS = new LinkedHashMap<>();
    private static final Map<String, BasicStroke> LINE_STYLES = new LinkedHashMap<>();
    private static final Map<String, Marker> MARKER_STYLES = new LinkedHashMap<>();
    private static final List<StyleFormat> DEFAULT_STYLES = new ArrayList<>();
    
    static {
        // Shortcut format string is [colour][marker][line]
        COLOURS.put("b", XChartSeriesColors.BLUE);
        COLOURS.put("r", XChartSeriesColors.RED);
        COLOURS.put("g", XChartSeriesColors.GREEN);     
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
        
        initialiseDefaultStyleFormats();
    }

    private static void initialiseDefaultStyleFormats() {
        for (BasicStroke lineStyle : LINE_STYLES.values()) {
            for (Marker marker : MARKER_STYLES.values() ) {
                for (Color colour : COLOURS.values()) {
                    DEFAULT_STYLES.add(new StyleFormat(colour, lineStyle, marker));
                }
            }
        }
    }
    
    public Color colour = XChartSeriesColors.BLUE;
    public BasicStroke lineStyle = SeriesLines.NONE;
    public Marker markerStyle = SeriesMarkers.CIRCLE;
    
    public static StyleFormat getStyleFormat(final int index, final String fmt) {
        if (fmt == null) {
            int i = (index >= DEFAULT_STYLES.size()) ? (index % DEFAULT_STYLES.size()) : index;
            return DEFAULT_STYLES.get(i);
        }
        
        StyleFormat styleFormat = new StyleFormat();
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
    
    public StyleFormat() {
        
    }

    private StyleFormat(final Color colour, final BasicStroke lineStyle, final Marker marker) {
        this.colour = colour;
        this.lineStyle = lineStyle;
        this.markerStyle = marker;
    }
}
