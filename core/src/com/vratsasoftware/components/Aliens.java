package com.vratsasoftware.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Aliens {
	
	private final int AMOUNT = 55;
	private Vector2 aliensPosition;
	public int[][] aliens;
	private Texture alien;
	public float alienX;
	public float alienY;
	
	
	
	public Aliens(float alienX, float alienY) { 
		this.alienX = 10;
		this.alienY = 10;
		aliens = new int[11][5];
		alien = new Texture("images//aliens.png");
		
		
	}

	protected void createNewAliens() {
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				aliens[i][j] = 1;
			}
		}
	}
	
	public void update(float delta)  {
		
	}
	public Vector2 getAliensPosition() {
		return aliensPosition;
	}

	public void setAliensPosition(Vector2 aliensPosition) {
		this.aliensPosition = aliensPosition;
	}

	public int[][] getAliens() {
		return aliens;
	}

	public void setAliens(int[][] aliens) {
		this.aliens = aliens;
	}

	public Texture getAlien() {
		return alien;
	}

	public void setAlien(Texture alien) {
		this.alien = alien;
	}

	public int getAMOUNT() {
		return AMOUNT;
	}

	public float getAlienX() {
		return alienX;
	}

	public float getAlienY() {
		return alienY;
	}

	public void setAlienX(float alienX) {
		this.alienX = alienX;
	}

	public void setAlienY(float alienY) {
		this.alienY = alienY;
	}
	
}
