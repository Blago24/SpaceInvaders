package com.vratsasoftware.spaceinvaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.components.AllComponents;
import com.vratsasoftware.components.Spaceship;
import com.vratsasoftware.spaceinvaders.splash.Splash;

public class SpaceInvaders extends Game {
	
	public static final String GAME_NAME = "Space invaders", VERSION = "1.0";
	
	@Override
	public void create () {
		setScreen(new Spaceship());

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
