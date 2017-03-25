package com.vratsasoftware.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Laser {
	private Vector2 position;
	private static final int SPEED = 10;
	
	private Texture laser;
	private Ship ship = new Ship();
	private float laserX;
	private float laserY;
	private final float LASER_MOVEMENT_SPEED = 7.5f;
	
	public Laser() {
		laserX = ship.getPlayerX();
		laserY = ship.getPlayerY();
		position = new Vector2(laserX, laserY);
		laser = new Texture("images//laser.png");
	}
	//extract the update from the render 
	// in the update, so that it would be init only once
	public void update(float delta) { 
		laserY += LASER_MOVEMENT_SPEED;
		System.out.println(laserY);
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
	
	public void setLaserY(float laserY) { 
		this.laserY = laserY;
		
	}
}
