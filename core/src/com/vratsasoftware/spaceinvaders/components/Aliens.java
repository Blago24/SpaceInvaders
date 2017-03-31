package com.vratsasoftware.spaceinvaders.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class Aliens {

	private final int ALIENS_PER_ROW = 11;
	private final int ALIENS_ROWS = 5;

	public Aliens[][] aliens;
	public float alienX;
	public float alienY;

	private int aliensX;
	private int aliensY;
	private int aliensXHolder;

	public int[][] aliensCoordinatesX = new int[ALIENS_ROWS][ALIENS_PER_ROW];
	public int[][] aliensCoordinatesY = new int[ALIENS_ROWS][ALIENS_PER_ROW];

	private Vector2 aliensPosition;
	private Texture alien;
	private int[][] aliensValue;

	private int alienWidth = (int) ((int) SpaceInvaders.SCREEN_WIDTH * 0.065f);
	private int alienHeight = (int) ((int) SpaceInvaders.SCREEN_HEIGHT * 0.0475f);

	SpaceInvaders spaceInvader = new SpaceInvaders();
	Components component = new Components();

	public Aliens() {
		aliens = new Aliens[ALIENS_ROWS][ALIENS_PER_ROW];
		aliensValue = new int[ALIENS_ROWS][ALIENS_PER_ROW];
		alien = new Texture("images//alien.png");

	}

	// TODO Try adding an animation to the alien

	protected void showAliens(SpriteBatch batch) {

		float xDistance = (float) Math.floor((float) spaceInvader.getWidth() / 1000 * 2f);
		float yDistance = (float) Math.floor((float) spaceInvader.getHeight() / 100 * 4f);
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				// check if the alien is alive
				float leftBound = checkForFirstAlive();
				float rightBound = checkForLastAlive();

				if (isAlienAlive(i, j)) {
					batch.draw(aliens[i][j].getAlien(), aliensCoordinatesX[i][j], aliensCoordinatesY[i][j], alienWidth,
							alienHeight);
					// keep the X and Y coordinates for each alien
					// would probably use it to detect collision

					moveAliens(batch, xDistance, i, j, leftBound, rightBound);

				}

			}
		}

	}

	private void moveAliens(SpriteBatch batch, float xDistance, int i, int j, float leftBound, float rightBound) {
		if (component.areAliensGoingRight) {
			moveAliensRight(batch, xDistance, i, j, rightBound);
		} else {
			moveAliensLeft(batch, xDistance, i, j, leftBound);
		}
	}

	private void moveAliensLeft(SpriteBatch batch, float xDistance, int i, int j, float leftBound) {
		if (leftBound >= SpaceInvaders.SCREEN_WIDTH - (SpaceInvaders.SCREEN_WIDTH - 10)) {
			moveAliens(batch, i, j, -xDistance);
		} else {
			component.setAreAliensGoingRight(true);
		}
		// moveAliens(batch, i, j, -xDistance);
	}

	private void moveAliensRight(SpriteBatch batch, float xDistance, int i, int j, float rightBound) {
		if (rightBound <= SpaceInvaders.SCREEN_WIDTH - alien.getWidth() / 4) {
			// tuk e problema
			System.out.println("Right bound: " + rightBound);
			moveAliens(batch, i, j, xDistance);
			// Zakruglqva na chetno chislo
			// Bezkraen cikul - fixed 

		} else {
			component.setAreAliensGoingRight(false);
		}
	}

	private int checkForFirstAlive() {
		for (int i = 0; i < aliensCoordinatesX[0].length; i++) {
			for (int j = 0; j < aliens.length; j++) {
				if (aliensValue[j][i] == 1) {
					if (isAlienAlive(0, i)) {
						return aliensCoordinatesX[0][i];
					}
				}
			}
		}
		return 0;
	}

	private int checkForLastAlive() {
		for (int i = aliensCoordinatesX[0].length - 1; i >= 0; i--) {
			for (int j = 0; j < aliens.length; j++) {
				if (aliensValue[j][i] == 1) {
					if (isAlienAlive(0, i)) {
						return aliensCoordinatesX[0][i];
					}
				}
			}
		}
		return 0;
	}

	protected void moveAliens(SpriteBatch batch, int i, int j, float distance) {

		aliensCoordinatesX[i][j] += distance;

	}

	protected boolean isAlienAlive(int i, int j) {
		return aliensValue[i][j] == 1;
	}

	public boolean isHit() {
		boolean isHit = false;
		return isHit;
	}

	protected void showX() {
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				System.out.print(aliensCoordinatesX[i][j] + " ");

			}
			System.out.println();
		}
	}

	protected void testShow(SpriteBatch batch) {

		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				// check if the alien is alive
				if (isAlienAlive(i, j)) {
					batch.draw(aliens[i][j].getAlien(), aliensCoordinatesX[i][j], aliensCoordinatesY[i][j], alienWidth,
							alienHeight);

					System.out.println();

				}

			}

		}
	}

	protected void createNewAliens() {

		aliensX = (int) Math.floor((float) spaceInvader.getWidth() / 100);
		aliensXHolder = aliensX;
		aliensY = (int) Math.floor((float) spaceInvader.getHeight() / 10 * 7);
		float xDistance = (float) Math.floor((float) spaceInvader.getWidth() / 100 * 2.65f);
		float yDistance = (float) Math.floor((float) spaceInvader.getHeight() / 100 * 4f);
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				aliens[i][j] = new Aliens();
				aliensValue[i][j] = 1;
				aliensCoordinatesX[i][j] = aliensX;
				aliensCoordinatesY[i][j] = aliensY;
				aliensX += xDistance;
			}

			aliensX = aliensXHolder;
			aliensY -= yDistance;
		}

	}

	protected void killAlien(int x, int y) {
		aliensValue[x][y] = 0;
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

	public int getAliensCoordinatesX(int x, int y) {
		for (int i = 0; i < this.aliensCoordinatesX.length; i++) {
			for (int j = 0; j < this.aliensCoordinatesX[0].length; j++) {
				if ((x == i) && (y == j)) {
					return this.aliensCoordinatesX[i][j];
				}
			}

		}
		return 1;
	}

	public int getAliensCoordinatesY(int x, int y) {
		for (int i = 0; i < this.aliensCoordinatesY.length; i++) {
			for (int j = 0; j < this.aliensCoordinatesY[0].length; j++) {
				if ((x == i) && (y == j)) {
					return this.aliensCoordinatesY[i][j];
				}
			}

		}
		return 1;
	}

	public int[][] getAliensCoordinatesY() {
		return aliensCoordinatesY;
	}

}
