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

	protected Camera camera;
	protected Rectangle viewport;

	private GameStateManager gsm;
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
//		setScreen(new MenuScreen(this));
		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		gsm = new GameStateManager();

	}

	@Override
	public void render() {
		super.render();

		// gsm.update(Gdx.graphics.getDeltaTime());
		// gsm.render(batch);
		// set viewport
		// Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int)
		// viewport.width, (int) viewport.height);
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
		float aspectRatio = (float) width / (float) height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);
		if (aspectRatio > ASPECT_RATIO) {
			scale = (float) height / (float) SCREEN_HEIGHT;
			crop.x = (width - SCREEN_WIDTH * scale) / 2f;
		} else if (aspectRatio < ASPECT_RATIO) {
			scale = (float) width / (float) SCREEN_WIDTH;
			crop.y = (height - SCREEN_HEIGHT * scale) / 2f;
		} else {
			scale = (float) width / (float) SCREEN_WIDTH;
		}

		float w = (float) SCREEN_WIDTH * scale;
		float h = (float) SCREEN_HEIGHT * scale;
		viewport = new Rectangle(crop.x, crop.y, w, h);

	}

	public void setIndex(int index) {
		this.index = index;
	}
}