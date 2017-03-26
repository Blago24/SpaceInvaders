package com.vratsasoftware.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ship {
	private Vector2 position;
	private static final int SPEED = 10;
	
	private Texture ship;
	private float playerX = Gdx.graphics.getPpcX() / 2;
	private float playerY = Gdx.graphics.getPpcY() / 2;
	private final float SHIP_MOVEMENT_SPEED = 5.0f;
	private final int LEFT_SCREEN_X_BOUND = 5;
	private final int RIGHT_SCREEN_X_BOUND = 535;
	
	public Ship() {
		position = new Vector2(playerX,playerY);
		ship = new Texture("images//spaceship.jpg");
	}
	
	public void update(float delta) { 
		keepShipInBounds();
		moveShip();
	}
	
	private void moveShip() {

		if (checkForDirection() == 1) {
			moveLeft();
		} else if (checkForDirection() == -1) {
			moveRight();
		}
	}

	private int checkForDirection() {
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)
				|| Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			return -1;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)
				|| Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			return 1;
		}
		
		return 0;
		
	}
	
	private void keepShipInBounds() {
		if (playerX <= LEFT_SCREEN_X_BOUND) {
			playerX = LEFT_SCREEN_X_BOUND;
		}

		if (playerX >= RIGHT_SCREEN_X_BOUND) {
			playerX = RIGHT_SCREEN_X_BOUND;
		}
	}


	private void moveLeft() {
		playerX -= SHIP_MOVEMENT_SPEED;
	}

	private void moveRight() {
		playerX += SHIP_MOVEMENT_SPEED;
	}
	
	public Texture getShipTexture() { 
		return this.ship;
	}
	
	public float getPlayerX() { 
		return playerX;
	}
	

	public float getPlayerY() { 
		return playerY;
	}
}
