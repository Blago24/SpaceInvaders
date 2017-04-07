package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CollisionWithAliensShot extends ComponentsScreen {
	public CollisionWithAliensShot(Ship ship, int lives, ArrayList<Laser> aliensLasersShot, SpriteBatch batch) {
		this.ship = ship;
		this.aliensLasersShot = aliensLasersShot;
		this.lives = lives;
		this.batch = batch;
	}

	protected void checkForCollisionWithAliensShot() {

		int aliensLaserX = 0;
		int aliensLaserY = 0;

		boolean killed = false;

		if (aliensLasersShot.size() > 0) {
			for (int x = 0; x < aliensLasersShot.size(); x++) {

				aliensLaserX = checkTheLaserCoordinatesX(aliensLasersShot, batch, x);
				aliensLaserY = checkTheLaserCoordinatesY(aliensLasersShot, batch, x);
				int shipX = ship.getPlayerX();
				int shipY = ship.getPlayerY();
				// System.out.println("SHIPX" + shipX);
				// System.out.println("SHIPY" + shipY);
				for (int shipSize = 1; shipSize <= ship.getShipTexture().getHeight() / 100 + 10; shipSize++) {
					if ((aliensLaserY == (shipY - shipSize)) && (aliensLaserX >= shipX - 50)
							&& (aliensLaserX <= (shipX + 50))) {
						ship.lowerTheLives();
						//lives--;
						killed = true;
						if (aliensLasersShot.size() == 1) {
							aliensLasersShot.clear();
						} else {
							aliensLasersShot.remove(x);
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
