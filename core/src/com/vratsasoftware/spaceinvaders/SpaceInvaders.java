package com.vratsasoftware.spaceinvaders;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.components.Components;

import States.GameStateManager;

public class SpaceInvaders extends Game {
	
	public static final String GAME_NAME = "Space invaders";
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 800;
	private GameStateManager gsm;
	public SpriteBatch batch; 
	
	@Override
	public void create () {
		setScreen(new Components());
	}


	@Override
	public void render () {
		super.render();
	}

	
	@Override
	public void dispose () {
		super.dispose();
	}
	
	public void pause() {
		super.pause();
	}
	
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
