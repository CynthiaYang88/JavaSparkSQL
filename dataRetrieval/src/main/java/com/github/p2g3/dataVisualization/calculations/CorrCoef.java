package com.github.p2g3.dataVisualization.calculations;

import java.util.ArrayList;

/**
 * This class computes the R^2 correlation coefficient of two datasets.
 * @author Revature Team 3 (Cynthia, Phuc, Christian, Brandon)
 * @version 0.1.0 
 */
public class CorrCoef {
	private ArrayList<Double> x;
	private ArrayList<Double> y;
	private double xBar;
	private double yBar;

	/**
	 * This method computes the R^2 correlation coefficient of two input arraylists.
	 * 
	 * @param x, independent variable arraylist data
	 * @param y, dependent variable arraylist data
	 * @return R^2 correlation coefficient
	 */
	public double computeR2(ArrayList<Double> x, ArrayList<Double> y) {
		this.xBar = computeAverage(x);
		this.yBar = computeAverage(y);
		ArrayList<Double> xMinusXBar = tMinusTBar(x, xBar);
		ArrayList<Double> yMinusyBar = tMinusTBar(y, yBar);
		ArrayList<Double> xxBarProdyyBar = arrayProduct( xMinusXBar, yMinusyBar);
		ArrayList<Double> xxBar2 = arrayProduct( xMinusXBar, xMinusXBar);
		ArrayList<Double> yyBar2 = arrayProduct( yMinusyBar, yMinusyBar);
		double temp0 = sumArray( xxBarProdyyBar );
		double temp1 = sumArray( xxBar2 );
		double temp2 = sumArray( yyBar2 );
		if( (temp1*temp2) != 0 ) {
			double r  = temp0 / ( Math.sqrt(temp1*temp2) );
			return r*r;
		}
		return 0; // avoid undefined
	}
	
	/**
	 * This method computes the mean.
	 * 
	 * @param t, input arraylist of data
	 * @return mean of values in dataset
	 */
	public double computeAverage(ArrayList<Double> t) {
		double sum = 0;
		if (!t.isEmpty()) {
			for (double xx : t) {
				sum += xx;
			}
			return sum / t.size();
		}
		return sum;
	}

	/**
	 * This method generates the resulting arraylist of t-t-bar.
	 * 
	 * @param t, data set arraylist
	 * @param tBar, mean of dataset "t"
	 * @return array list obtained from appliying a shift of t-bar to t
	 */
	public ArrayList<Double> tMinusTBar(ArrayList<Double> t, double tBar) {
		ArrayList<Double> diff = new ArrayList<Double>();
		for (int i = 0; i < t.size(); i++) {
			diff.add(i, t.get(i).doubleValue() - tBar);
		}
		return diff;
	}
	
	/**
	 * This method computes the item by item product of two arraylists.
	 * 
	 * @param a, arraylist a
	 * @param b, arraylist b
	 * @return item by item product of arraylist a and b
	 */
	public ArrayList<Double> arrayProduct(ArrayList<Double> a, ArrayList<Double> b){
		ArrayList<Double> prod = new ArrayList<Double>();
		for (int i = 0; i < a.size(); i++) {
			prod.add(i, a.get(i).doubleValue() * b.get(i).doubleValue() );
		}
		return prod;	
	}
	
	/**
	 * This method returns the sum of an arraylist.
	 * 
	 * @param t, input arraylist
	 * @return sum of all elements in t
	 */
	public double sumArray(ArrayList<Double> t) {
		double sum = 0;
			for (double xx : t) {
				sum += xx;
			}
		return sum;
	}
}
