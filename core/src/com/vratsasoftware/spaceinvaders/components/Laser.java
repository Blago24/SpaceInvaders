package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Laser {

	private final float LASER_MOVEMENT_SPEED = 7.5f;

	private Vector2 position;
	private Texture laser;
	private Aliens alien = new Aliens();
	
	private float laserX;
	private float laserY;

	

	public Laser(float currentPosition) {
		this.laserX = currentPosition;
		
		position = new Vector2(laserX, laserY);
		laser = new Texture("images//laser.png");
		
	}

	public void update(float delta) {
		this.laserY += LASER_MOVEMENT_SPEED;
		System.out.println("fasfasA" + this.laserY);
	}


	protected void shootNewLaser(ArrayList<Laser> lasersShot, float currentShipXPosition, Ship ship) {
		if (laserShot()) {
			currentShipXPosition = ship.getPlayerX();
			lasersShot.add(new Laser(currentShipXPosition));

		}
	}

	protected void displayLasersShot(ArrayList<Laser> lasersShot, SpriteBatch batch) {
		int index = 0;
		// we have to make this checks only if we have launched laser/s
		if (lasersShot.size() > 0) {
			for (Laser laser : lasersShot) {

				batch.draw(laser.getLaser(), laser.getLaserX() + 20, laser.getLaserY() + 60, 5, 20);
				
				if (lasersShot.get(index).getLaserY() > Gdx.graphics.getHeight()) {
					// The height is more than the window height
					resizeTheArrayList(lasersShot);
					break;

				}
				index++;
				laser.update(Gdx.graphics.getDeltaTime() + 20);
			}
		}

	}

	private void resizeTheArrayList(ArrayList<Laser> lasersShot) {

		if (lasersShot.size() == 1) {
			// if we have only one laser, we only have to clear the whole
			// arrayList
			lasersShot.clear();
		} else {
			// but if we have more than 1 , (for example : 2 ,3 ,4 ), we have to
			// remove the first laser ,because it will
			// pass the bound first
			lasersShot.remove(0);
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

	// killAlien() {
	// alien.killAlien[0][2];
	// }

	public void checkForCollision(float alienX, float alienY) {

		for (int i = 0; i < alien.aliensCoordinatesX.length; i++) {
			for (int j = 0; j < alien.aliensCoordinatesX[0].length; j++) {
				if ((alien.getAliensCoordinatesX(i, j) == (this.laserX))) {
					
				}

			}
		}

		// compares X and Y of the laser
		// to the Aliens X and Y at [i][j]
		// return true if collided;

	}

	

}
