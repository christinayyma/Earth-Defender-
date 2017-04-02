package com.game.main;

import java.awt.Graphics;
import java.util.ArrayList;

import com.game.main.Game.STATE;

public class Mission2Handler {

	ArrayList<GameObject> object2 = new ArrayList<GameObject>();
	
	public void tick(){
		
		if(!missionCompleted()){
			laserOffScreen();
			collisionWithBig();
			collisionWithSmall();
			
			if(MeteoroidOffScreen() || playerHit()){
				clearObjects();
				AudioPlayer.getSound("mission_failed").play();
				Game.missionNumber = 2;
				Game.gameState = STATE.InfoScreen;
			}
			
			for(int i = 0; i < object2.size(); i++){
				GameObject tempObject = object2.get(i);
		
				tempObject.tick();
			}
		}
		else {
			AudioPlayer.getSound("mission_completed").play();
			Game.missionNumber = 0;
			clearObjects();
			Game.gameState = STATE.InfoScreen;
		}
	}
	
	public void render(Graphics g){
		for (int i = 0; i < object2.size(); i++){ 
			GameObject tempObject = object2.get(i);
			
			try{
				tempObject.render(g);
			}
			catch(NullPointerException e){}
		}
	}
	
	public void addObject(GameObject object){
		this.object2.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object2.remove(object);
	}
	
	private void collisionWithBig(){
		int iteration = 0;
		for(int i = 0; i < object2.size() - iteration; i++){
			if(object2.get(i).getID() == ID.LaserBeam){
				
				for(int j = 0; j < object2.size() - iteration; j++){
					if(object2.get(j).getID() == ID.BigMeteoroid){
						if(object2.get(i).getBounds().intersects(object2.get(j).getBounds())){
							
							AudioPlayer.getSound("meteoroid_break").play();
							
							int bigMX1 = (int)object2.get(j).getX();
							int bigMX2 = (int)object2.get(j).getX() + 80;
							int bigMY = (int)object2.get(j).getY();
							
							bigMX1 = (int) Game.limit(bigMX1, 1, Game.WIDTH - 50);//TWEAK NEEDED!!
							bigMX2 = (int) Game.limit(bigMX2, 1, Game.WIDTH - 50);//TWEAK NEEDED!!
							
							removeObject(object2.get(i));
							removeObject(object2.get(j));
							addObject(new SmallMeteoroid(bigMX1, bigMY,ID.SmallMeteoroid));
							addObject(new SmallMeteoroid(bigMX2, bigMY,ID.SmallMeteoroid));		

							iteration++;
							break;
						}
					}
				}
			}
		}
	}
	
	private void collisionWithSmall(){
		int iteration = 0;
		for(int i = 0; i < object2.size() - iteration; i++){
			if(object2.get(i).getID() == ID.LaserBeam){
				
				for(int j = 0; j < object2.size() - iteration; j++){
					if(object2.get(j).getID() == ID.SmallMeteoroid){
						if(object2.get(i).getBounds().intersects(object2.get(j).getBounds())){
							
							AudioPlayer.getSound("meteoroid_break").play();
							
							removeObject(object2.get(i));
							removeObject(object2.get(j));
							
							iteration++;
							break;
						}
					}
				}
			}
		}
	}
	
	private boolean playerHit(){
		boolean playerHit = false;
		GameObject player = findPlayer();
		
		for(int i = 0; i < object2.size(); i++){
			if(object2.get(i).getID() == ID.BigMeteoroid || object2.get(i).getID() == ID.SmallMeteoroid){
				if(object2.get(i).getBounds().intersects(player.getBounds()))
					//AudioPlayer.getSound("meteoroid_break").play();
					playerHit = true;
			}
		}
		
		return playerHit;
	}
	
	private boolean MeteoroidOffScreen(){
		boolean offScreen = false;
		for(int i = 0; i < object2.size(); i++){
			GameObject tempObject = object2.get(i);
			
			if(tempObject.getY() > Game.HEIGHT){
				offScreen = true;
			}
		}
		return offScreen;
	}
	
	private boolean missionCompleted(){
		if(object2.size() > 1) return false;
		else return true;
	}
	
	private void clearObjects(){
		while(object2.size() != 0){
			object2.remove(object2.get(0));
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
		for(int i = 0; i < object2.size(); i++){
			GameObject tempObject = object2.get(i);
			if(tempObject.getID() == ID.Player){
				player = tempObject;
			}
		}
		return player;
	}
	
	public GameObject findLaser(){
		GameObject laser = null;
		for(int i = 0; i < object2.size(); i++){
			GameObject tempObject = object2.get(i);
			if(tempObject.getID() == ID.LaserBeam){
				laser = tempObject;
			}
		}
		return laser;
	}
	
}

