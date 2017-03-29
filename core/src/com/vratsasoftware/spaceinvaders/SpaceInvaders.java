package com.vratsasoftware.spaceinvaders;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.components.Components;

import States.GameStateManager;

public class SpaceInvaders extends Game {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double width = screenSize.getWidth();
	public static double getWidth() {
		return width;
	}

	static double height = screenSize.getHeight();

	public static double getHeight() {
		return height;
	}

	public static final String GAME_NAME = "Space invaders";
	public static final int SCREEN_WIDTH = (int) width/10*5;
	public static final int SCREEN_HEIGHT = (int) height/10*8;
	private GameStateManager gsm;
	public SpriteBatch batch;

	@Override
	public void create() {
		setScreen(new Components());
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
		super.resize(width, height);
	}
}
