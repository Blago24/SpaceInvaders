package com.vratsasoftware.spaceinvaders.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class Wall {

	private final int WALL_WIDTH = 100;
	private final int WALL_HEIGHT = 50;
	private final int WALL_AMOUNT = 3;

	private final int WALL_Y = 150;
	private int wallX;
	private int lives;

	ComponentsScreen comp = new ComponentsScreen();
	Texture wall;
	private boolean isWallHit = false;

	public Wall() {
		this.wallX = 50;
		this.lives = 10;
		this.wall = new Texture("images//bunker.png");
	}

	public void display(SpriteBatch batch) {
		batch.draw(wall, SpaceInvaders.SCREEN_WIDTH - (SpaceInvaders.SCREEN_WIDTH - 40), WALL_Y, WALL_WIDTH, WALL_HEIGHT);
		batch.draw(wall,  SpaceInvaders.SCREEN_WIDTH - (SpaceInvaders.SCREEN_WIDTH - 200), WALL_Y, WALL_WIDTH, WALL_HEIGHT);
		batch.draw(wall,  SpaceInvaders.SCREEN_WIDTH - (wall.getWidth() + 25), WALL_Y, WALL_WIDTH, WALL_HEIGHT);
	}
}
