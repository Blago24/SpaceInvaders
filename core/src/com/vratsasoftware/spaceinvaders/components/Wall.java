package com.vratsasoftware.spaceinvaders.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class Wall {

	private final int WALL_WIDTH = 100;
	private final int WALL_HEIGHT = 50;
	private final int WALL_AMOUNT = 3;
	private final int WALL_Y = 150;


	private int[] values;
	private int[] numberOfHitsIntoWall;
	
	private float[] wallX;
	public Wall[] walls;
	
	private Texture wallNoHit;
	private Texture wallOneHit;
	private Texture wallTwoHits;
	private Texture wallThreeHits;
	private Texture wallFourHits;
	private	Texture wallFiveHits;
	private Texture wallSixHits;

	ComponentsScreen comp = new ComponentsScreen();
	
	public Wall() {
		walls = new Wall[3];
		values = new int[3];
		wallX = new float[3];
		numberOfHitsIntoWall = new int[3];

		this.wallNoHit = new Texture("images//bunker.png");
		this.wallOneHit = new Texture("images//bunker1.png");
		this.wallTwoHits = new Texture("images//bunker2.png");
		this.wallThreeHits = new Texture("images//bunker3.png");
		this.wallFourHits = new Texture("images//bunker4.png");
		this.wallFiveHits = new Texture("images//bunker5.png");
		this.wallSixHits = new Texture("images//bunker6.png");

	}

	public void createWalls() {
		float position = 100;
		for (int i = 0; i < walls.length; i++) {
			switch (i) {
			case 0:
				position = SpaceInvaders.SCREEN_WIDTH - (SpaceInvaders.SCREEN_WIDTH - 60);
				break;
			case 1:
				position = Gdx.graphics.getWidth() / 2 - wallNoHit.getWidth() / 2;
				break;
			case 2:
				position = SpaceInvaders.SCREEN_WIDTH - (wallNoHit.getWidth() + 25);
				break;

			}
			wallX[i] = position;
			values[i] = 1;
			numberOfHitsIntoWall[i] = 0;

		}
	}

	public void display(SpriteBatch batch) {

		for (int i = 0; i < values.length; i++) {
			if (!isWallDestroyed(i)) {
				int indexHit = checkHowManyTimesWallIsHit(i);
				Texture wall = getTexture(indexHit);
				batch.draw(wall, wallX[i], WALL_Y, WALL_WIDTH, WALL_HEIGHT);
			}

		}

	}

	public int getWALL_Y() {
		return WALL_Y;
	}

	protected float getWallX(int i) {
		return wallX[i];
	}

	protected void increaseTheNumberOfHits(int i) {
		numberOfHitsIntoWall[i]++;
	}

	protected int checkHowManyTimesWallIsHit(int i) {
		return numberOfHitsIntoWall[i];
	}

	private Texture getTexture(int index) {
		switch (index) {
		case 0:
			return this.wallNoHit;

		case 1:
			return this.wallOneHit;

		case 2:
			return this.wallTwoHits;

		case 3:
			return this.wallThreeHits;

		case 4:
			return this.wallFourHits;

		case 5:
			return this.wallFiveHits;

		case 6:
			return this.wallSixHits;

		}
		return null;
	}

	public void destroyWall(int index) {
		values[index] = 0;
		// setValue(index);
	}

	public boolean isWallDestroyed(int index) {
		if (getValue(index) == 0) {
			return true;
		}
		return false;
	}

	public float getValue(int index) {
		return values[index];
	}

	public void setValue(int value) {
		this.values = values;
	}

	public Texture getWallNoHit() {
		return wallNoHit;
	}
}
