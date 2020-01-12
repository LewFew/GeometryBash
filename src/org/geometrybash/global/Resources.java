package org.geometrybash.global;

import java.awt.Image;
import java.util.HashMap;

import org.geometrybash.main.Map;

public class Resources {
	
	public static HashMap<String, Image> sprites = new HashMap<String, Image>();
	public static HashMap<String, Map> maps = new HashMap<String, Map>();
	
	public static String getPath(String relative) {
		return Resources.class.getClassLoader().getResource(relative).getPath();
	}
	
}
