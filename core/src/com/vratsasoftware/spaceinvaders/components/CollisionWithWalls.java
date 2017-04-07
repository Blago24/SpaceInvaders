package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CollisionWithWalls extends ComponentsScreen{
	public CollisionWithWalls(Wall wall ,ArrayList<Laser> aliensLasersShot  ,ArrayList<Laser> lasersShot,SpriteBatch batch){
		this.wall=wall;
		this.aliensLasersShot=aliensLasersShot;
		this.lasersShot=lasersShot;
		this.batch=batch;
	}
	protected void checkForCollisionWithTheWalls() {
		
		int aliensLaserX = 0;
		int aliensLaserY = 0;
		int laserX = 0;
		int laserY = 0;
		boolean killed = false;
		int wallY = wall.getWALL_Y();
		for (int i = 0; i < wall.walls.length; i++) {

			if (aliensLasersShot.size() > 0) {
				for (int x = 0; x < aliensLasersShot.size(); x++) {

					aliensLaserX = checkTheLaserCoordinatesX(aliensLasersShot, batch, x);
					aliensLaserY = checkTheLaserCoordinatesY(aliensLasersShot, batch, x);
					int wallX = (int) wall.getWallX(i);

//					System.out.println("wallX" + wallX);
//					System.out.println("jitsss" + wall.checkHowManyTimesWallIsHit(i));

					// System.out.println("SHIPY" + wallY);
					for (int wallSize = 1; wallSize <= wall.getWallNoHit().getHeight() / 100 + 10; wallSize++) {
						if (aliensLaserY == (wallY + wallSize) && (aliensLaserX >= wallX - 70)
								&& (aliensLaserX <= (wallX + 70)) && !wall.isWallDestroyed(i)) {
							// TODO wall.isWallDestroyed(0) have to be different
							wall.increaseTheNumberOfHits(i);
							//System.out.println("jitsss" + i + wall.checkHowManyTimesWallIsHit(i));
							if (wall.checkHowManyTimesWallIsHit(i) > 6) {
								wall.destroyWall(i);
								break;
							}
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
			if (lasersShot.size() > 0) {
				for (int x = 0; x < lasersShot.size(); x++) {
					int wallX = (int) wall.getWallX(i);

					laserX = checkTheLaserCoordinatesX(lasersShot, batch, x);
					laserY = checkTheLaserCoordinatesY(lasersShot, batch, x);
					System.out.println("l" + laserY);
					System.out.println("w" + wallY);
					System.out.println("HEI" + wall.getWallNoHit().getHeight() / 100);
					for (int wallSize = 1; wallSize <= wall.getWallNoHit().getHeight() / 100 + 10; wallSize++) {
						System.out.println("laserY" + laserY + "  wall" + ((wallY + wallSize)));
						System.out.println("X" + laserX);
						if ((laserY == (wallY + wallSize)) && (laserX >= wallX - 70) && (laserX <= (wallX + 70))
								&& !wall.isWallDestroyed(i)) {

							wall.increaseTheNumberOfHits(i);
							System.out.println("jitsss" + i + wall.checkHowManyTimesWallIsHit(i));
							if (wall.checkHowManyTimesWallIsHit(i) > 6) {
								wall.destroyWall(i);
								break;
							}
							System.out.println("HITTTT");
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
