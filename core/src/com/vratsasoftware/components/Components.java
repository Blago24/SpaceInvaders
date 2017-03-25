package com.vratsasoftware.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Components implements Screen {
 int  count=0;
	Ship ship;
	Laser laser;
	SpriteBatch batch;
	ArrayList<Laser> lasersShot;
	private float currentPosition;
	Aliens alien; 

	@Override
	public void show() {
		ship = new Ship();
		batch = new SpriteBatch();
		laser = new Laser(currentPosition);
		lasersShot = new ArrayList<Laser>();
		alien = new Aliens(10, 10);
		alien.createNewAliens();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(ship.getShipTexture(), ship.getPlayerX(), ship.getPlayerY());
		shootNewBullet();
		displayLasersShot();
		
		ship.update(Gdx.graphics.getDeltaTime());
		batch.end();
	}
	//TODO see how to initialize and show all the aliens
	// TODO fix the laserX;
	private void initializeAliens() {
		for (int i = 0; i < alien.getAliens().length; i++) {
			for (int j = 0; j < alien.getAliens()[0].length; j++) {
				if(alien.getAliens()[i][j] == 1) { 
					batch.draw(alien.getAlien(), alien.getAlienX(), alien.getAlienY());
					alien.setAlienX(alien.getAlienX() + 10);
				}
				if (i % 11 == 0) {
					alien.setAlienY(alien.getAlienY() + 20);
				}
			}
		}
		
	}

	private void shootNewBullet() {
		if (laserShot()) {
			 currentPosition = ship.getPlayerX(); 
			lasersShot.add(new Laser(currentPosition));
			count++;
			System.out.println(count);
		}
	}

	private void displayLasersShot() {
		for (Laser laser : lasersShot) {
			batch.draw(laser.getLaser(),  laser.getLaserX() + 25, laser.getLaserY() + 65);
			if (!isBulletAlive()) {
				lasersShot.remove(0);
			}
			laser.update(Gdx.graphics.getDeltaTime() + 20);
		}
	}

	private boolean isBulletAlive() {
		if (laser.getLaserY() < Gdx.graphics.getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	// TODO - find a way to hardcode the playerX position;
	// TODO - separate code into methods; the drawing of the ship should be
	// applied only once;

	private boolean laserShot() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			return true;
		} else {
			return false;
		}
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
