package com.github.p2g3.dataVisualization.calculations;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

/**
 * This class generates a continuous function to a set of discrete data.
 * 
 * @author Revature Team 3 (Cynthia, Phuc, Christian, Brandon)
 * @version 0.1.0 *
 */
public class ContinuousToDiscrete {
	private double[] coeff;
	private double[] fittedCurve; // y
	private double[] xExp; // x no repeats

	public ContinuousToDiscrete(double[] coeff) {
		this.coeff = coeff;
	}

	/**
	 * This method transforms continuos data to discrete data.
	 */
	public void discretize() {
		PolynomialFunction function = new PolynomialFunction(this.coeff);

		double[] fitted = new double[xExp.length];

		for (int i = 0; i < xExp.length; i++) {
			fitted[i] = function.value(i);
		}

		this.fittedCurve = fitted;
	}

	/**
	 * This method removes duplicate values in an arraylist, and generates an
	 * independent variable dataset with a similar range as the original observed
	 * independent dataset.
	 * 
	 * @param xObs, independent observational data
	 * @return expected independent data, with duplicates removed
	 */
	public double[] removeDuplicates(ArrayList<Double> xObs) {
		Double min = Collections.min(xObs); ///////
		Double max = Collections.max(xObs);
		double[] tempReturn = new double[(int) (max - min)];
		int cnt = 0;
		for (int i = min.intValue(); i < max.intValue(); i++) {
			tempReturn[cnt] = i;
			cnt++;
		}
		this.xExp = tempReturn;
		return this.xExp;
	}
}
