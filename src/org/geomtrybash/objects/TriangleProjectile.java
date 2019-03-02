package org.geomtrybash.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Random;

import org.geometrybash.global.Resources;
import org.geometrybash.main.GameObject;
import org.geometrybash.main.Main;
import org.geometrybash.main.Timer;

public class TriangleProjectile extends Attack {
	
	private Image img = Resources.sprites.get("TriangleProjectile");
	private boolean hit = false;

	public TriangleProjectile(int x, int y, Player allegience) {
		super(x, y, allegience);
		
		width = img.getWidth(null);
		height = img.getHeight(null);
		
		hitBox = new Rectangle(x, y, width, height);
		
		Timer t = new Timer(this, 200, 1);
		Main.handler.addTimer(t);
		acceleration.setTheta(allegience.getVelocity().getTheta());
		
		damage = 3;
	}

	@Override
	public void timeAction(int id) {	
		if (id == 1) {
			exists = false;
		} else if (id == 2) {
			exists = false;
		}
	}

	@Override
	public void update() {
		if (!hit) {
			acceleration.setMagnitude(5);
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform prev = g2d.getTransform();
		AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(velocity.getTheta() + 90), position.getX() + width/2, position.getY() + height/2);
		
		g2d.setTransform(rotate);
		
		g2d.drawImage(img, (int)position.getX(), (int)position.getY(), null);
		
		g.drawImage(img, (int)position.getX(), (int)position.getY(), null);
		
		g2d.setTransform(prev);
		
	}

	@Override
	public void collide(GameObject object) {
		if (object instanceof Block) {
			hit = true;
			advancedBounce(object, 1);
			acceleration.setTheta(velocity.getTheta());
			acceleration.setMagnitude(3);
		}
		if (object instanceof Orbiter) {
			hit = true;
			acceleration.setMagnitude(0);
			advancedBounce(object, 1);
			velocity.add(object.getVelocity());
		}
		if (object instanceof Player && allegience != object) {
			//hit = true;
			//acceleration.scale(0);
			//bounce(1);
			//acceleration.setTheta(velocity.getTheta());
			//acceleration.setMagnitude(2);
		//	hit = true;
		//	advancedBounce(object, 1);
		//	acceleration.setTheta(velocity.getTheta());
		//	acceleration.setMagnitude(3);
			Timer t = new Timer(this, 2, 2);
			Main.handler.addTimer(t);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

}
