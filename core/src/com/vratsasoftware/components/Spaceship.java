package com.vratsasoftware.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Spaceship implements Screen {

	private SpriteBatch batch;
	private Sprite ship;

	private float playerX = Gdx.graphics.getPpcX() / 2;
	private float playerY = Gdx.graphics.getPpcY() / 2;
	private float shipMovementSpeed = 5.0f;
	private final int LEFT_SCREEN_X_BOUND = 5;
	private final int RIGHT_SCREEN_X_BOUND = 535;

	@Override
	public void show() {

		batch = new SpriteBatch();
		Texture splashTexture = new Texture("images/spaceship.jpg");
		ship = new Sprite(splashTexture, 60, 65);
		ship.setSize(65, 65);
		ship.setPosition(playerX + 250, playerY);

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveShip();
		batch.begin();
		keepShipInBounds();
		batch.draw(ship, playerX, playerY);
		batch.end();

	}

	private void keepShipInBounds() {
		if (playerX <= LEFT_SCREEN_X_BOUND) {
			playerX = LEFT_SCREEN_X_BOUND;
		}

		if (playerX >= RIGHT_SCREEN_X_BOUND) {
			playerX = RIGHT_SCREEN_X_BOUND;
		}
	}

	private void moveShip() {

		if (checkForDirection() == 1) {
			moveLeft();
			System.out.println(playerX);
		} else if (checkForDirection() == -1) {
			System.out.println(playerX);
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


	private void moveLeft() {
		playerX -= shipMovementSpeed;
	}

	private void moveRight() {
		playerX += shipMovementSpeed;
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
