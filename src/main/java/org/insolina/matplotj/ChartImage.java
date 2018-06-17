/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.internal.chartpart.Chart;

/**
 *
 * @author Nigel Currie
 * 
 * Although XChart supports Vector graphics, it depends on a third party library, and the eps and pdf
 * generation is really slow. The SVG generation is fast enough, but it doesn't give an accurate 
 * representation of the chart.
 */
public class ChartImage {
    private final static Map<String, BitmapFormat> bitmapFormats = new HashMap<>();
    
    enum ImageType {
        Bitmap, Vector, Unknown
    }
    
    static {
        bitmapFormats.put("png", BitmapFormat.PNG);
        bitmapFormats.put("jpg", BitmapFormat.JPG);
        bitmapFormats.put("jpeg", BitmapFormat.JPG);
        bitmapFormats.put("bmp", BitmapFormat.BMP);
        bitmapFormats.put("gif", BitmapFormat.GIF);
    }
    
    public static void saveImage(final Chart chart, final String filename) throws IOException {
        if (filename == null) {
            return;
        }
        
        int index = filename.lastIndexOf(".");
        String extension = (index <= -1) ? null : filename.substring(index + 1, filename.length());
        if (extension == null) {
            return;
        }
        
        extension = extension.toLowerCase();
        ImageType imageType = determineImageType(extension);
        if (imageType == ImageType.Bitmap) {
            BitmapFormat format = bitmapFormats.get(extension);
            if (format != null) {
                BitmapEncoder.saveBitmap(chart, filename, format);
            }
        } 
    }
    
    private static ImageType determineImageType(final String extension) {
        switch(extension) {
            case "png":
            case "jpg":
            case "jpeg":
            case "bmp":
            case "gif":
                return ImageType.Bitmap;
                
            case "eps":
            case "pdf":
            case "svg":
                return ImageType.Vector;
                
            default:
                return ImageType.Unknown;
        }
    }
}
