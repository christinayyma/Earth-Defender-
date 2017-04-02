package com.game.main;

import java.awt.Graphics;
import java.util.ArrayList;

import com.game.main.Game.STATE;

public class Mission1Handler {
	
	ArrayList<GameObject> object1 = new ArrayList<GameObject>();
	
	public void tick(){
		
		laserOffScreen();
		collision();
		if(missionCompleted()){
			AudioPlayer.getSound("mission_completed").play();
			clearObjects();
			Game.gameState = STATE.InfoScreen;
		}
		
		for(int i = 0; i < object1.size(); i++){
			GameObject tempObject = object1.get(i);
	
			tempObject.tick();
		}
	}
	
	private boolean missionCompleted(){
		if(object1.size() > 1) return false;
		else return true;
	}
	
	public void render(Graphics g){
		
		for (int i = 0; i < object1.size(); i++){ 
			try{
				object1.get(i).render(g);
			}
			catch(NullPointerException e){}
		}
	}
	
	public void addObject(GameObject object){
		this.object1.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object1.remove(object);
	}
	
	private void collision(){
		
		int iteration = 0;
		for(int i = 0; i < object1.size() - iteration; i++){
			if(object1.get(i).getID() == ID.LaserBeam){
				
				for(int j = 0; j < object1.size() - iteration; j++){
					if(object1.get(j).getID() == ID.Target){
						if(object1.get(i).getBounds().intersects(object1.get(j).getBounds())){
		
							object1.get(j).decrementLife();
							
							if(object1.get(j).getLife() == 0) {
								removeObject(object1.get(j));
							    removeObject(object1.get(i - 1));
							}
							else
								removeObject(object1.get(i));
			
							
							AudioPlayer.getSound("target_broken_sound").play();
							iteration++;
							break;
						}
					}
				}
			}
		}
	}
	
	private void clearObjects(){
		while(object1.size() != 0){
			object1.remove(object1.get(0));
		}
	}
	
	private void laserOffScreen(){
		GameObject laser = findLaser();
		
		if(laser != null) {
			if(laser.getY() + 30 < 0) { 
				removeObject(laser);
			}
		}
	}
	
	public GameObject findPlayer(){
		GameObject player = null;
		for(int i = 0; i < object1.size(); i++){
			GameObject tempObject = object1.get(i);
			if(tempObject.getID() == ID.Player){
				player = tempObject;
			}
		}
		return player;
	}
	
	public GameObject findLaser(){
		GameObject laser = null;
		for(int i = 0; i < object1.size(); i++){
			GameObject tempObject = object1.get(i);
			if(tempObject.getID() == ID.LaserBeam){
				laser = tempObject;
			}
		}
		return laser;
	}
	
}