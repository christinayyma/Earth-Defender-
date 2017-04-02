package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Target extends GameObject{

	private BufferedImage target_image;
	private BufferedImage target_image_broken;
	
	public Target(float x, float y, ID id){
		super(x, y, id);
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet1);
		target_image = ss.grabImage(1, 2, 64, 64);
		target_image_broken = ss.grabImage(2, 2, 64, 64);
		velX = 3;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x + 64 >= Game.WIDTH || x <= 0) velX *= -1;	
	}
	
	public void render(Graphics g) {
		if(life == 2) {
			g.drawImage(target_image, (int)x, (int)y, null);
		} 
		else {
			g.drawImage(target_image_broken, (int)x, (int)y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}
}
