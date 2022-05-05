package application;

import java.lang.Math;

public class Distance {
	private final double radius = 6371; // mean earth radius approximation in KMs
	private double longA;
	private double latA;
	private double longB;
	private double latB;
	private double result;

	public Distance() {

	}

	public Distance(double longA, double latA, double longB, double latB) {
		this.longA = longA;
		this.latA = latA;
		this.longB = longB;
		this.latB = latB;
		findResult();
	}

	private void findResult() { // using Haversine formula
		double theta1 = latA * Math.PI / 180; // theta, lambda in radians
		double theta2 = latB * Math.PI / 180;
		double deltaTheta = (latB - latA) * Math.PI / 180;
		double deltaLambda = (longB - longA) * Math.PI / 180;

		// a is the square of half the chord length between the podoubles
		double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2)
				+ Math.cos(theta1) * Math.cos(theta2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
		// c is the angular distance in radians
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		result = Math.round(radius * c * 100.0) / 100.0; // in Kilometers
	}

	public double getLongA() {
		return longA;
	}

	public void setLongA(double longA) {
		this.longA = longA;
	}

	public double getLatA() {
		return latA;
	}

	public void setLatA(double latA) {
		this.latA = latA;
	}

	public double getLongB() {
		return longB;
	}

	public void setLongB(double longB) {
		this.longB = longB;
	}

	public double getLatB() {
		return latB;
	}

	public void setLatB(double latB) {
		this.latB = latB;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

}
