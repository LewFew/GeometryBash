package org.geometrybash.main;

import java.util.ArrayList;

import org.geomtrybash.objects.Block;
import org.geomtrybash.objects.Circle;
import org.geomtrybash.objects.Triangle;

public class Map {
	
	private GameObject[][] items;
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Map(String[] lines, int p1Shape, int p2Shape) {
		GameObject[][] items = new GameObject[20][25];
		for (int i = 0; i < lines.length; i++) {
			char[] k = lines[i].toCharArray();
			//System.out.println("");
			for (int j = 0; j < k.length; j++) {
			//	System.out.print(k[j]);
				if (k[j] == '1') {
					objects.add(new Block(j * 32, i * 32));
				} else if (k[j] == '!') {
					if (p1Shape == 1) { //Triangle
						objects.add(new Triangle(j * 32, i * 32, true));
					} else if (p1Shape == 2) { //Circle
						objects.add(new Circle(j * 32, i * 32, true));
					}
				} else if (k[j] == '@') {
					if (p2Shape == 1) { //Triangle
						objects.add(new Triangle(j * 32, i * 32, false));
					} else if (p2Shape == 2) { //Circle
						objects.add(new Circle(j * 32, i * 32, false));
					}
				}
			}
		}
		this.items = items;
	}
	
	public ArrayList<GameObject> getItems() {
		return objects;
	}
}
