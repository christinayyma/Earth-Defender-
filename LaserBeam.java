package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class LaserBeam extends GameObject{
	
	
	public LaserBeam(float x, float y, ID id){
		super(x, y, id);
		
		velY = -7;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 2, 30);
	}
	
	public void collision(){
	 
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 2, 30);
	}

}
