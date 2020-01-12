package org.geometrybash.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyInput implements KeyListener {
	
	private static HashMap<Integer, Boolean> isDown = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> isReleased = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> isPressed = new HashMap<Integer, Boolean>();
	
	public static boolean isKeyDown(int keycode) {
		if (isDown.containsKey(keycode)) 
			return true;
		else
			return false;
	}
	
	public static boolean isKeyReleased(int keycode) {
		if (isReleased.containsKey(keycode))
			return true;
		else
			return false;
	}
	
	public static boolean isKeyPressed(int keycode) {
		if (isPressed.containsKey(keycode))
			return true;
		else
			return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		isDown.put(e.getKeyCode(), true);
		isPressed.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isDown.remove(e.getKeyCode());
		isReleased.put(e.getKeyCode(), true);
	}
	
	public void clear() {
		isReleased.clear();
		isPressed.clear();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
