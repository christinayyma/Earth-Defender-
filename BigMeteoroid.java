package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BigMeteoroid extends GameObject{

	private BufferedImage big_meteoroid;

	public BigMeteoroid(float x, float y, ID id) {
		super(x, y, id);
		velX = 0;
		velY = 1;
				
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet3);
		big_meteoroid = ss.grabImage(1, 1, 96, 96); 
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x + 96 > Game.WIDTH || x <= 0) velX *= -1;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(big_meteoroid, (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x + 2, (int)y + 2, 92, 92);
	}

}
