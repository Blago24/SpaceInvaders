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

	public float[][] aliensCoordinatesX = new float[ALIENS_ROWS][ALIENS_PER_ROW];
	public float[][] aliensCoordinatesY = new float[ALIENS_ROWS][ALIENS_PER_ROW];

	private Vector2 aliensPosition;
	private Texture alien;
	private int[][] aliensValue;

	public Aliens() {
		aliens = new Aliens[ALIENS_ROWS][ALIENS_PER_ROW];
		aliensValue = new int[ALIENS_ROWS][ALIENS_PER_ROW];
		alien = new Texture("images//alien.png");

	}

	// TODO Try adding an animation to the alien

	protected void showAliens(SpriteBatch batch) {
		float aliensX = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() - 50);
		float aliensXHolder = aliensX;
		float aliensY = Gdx.graphics.getHeight() - 100;
		int index = 0;
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				// check if the alien is alive
				if (isAlienAlive(i, j)) {
					batch.draw(aliens[i][j].getAlien(), aliensX, aliensY, 35, 35);
					// keep the X and Y coordinates for each alien
					// would probably use it to detect collision
					aliensCoordinatesX[i][j] = aliensX;
					aliensCoordinatesY[i][j] = aliensY;
					//System.out.println("y"+i+j+" "+aliensY);
				}
				aliensX += 45;
			}
			aliensX = aliensXHolder;
			aliensY -= 50;
		}

		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {

			}
		}
	}

	protected boolean isAlienAlive(int i, int j) {
		return aliensValue[i][j] == 1;
	}

	

	public boolean isHit() {
		boolean isHit = false;

		return isHit;

	}

	protected void createNewAliens() {
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				aliens[i][j] = new Aliens();
				/*
				 * set the default value of the alien to 1 if changed to a
				 * different number (e.g. 0) the alien won't be displayed on the
				 * screen.
				 */
				aliensValue[i][j] = 1;
				// test to see if working
			}
		}
	}
	protected void killAlien(int x , int y){
		aliensValue[x][y] = 0;
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

	public float getAliensCoordinatesX(int x, int y) {
		for (int i = 0; i < this.aliensCoordinatesX.length; i++) {
			for (int j = 0; j < this.aliensCoordinatesX[0].length; j++) {
				if ((x == i) && (y == j)) {
					return this.aliensCoordinatesX[i][j];
				}
			}

		}
		return 1;
	}
	
	public float getAliensCoordinatesY(int x, int y) {
		for (int i = 0; i < this.aliensCoordinatesY.length; i++) {
			for (int j = 0; j < this.aliensCoordinatesY[0].length; j++) {
				if ((x == i) && (y == j)) {
					return this.aliensCoordinatesY[i][j];
				}
			}

		}
		return 1;
	}

	protected void showArray() {
		System.out.println("______________________________");
		for (int i = 0; i < aliensCoordinatesX.length; i++) {
			for (int j = 0; j < aliensCoordinatesX[0].length; j++) {
				if (i == 1 && j == 1) {
					System.out.println(aliensCoordinatesX[i][j]);
				}

			}

		}

		System.out.println("______________________________");
	}

	public float[][] getAliensCoordinatesY() {
		return aliensCoordinatesY;
	}

}
