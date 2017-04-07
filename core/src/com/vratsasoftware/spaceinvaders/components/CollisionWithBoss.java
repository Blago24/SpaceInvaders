package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CollisionWithBoss extends ComponentsScreen {
	public CollisionWithBoss(Boss boss ,ArrayList<Laser> lasersShot,int playerPoints, int aliensKilled , SpriteBatch batch){
		this.boss=boss;
		this.lasersShot=lasersShot;
		this.playerPoints=playerPoints;
		this.aliensKilled=aliensKilled;
		this.batch=batch;
	}
	protected void checkForCollisionWithTheBoss() {
		int laserX = 0;
		int laserY = 0;

		boolean killed = false;
		if (boss != null) {
			if (lasersShot.size() > 0) {
				for (int x = 0; x < lasersShot.size(); x++) {

					laserX = checkTheLaserCoordinatesX(lasersShot, batch, x);
					laserY = checkTheLaserCoordinatesY(lasersShot, batch, x);
					int bossX = boss.getBossX();
					int bossY = boss.getBossY();

					for (int bossSize = 1; bossSize <= boss.getBoss().getHeight() / 100 + 10; bossSize++) {
						if ((laserY == (bossY - bossSize)) && (laserX >= bossX - 20) && (laserX <= (bossX + 90))
								&& boss.isBossAlive()) {
							boss.setBossValue(0);
							playerPoints += 100;
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
