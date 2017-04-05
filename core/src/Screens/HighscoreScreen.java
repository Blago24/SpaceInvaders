package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class HighscoreScreen implements Screen, InputProcessor {

	private final int BACKGROUND_WIDTH = SpaceInvaders.SCREEN_WIDTH + 100;
	private final int BACKGROUND_HEIGHT = SpaceInvaders.SCREEN_HEIGHT;
	private boolean newHighscore;
	private char[] newName;

	private FreeTypeFontGenerator generator;
	private BitmapFont gameOverFont;
	

	private Texture backButton;
	private Texture background;
	private Texture backgroundTwo;
	String text = "Highscores \n 1: \n 2: \n 3:";
	private int firstBgX;
	private int firstBgY;

	private int secondBgX;
	private int secondBgY;

	private SpriteBatch batch;
	Game game; 
	public HighscoreScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {

		this.backButton = new Texture("images//backButton.png");
		this.background = new Texture("images//highScoreBackground.png");
		this.backgroundTwo = new Texture("images//highScoreBackground.png");
		FileHandle fh = new FileHandle("assets//adrip1.fnt");
		this.generator = new FreeTypeFontGenerator(Gdx.files.local("assets//adrip1.ttf")); 
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	    parameter.size = 96;
	    parameter.color = Color.CORAL;
		gameOverFont = generator.generateFont(parameter);
		generator.dispose();
		this.batch = new SpriteBatch();
		this.firstBgX = SpaceInvaders.SCREEN_WIDTH - this.background.getWidth() / 2 - 20;
		this.firstBgY = 0;
		this.secondBgX = SpaceInvaders.SCREEN_WIDTH - this.background.getWidth() / 2 - 20;
		this.secondBgY = firstBgY - SpaceInvaders.SCREEN_HEIGHT;
		Gdx.input.setInputProcessor(this);
		

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		moveBackground(this.batch);
		
		if (moveBackground(this.batch)) {
			firstBgY--;
			secondBgY--;
		} else {
//			System.out.println(firstBgY);
			firstBgY++;
			secondBgY++;
		}
		
		
		batch.draw(backgroundTwo, secondBgX, secondBgY, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		batch.draw(background, firstBgX, firstBgY, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		batch.draw(backButton, 0, -100, 500, 20);
		gameOverFont.draw(batch, text, SpaceInvaders.SCREEN_WIDTH/2 - 300, SpaceInvaders.SCREEN_HEIGHT - 250);
		// working;
		// TODO Implement the high score results;
		// Would probably need to use a hash map
		batch.end();

	}

	private boolean moveBackground(SpriteBatch batch) {
		//TODO fix the movement of the background;
		if (firstBgY > 888) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float pointerX = InputTransform.getCursorToModelX(SpaceInvaders.SCREEN_WIDTH, screenX);
		float pointerY = InputTransform.getCursorToModelY(SpaceInvaders.SCREEN_HEIGHT, screenY);

		int backButtonTopY = 160;
		int backButtonBottomY = 10;
		int backButtonRightX = 240;
		int backButtonLeftX = 10;
		
		if ((pointerY >= backButtonBottomY && pointerY <= backButtonTopY)
				&& (pointerX >= backButtonLeftX && pointerX <= backButtonRightX)) {
			game.setScreen(new MenuScreen(game));
			this.dispose();
		}
		return false;
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
