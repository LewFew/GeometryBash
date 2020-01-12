package org.geometrybash.main;

import java.awt.geom.Line2D;

public class HitLine {
	
	private Line2D line;
	private GameObject parent;
	
	public HitLine(Vector2D vector, GameObject parent) {
		line = new Line2D.Double(parent.position.getX(), parent.position.getY(), parent.position.getX() + vector.getX(), parent.position.getY() + vector.getY());
		this.parent = parent;
	}
	
	public Line2D getLine() {
		return line;
	}
}
