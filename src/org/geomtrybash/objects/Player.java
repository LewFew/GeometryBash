package org.geomtrybash.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import org.geometrybash.global.Resources;
import org.geometrybash.main.Entity;
import org.geometrybash.main.GameObject;
import org.geometrybash.main.KeyInput;
import org.geometrybash.main.Main;
import org.geometrybash.main.Timer;
import org.geometrybash.main.Vector2D;

public class Player extends Entity {
	
	protected boolean p1;
	protected int safeID;
	protected Image playerImage;
	
	protected double walkSpeed = 8;
	
	protected int attackCool, specialCool, defendCool, utilityCool;
	protected boolean canAttack = true, canSpecial = true, canDefend = true, canUtility = true;

	public Player(int x, int y, boolean p1) {
		super(x, y);
		
		this.p1 = p1;
		
		width = 64;
		height = 64;
		
		hitBox = new Rectangle(x, y, width, height);
		
		health = 100;
	}
	
	protected void attack() {
		
	}
	
	protected void special() {
		
	}
	
	protected void defense() {
		
	}
	
	protected void utility() {
		
	}
	
	protected void preAttack() {
		canAttack = false;
		Timer t = new Timer(this, attackCool, 1);
		Main.handler.addTimer(t);
	}
	
	protected void preSpecial() {
		canSpecial = false;
		Timer t = new Timer(this, specialCool, 2);
		Main.handler.addTimer(t);
	}
	
	protected void preDefense() {
		canDefend = false;
		Timer t = new Timer(this, defendCool, 3);
		Main.handler.addTimer(t);
	}
	
	protected void preUtility() {
		canUtility = false;
		Timer t = new Timer(this, utilityCool, 4);
		Main.handler.addTimer(t);
	}
	
	protected void postAttack() {
		
	}
	
	protected void postSpecial() {
		
	}
	
	protected void postDefend() {
		
	}
	
	protected void postUtility() {
		
	}

	@Override
	public void update() {
		
		if (p1) {
		
			if (KeyInput.isKeyDown(KeyEvent.VK_A)) {
				velocity.add(new Vector2D(-walkSpeed, 0));
			}
			if (KeyInput.isKeyDown(KeyEvent.VK_D)) {
				velocity.add(new Vector2D(walkSpeed, 0));
			}
			if (KeyInput.isKeyDown(KeyEvent.VK_W)) {
				velocity.add(new Vector2D(0, -walkSpeed));
			}
			if (KeyInput.isKeyDown(KeyEvent.VK_S)) {
				velocity.add(new Vector2D(0, walkSpeed));
			}
			if (KeyInput.isKeyDown(KeyEvent.VK_T)) {
				velocity.setMagnitude(10);
				//System.out.println(velocity.getTheta() + 10);
				velocity.setTheta(velocity.getTheta() + 5);
				//System.out.println(velocity.getTheta());
			}
			
			if (KeyInput.isKeyPressed(KeyEvent.VK_4) && canAttack) {
				preAttack();
				attack();
			}
			if (KeyInput.isKeyPressed(KeyEvent.VK_5) && canSpecial) {
				preSpecial();
				special();
			}
			if (KeyInput.isKeyPressed(KeyEvent.VK_6) && canDefend) {
				preDefense();
				defense();
			}
			if (KeyInput.isKeyPressed(KeyEvent.VK_7) && canUtility) {
				preUtility();
				utility();
			}
			
			//System.out.println(velocity.getTheta());
			//System.out.println(velocity.getMagnitude());
		
		} else {
			
			if (KeyInput.isKeyDown(KeyEvent.VK_LEFT)) {
				velocity.add(new Vector2D(-walkSpeed, 0));
			}
			if (KeyInput.isKeyDown(KeyEvent.VK_RIGHT)) {
				velocity.add(new Vector2D(walkSpeed, 0));
			}
			if (KeyInput.isKeyDown(KeyEvent.VK_UP)) {
				velocity.add(new Vector2D(0, -walkSpeed));
			}
			if (KeyInput.isKeyDown(KeyEvent.VK_DOWN)) {
				velocity.add(new Vector2D(0, walkSpeed));
			}
			
			if (KeyInput.isKeyPressed(KeyEvent.VK_O) && canAttack) {
				preAttack();
				attack();
			}
			if (KeyInput.isKeyPressed(KeyEvent.VK_P) && canSpecial) {
				preSpecial();
				special();
			}
			if (KeyInput.isKeyPressed(KeyEvent.VK_OPEN_BRACKET) && canDefend) {
				preDefense();
				defense();
			}
			if (KeyInput.isKeyPressed(KeyEvent.VK_CLOSE_BRACKET) && canUtility) {
				preUtility();
				utility();
			}
			
			//System.out.println(velocity.getTheta());
			//System.out.println(velocity.getMagnitude());
		
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(playerImage, (int)drawPosition.getX() + width/2, (int)drawPosition.getY() + height/2, null);
		
		g.setColor(Color.GREEN);
		g.drawString("Health: " + health, (int)position.getX(), (int)position.getY() - 30);
		
		if (p1) {
			g.drawString("Player 1", (int)position.getX(), (int)position.getY() - 50);
		} else {
			g.drawString("Player 2", (int)position.getX(), (int)position.getY() - 50);
		}
	}

	@Override
	public void collide(GameObject object) {
		if (object instanceof Block) {
			//advancedBounce(object, 0);
			bounce(0);
		} else if (object instanceof Attack) {
			if (((Attack) object).getAllegience() != this && !immune) {
				health -= ((Attack) object).getDamage();
				velocity.add(object.getVelocity());
				immune = true;
				Timer t = new Timer(this, 2, 5);
				Main.handler.addTimer(t);
			}
		}
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
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

}
