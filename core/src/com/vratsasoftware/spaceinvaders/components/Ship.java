package com.vratsasoftware.spaceinvaders.components;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import Screens.GameOverScreen;

public class Ship {

	private final float SHIP_MOVEMENT_SPEED = 5.0f;
	private final int RIGHT_SCREEN_X_BOUND = Gdx.graphics.getWidth() - 60;
	private final int LEFT_SCREEN_X_BOUND = 10;

	private Vector2 position;
	private Texture ship;
	private Texture shipLeft;
	private Texture bomb1;
	private Texture bomb2;
	private Texture bomb3;
	private Texture bomb4;
	private Texture bomb5;
	private Texture bomb6;

	public Texture getShipLeft() {
		return shipLeft;
	}

	public Texture getShipRight() {
		return shipRight;
	}

	private Texture shipRight;
	private Texture live;
	private int lives;

	// initialize initial values so that the ship would be centered;

	private int playerX = Gdx.graphics.getWidth() / 2 - 30;
	private int playerY = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() - 15);

	public Ship(Game game) {
		position = new Vector2(playerX, playerY);
		ship = new Texture("images//spaceShip.png");
		shipLeft = new Texture("images//spaceShip-left.png");
		shipRight = new Texture("images//spaceShip-right.png");
		bomb1 = new Texture("images//Bomb1.png");
		bomb2 = new Texture("images//Bomb2.png");
		bomb3 = new Texture("images//Bomb3.png");
		bomb4 = new Texture("images//Bomb4.png");
		bomb5 = new Texture("images//Bomb5.png");
		bomb6 = new Texture("images//Bomb6.png");
		live = new Texture("images//pixel_heart.png");
		lives = 3;
	}

	public void explosion(SpriteBatch batch, int i, float f) {

		Texture bomb = null;

		switch (i) {
		case 0:
			bomb = getBomb6();

			break;
		case 1:
			bomb = getBomb5();

			break;
		case 2:
			bomb = getBomb4();

			break;
		case 3:
			bomb = getBomb3();

			break;
		case 4:
			bomb = getBomb2();

			break;
		case 5:
			bomb = getBomb1();

			break;
		default:
			bomb=getShipTexture();
			break;
		}
		batch.draw(bomb, getPlayerX(), getPlayerY(), 100, 100);

	}

	public Texture getBomb1() {
		return bomb1;
	}

	public Texture getBomb2() {
		return bomb2;
	}

	public Texture getBomb3() {
		return bomb3;
	}

	public Texture getBomb4() {
		return bomb4;
	}

	public Texture getBomb5() {
		return bomb5;
	}

	public Texture getBomb6() {
		return bomb6;
	}

	public void lowerTheLives() {
		setLives(getLives() - 1);
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void update(float delta, SpriteBatch batch) {

		keepShipInBounds();
		if (!moveShip(batch)) {
			drawShip(batch);
		}

	}

	protected void shipFlashing(SpriteBatch batch) {

	}

	protected void drawShip(SpriteBatch batch) {
		batch.draw(getShipTexture(), getPlayerX(), getPlayerY(), 100, 100);
	}

	protected boolean chechIfLose() {
		if (getLives() == 0) {
			return true;
		}
		return false;
	}

	protected void drawLives(SpriteBatch batch) {
		if (getLives() > 0) {
			int distance = 0;
			for (int i = 0; i < getLives(); i++) {
				batch.draw(getLiveTexture(), Gdx.graphics.getWidth() - 200 - distance, Gdx.graphics.getHeight() - 50,
						50, 50);
				distance -= 50;
			}
		}

	}

	private boolean moveShip(SpriteBatch batch) {

		if (checkForDirection() == 1) {
			batch.draw(getShipLeft(), getPlayerX(), getPlayerY(), 100, 100);

			moveLeft();
			return true;
		} else if (checkForDirection() == -1) {
			batch.draw(getShipRight(), getPlayerX(), getPlayerY(), 100, 100);
			moveRight();
			return true;
		}
		return false;
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

	public Texture getLiveTexture() {
		return live;
	}

}
