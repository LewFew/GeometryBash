package org.geometrybash.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.geometrybash.global.Constants;
import org.geomtrybash.objects.Orbiter;
import org.geomtrybash.objects.Player;

public abstract class GameObject implements Timable {
	
	protected Vector2D position;
	protected Vector2D prevPosition;
	protected Vector2D velocity = new Vector2D(0, 0);
	protected Vector2D acceleration = new Vector2D(0, 0);
	protected Vector2D drawPosition;
	protected Vector2D centerPosition = new Vector2D(0, 0);
	
	protected boolean immune = false;
	protected boolean physicsImmune = false;
	
	protected HitLine hitLine;
	
	protected boolean frictionLess = false;
	
	protected GameObject me = this;
	
	protected boolean exists = true;
	
	protected int width, height;
	
	public boolean willStop = false;
	
	protected Rectangle hitBox;
	
	public GameObject(int x, int y) {
		position = new Vector2D(x, y);
		prevPosition = new Vector2D(x, y);
		drawPosition = new Vector2D(x, y);
		
		hitLine = new HitLine(velocity, this);
	}
	
	public GameObject(Vector2D position) {
		this.position = position;
	}
	
	public abstract void create();
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract void collide(GameObject object);
	
	protected void bounce(double bounceMagnitude, GameObject bounceObject) {
		Vector2D v = new Vector2D(velocity.getX(), velocity.getY());
		
		Rectangle xBack = new Rectangle((int)prevPosition.getX(), (int)hitBox.getY(), (int)hitBox.getWidth(), (int)hitBox.getHeight());
		Rectangle yBack = new Rectangle((int)hitBox.getX(), (int)prevPosition.getY(), (int)hitBox.getWidth(), (int)hitBox.getHeight());
		
		velocity.setMagnitude(0);
		
		if (yBack.intersects(bounceObject.hitBox)) {
			position.setX(prevPosition.getX());
			v.setX(-v.getX() * bounceMagnitude);
		}
		
		if (xBack.intersects(bounceObject.hitBox)) {
			position.setY(prevPosition.getY());
			v.setY(-v.getY() * bounceMagnitude);
		}
		
		velocity = v;
	}
	
	protected void advancedBounce(GameObject object, double magnitude) {
		
		if (object.hitBox.intersects(hitBox)) {
		
		Rectangle2D r = hitBox.createIntersection(object.hitBox);
		
		double a, b;
		
		a = (position.getX() < object.getPosition().getX()) ? r.getWidth() : -r.getWidth();
		b = (position.getY() < object.getPosition().getY()) ? r.getHeight() : -r.getHeight();
		
		if (r.getWidth() < r.getHeight()) {
			System.out.println("Shifting Width by " + a);
			position.setX(position.getX() - a);
			velocity.add(new Vector2D(-a * magnitude, 0));
		} else if (r.getHeight() < r.getWidth()) {
			System.out.println("Shifting Height by " + b);
			position.setY(position.getY() - b);
			velocity.add(new Vector2D(0, -b * magnitude));
		} else {
			position.setX(position.getX() - r.getWidth());
			position.setY(position.getY() - r.getHeight());
			//velocity.add(new Vector2D(r.getWidth(), r.getHeight()));
		}
		
		} else if (hitLine.getLine().intersects(object.hitBox)) {
			position.setX(hitLine.getLine().getX1());
			position.setY(hitLine.getLine().getY1());
			velocity.setMagnitude(0);
		}
		
		willStop = true;
		
		//velocity.setMagnitude(0);
		
	}
	
	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Vector2D getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}
	
	public Vector2D getCenterPosition() {
		return centerPosition;
	}
	
	public void bound(Vector2D boundingVector, Vector2D boundingOrigin) {
		position.setX(boundingOrigin.getX() + boundingVector.getX() - width/2);
		position.setY(boundingOrigin.getY() + boundingVector.getY() - height/2);
	}
	
	public abstract void destroy();

	public void physics() {
		
		if (!physicsImmune) {
		
			if (!frictionLess) {
				velocity.scale(Constants.friction);
			}
			velocity.add(acceleration);
			
			hitLine.getLine().setLine(position.getX() + width/2, position.getY() + height/2, (position.getX() + width/2) + velocity.getX(), (position.getY() + height/2) + velocity.getY());
			position.add(velocity);
			
			centerPosition.setX(position.getX() + width/2);
			centerPosition.setY(position.getY() + height/2);
			
			drawPosition.setX(position.getX() - width/2);
			drawPosition.setY(position.getY() - height/2);
			
			if (this instanceof Player) {
			//	System.out.println(velocity.getMagnitude());
			}
		
		}
		
		hitBox.setLocation((int)position.getX(), (int)position.getY());
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void temp() {
		position.setX(200);
		position.setY(200);
	}
	
	public void finalStep() {
		prevPosition.setX(position.getX());
		prevPosition.setY(position.getY());
	}

}
