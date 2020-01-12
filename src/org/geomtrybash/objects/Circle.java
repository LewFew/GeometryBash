package org.geomtrybash.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import org.geometrybash.global.Resources;
import org.geometrybash.main.Main;

public class Circle extends Player {
	
	private Orbiter orbiters[] = new Orbiter[4];
	protected Image arrow = Resources.sprites.get("Arrow");
	
	public Circle(int x, int y, boolean p1) {
		super(x, y, p1);

		//Main.handler.addObject(orbiters[1]);
		//Main.handler.addObject(orbiters[2]);
		//Main.handler.addObject(orbiters[3]);
		
		//attackCool = 100;
		utilityCool = 80;
		
		health = 100;
		
		playerImage = Resources.sprites.get("Circle");
		width = 32;
		height = 32;
		hitBox = new Rectangle((int)position.getX(), (int)position.getY(), width, height);
	}
	
	@Override
	protected void preAttack() {
		canAttack = false;
	}
	
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}
	
	@Override
	public void create() {
		
		orbiters[0] = new Orbiter((int)position.getX(), (int)position.getY() , this);
		//orbiters[1] = new Orbiter(x - 50, y + 50, this);
		//orbiters[2] = new Orbiter(x + 50, y - 50, this);
		//orbiters[3] = new Orbiter(x + 50, y + 50, this);
		
		Main.handler.addObject(orbiters[0]);
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.drawImage(playerImage, (int)position.getX(), (int)position.getY(), null);
		
		g2d.setColor(new Color(46, 132, 144, 150));
		g2d.setColor(new Color(Color.CYAN.getRed(), Color.CYAN.getGreen(), Color.CYAN.getBlue(), 150));
		g2d.fillOval((int)position.getX() - 5, (int)position.getY() - 5, width + 10, height + 10);
		
		g2d.setColor(new Color(255, 255, 255, 255));
		g2d.fillOval((int)position.getX(), (int)position.getY(), width, height);
		
		AffineTransform original = g2d.getTransform();
		AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(velocity.getTheta() + 90), centerPosition.getX() * original.getScaleX(), centerPosition.getY() * original.getScaleY());
		
		rotate.scale(original.getScaleX(), original.getScaleY());
		
		g2d.setTransform(rotate);
		g2d.drawImage(arrow, (int)position.getX(), (int)position.getY() - width - 10, null);
		
		g2d.setTransform(original);
		
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
		canAttack = false;
		orbiters[0].shoot();
	}
	
	@Override
	public void utility() {
		orbiters[0] = new Orbiter((int)position.getX(), (int)position.getY(), this);
		Main.handler.addObject(orbiters[0]);
	}
	
	@Override
	public void destroy() {
		orbiters[0].kill();
	}
	

}
