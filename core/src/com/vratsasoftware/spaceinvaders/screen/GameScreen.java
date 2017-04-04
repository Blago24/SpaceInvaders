package com.vratsasoftware.spaceinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;
import com.vratsasoftware.spaceinvaders.components.Ship;

public class GameScreen implements Screen {

	public SpaceInvaders game; 
	Texture texture;
	private Ship ship;
	SpaceInvaders si = new SpaceInvaders();
	
	public GameScreen(SpaceInvaders game) {
		this.game = game;
		texture = new Texture("images//spaceship.jpg");
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(texture,0, 0);
		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}