package com.vratsasoftware.spaceinvaders.components;

import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ship {

	private final float SHIP_MOVEMENT_SPEED = 5.0f;
	private final int RIGHT_SCREEN_X_BOUND = Gdx.graphics.getWidth() - 60;
	private final int LEFT_SCREEN_X_BOUND = 10;

	private Vector2 position;
	private Texture ship;

	//initialize initial values so that the ship would be centered; 
	
	private int playerX = Gdx.graphics.getWidth() / 2 - 30;
	private int playerY = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() - 15);

	public Ship() {
		position = new Vector2(playerX, playerY);
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
		if (this.playerX <= LEFT_SCREEN_X_BOUND) {
			this.playerX = LEFT_SCREEN_X_BOUND;
		}

		if (this.playerX >= RIGHT_SCREEN_X_BOUND) {
			this.playerX = RIGHT_SCREEN_X_BOUND;
		}
	}

	private void moveLeft() {
		this.playerX -= SHIP_MOVEMENT_SPEED;
	}

	private void moveRight() {
		this.playerX += SHIP_MOVEMENT_SPEED;
	}

	public Texture getShipTexture() {
		return this.ship;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}
}
