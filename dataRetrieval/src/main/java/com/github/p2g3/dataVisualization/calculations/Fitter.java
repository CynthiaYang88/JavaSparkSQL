package com.github.p2g3.dataVisualization.calculations;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.util.ArrayList;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

/**
 * This class contains the methods necessary to perform polynomial fitting
 * processes.
 * 
 * @author Revature Team 3 (Cynthia, Phuc, Christian, Brandon)
 * @version 0.1.0
 *
 */
public class Fitter {
	private ArrayList<Double> x;
	private ArrayList<Double> y;
	private double[] coeff;
	private double[] fittedCurve; // II

	/**
	 * Contructor method that takes in two array lists representing the independent
	 * and dependent dataset.
	 * 
	 * @param x, independent observed dataset
	 * @param y, dependent observed dataset
	 */
	public Fitter(ArrayList<Double> x, ArrayList<Double> y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This method performs the polynomial fit.
	 */
	public void curveFitter() {
		// Collect data.
		final WeightedObservedPoints obs = new WeightedObservedPoints();
		for (int i = 0; i < x.size(); i++) {
			obs.add(x.get(i), y.get(i));
		}

		// Instantiate fitter
		final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(1);

		// Compute Coefficients
		double[] coeffDouble = fitter.fit(obs.toList());
		coeff = coeffDouble;
	}

	/**
	 * This method generates corresponding expected values based on the fitted
	 * function.
	 * 
	 * @param xExp, expected independent variable dataset
	 * @return expected dependent variable dataset
	 */
	public double[] discretize(double[] xExp) {
		PolynomialFunction function = new PolynomialFunction(this.coeff);

		double[] fitted = new double[xExp.length];

		for (int i = 0; i < xExp.length; i++) {
			fitted[i] = function.value(xExp[i]);
		}

		this.fittedCurve = fitted;
		return this.fittedCurve;
	}

	// getters/setters
	public double[] getCoeff() {
		return coeff;
	}

	public void setCoeff(double[] coeff) {
		this.coeff = coeff;
	}

	public ArrayList<Double> getX() {
		return x;
	}

	public void setX(ArrayList<Double> x) {
		this.x = x;
	}

	public ArrayList<Double> getY() {
		return y;
	}

	public void setY(ArrayList<Double> y) {
		this.y = y;
	}

	public double[] getFittedCurve() { // II
		return fittedCurve;
	}

	public void setFittedCurve(double[] fittedCurve) { // II
		this.fittedCurve = fittedCurve;
	}

}
