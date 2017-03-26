package com.vratsasoftware.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Aliens {

	private final int ALIENS_PER_ROW = 11;
	private final int ALIENS_ROWS = 5;
	private Vector2 aliensPosition;
	public Aliens[][] aliens;
	private Texture alien;
	public float alienX;
	public float alienY;

	public Aliens() {
		aliens = new Aliens[ALIENS_ROWS][ALIENS_PER_ROW];
		alien = new Texture("images//aliens.png");
	}

	protected void showAliens(SpriteBatch batch) {
		int aliensX = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() - 50);
		int aliensXHolder = aliensX;
		int aliensY = Gdx.graphics.getHeight() - 100;
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				batch.draw(aliens[i][j].getAlien(), aliensX, aliensY, 35, 35);
				aliensX += 45;
			}
			aliensX = aliensXHolder;
			aliensY -= 50;
		}
	}

	// TODO Generate a moveAliens() method;

	protected void createNewAliens() {
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[0].length; j++) {
				aliens[i][j] = new Aliens();
			}
		}
	}

	public void update(float delta) {

	}

	public Vector2 getAliensPosition() {
		return aliensPosition;
	}

	public void setAliensPosition(Vector2 aliensPosition) {
		this.aliensPosition = aliensPosition;
	}

	public Aliens[][] getAliens() {
		return aliens;
	}

	public Texture getAlien() {
		return alien;
	}

	public float getAlienX() {
		return alienX;
	}

	public float getAlienY() {
		return alienY;
	}

	public void setAlienX(float alienX) {
		this.alienX = alienX;
	}

	public void setAlienY(float alienY) {
		this.alienY = alienY;
	}

}
