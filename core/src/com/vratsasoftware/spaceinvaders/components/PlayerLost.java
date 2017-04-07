package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.GameOverScreen;

public class PlayerLost extends ComponentsScreen {
	public PlayerLost(Ship ship ,boolean isPlayerAlieve  ,int explosionIndex,long timerForExpolosions,SpriteBatch batch){
		this.ship=ship;
		this.isPlayerAlieve=isPlayerAlieve;
		this.explosionIndex=explosionIndex;
		this.timerForAliensShot=timerForExpolosions;
		this.batch=batch;
		
	}
	protected void checkIfPlayerLose(SpriteBatch batch) {
		
		if (ship.chechIfLose()) {
			isPlayerAlieve = false;
			if (explosionIndex == 0) {
				ship.explosion(batch, -1, Gdx.graphics.getDeltaTime());
				timerForExpolosions = System.currentTimeMillis();
				explosionIndex++;
			}

			int start = (int) (timerForExpolosions / 1000) % 60;
			int end = (int) (System.currentTimeMillis() / 1000) % 60;
			// System.out.println("START" + start);
			// System.out.println("END" + end);
			if (end - start == 1) {
				explosionIndex++;
				timerForExpolosions = System.currentTimeMillis();
			} else {
				if ((60 - start) + end == 1 || (60 - start) + end == 0) {
					explosionIndex++;
					timerForExpolosions = System.currentTimeMillis();
				}

				if (explosionIndex == 5) {
					game.setScreen(new GameOverScreen(game, playerPoints, aliensKilled));
				} else {
					ship.explosion(batch, explosionIndex, Gdx.graphics.getDeltaTime());
				}

			}
		}
	}
}
