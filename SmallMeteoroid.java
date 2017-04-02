package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SmallMeteoroid extends GameObject{

	private BufferedImage small_meteoroid;
	Random r = new Random();
	
	public SmallMeteoroid(float x, float y, ID id) {
		super(x, y, id);
		
		velX = r.nextInt(6) - 3;
		velY = r.nextInt(2) + 1;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet3);
		small_meteoroid = ss.grabImage(3, 1, 48, 48);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x + 48 >= Game.WIDTH || x <= 0) velX *= -1;	
	}

	public void render(Graphics g) {
		g.drawImage(small_meteoroid, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x + 2, (int)y + 2, 44, 44);
	}

}
