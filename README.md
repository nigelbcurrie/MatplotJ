# MatplotJ
Java Matlab-type plotting library

 A shameless copy of the [Matplotlib](https://matplotlib.org/users/pyplot_tutorial.html) plotting interface, using Tim Molter's [XChart library](https://knowm.org/open-source/xchart) to do the heavy lifting.

I've really written this for my own benefit, because I like the simple interface of Matplotlib. To show how concise it can be, here's how to display a simple XY plot in 3 lines of code (in this example the single array of doubles is used for both the x and y data): 

```java
Plotter plt = new Plotter();
plt.plot(new double[] {1, 2, 3});
plt.show();
```

![Example 1](https://github.com/nigelbcurrie/MatplotJ/tree/master/images/MatplotJ_Example1.jpg Example 1)