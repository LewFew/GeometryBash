package org.geomtrybash.objects;

import java.awt.Graphics;

import org.geometrybash.main.GameObject;

public abstract class Attack extends GameObject {
	
	protected Player allegience;
	protected int damage;

	public Attack(int x, int y, Player allegience) {
		super(x, y);
		this.allegience = allegience;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public Player getAllegience() {
		return allegience;
	}
	
}
