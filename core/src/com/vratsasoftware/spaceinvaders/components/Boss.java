package com.vratsasoftware.spaceinvaders.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boss {
	
	private int bossSpeed;
	private Texture boss;
	private int bossInitialX = Gdx.graphics.getWidth() + 50;
	private int bossX;
	private int bossY;
	private int bossWidth;
	private int bossHeight;
	
	public Boss() { 
		this.boss = new Texture("images//boss.png");
		this.bossY = Gdx.graphics.getHeight()  - boss.getHeight() / 4;
		this.bossSpeed = 5;
		this.bossX = bossInitialX;
		this.bossWidth = 80;
		this.bossHeight = 100;
		
	}
	
	public void update(float delta, SpriteBatch batch) {
		batch.draw(boss, bossX, bossY, bossHeight, bossWidth);
		bossX -= bossSpeed;
	}
	
	
}
