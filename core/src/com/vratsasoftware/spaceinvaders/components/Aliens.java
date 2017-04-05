package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
	private Texture alienSizes;
	private Texture alien1;
	private Texture alien2;
	private Texture alien3;
	private Texture alien4;
	private Texture alien5;

	private Sound alienKilledSound;

	private int[][] aliensValue;

	private int alienWidth = (int) ((int) SpaceInvaders.SCREEN_WIDTH * 0.065f);
	private int alienHeight = (int) ((int) SpaceInvaders.SCREEN_HEIGHT * 0.0475f);

	int leftCol = 0;
	int rightCol = 10;

	SpaceInvaders spaceInvader = new SpaceInvaders();
	ComponentsScreen component = new ComponentsScreen(null);

	public Aliens() {
		aliens = new Aliens[ALIENS_ROWS][ALIENS_PER_ROW];
		aliensValue = new int[ALIENS_ROWS][ALIENS_PER_ROW];
		alienSizes = new Texture("images//alien.png");
		alien1 = new Texture("images//Alien1.png");
		alien2 = new Texture("images//Alien2.png");
		alien3 = new Texture("images//Alien3.png");
		alien4 = new Texture("images//Alien4.png");
		alien5 = new Texture("images//Alien5.png");
		alienKilledSound = Gdx.audio.newSound(Gdx.files.local("assets//invaderkilled.ogg"));
	}

	// TODO Try adding an animation to the alien

	public Texture getAlienSizes() {
		return alienSizes;
	}

	protected void showAliens(SpriteBatch batch) {

		float xDistance = (float) Math.floor((float) spaceInvader.getWidth() / 1000 * 1.2f);
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				// check if the alien is alive
				float leftBound = checkForFirstAlive();
				float rightBound = checkForLastAlive();

				if (isAlienAlive(i, j)) {
					batch.draw(aliens[i][j].getAlien(i), aliensCoordinatesX[i][j], aliensCoordinatesY[i][j], alienWidth,
							alienHeight);
					aliensMovement(batch, xDistance, i, j, leftBound, rightBound);

				}

			}
		}

	}

	private void aliensMovement(SpriteBatch batch, float xDistance, int i, int j, float leftBound, float rightBound) {
		if (component.areAliensGoingRight) {
			moveAliensRight(batch, xDistance, i, j, rightBound);
		} else {
			moveAliensLeft(batch, xDistance, i, j, leftBound);
		}
	}

	private void moveAliensLeft(SpriteBatch batch, float xDistance, int i, int j, float leftBound) {
		if (leftBound >= SpaceInvaders.SCREEN_WIDTH - (SpaceInvaders.SCREEN_WIDTH - 10)) {
			float distanceToTheEndOfTheScreen = SpaceInvaders.SCREEN_WIDTH - leftBound;
			moveAliens(batch, i, j, -xDistance, distanceToTheEndOfTheScreen);
			// System.out.println("1st Alien, 1st Row: " +
			// getAliensCoordinatesX(0, 0));
			// System.out.println("1st Alien, 2st Row: " +
			// getAliensCoordinatesX(1, 0));
			// System.out.println("1st Alien, 3st Row: " +
			// getAliensCoordinatesX(2, 0));
			// System.out.println("1st Alien, 4st Row: " +
			// getAliensCoordinatesX(3, 0));
		} else {
			component.setAreAliensGoingRight(true);
		}
		// moveAliens(batch, i, j, -xDistance);
	}

	private void moveAliensRight(SpriteBatch batch, float xDistance, int i, int j, float rightBound) {
		if (rightBound <= SpaceInvaders.SCREEN_WIDTH - alien1.getWidth() / 4) {
			float distanceToTheEndOfTheScreen = SpaceInvaders.SCREEN_WIDTH - rightBound;
			// System.out.println("Distance: " + distanceToTheEndOfTheScreen);
			moveAliens(batch, i, j, xDistance, distanceToTheEndOfTheScreen);

		} else {
			component.setAreAliensGoingRight(false);
		}
	}

	protected void moveAliens(SpriteBatch batch, int i, int j, float distance, float distanceToTheEndOfTheScreen) {

		int amountOfMovementDown = (int) 7.5f;
		aliensCoordinatesX[i][j] += distance;
		if ((distanceToTheEndOfTheScreen >= 49 && distanceToTheEndOfTheScreen <= 51)
				|| (distanceToTheEndOfTheScreen >= 789 && distanceToTheEndOfTheScreen <= 791)) {
			aliensCoordinatesY[i][j] -= amountOfMovementDown;
		}

	}

	protected void checkForLowestAliensAlive(ArrayList<Integer> xIndexesOfAliensWhichCanShoot,
			ArrayList<Integer> yIndexesOfAliensWhichCanShoot) {
		// System.out.println("X+++++"+aliensCoordinatesX[0].length);

		for (int i = 0; i < aliensCoordinatesX[0].length; i++) {
			for (int j = aliensCoordinatesX.length - 1; j >= 0; j--) {

				if (aliensValue[j][i] == 1) {

					xIndexesOfAliensWhichCanShoot.add(i);
					yIndexesOfAliensWhichCanShoot.add(j);
					System.out.println("X-" + i + "Y-" + j);

					break;
				}
			}
		}
	}

	private int checkForFirstAlive() {
		// showAliensValues();
		int index = leftCol;
		int countForDeadAliens = 0;
		for (int i = 0; i < aliensCoordinatesX.length; i++) {
			if (aliensValue[i][index] == 1) {
				return aliensCoordinatesX[i][index];
			} else {
				countForDeadAliens++;
			}
		}
		if (countForDeadAliens == 5) {
			leftCol = (index + 1);
			// checkForFirstAlive();
		}

		return 0;
	}

	private int checkForLastAlive() {
		// showAliensValues();
		int index = rightCol;
		int countForDeadAliens = 0;
		for (int i = 0; i < aliensCoordinatesX.length; i++) {
			if (aliensValue[i][index] == 1) {

				return aliensCoordinatesX[i][index];
			} else {
				countForDeadAliens++;
			}
		}
		if (countForDeadAliens == 5) {
			rightCol = (index - 1);
		}

		return 0;
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
					batch.draw(aliens[i][j].getAlien(i), aliensCoordinatesX[i][j], aliensCoordinatesY[i][j], alienWidth,
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

	public int getAliensCoordinatesX(int x, int y) {
		for (int i = 0; i < this.aliensCoordinatesX.length; i++) {
			for (int j = 0; j < this.aliensCoordinatesX[0].length; j++) {
				if ((x == i) && (y == j)) {
					// System.out.println("X-"+x+"Y-"+y+"
					// "+this.aliensCoordinatesX[i][j]);
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

	public void showAliensValues() {
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens.length; j++) {
				System.out.print(aliens[i][j]);

			}
			System.out.println();
		}
	}

	protected boolean checkForWin() {
		int countAliveAliens = 0;
		for (int i = 0; i < aliensValue.length; i++) {
			for (int j = 0; j < aliensValue[0].length; j++) {
				if (isAlienAlive(i, j)) {
					countAliveAliens++;
				}
			}
		}
		if (countAliveAliens == 0) {
			return true;
		}
		return false;
	}

	protected void killAlien(int x, int y) {

		alienKilledSound.play();
		aliensValue[x][y] = 0;
	}

	public Vector2 getAliensPosition() {
		return aliensPosition;
	}

	public Aliens[][] getAliens() {
		return aliens;
	}

	public Texture getAlien(int index) {
		switch (index) {
		case 0:
			return getAlienOne();
		case 1:
			return getAlienTwo();
		case 2:
			return getAlienThree();
		case 3:
			return getAlienFour();
		case 4:
			return getAlienFive();

		}
		return alien1;
		
	}

	public Texture getAlienOne() {
		return alien1;
	}

	public Texture getAlienTwo() {
		return alien2;
	}

	public Texture getAlienThree() {
		return alien3;
	}

	public Texture getAlienFour() {
		return alien4;
	}

	public Texture getAlienFive() {
		return alien5;
	}

	public float getAlienX() {
		return alienX;
	}

	public float getAlienY() {
		return alienY;
	}

}