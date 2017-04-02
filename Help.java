package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

public class Help extends MouseAdapter {

	Random r = new Random();
	
	public void render(Graphics g){
		g.setColor(Color.YELLOW);
		Font fnt1 = new Font("Stencil", 1, 50);
		Font fnt2 = new Font("Stencil", 1, 25);
		
		g.setFont(fnt1);
		g.drawString("Help", 250, 100);
		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Use left and right arrow keys to move", 25, 200);
		g.drawString("player. Press space to shoot.", 30, 230);
		g.drawString("Back", 100, 400); //g.drawRect(95, 380, 100, 25);
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if (my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 95, 380, 100, 25)){//Back to Menu
				Game.gameState = STATE.Menu;
				System.out.println("back to menu");
			}
		}
	}
	
}
