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
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat;
import org.knowm.xchart.internal.chartpart.Chart;

/**
 *
 * @author Nigel Currie
 */
public class ChartImage {
    private final static Map<String, BitmapFormat> bitmapFormats = new HashMap<>();
    private final static Map<String, VectorGraphicsFormat> vectorFormats = new HashMap<>();
    
    enum ImageType {
        Bitmap, Vector, Unknown
    }
    
    static {
        bitmapFormats.put("png", BitmapFormat.PNG);
        bitmapFormats.put("jpg", BitmapFormat.JPG);
        bitmapFormats.put("jpeg", BitmapFormat.JPG);
        bitmapFormats.put("bmp", BitmapFormat.BMP);
        bitmapFormats.put("gif", BitmapFormat.GIF);
        
        // The vector graphics stuff is really slow. Maybe don't support them.
        // The SVG generation is fast enough, but it doesn't give an accurate 
        // representation of the chart.
        vectorFormats.put("eps", VectorGraphicsFormat.EPS);
        vectorFormats.put("pdf", VectorGraphicsFormat.PDF);
        vectorFormats.put("svg", VectorGraphicsFormat.SVG);
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
        } else if (imageType == ImageType.Vector) {
            VectorGraphicsFormat format = vectorFormats.get(extension);
            if (format != null) {
                VectorGraphicsEncoder.saveVectorGraphic(chart, filename, format);
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
