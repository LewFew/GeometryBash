package org.geomtrybash.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import org.geometrybash.global.Functions;
import org.geometrybash.global.Resources;
import org.geometrybash.main.CyclicTimer;
import org.geometrybash.main.GameObject;
import org.geometrybash.main.Main;
import org.geometrybash.main.Timer;
import org.geometrybash.main.Vector2D;

public class Orbiter extends Attack {
	
	private GameObject center;
	private boolean shot = false;

	private double q = 0;
	
	private Image image = Resources.sprites.get("Orbiter");
	
	boolean defineStartingAngle = false;
	
	private double shootTheta = 0;
	
	private boolean bounced = false;
	
	private CyclicTimer trailTimer;

	public Orbiter(int x, int y, Player center) {
		super(x, y, center);
		this.center = center;
		
		hitBox = new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
		
		width = image.getWidth(null);
		height = image.getHeight(null);
		
		frictionLess = true;
		damage = 10;
		
		trailTimer = new CyclicTimer(this, 2, 3);
		Main.handler.addTimer(trailTimer);
	}
	
	public void shoot() {
		shot = true;
		
		acceleration.setTheta(center.getVelocity().getTheta());
		//acceleration.setMagnitude(3);
		
		acceleration.setMagnitude(8);
		
		velocity.setMagnitude(0);
		
		Timer t = new Timer(this, 10, 1);
		Main.handler.addTimer(t);
	}
	
	public void kill() {
		exists = false;
	}
	
	@Override
	public void timeAction(int id) {
		
		Random r = new Random();
		
		if (id == 1) {
			shot = false;
			acceleration.scale(0);
		} else if (id == 2) {
			bounced = false;
		} else if (id == 3) {
			if (r.nextInt(2) == 1) {
				Main.handler.addObject(new TrailParticle((int)position.getX() + r.nextInt(10), ((int)position.getY()) + r.nextInt(10), Color.cyan));
			} else {
				Main.handler.addObject(new TrailParticle((int)position.getX() - r.nextInt(10), ((int)position.getY()) - r.nextInt(10), Color.cyan));
			}
		}
	}

	@Override
	public void update() {
		
		damage = (int)(velocity.getMagnitude() * 0.25) + 5;
		
		if (!bounced) {
		
		if (shot) {
		
		//	acceleration.scale(0.8);
		//	System.out.println("ncujewkewq");
			
		} else if (Functions.getDistance(centerPosition, center.getCenterPosition()) > 100) {

//			if (position.getX() < center.getPosition().getX()) {
//				velocity.add(new Vector2D(3, 0));
//			} else if (position.getX() > center.getPosition().getX()) {
//				velocity.add(new Vector2D(-3, 0));
//			}
//			if (position.getY() < center.getPosition().getY()) {
//				velocity.add(new Vector2D(0, 3));
//			} else if (position.getY() > center.getPosition().getY()) {
//				velocity.add(new Vector2D(0, -3));
//			}
			//velocity.setY(center.getPosition().getY() - position.getY());
			//velocity.setX(center.getPosition().getX() - position.getX());
			//System.out.println(center.getPosition().getX() - position.getX());
			
			velocity.setTheta(Math.toDegrees(Math.atan2((center.getCenterPosition().getY() - centerPosition.getY()), (center.getCenterPosition().getX() - centerPosition.getX()))));
			velocity.setMagnitude((int) (Functions.getDistance(position, center.getPosition()) / 20));
			
		//	System.out.println(velocity.getX() + " h");
			
			defineStartingAngle = true;
			
		//	System.out.println(velocity.getX() + " " + velocity.getY() + " " + velocity.getMagnitude());
		} else if (Functions.getDistance(centerPosition, center.getCenterPosition()) <= 100 && defineStartingAngle) {
			//define starting angle

			q = 180 + (Math.toDegrees(Math.atan2((center.getCenterPosition().getY()) - (centerPosition.getY()), (center.getCenterPosition().getX() + center.getWidth() / 2) - (centerPosition.getX() + width/2))));
			//System.out.println(velocity.getTheta());
			
			//position.setX(position.getX() - (30 * Math.cos(Math.toRadians(velocity.getTheta()))));
			//position.setY(position.getY() - (30 * Math.sin(Math.toRadians(velocity.getTheta()))));
			
			//velocity.setTheta(180);
			
			velocity.setMagnitude(0);
			
			defineStartingAngle = false;
			((Circle) center).setCanAttack(true);

		} else if (Functions.getDistance(centerPosition, center.getCenterPosition()) <= 100) {
		//	System.out.println(velocity.getPrevTheta() + " + 2 = " + velocity.getTheta() + "  some reason idk");
			////velocity.setTheta(velocity.getTheta() - (360 / (((Math.PI * 200)) / velocity.getMagnitude())));
		//	velocity.setTheta(velocity.getTheta() + 2);
			////velocity.setMagnitude(10);
			//System.out.println(velocity.getTheta());
			//velocity.setTheta(velocity.getTheta() + 5);
		//	System.out.println(velocity.getPrevTheta() + " + 2 = " + velocity.getTheta());
			//System.out.println(Functions.normalizedTheta(velocity.getTheta()));
			
			bound(Vector2D.createDirectional(95, q), center.getCenterPosition());
			
			//velocity.setMagnitude(5);
		//	velocity.setTheta(velocity.getTheta() + (360 / ((Math.PI * 200)));
			
			((Circle) center).setCanAttack(true);
		}
		
		
		q = Functions.normalizedTheta(q + 5);
		
		}
		
		//System.out.println(position.getX());
		
	//	System.out.println(velocity.getX());
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(image, (int)drawPosition.getX() + width/2, (int)drawPosition.getY() + height/2, null);
		
		/*g.setColor(Color.PINK);
		g2d.draw(Vector2D.createDirectional(100, q).getLine(center.getCenterPosition()));
		
		g.setColor(Color.GREEN);
		g.drawLine((int)centerPosition.getX(), (int)centerPosition.getY(), (int)center.getCenterPosition().getX(), (int)center.getCenterPosition().getY());
		
		g.setColor(Color.CYAN);
		g.drawString("Distance: " + String.valueOf(Functions.getDistance(centerPosition, center.getCenterPosition())), (int)center.getPosition().getX(), (int)center.getPosition().getY() - 50);
		g.drawString("Angle: " + String.valueOf(velocity.getTheta()), (int)center.getPosition().getX(), (int)center.getPosition().getY() - 80);*/
		
		//System.out.println((drawPosition.getX() + width/2) + " " + (drawPosition.getY()) + height/2 + " " + position.getX() + " " + position.getY());
		//System.out.println(position.getX() + " Render");
	}

	@Override
	public void collide(GameObject object) {
		if (object instanceof Block) {
			//bounced = true;
			//shot = false;
			//bounced = true;
			//Timer t = new Timer(this, 20, 2);
			//Main.handler.addTimer(t);
			//velocity.scale(0);
			//advancedBounce(object, 1);
			//bounce(1);
			
		//	System.out.println(position.getX() + " Orbiter");
			
		} else if (object instanceof Player) {
			if (object != allegience) {
				bounced = true;
				shot = false;
				Timer t = new Timer(this, 20, 2);
				Main.handler.addTimer(t);
				advancedBounce(object, 1);
				//bounce(1);
			}
		} else if (object instanceof Orbiter) {
			bounced = true;
			shot = false;
			Timer t = new Timer(this, 20, 2);
			Main.handler.addTimer(t);
			advancedBounce(object, 1);
		}
	}

	@Override
	public void destroy() {
		trailTimer.stop();
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

}
