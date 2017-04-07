package Screens;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
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
import com.vratsasoftware.spaceinvaders.files.HighscoreFileManager;
import com.vratsasoftware.spaceinvaders.files.writeInFile;

public class GameOverScreen implements Screen, InputProcessor {

	private Texture replayButton;
	private Texture pic;

	private SpriteBatch batch;
	private FreeTypeFontGenerator generator;
	private BitmapFont gameOver;
	private BitmapFont result;
	private ComponentsScreen cs;

	private int scoreCount;
	private static String score;
	private int aliensKilled;
	private static String aliens;

	private String playerName;

	String text = "Your result : " + score;
	String finalResult;
	Game game;

	public GameOverScreen(Game game, int points, int aliensKilled) {
		this.game = game;
		this.scoreCount = points;
		this.aliensKilled = aliensKilled;
	}

	public GameOverScreen(Game game) {
		this.game = game;
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
		cs = new ComponentsScreen(game, 0, 0, 1,3);
		score = scoreCount + "";
		aliens = aliensKilled + "";
		replayButton = new Texture("images//replayButton.png");
		Gdx.input.setInputProcessor(this);
		this.batch = new SpriteBatch();
		pushToGameOverScreen();
	}

	public void pushToGameOverScreen() {

		System.out.println("ACCESSED");
		writeInFile wf = new writeInFile(scoreCount);
		wf.addNewPlayerScore(scoreCount);
		System.out.println("Score: " + score);
		HighscoreFileManager hfm = new HighscoreFileManager();
		hfm.sortHighscores();
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gameOver.draw(batch, "Game over!", 235, 700);
		result.draw(batch, "Your result : " + this.score + "\nAliens Killed : " + aliens, 80, 550);
		batch.draw(replayButton, 350, 150, 100, 100);
		batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float pointerX = InputTransform.getCursorToModelX(SpaceInvaders.SCREEN_WIDTH, screenX);
		float pointerY = InputTransform.getCursorToModelY(SpaceInvaders.SCREEN_HEIGHT, screenY);

		int replayButtonTopY = 270;
		int replayButtonBottomY = 130;
		int replayButtonRightX = 530;
		int replayButtonLeftX = 340;

		System.out.println("PointerX: " + pointerX);
		System.out.println("PointerY: " + pointerY);
		System.out.println();

		if ((pointerY >= replayButtonBottomY && pointerY <= replayButtonTopY)
				&& (pointerX >= replayButtonLeftX && pointerX <= replayButtonRightX)) {
			game.setScreen(new ComponentsScreen(game, 0, 0, 1,3));
			Gdx.input.setInputProcessor(null);
		}
		return false;
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
