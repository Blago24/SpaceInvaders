package Screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;
import com.vratsasoftware.spaceinvaders.components.ComponentsScreen;

public class GameOverScreen implements Screen, InputProcessor {

	private Texture replayButton;
	private SpriteBatch batch;
	private FreeTypeFontGenerator generator;
	private BitmapFont gameOver;
	private BitmapFont result;
	private ComponentsScreen cs;

	private int scoreCount;
	private static String score;
	String text = "Your result : " + score;
	String finalResult;
	Game game;

	public GameOverScreen(Game game , int points) {
		this.game = game;
		this.scoreCount= points;
	}

	@Override
	public void show() {

		generator = new FreeTypeFontGenerator(Gdx.files.local("assets//adrip1.ttf"));
		FreeTypeFontParameter headerParameter = new FreeTypeFontParameter();
		headerParameter.size = 96;
		headerParameter.color = Color.FIREBRICK;
		gameOver = generator.generateFont(headerParameter);
		FreeTypeFontParameter textParameter = new FreeTypeFontParameter();
		textParameter.size = 80;
		textParameter.color = Color.CHARTREUSE;
		result = generator.generateFont(textParameter);
		generator.dispose();
		cs = new ComponentsScreen(game);
		score = scoreCount + "";
		replayButton = new Texture("images//replayButton.png");
		Gdx.input.setInputProcessor(this);
		this.batch = new SpriteBatch();
	}

	

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gameOver.draw(batch, "Game over!", 235, 700);
		result.draw(batch, "Your result : " + this.score, 80, 550);
		batch.draw(replayButton, 350, 150, 100, 100);
		batch.end();
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float pointerX = InputTransform.getCursorToModelX(SpaceInvaders.SCREEN_WIDTH, screenX);
		float pointerY = InputTransform.getCursorToModelY(SpaceInvaders.SCREEN_HEIGHT, screenY);

		System.out.println("PointerX: " + pointerX);
		System.out.println("PointerY: " + pointerY);
		System.out.println();

//		if ((pointerY >= backButtonBottomY && pointerY <= backButtonTopY)
//				&& (pointerX >= backButtonLeftX && pointerX <= backButtonRightX)) {
//			
//		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
