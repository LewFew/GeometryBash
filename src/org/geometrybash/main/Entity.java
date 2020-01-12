package org.geometrybash.main;

public abstract class Entity extends GameObject {
	
	protected int health = 1;

	public Entity(int x, int y) {
		super(x, y);
	}
	
	public Entity(Vector2D position) {
		super(position);
	}
	
	
	public void life() {
		if (health <= 0) {
			exists = false;
		}
	}

}
