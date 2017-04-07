package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;
import java.util.Random;

public class AliensWhichShoot extends ComponentsScreen {
	public AliensWhichShoot(Aliens alien,Laser laser ,ArrayList<Integer> xIndexesOfAliensWhichCanShoot,ArrayList<Laser>aliensLasersShot, ArrayList<Integer> yIndexesOfAliensWhichCanShoot,int level){
		this.alien=alien;
		this.laser=laser;
		this.xIndexesOfAliensWhichCanShoot=xIndexesOfAliensWhichCanShoot;
		this.yIndexesOfAliensWhichCanShoot=yIndexesOfAliensWhichCanShoot;
		this.aliensLasersShot=aliensLasersShot;
		this.level=level;
	}
	protected void checkForAliensWhichCanShoot() {
		alien.checkForLowestAliensAlive(xIndexesOfAliensWhichCanShoot, yIndexesOfAliensWhichCanShoot);
		for (int i = 0; i < level; i++) {
			System.out.println("LEVEl" + level);
			int rand = randomIndex();

			float currentAlienX = alien.getAliensCoordinatesX(yIndexesOfAliensWhichCanShoot.get(rand),
					xIndexesOfAliensWhichCanShoot.get(rand));
			float currentAlienY = alien.getAliensCoordinatesY(yIndexesOfAliensWhichCanShoot.get(rand),
					xIndexesOfAliensWhichCanShoot.get(rand));
System.out.println("CX"+currentAlienX);

			laser.aliensNewLaser(aliensLasersShot, currentAlienX, currentAlienY);

		}
	}
	private int randomIndex() {

		Random rand = new Random();

		return rand.nextInt(xIndexesOfAliensWhichCanShoot.size());

	}
}
