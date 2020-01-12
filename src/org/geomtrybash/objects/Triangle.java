package org.geomtrybash.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.geometrybash.global.Resources;
import org.geometrybash.main.CyclicTimer;
import org.geometrybash.main.Main;
import org.geometrybash.main.Timer;

public class Triangle extends Player {
	
	CyclicTimer trailTimer = new CyclicTimer(this, 2, 6);

	public Triangle(int x, int y, boolean p1) {
		super(x, y, p1);
		
		playerImage = Resources.sprites.get("Triangle");
		
		width = playerImage.getWidth(null);
		height = playerImage.getHeight(null);
		
		hitBox = new Rectangle((int)position.getX(), (int)position.getY(), width, height);

		health = 150;
		
		attackCool = 10;
		utilityCool = 500;
		
	}
	
	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform oldTransform = g2d.getTransform();

		AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(velocity.getTheta() + 90), (position.getX() + width/2) * oldTransform.getScaleX(), (position.getY() + height/2) * oldTransform.getScaleY());
		rotation.scale(oldTransform.getScaleX(), oldTransform.getScaleY());

		g2d.setTransform(rotation);
		g2d.drawImage(playerImage, (int)position.getX(), (int)position.getY(), null);
		
		g2d.setTransform(oldTransform);
		
		g.setColor(Color.GREEN);
		g.drawString("Health: " + health, (int)position.getX(), (int)position.getY() - 30);
		
		if (p1) {
			g.drawString("Player 1", (int)position.getX(), (int)position.getY() - 50);
		} else {
			g.drawString("Player 2", (int)position.getX(), (int)position.getY() - 50);
		}
	}
	
	@Override
	public void attack() {
		Main.handler.addObject(new TriangleProjectile((int)(drawPosition.getX() + width/1.3), (int)(drawPosition.getY() + height/1.3), this));
	}
	
	@Override
	public void utility() {
		Main.handler.addTimer(trailTimer);
		walkSpeed = 15;
		
		Timer t = new Timer(this, 300, 7);
		Main.handler.addTimer(t);
	}
	
	@Override
	public void timeAction(int id) {
		if (id == 1) {
			canAttack = true;
			postAttack();
		} else if (id == 2) {
			canSpecial = true;
			postSpecial();
		} else if (id == 3) {
			canDefend = true;
			postDefend();
		} else if (id == 4) {
			canUtility = true;
			postUtility();
		} else if (id == 5) {
			immune = false;
		} else if (id == 6) {
				
				Random r = new Random();
				
				if (r.nextInt(2) == 1) {
					Main.handler.addObject(new TrailParticle((int)centerPosition.getX() + r.nextInt(10), ((int)centerPosition.getY()) + r.nextInt(10), Color.green));
				} else {
					Main.handler.addObject(new TrailParticle((int)centerPosition.getX() - r.nextInt(10), ((int)centerPosition.getY()) - r.nextInt(10), Color.green));
				}
				
		 } else if (id == 7) {
			 walkSpeed = 8;
			 Main.handler.removeTimer(trailTimer);
		 }
	}
	
	@Override
	public void postAttack() {
		//acceleration.scale(0);
	}

}
