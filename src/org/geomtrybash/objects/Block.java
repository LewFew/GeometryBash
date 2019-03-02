package org.geomtrybash.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import org.geometrybash.global.Resources;
import org.geometrybash.main.GameObject;
import org.geometrybash.main.Vector2D;

public class Block extends GameObject {
	
	private Image blockImage = Resources.sprites.get("Block");

	public Block(int x, int y) {
		super(x, y);
		
		width = blockImage.getWidth(null);
		height = blockImage.getHeight(null);
		
		hitBox = new Rectangle(x, y, width, height);
	}
	
	public Block(Vector2D position) {
		super(position);
		
		width = blockImage.getWidth(null);
		height = blockImage.getHeight(null);
		
		hitBox = new Rectangle((int)position.getX(), (int)position.getY(), width, height);
	}

	@Override
	public void timeAction(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(blockImage, (int)position.getX(), (int)position.getY(), null);
	}

	@Override
	public void collide(GameObject object) {
		// TODO Auto-generated method stub
		
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
