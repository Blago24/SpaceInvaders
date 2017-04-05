package com.vratsasoftware.spaceinvaders;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vratsasoftware.spaceinvaders.components.Aliens;

import Screens.GameOverScreen;
import Screens.MenuScreen;
import States.GameStateManager;

public class SpaceInvaders extends Game {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	static double width = screenSize.getWidth();

	public double getWidth() {
		return width;
	}

	// ?
	public static double height = screenSize.getHeight();

	public double getHeight() {
		return height;
	}

	public static final String GAME_NAME = "Space invaders";
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 950;
	private static final float ASPECT_RATIO = (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;

	public SpriteBatch batch;
	public int index = 0;
	Aliens alien;

	private Game game;

	public SpaceInvaders() {
		game = this;
	}

	@Override
	public void create() {

		 setScreen(new MenuScreen(this));
		// Game.setScreen(new MenuScreen());
		//setScreen(new GameOverScreen(this));
		

	}

	@Override
	public void render() {
		super.render();

		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void pause() {
		super.pause();
	}

	public void resize(int width, int height) {
		

	}

	public void setIndex(int index) {
		this.index = index;
	}
}