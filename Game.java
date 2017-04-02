package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	int i = 0, m = 0, h = 0, gam = 0, s = 0;
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private Mission1Handler handler1;
	private Mission2Handler handler2;
	private InfoScreen infoScreen;
	private Menu menu;
	private Help help;
	private Stars stars;
	public static BufferedImage sprite_sheet1, sprite_sheet2, sprite_sheet3;
	private boolean running             = false;
	public static STATE gameState       = STATE.Menu;
	public static int laserTimer        = 35;
	public static int meteoroidHitCount = 0;
	public static int missionNumber     = 1;
	
	public Game(){
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet1 = loader.loadImage("/Earth_Defender_SS.png");
		sprite_sheet2 = loader.loadImage("/Menu_Earth_Defender_SS.png");
		sprite_sheet3 = loader.loadImage("/meteoroid_SS.png");
		
		handler1 = new Mission1Handler();
		handler2 = new Mission2Handler();
		menu = new Menu();
		infoScreen = new InfoScreen(handler1, handler2);
		help = new Help();
		
		stars = new Stars();
		this.addKeyListener(new KeyInput1(handler1, this));
		this.addKeyListener(new KeyInput2(handler2, this));
		this.addMouseListener(menu);
		this.addMouseListener(help);
		this.addMouseListener(infoScreen);
		
		AudioPlayer.load();
		
		new Window(WIDTH, HEIGHT, "Earth Defender", this);
		
	}
	
	public enum STATE{
		Menu,
		InfoScreen,
		Mission2,
		Help,
		Mission1,
	};
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while( delta >= 1 ){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frames = 0;
			}
		}
		stop();
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void tick(){
		if(gameState == STATE.Mission1) {
			if(laserTimer > 0) laserTimer--;
			handler1.tick();
		} 
		if(gameState == STATE.Mission2) {
			if(laserTimer > 0) laserTimer--;
			handler2.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		stars.render(g);
		
		if(gameState == STATE.Mission1){
			handler1.render(g);
		}
		else if(gameState == STATE.Mission2){
			handler2.render(g);
		}
		else if(gameState == STATE.Menu){
			menu.render(g);
		} 
		else if(gameState == STATE.Help){
			help.render(g);
		}
		else if(gameState == STATE.InfoScreen){
			infoScreen.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float limit(float var, float min, float max){
		if (var >= max) 
			return var = max;
		else if (var <= min) 
			return var = min;
		else 
			return var;
	}
	
	public static void main(String args[]){
		new Game();
	}
}


