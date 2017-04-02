package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private BufferedImage main_image;
	Random r = new Random();
	
	public Menu(){
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet2);
		main_image = ss.grabImage(1, 1, 128, 128);
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu){
			if(mouseOver(mx, my, 198, 270, 230, 35)){//Start Game
				Game.gameState = STATE.InfoScreen;
			}
			if(mouseOver(mx, my, 260, 320, 100, 35)){//Help
				Game.gameState = STATE.Help;
			}
			if(mouseOver(mx, my, 210, 370, 200, 35)){//Quit Game
				System.exit(0);
			}
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if (my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		g.setColor(Color.yellow);
		Font fnt1 = new Font("Stencil", 1, 50);
		Font fnt2 = new Font("Stencil", 1, 35);
 		
		g.setFont(fnt1);
		g.drawString("EARTH DEFENDER", 80, 100);
		
		g.drawImage(main_image, 250, 130, null);
		
		g.setColor(Color.white);
		g.setFont(fnt2);
		g.drawString("Start Game", 198, 300); //g.drawRect(198, 270, 230, 35);
		g.drawString("Help", 260, 350); //g.drawRect(260, 320, 100, 35);
		g.drawString("Quit Game", 210, 400); //g.drawRect(210, 370, 200, 35);
	}
	
}