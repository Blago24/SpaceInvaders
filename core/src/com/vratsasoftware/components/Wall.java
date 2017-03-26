package com.vratsasoftware.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall {

	private final int WALL_WIDHT = 100;
	private final int WALL_HEIGHT = 50;
	private final int WALL_AMOUNT = 3;

	private final int WALL_Y = 150;
	private int wallX;
	private int lives;

	Components comp = new Components();
	Texture wall;
	private boolean isWallHit = false;

	public Wall() {
		this.wallX = 50;
		this.lives = 10;
		this.wall = new Texture("images//bunker.png");
	}

	public void display(SpriteBatch batch) {
		batch.draw(wall, wallX, WALL_Y, WALL_WIDHT, WALL_HEIGHT);
		batch.draw(wall, wallX + 200, WALL_Y, WALL_WIDHT, WALL_HEIGHT);
		batch.draw(wall, wallX + 400, WALL_Y, WALL_WIDHT, WALL_HEIGHT);
	}
}
