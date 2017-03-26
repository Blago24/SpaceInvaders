package com.vratsasoftware.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Laser {
	private Vector2 position;
	private static final int SPEED = 10;
	
	private Texture laser;
	private Ship ship = new Ship();
	private float laserX;
	private float laserY;
	private final float LASER_MOVEMENT_SPEED = 7.5f;
	
	
	public Laser(float currentPosition) {
		laserX = currentPosition;
		laserY = ship.getPlayerY();
		position = new Vector2(laserX, laserY);
		laser = new Texture("images//laser.png");
	}
	
	public void update(float delta) { 
		this.laserY += LASER_MOVEMENT_SPEED;
	}
	
	protected void shootNewLaser(ArrayList<Laser> lasersShot, float currentShipXPosition, Ship ship) {
		if (laserShot()) {
			currentShipXPosition = ship.getPlayerX();
			lasersShot.add(new Laser(currentShipXPosition));
		}
	}

	protected void displayLasersShot(ArrayList<Laser> lasersShot, SpriteBatch batch) {
		for (Laser laser : lasersShot) {
			batch.draw(laser.getLaser(), laser.getLaserX() + 20, laser.getLaserY() + 60, 5, 20);
			laser.update(Gdx.graphics.getDeltaTime() + 20);
		}
	}
	
	private boolean laserShot() {

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			return true;
		} else {
			return false;
		}
	}

	public Vector2 getPosition() {
		return position;
	}

	public Texture getLaser() {
		return laser;
	}
	
	public float getLaserX() { 
		return this.laserX;
	}

	public float getLaserY() { 
		return this.laserY;
	}
	
}
