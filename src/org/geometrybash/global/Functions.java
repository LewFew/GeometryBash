package org.geometrybash.global;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

import org.geometrybash.main.Vector2D;

public class Functions {

	public static double getDistance(Vector2D pos1, Vector2D pos2) {
		return Math.sqrt(Math.pow((pos1.getX() - pos2.getX()), 2) + Math.pow(pos1.getY() - pos2.getY(), 2));
	}
	
	public static double normalizedTheta(double theta) {
		return theta % 360;
	}
	
	public static boolean withinError(double target, double value, double acceptedError) {
		if (Math.abs(target - value) <= acceptedError) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Vector2D lineRectPOI(Line2D.Double line, Rectangle rect) {
		if (rect.intersectsLine(line)) {
			//rect.int
			return null;
		} else {
			return null;
		}
	}
	
	public static boolean withinErrorPercent(double target, double value, double acceptedError) {
		
		if (value != 0) {	
			if (Math.abs(1 - (target/value)) <= acceptedError) {
				return true;
			} else {
				return false;
			}	
		} else if (target != 0) {
			if (Math.abs(1 - (value/target)) <= acceptedError) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
