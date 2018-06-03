/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.insolina.matplotj;

/**
 *
 * @author Nigel Currie
 */
public class PlotData<X, Y> {
    public X xData;
    public Y yData;
    
    public PlotData(final X xData, final Y yData) {
        this.xData = xData;
        this.yData = yData;
    }
}
