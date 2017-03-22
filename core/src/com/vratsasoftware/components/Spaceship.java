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

	private float xPosition = Gdx.graphics.getPpcX() / 2;
	private float yPosition = Gdx.graphics.getPpcY() / 2;
	private float shipMovementSpeed = 5.0f;
	private final int LEFT_SCREEN_X_BOUND = 5;
	private final int RIGHT_SCREEN_X_BOUND = 535;

	@Override
	public void show() {

		batch = new SpriteBatch();
		Texture splashTexture = new Texture("img/spaceship.jpg");
		ship = new Sprite(splashTexture, 60, 65);
		ship.setSize(65, 65);
		ship.setPosition(xPosition + 250, yPosition);

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveShip();
		batch.begin();
		keepShipInBounds();
		batch.draw(ship, xPosition, yPosition);
		batch.end();

	}

	private void keepShipInBounds() {
		if (xPosition <= LEFT_SCREEN_X_BOUND) {
			xPosition = LEFT_SCREEN_X_BOUND;
		}

		if (xPosition >= RIGHT_SCREEN_X_BOUND) {
			xPosition = RIGHT_SCREEN_X_BOUND;
		}
	}

	private void moveShip() {

		if (checkForDirection() == 1) {
			moveLeft();
		} else if (checkForDirection() == -1) {
			moveRight();
		}
	}

	private void moveRight() {
		xPosition += shipMovementSpeed;
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
		xPosition -= shipMovementSpeed;
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
