package com.vratsasoftware.spaceinvaders.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
		splash.setSize(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 400);
		splash.setPosition(Gdx.graphics.getPpcX() / 2, Gdx.graphics.getPpcY() / 2);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		splash.draw(batch);
		batch.end();
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
