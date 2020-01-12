package org.geomtrybash.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import org.geometrybash.global.Resources;
import org.geometrybash.main.GameObject;
import org.geometrybash.main.Main;
import org.geometrybash.main.Timer;
import org.geometrybash.main.Vector2D;

public class Particle extends GameObject {
	
	protected boolean fade;
	protected Color color;
	protected String imgString;
	protected Image img;
	protected boolean useImage;
	protected int life;
	protected Rectangle rect;
	protected Ellipse2D ellipse;
	protected Polygon polygon;
	protected int shapeType;
	protected boolean bright;
	
	private Timer t;

	public Particle(int x, int y) {
		super(x, y);
		
		if (useImage) {
			img = Resources.sprites.get(imgString);
		}
		
		Random r = new Random();
		
		velocity = Vector2D.createDirectional(20, r.nextInt(360));
	}

	@Override
	public void timeAction(int id) {
		if (id == 1) {
			exists = false;
		}
	}

	@Override
	public void create() {
		t = new Timer(this, life, 1);
		Main.handler.addTimer(t);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), ((t.timeRemaining() * 255 / life) / 2)));
		
		if (useImage) {
			g.drawImage(img, (int)position.getX(), (int)position.getY(), null);
		} else if (shapeType == 0) {
			g2d.fillOval((int)ellipse.getX(), (int)ellipse.getY(), (int)ellipse.getWidth(), (int)ellipse.getHeight());
			if (bright) {
				g2d.setColor(new Color(255, 255, 255, (t.timeRemaining() * 255) / life));
				g2d.fillOval((int)(ellipse.getCenterX() - ellipse.getWidth() / 3), (int)(ellipse.getCenterY() - ellipse.getHeight() / 3), (int)(ellipse.getWidth() * 0.75), (int)(ellipse.getHeight() * 0.75));
			}
		} else if (shapeType == 1) {
			
		} else if (shapeType == 2) {
			
		}
	}

	@Override
	public void collide(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
