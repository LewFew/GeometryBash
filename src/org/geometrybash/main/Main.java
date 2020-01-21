
package org.geometrybash.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.geometrybash.global.Resources;
import org.geomtrybash.objects.Block;
import org.geomtrybash.objects.Circle;
import org.geomtrybash.objects.Player;
import org.geomtrybash.objects.Triangle;

public class Main implements Runnable {
	
	private static boolean running;
	private Graphics g;
	
	private KeyInput keyInput = new KeyInput();
	
	public static Handler handler = new Handler();
	
	public static final int WIDTH = 800, HEIGHT = 608; //25 x 19
	
	private JFrame frame;
	
	public static boolean debugMode = false;

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		initialize();
	}
	
	public static void stop() {
		running = false;
	}
	
	public void loadMap(Map map) {
		handler.clearAll();
		for (int i = 0; i < map.getItems().size(); i++) {
			handler.addObject(map.getItems().get(i));
		}
	}
	
	public void initialize() {
		running = true;
		
		try {
			
			//FileReader fileReader = new FileReader(Resources.getPath("resources/sprites/SpriteList.txt"));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/sprites/SpriteList.txt")));
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] information = line.split(" ### ");
				
				Image q = new ImageIcon(getClass().getResource("/" + information[1])).getImage();
				
				Resources.sprites.put(information[0], q);
			}
			
			bufferedReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/maps/MapList.txt")));
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] information = line.split(" ### ");
				
				BufferedReader br2 = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + information[1])));
				
				String line2;
				ArrayList<String> items = new ArrayList<String>();
				
				while ((line2 = br2.readLine()) != null) {
					items.add(line2);
				}
				
				String[] itemsArr = new String[items.size()];
				
				items.toArray(itemsArr);
				
				Resources.maps.put(information[0], new Map(itemsArr, 2, 1));
			}
			
			bufferedReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//handler.addObject(new Circle(100, 100, true));
		//handler.addObject(new Circle(100, 100, true));
		//handler.addObject(new Triangle(400, 400, false));
		//handler.addObject(new Block(300, 300));
		
		loadMap(Resources.maps.get("Basic"));
		
		frame = new JFrame("Geometry Bash");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.addKeyListener(keyInput);
		
		this.run();
	}
	
	private void update() {
		
		handler.update();
		keyInput.clear();
		
	}
	
	private void render() {
		
		BufferStrategy bs;
		
		if (frame.getBufferStrategy() == null) {
			frame.createBufferStrategy(3);
		}
		bs = frame.getBufferStrategy();
		
		g = bs.getDrawGraphics();
	
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		bs.show();
	}

	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		
		while (running) {
			long currentTime = System.nanoTime();
			if (currentTime - lastTime >= 1000000000/60) {
				lastTime = currentTime;
				update();
				render();
			}
		}
	}

}
