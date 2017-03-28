package com.vratsasoftware.spaceinvaders.components;

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
	Background background;

	@Override
	public void show() {
		ship = new Ship();
		batch = new SpriteBatch();
		laser = new Laser(ship.getPlayerX());
		System.out.println(currentShipXPosition);
		lasersShot = new ArrayList<Laser>();
		alien = new Aliens();
		alien.createNewAliens();
		wall = new Wall();
		background = new Background();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.showBackground(batch);
		batch.draw(ship.getShipTexture(), ship.getPlayerX(), ship.getPlayerY(), 50, 50);
		laser.shootNewLaser(this.lasersShot, currentShipXPosition, this.ship);
		laser.displayLasersShot(this.lasersShot, this.batch);

		alien.showAliens(this.batch);
		wall.display(batch);
		currentShipXPosition = ship.getPlayerX();
		checkForCollision();
		ship.update(Gdx.graphics.getDeltaTime());
		// System.out.println("laszeras " + laser.getLaserY());
		batch.end();
	}

	protected float checkTheLaserCoordinatesY(ArrayList<Laser> lasersShot, SpriteBatch batch) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(0).getLaserY();
		}
		return 0;
	}

	protected float checkTheLaserCoordinatesX(ArrayList<Laser> lasersShot, SpriteBatch batch) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(0).getLaserX();
		}
		return 0;

	}

	private boolean checkForCollision() {

		float laserX = checkTheLaserCoordinatesX(lasersShot, batch);
		float laserY = checkTheLaserCoordinatesY(lasersShot, batch);
		// System.out.println("laserY2-"+laserY);
		// System.out.println(alien.aliensCoordinatesX[0][0]);
		for (int i = 0; i < alien.aliensCoordinatesX.length; i++) {
			for (int j = 0; j < alien.aliensCoordinatesX[0].length; j++) {

				float alienX = alien.getAliensCoordinatesX(i, j);
				float alienY = alien.getAliensCoordinatesY(i, j);
				// System.out.println("laserY==="+laserY);
				// if(laserY==300){
				// System.out.println("300");
				//
				// }
				// System.out.println("alienY==="+alienX);
//				if (laserY >= alienY + 15 && laserY >= alienY - 15) {
//					System.out.println("lY" + laserX + "aY" + alienY);
//					System.out.println("hidat");
//				}
				//we have to make so changes in this if
				//TODO change the IF
				if (laserY == alienY + 15 && laserY >= alienY - 15) {
					System.out.println("lY" + laserY + "aY" + alienY);
					System.out.println("hitva");
					alien.killAlien(i,j);
					
				}
				
			}
		}
		return false;

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
