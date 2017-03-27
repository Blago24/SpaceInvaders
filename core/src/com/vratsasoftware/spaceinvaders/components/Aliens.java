package com.vratsasoftware.spaceinvaders.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Aliens {

	private final int ALIENS_PER_ROW = 11;
	private final int ALIENS_ROWS = 5;
	
	public Aliens[][] aliens;
	public float alienX;
	public float alienY;
	
	
	private Vector2 aliensPosition;
	private Texture alien;
	private int[][] aliensValue; 
	private int[][] aliensCoordinatesX;
	private int[][] aliensCoordinatesY;
	

	public Aliens() {
		aliens = new Aliens[ALIENS_ROWS][ALIENS_PER_ROW];
		aliensValue = new int[ALIENS_ROWS][ALIENS_PER_ROW];
		aliensCoordinatesX = new int[ALIENS_ROWS][ALIENS_PER_ROW];
		aliensCoordinatesY = new int[ALIENS_ROWS][ALIENS_PER_ROW];
		alien = new Texture("images//alien.png");
	}

	//TODO Try adding an animation to the alien 
	
	protected void showAliens(SpriteBatch batch) {
		int aliensX = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() - 50);
		int aliensXHolder = aliensX;
		int aliensY = Gdx.graphics.getHeight() - 100;
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				//check if the alien is alive
				if (isAlienAlive(i, j)) {
					batch.draw(aliens[i][j].getAlien(), aliensX, aliensY, 35, 35);
					//keep the X and Y coordinates for each alien 
					// would probably use it to detect collision
					aliensCoordinatesX[i][j] = aliensX;
					aliensCoordinatesY[i][j] = aliensY;
				}
				aliensX += 45;
			}
			aliensX = aliensXHolder;
			aliensY -= 50;
		}
	}
	

	private boolean isAlienAlive(int i, int j) {
		return aliensValue[i][j] == 1;
	}

	protected void createNewAliens() {
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				aliens[i][j] = new Aliens();
				/* 
				 * set the default value of the alien to 1
				 * if changed to a different number (e.g. 0) 
				 * the alien won't be displayed on the screen. 
				 */
				aliensValue[i][j] = 1; 
				//test to see if working
			}
		}
	}

	public void update(float delta) {

	}

	public Vector2 getAliensPosition() {
		return aliensPosition;
	}

	public void setAliensPosition(Vector2 aliensPosition) {
		this.aliensPosition = aliensPosition;
	}

	public Aliens[][] getAliens() {
		return aliens;
	}

	public Texture getAlien() {
		return alien;
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
	
	public void setAlien(Texture texture) {
		this.alien = texture;
	}

}
