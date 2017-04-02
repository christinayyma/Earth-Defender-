package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;

public class KeyInput1 extends KeyAdapter{
	
	private Mission1Handler handler1;
	Game game;
	
	public KeyInput1(Mission1Handler handler1, Game game){
		this.handler1 = handler1;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		
		if(Game.gameState == STATE.Mission1) {
			int key = e.getKeyCode();
			GameObject player = handler1.findPlayer();
			
			for(int i = 0; i < handler1.object1.size(); i++){
				GameObject tempObject = handler1.object1.get(i);
				
				if(tempObject.getID() == ID.Player){
					if(key == KeyEvent.VK_LEFT)  tempObject.setVelX(-7);
					if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(7);
				}
			}
			
			if(Game.laserTimer == 0){
				if(key == KeyEvent.VK_SPACE){
					handler1.addObject(new LaserBeam(player.getX() + 32, player.getY(), ID.LaserBeam));
					AudioPlayer.getSound("laser_sound").play();
					Game.laserTimer = 35;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		
		if(Game.gameState == STATE.Mission1) {
			int key = e.getKeyCode();
			for(int i = 0; i < handler1.object1.size(); i++){
				GameObject tempObject = handler1.object1.get(i);
				
				if(tempObject.getID() == ID.Player){
					if(key !=  KeyEvent.VK_LEFT || key != KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				}
			}
		}
	}

}
