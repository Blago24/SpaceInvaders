package com.vratsasoftware.spaceinvaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.splash.Splash;

public class runGame extends Game {
	
	
	@Override
	public void create () {
		setScreen(new Splash());
	}

	@Override
	public void render () {
		
	}
	
	@Override
	public void dispose () {
		
	}
}
