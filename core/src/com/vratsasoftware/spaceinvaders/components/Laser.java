package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class Laser {
	SpaceInvaders spaceInvader = new SpaceInvaders();
	private final int LASER_MOVEMENT_SPEED = (int) Math.floor((int) spaceInvader.getHeight() / 88);

	private Vector2 position;
	private Texture laser;
	static long startTime;

	private int laserX;
	private int laserY;

	private Sound shootSound;

	public Laser(int currentPosition) {
		this.laserX = currentPosition;
		position = new Vector2(laserX, laserY);
		laser = new Texture("images//laser.png");
		shootSound = Gdx.audio.newSound(Gdx.files.local("assets//shoot.ogg"));

	}

	public Laser(int currentPosition, int y) {
		this.laserX = currentPosition;
		this.laserY = y;
		position = new Vector2(laserX, laserY);
		laser = new Texture("images//laser.png");

	}

	public void update(float delta) {
		this.laserY += LASER_MOVEMENT_SPEED;
	}

	public void updateForAliensLaser(float delta) {
		this.laserY -= LASER_MOVEMENT_SPEED;
	}

	protected void aliensNewLaser(ArrayList<Laser> aliensLasersShot, float currentAlienX, float currentAlienY) {
		aliensLasersShot.add(new Laser((int) currentAlienX,
				(int) ((int) currentAlienY - (float) Math.floor((float) spaceInvader.getHeight() / 100 * 8f))));

	}

	protected void displayAliensLasersShot(ArrayList<Laser> aliensLasersShot, SpriteBatch batch) {
		int index = 0;
		// we have to make this checks only if we have launched laser/s
		if (aliensLasersShot.size() > 0) {
			for (Laser alienLaser : aliensLasersShot) {
				batch.draw(alienLaser.getLaser(), alienLaser.getLaserX() + 20, alienLaser.getLaserY() + 60, 5, 20);

				System.out.println("AL" + alienLaser.getLaserY());
				if (aliensLasersShot.get(index).getLaserY() < -100) {

					resizeTheArrayList(aliensLasersShot);
					break;
				}
				index++;
				alienLaser.updateForAliensLaser(Gdx.graphics.getDeltaTime() + 20);
			}
		}

	}

	protected void shootNewLaser(ArrayList<Laser> lasersShot, float currentShipXPosition, Ship ship) {
		if (laserShot()) {
			if (lasersShot.size() < 3) {
				currentShipXPosition = ship.getPlayerX();
				lasersShot.add(new Laser((int) currentShipXPosition+25));
				shootSound.play();

			}
		}

	}

	protected boolean shootSuperLaser(ArrayList<Laser> lasersShot, float currentShipXPosition, Ship ship) {

		if (superlaserShot()) {

			
			int yPosition = LASER_MOVEMENT_SPEED;
			for (int i = 0; i < 5; i++) {
				lasersShot.add(new Laser((int) currentShipXPosition+25, yPosition));
				yPosition += 10;

			}

			return true;
		}
		return false;
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
			startTime = System.nanoTime();
			return true;
		} else {
			return false;
		}
	}

	protected boolean superlaserShot() {

		if (Gdx.input.isKeyJustPressed(Keys.DPAD_DOWN)) {
			// startTime = System.nanoTime();
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

	public int getLaserX() {
		return this.laserX;
	}

	public int getLaserY() {
		return this.laserY;
	}

}
