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

	protected float checkTheLaserCoordinatesY(ArrayList<Laser> lasersShot, SpriteBatch batch, int x) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(x).getLaserY();
		}
		return 0;
	}

	protected float checkTheLaserCoordinatesX(ArrayList<Laser> lasersShot, SpriteBatch batch,int x) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(x).getLaserX();
		}
		return 0;

	}

	private boolean checkForCollision() {
		float laserX = 0;
		float laserY = 0;
//System.out.println(lasersShot.size());
		for (int x = 0; x < lasersShot.size(); x++) {
//System.out.println(x);
			laserX = checkTheLaserCoordinatesX(lasersShot, batch,x);
			laserY = checkTheLaserCoordinatesY(lasersShot, batch , x );
//System.out.println(x+" "+laserY 	);
			for (int i = 0; i < alien.aliensCoordinatesX.length; i++) {
				System.out.println(i);
				for (int j = 0; j < alien.aliensCoordinatesX[0].length; j++) {

					float alienX = alien.getAliensCoordinatesX(i, j);
					float alienY = alien.getAliensCoordinatesY(i, j);
System.out.println("a-"+(alienY - alien.getAlien().getHeight() + 120)+" y-"+laserY);
					if ((laserY == (alienY - alien.getAlien().getHeight() + 120)) && (laserX >= alienX - 20)
							&& (laserX <= (alienX + 20)) && alien.isAlienAlive(i, j)) {
						System.out.println("lY" + laserY + "aY" + alienY);
						System.out.println("hitva");
						alien.killAlien(i, j);
						if (lasersShot.size() == 1) {
							lasersShot.clear();
						} else {
							lasersShot.remove(x);
						}
						break;
					}

				}
			}
		}
		return false;

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

	public SpriteBatch getBatch() {
		return batch;
	}
}
