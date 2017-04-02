package com.game.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss){
		this.sprite = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = null;
		img = sprite.getSubimage((row * width) - width, (col * width) - width, width, height);
		return img;
	}
}
