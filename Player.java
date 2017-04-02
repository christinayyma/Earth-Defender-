package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	private BufferedImage player_image;
	
	public Player(float x, float y, ID id){
		super(x, y, id);
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet1);
		player_image = ss.grabImage(1, 1, 64, 64);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.limit(x, 0, Game.WIDTH - 38);
		y = Game.limit(y, 0, Game.HEIGHT - 60);
	}

	public void render(Graphics g) {
		g.drawImage(player_image, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x + 10, (int)y + 10, 44, 44);
	}
}
