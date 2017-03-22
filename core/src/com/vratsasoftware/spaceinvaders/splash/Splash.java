package com.vratsasoftware.spaceinvaders.splash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen, KeyListener, ActionListener {

	private SpriteBatch batch;
	private Sprite splash;

	private float xPosition = Gdx.graphics.getPpcX() / 2;
	private float yPosition = Gdx.graphics.getPpcY() / 2;

	@Override
	public void show() {

		batch = new SpriteBatch();
		Texture splashTexture = new Texture("img/spaceship.gif");
		splash = new Sprite(splashTexture);

		splash.setSize(50, 50);
		splash.setPosition(xPosition + 350, yPosition);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		splash.setPosition(xPosition, yPosition);
		show();
		render(2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			xPosition -= 20;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xPosition += 50;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
