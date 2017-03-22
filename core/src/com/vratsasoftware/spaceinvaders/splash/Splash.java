package com.vratsasoftware.spaceinvaders.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen {
	
	private SpriteBatch batch; 
	private Sprite splash; 
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		Texture splashTexture = new Texture("img/alien.jpg");
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
