package com.vratsasoftware.spaceinvaders;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.components.Aliens;
import com.vratsasoftware.spaceinvaders.screens.GameOverScreen;
import com.vratsasoftware.spaceinvaders.screens.MenuScreen;

public class SpaceInvaders extends Game {

	public static final String GAME_NAME = "Space Invaders";
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 950;
	private static final float ASPECT_RATIO = (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double height = screenSize.getHeight();
	public static double width = screenSize.getWidth();
	
	private Game game;

	public SpaceInvaders() {
		game = this;
	}

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
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


	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}