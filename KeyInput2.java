package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;

public class KeyInput2 extends KeyAdapter{
	
	private Mission2Handler handler2;
	Game game;
	
	public KeyInput2(Mission2Handler handler2, Game game){
		this.handler2 = handler2;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		if(Game.gameState == STATE.Mission2) {
			int key = e.getKeyCode();
			GameObject player = handler2.findPlayer();
			
			for(int i = 0; i < handler2.object2.size(); i++){
				GameObject tempObject = handler2.object2.get(i);
				
				if(tempObject.getID() == ID.Player){
					if(key == KeyEvent.VK_LEFT)  tempObject.setVelX(-7);
					if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(7);
				}
			}
			
			if(Game.laserTimer == 0){
				if(key == KeyEvent.VK_SPACE){
					handler2.addObject(new LaserBeam(player.getX() + 32, player.getY(), ID.LaserBeam));
					AudioPlayer.getSound("laser_sound").play();
					Game.laserTimer = 8;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(Game.gameState == STATE.Mission2) {
			int key = e.getKeyCode();
			for(int i = 0; i < handler2.object2.size(); i++){
				GameObject tempObject = handler2.object2.get(i);
				
				if(tempObject.getID() == ID.Player){
					if(key !=  KeyEvent.VK_LEFT || key != KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				}
			}
		}
	}

}
