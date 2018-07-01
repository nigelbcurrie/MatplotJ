# MatplotJ

A shameless copy of the [Matplotlib](https://matplotlib.org/users/pyplot_tutorial.html) plotting interface, using Tim Molter's [XChart library](https://knowm.org/open-source/xchart) to do the heavy lifting.

I've really written this for my own benefit, because I like the simple interface of Matplotlib. To show how concise it can be, here's how to display a simple XY plot in 3 lines of code (in this example the single array of doubles is used for both the x and y data): 

```java
Plotter plt = new Plotter();
plt.plot(new double[] {1, 2, 3});
plt.show();
```

![Example 1](/images/MatplotJ_Example1.png)

This being Java rather than Python, I've provided a constructor that allows you to define the title, the x-axis label and the y-axis label:

```java
Plotter plt = new Plotter("My Chart Title", "My X-Axis Label", "My Y-Axis Label");
...
```

![Example 2](/images/MatplotJ_Example2.png)
 
But if you want to do it the Python way, you can do that too:

```java 
Plotter plt = new Plotter();
plt.title("My Chart Title");
plt.xlabel("My X-Axis Label");
plt.ylabel("My Y-Axis Label");
...
```
 
## Series Legends

Each call to plot creates a new series on the chart, with a default legend of 'Series 1', 'Series 2', etc. If you want to specify the legend yourself, just pass that as a third parameter in the call to plot.

```java
Plotter plt = new Plotter();
plt.plot(new double[] {1, 2, 3}, new double[] {1, 2, 3}, "My Series Legend");
plt.show();
```
 
## Plot Style

The colour, line-style and marker-style of the plot will default to something sensible, and each time you call plot you'll get a new plot style by default. But if you want to set the style yourself, you can define it using the standard Matlab format string, where the first character defines the colour, the second character defines the marker style, and the remaining characters define the line-style (the marker and line characters are optional). For example, the following code will create a red plot with upright triangular markers and a solid line:

```java
Plotter plt = new Plotter();
plt.plot(new double[] {1, 2, 3}, new double[] {1, 2, 3}, "My Series Legend", "r^-");
plt.show();
``` 

![Example 3](/images/MatplotJ_Example3.png)

## Supported Plot Styles
 
### Colours

Character|Colour 
---------|------
b|Blue
r|Red
g|Green
c|Cyan
m|Magenta
y|Yellow
x|Black
w|White

### Marker Styles

Character|Marker Style
---------|------------
o|Circle
d|Diamond
s|Square
v|Upside-down triangle
^|Upright triangle
x|Cross
+|Plus

If you don't provide a marker character, no marker will be printed.

### Line Styles

Characters|Line Style
----------|----------
-|Solid
--|Dash Dash
-.|Dash Dot

If you don't provide a line character, no line will be printed.

## Saving the Chart

The chart can be saved to an image file by calling savefig. The supported file formats are bmp, jpg, png and gif. Note that you need to call show before you call savefig because the image file is generated from the displayed chart.

```java 
Plotter plt = new Plotter();
plt.plot(new double[] {1, 2, 3}, new double[] {1, 2, 3}, "My Series Legend", "r^-");
plt.show();
plt.savefig("MySampleChart.jpg");
``` 
 
## An Example Chart with Multiple Series

```java
int size = 30;
double[] xData1 = new double[size];
double[] xData2 = new double[size];
double[] yData1 = new double[size];
double[] yData2 = new double[size]; 
for (int i = 0; i < size; i++) {
 double radians = (Math.PI / (size / 2) * i);
 xData1[i] = xData2[i] = i - size / 2;
 yData1[i] = -.000001 * Math.sin(radians);
 yData2[i] = -.000001 * Math.cos(radians);
}
   
Plotter plt = new Plotter("Example sin and cos plots", "x", "f(x)");
plt.plot(xData1, yData1, "sin(x)");
plt.plot(xData2, yData2, "cos(x)");
plt.show();
```

![Example 4](/images/MatplotJ_Example4.png)

## Multiple Charts (Subplots)

To create multiple charts on a single figure, use subplots(rows, cols), to set up the 
matrix of charts, then call subplot(index) to position the plotter on the chart you want 
to add data to. Note that indexing starts at 1, and the matrix is traversed from left to right
and top to bottom. 

Those familiar will matlab/matplotlib will notice that I've diverged from the interface here. But I 
find the matlab api for subplots overly complex, so I've decided not to copy it slavishly.

```java
Plotter plt = new Plotter();
plt.subplots(2, 2);
        
for (int i = 1; i <= 4; i++) {
    double[] x = getConsecutiveValues(200);
    double[] y1 = getRandomWalk(200);
    double[] y2 = getRandomWalk(200);
    
    plt.subplot(i);
    plt.title("Title " + i).xlabel("X Label " + i).ylabel("YLabel " + i);
    plt.plot(x, y1, "Series A" + i, "b-");
    plt.plot(x, y2, "Series B" + i, "r-");
}

plt.show();
```

![Example 5](/images/MatplotJ_Example5.png)




 