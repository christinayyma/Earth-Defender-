package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.main.Game.STATE;

public class InfoScreen extends MouseAdapter {

	private Mission1Handler handler1;
	private Mission2Handler handler2;
	
	public InfoScreen(Mission1Handler handler1, Mission2Handler handler2){
		this.handler1 = handler1;
		this.handler2 = handler2;
	}
	
	public void render(Graphics g){
		
		if(Game.missionNumber == 1){
			//Display instructions for mission 1
			g.setColor(Color.white);
			Font fnt1 = new Font("Stencil", 1, 50);
			Font fnt2 = new Font("Stencil", 1, 25);
			g.setFont(fnt1);
			g.drawString("Mission 1", 100, 100);
			g.setFont(fnt2);
		    g.drawString("Hit all the Targets.", 100, 170);
		    
		    g.setColor(Color.yellow);
		    g.drawString("Back to Menu", 400, 400); //g.drawRect(395, 380, 200, 25);
			g.drawString("Continue", 100, 400); //g.drawRect(95, 380, 140, 25);
		}
		else if(Game.missionNumber == 2){
			//Display instructions for mission 2
			g.setColor(Color.white);
			Font fnt1 = new Font("Stencil", 1, 50);
			Font fnt2 = new Font("Stencil", 1, 25);
			g.setFont(fnt1);
			g.drawString("Mission 2", 100, 100);
			g.setFont(fnt2);
		    g.drawString("There is a group of meteoroids", 50, 170);
		    g.drawString("heading towards Earth! Destroy them", 50, 210);
		    g.drawString("before they have a chance to enter", 50, 250);
		    g.drawString("the Earth's atmosphere!", 50, 290);
		    
		    g.setColor(Color.yellow);
		    g.drawString("Back to Menu", 400, 400); //g.drawRect(395, 380, 200, 25);
			g.drawString("Continue", 100, 400); //g.drawRect(95, 380, 140, 25);
			
			g.setColor(Color.red);
			g.drawString("Be careful not to get hit!", 50, 325);
		}
		else if(Game.missionNumber == 0){
			Game.gameState = STATE.Menu;
			Game.missionNumber = 1;
		}
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.InfoScreen) {
			
			if(mouseOver(mx, my, 95, 380, 140, 25)) {//Continue Button
				if(Game.missionNumber == 1) {
					
					handler1.addObject(new Player(290, 380, ID.Player));
					handler1.addObject(new Target(500, 20, ID.Target));
					handler1.addObject(new Target(20, 90, ID.Target));
					handler1.addObject(new Target(300, 160, ID.Target));
					handler1.addObject(new Target(150, 230, ID.Target));
					handler1.addObject(new Target(400, 300, ID.Target));
					
					Game.gameState = STATE.Mission1;
					Game.missionNumber = 2;
				}
				else if(Game.missionNumber == 2) {
					
					handler2.addObject(new Player(290, 380, ID.Player));
					handler2.addObject(new BigMeteoroid(50, -100, ID.BigMeteoroid));
					handler2.addObject(new BigMeteoroid(400, -200, ID.BigMeteoroid));
					handler2.addObject(new BigMeteoroid(200, -300, ID.BigMeteoroid));
					handler2.addObject(new BigMeteoroid(500, -400, ID.BigMeteoroid));
					
					Game.gameState = STATE.Mission2;
					Game.missionNumber = 0;
				}			
			}
			else if(mouseOver(mx, my, 395, 380, 200, 25)) {//Back to Menu
				Game.missionNumber = 0;
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
}
