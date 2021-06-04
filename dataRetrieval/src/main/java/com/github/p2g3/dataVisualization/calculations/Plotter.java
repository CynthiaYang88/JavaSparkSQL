package com.github.p2g3.dataVisualization.calculations;

//import tech.tablesaw.plotly.Plot;
//import tech.tablesaw.plotly.components.Figure;
//import tech.tablesaw.plotly.components.Layout;
//import tech.tablesaw.plotly.traces.ScatterTrace;

/**
 * This class generates the observed data scatter plot and the expected
 * polynomial fitted line graph.
 * 
 * @author Revature Team 3 (Cynthia, Phuc, Christian, Brandon)
 * @version 0.1.0
 *
 */
public class Plotter {

	/**
	 * This method generates a multiple trace scatter plot.
	 * 
	 * @param xObs, Observed independent dataset
	 * @param yObs, Observed dependent dataset
	 * @param xExp, Expected independent dataset
	 * @param yExp, Expected dependent dataset
	 */
	public void multiPlot(double[] xObs, double[] yObs, double[] xExp, double[] yExp) {
		/*ScatterTrace traceObs = ScatterTrace.builder(xObs, yObs).build();
		ScatterTrace traceExp = ScatterTrace.builder(xExp, yExp).mode(ScatterTrace.Mode.LINE).build();
		Layout layout = Layout.builder().width(800).height(700).title(DataIO.yTitle + " over " + DataIO.xTitle).build();
		Plot.show(new Figure(layout, traceObs, traceExp));*/
	}

}
