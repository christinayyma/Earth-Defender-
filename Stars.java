package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Stars {

	Random r = new Random();
	
	public void render(Graphics g){
		g.setColor(Color.white);
		for(int i = 0; i <= 3; i++){//stars
			g.fillRect(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 1, 1); 
		}
	}
}
