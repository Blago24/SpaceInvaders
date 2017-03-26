package com.vratsasoftware.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Components implements Screen {


	float currentShipXPosition;
	Ship ship;
	Laser laser;
	SpriteBatch batch;
	public ArrayList<Laser> lasersShot;
	Aliens alien;
	Wall wall;

	@Override
	public void show() {
		ship = new Ship();
		batch = new SpriteBatch();
		laser = new Laser(currentShipXPosition);
		lasersShot = new ArrayList<Laser>();
		alien = new Aliens();
		alien.createNewAliens();
		wall = new Wall();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(ship.getShipTexture(), ship.getPlayerX(), ship.getPlayerY(), 50, 50);
		laser.shootNewLaser(this.lasersShot, currentShipXPosition, this.ship);
		laser.displayLasersShot(this.lasersShot, this.batch);
		alien.showAliens(this.batch);
		wall.display(batch);
		ship.update(Gdx.graphics.getDeltaTime());
		batch.end();
	}


	// TODO see how to initialize and show all the aliens
	
	

	// private void RemoveLaserIfOutOfBounds(Laser laser) {
	// if (laser.getLaserY() >= Gdx.graphics.getBackBufferHeight()) {
	// laser.remove();
	// }
	// }
	//
	// private boolean isBulletAlive() {
	// System.out.println("Height: " + Gdx.graphics.getHeight());
	//
	// if (laser.getLaserY() < Gdx.graphics.getHeight()) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	

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
	

	public SpriteBatch getBatch() {
		return batch;
	}
}
