package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CollisionWithAliens extends ComponentsScreen {
	public CollisionWithAliens(Aliens alien ,ArrayList<Laser> lasersShot,int playerPoints, int aliensKilled , SpriteBatch batch){
		this.alien=alien;
		this.lasersShot=lasersShot;
		this.playerPoints=playerPoints;
		this.aliensKilled=aliensKilled;
		this.batch=batch;
	}
	protected void checkForCollision() {
		int laserX = 0;
		int laserY = 0;

		boolean killed = false;
		if (lasersShot.size() > 0) {
			for (int x = 0; x < lasersShot.size(); x++) {

				laserX = checkTheLaserCoordinatesX(lasersShot, batch, x);
				laserY = checkTheLaserCoordinatesY(lasersShot, batch, x);

				for (int i = 0; i < alien.aliensCoordinatesX.length; i++) {
					for (int j = 0; j < alien.aliensCoordinatesX[0].length; j++) {
						int alienX = alien.getAliensCoordinatesX(i, j);
						int alienY = alien.getAliensCoordinatesY(i, j);

						for (int alienSize = 5; alienSize <= alien.getAlienSizes().getHeight() / 10; alienSize++) {
							if ((laserY == (alienY - alien.getAlienSizes().getHeight() + 100 + alienSize))
									&& (laserX >= alienX - 30) && (laserX <= (alienX + 30))
									&& alien.isAlienAlive(i, j)) {
								alien.killAlien(i, j);
								playerPoints += 20;
								aliensKilled++;
								killed = true;
								if (lasersShot.size() == 1) {
									lasersShot.clear();
								} else {
									lasersShot.remove(x);
								}
							}
						}
						if (killed) {
							killed = false;
							break;
						}
					}
				}
			}
		}
		

	}

}
