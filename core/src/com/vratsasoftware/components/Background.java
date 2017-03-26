package com.vratsasoftware.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {

	
	private final static int BACKGROUND_WIDTH = Gdx.graphics.getWidth();
	private final static int BACKGROUND_HEIGHT = Gdx.graphics.getHeight();
	private final static Texture background = new Texture("images//stars.gif");
	
	public void showBackground(SpriteBatch batch) { 
		batch.draw(background, 0, 0, BACKGROUND_WIDTH, Background.BACKGROUND_HEIGHT);
	}
}
