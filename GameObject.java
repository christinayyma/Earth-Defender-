package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected int life = 2;
	protected float originalX, originalY;
	
	public GameObject(float x, float y, ID id){
		this.x = x;
		this.y = y;
		originalX = x;
		originalY = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setID(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public float getOriginalX(){
		return originalX;
	}
	public float getOriginalY(){
		return originalY;
	}
	public int getLife(){
		return life;
	}
	public void decrementLife(){
		life--;
	}
	public void setLife(int life){
		this.life = life;
	}	
}
