package org.geometrybash.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.geomtrybash.objects.Block;
import org.geomtrybash.objects.Orbiter;

public class Handler {

	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<Timer> timers = new ArrayList<Timer>();
	
	public void removeObject(GameObject object) {
		objects.set(objects.indexOf(object), objects.get(objects.size() - 1));
		objects.remove(objects.size() - 1);
	}
	
	public void addObject(GameObject object) {
		objects.add(object);
		object.create();
	}
	
	public GameObject getObject(int i) {
		return objects.get(i);
	}
	
	public void removeTimer(Timer object) {
		timers.set(timers.indexOf(object), timers.get(timers.size() - 1));
		timers.remove(timers.size() - 1);
	}
	
	public void addTimer(Timer timer) {
		timers.add(timer);
	}
	
	public Timer getTimer(int i) {
		return timers.get(i);
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			
			//if (!Main.debugMode)
				objects.get(i).render(g);
				
				if (objects.get(i).willStop) {
				//	Main.stop();
				}
			
			if (Main.debugMode) {
				g.setColor(Color.BLUE);
				g.drawRect((int)objects.get(i).position.getX(), (int)objects.get(i).position.getY(), (int)objects.get(i).hitBox.getWidth(), (int)objects.get(i).hitBox.getHeight());
				
				g.setColor(Color.GREEN);
				g.drawLine((int)objects.get(i).hitLine.getLine().getX1(), (int)objects.get(i).hitLine.getLine().getY1(),
						(int)objects.get(i).hitLine.getLine().getX2(), (int)objects.get(i).hitLine.getLine().getY2());
			}
		}
	}
	
	public void clearAll() {
		objects.clear();
		timers.clear();
	}
	
	public void update() {
		
		for (int i = 0; i < timers.size(); i++) {
			
			timers.get(i).update();
			
			if (timers.get(i).isFinished()) {
				timers.remove(i);
			}
		}
		
		for (int i = 0; i < objects.size(); i++) {
			getObject(i).update();
		}
		
		for (int i = 0; i < objects.size(); i++) {
			getObject(i).physics();
		}
		
		for (int i = 0; i < objects.size(); i++) {
			
			for (int j = 0; j < objects.size(); j++) {
			//	System.out.println(j + " j");
			//	System.out.println(getObject(j).position.getX() + " " + getObject(j).position.getY());
				getObject(i);
				if (i != j && (getObject(i).hitBox.intersects(objects.get(j).hitBox) || getObject(i).hitBox.intersectsLine(objects.get(j).hitLine.getLine()))) {
					getObject(i).collide(objects.get(j));
					if (getObject(i).hitBox.intersectsLine(objects.get(j).hitLine.getLine()) && !getObject(i).hitBox.intersects(objects.get(j).hitBox)) {
						//System.out.println("Vector collides! " + i);
						if (getObject(i) instanceof Block) {
							//System.out.println("xp");
							//getObject(i).temp();
						}
					}
				}
			}
			
		}
		
		for (int i = 0; i < objects.size(); i++) {
			if (getObject(i) instanceof Entity) {
				((Entity) getObject(i)).life();
			}
			
			if (!getObject(i).exists) {
				getObject(i).destroy();
				removeObject(getObject(i));
			}
		}
		
		for (int i = 0; i < objects.size(); i++) {
			getObject(i).finalStep();
		}
	}
	
}
