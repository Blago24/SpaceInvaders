package com.vratsasoftware.spaceinvaders.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boss {

	private int bossSpeed;
	private Texture boss;
	private int bossValue;

	private int bossInitialX = Gdx.graphics.getWidth() + 50;
	private int bossX;
	private int bossY;
	private int bossWidth;
	private int bossHeight;

	Sound bossSound;
	Sound bossDeath;

	public Boss() {
		this.boss = new Texture("images//boss.png");
		this.bossY = Gdx.graphics.getHeight() - boss.getHeight() / 4;
		this.bossSpeed = 5;
		this.bossX = bossInitialX;
		this.bossWidth = 80;
		this.bossHeight = 100;
		this.bossValue = 1;
		this.bossSound = Gdx.audio.newSound(Gdx.files.local("assets//ufo_lowpitch.ogg"));
		this.bossDeath = Gdx.audio.newSound(Gdx.files.local("assets//ufo_highpitch.ogg"));
		bossSound.play();

	}

	public void update(float delta, SpriteBatch batch) {
		if (isBossAlive()) {
			batch.draw(boss, bossX, bossY, bossHeight, bossWidth);

		}
		bossX -= bossSpeed;

	}

	public int getBossValue() {
		return bossValue;
	}

	public void setBossValue(int bossValue) {
		this.bossValue = bossValue;
	}

	protected boolean isBossAlive() {
		if (this.bossValue == 1) {
			return true;
		} else {
			bossDeath.play();
			return false;
		}
	}

	public Texture getBoss() {
		return boss;
	}

	public void setBoss(Texture boss) {
		this.boss = boss;
	}

	public int getBossX() {
		return bossX;
	}

	public void setBossX(int bossX) {
		this.bossX = bossX;
	}

	public int getBossY() {
		return bossY;
	}

	public void setBossY(int bossY) {
		this.bossY = bossY;
	}

	public int getBossWidth() {
		return bossWidth;
	}

	public void setBossWidth(int bossWidth) {
		this.bossWidth = bossWidth;
	}

	public int getBossHeight() {
		return bossHeight;
	}

	public void setBossHeight(int bossHeight) {
		this.bossHeight = bossHeight;
	}

}
