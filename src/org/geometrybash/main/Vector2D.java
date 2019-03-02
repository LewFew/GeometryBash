package org.geometrybash.main;

import java.awt.geom.Line2D;

import org.geometrybash.global.Functions;

public class Vector2D {
	
	private double x, y;
	
	public double getPrevTheta() {
		return prevTheta;
	}

	public void setPrevTheta(double prevTheta) {
		this.prevTheta = prevTheta;
	}

	private double magnitude;
	private double theta;
	private double prevTheta;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
		
		this.magnitude = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		theta = Math.toDegrees(Math.atan2(y, x));
	}
	
	public static Vector2D createDirectional(double magnitude, double direction) {
		return new Vector2D(Math.cos(Math.toRadians(direction)) * magnitude, Math.sin(Math.toRadians(direction)) * magnitude);
	}
	
	private void updateMagnitude() {
		magnitude = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	
	private void updateTheta() {
		prevTheta = theta;
		theta = Math.toDegrees(Math.atan2(y, x));
		if (theta < 0) {
			theta += 360;
		}
	}
	
	public Line2D.Double getLine(Vector2D origin) {
		return new Line2D.Double(origin.getX(), origin.getY(), origin.getX() + x, origin.getY() + y);
	}
	
	public void setMagnitude(int magnitude) {
		
		magnitude = Math.abs(magnitude);
		this.magnitude = magnitude;
		
		double k = Math.toRadians(theta);
		
		x = Math.cos(k) * magnitude;
		y = Math.sin(k) * magnitude;
		
		Math.toDegrees(theta);
			
	}
	
	public void scale(double scalar) {
		x *= scalar;
		y *= scalar;
		
		updateMagnitude();
	}
	
	public void add(Vector2D v2) {
		x += v2.getX();
		y += v2.getY();
		
		updateMagnitude();
		updateTheta();
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
		updateMagnitude();
		updateTheta();
	}
	
	public void setY(double y) {
		this.y = y;
		updateMagnitude();
		updateTheta();
	}
	
	public void setTheta(double theta) {
		
		prevTheta = this.theta;
		
		if (theta < 0) {
			theta += 360;
		}
		
		double k = Math.toRadians(theta);
		
		setX(Math.cos(k) * magnitude);
		setY(Math.sin(k) * magnitude);

		this.theta = Functions.normalizedTheta(theta);
		
		//System.out.println(theta);
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public double getTheta() {
		return theta;
	}

}
