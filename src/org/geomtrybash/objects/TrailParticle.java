package org.geomtrybash.objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class TrailParticle extends Particle {

	public TrailParticle(int x, int y, Color c) {
		super(x, y);
		
		Random r = new Random();
		
		int diameter = r.nextInt(20);
		
		life = 20;
		color = c;
		shapeType = 0;
		ellipse = new Ellipse2D.Double(x, y, diameter, diameter);
		bright = true;
		
		hitBox = new Rectangle(x, y, 1, 1);
	}

}
