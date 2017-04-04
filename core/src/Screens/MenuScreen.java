package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;
import com.vratsasoftware.spaceinvaders.components.ComponentsScreen;

public class MenuScreen extends SpaceInvaders implements Screen, InputProcessor {

	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();

	private float buttonX;
	private float buttonY;

	private float highscoreX;
	private float highscoreY;

	private boolean displayScreen;

	private SpriteBatch batch;
	private Texture background;
	private Texture playButton;
	private Texture logo;
	private Texture highscoreButton;

	private Music menuMusic;

	private Vector2 logoCoordinates;

	Game game;

	public MenuScreen(Game game) {
		this.game = game;
		this.displayScreen = false;
	}

	@Override
	public void show() {
		this.background = new Texture("images//backgr.png");
		this.playButton = new Texture("images//play-button.png");
		this.logo = new Texture("images//vsc-logo.png");
		this.highscoreButton = new Texture("images//highscore.png");
		this.batch = new SpriteBatch();
		this.logoCoordinates = new Vector2(0, 0);
		buttonX = (this.WIDTH / 2) - (playButton.getWidth() / 2 - 75);
		buttonY = this.HEIGHT / 2 - 135;
		highscoreX = ((this.WIDTH / 2) - (highscoreButton.getWidth() / 2 - 205));
		highscoreY = this.HEIGHT / 2 - 100;
		menuMusic = Gdx.audio.newMusic(Gdx.files.local("assets//backgroundMusic.ogg"));
		menuMusic.setLooping(true);
		menuMusic.play();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(background, -30, 0, this.WIDTH, this.HEIGHT);
		batch.draw(playButton, buttonX, buttonY, 150, 150);
		batch.draw(highscoreButton, highscoreX, highscoreY, 75, 75);
		animateLogo(this.batch);
		if (displayScreen) {
			batch.dispose();
			Gdx.gl20.glClearColor(1, 0, 0, 1);
		}
		batch.end();
	}

	private void animateLogo(SpriteBatch batch2) {
		batch.draw(logo, logoCoordinates.x, logoCoordinates.y, this.WIDTH, this.HEIGHT);
		logoCoordinates.y += 10; // 2.5;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float pointerX = InputTransform.getCursorToModelX(this.WIDTH, screenX);
		float pointerY = InputTransform.getCursorToModelY(this.HEIGHT, screenY);

		System.out.println("Highscore: " + pointerX);
		System.out.println("Y " + pointerY);
		//
		if (isButtonPressed(pointerX, pointerY)) {
			openGameScreen();
		} else if (highscorePress(pointerX, pointerY)) {
			openHighscoreScreen();
		}

		return false;
	}

	private void openHighscoreScreen() {
		game.setScreen(new HighscoreScreen());
	}

	private boolean isHighScorePressed(float pointerX, float pointerY) {
		int highscoreTopY = 450;
		int highscoreLeftX = 540;
		int highscoreBottomY = 360;
		int highscoreRightX = 630;

		if ((pointerY >= highscoreBottomY && pointerY <= highscoreTopY)
				&& (pointerX >= highscoreLeftX && pointerX <= highscoreRightX)) {
			return true;
		}
		return false;
	}

	private boolean highscorePress(float pointerX, float pointerY) {
		if (isHighScorePressed(pointerX, pointerY)) {
			if (displayScreen == false) {
				return true;
			}
		}
		return false;
	}

	private boolean isButtonPressed(float pointerX, float pointerY) {
		if (isPlayButtonPressed(pointerX, pointerY)) {
			if (displayScreen == false) {
				return true;
			}
		}
		return false;
	}

	private boolean isPlayButtonPressed(float pointerX, float pointerY) {
		int buttonTopY = 520;
		int buttonBottomY = 350;
		int buttonLeftX = 400;
		int buttonRightX = 530;

		if ((pointerY >= buttonBottomY && pointerY <= buttonTopY)
				&& (pointerX >= buttonLeftX && pointerX <= buttonRightX)) {
			return true;
		}
		return true;
	}

	private void openGameScreen() {
		game.setScreen(new ComponentsScreen(game));
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		displayScreen = true;
		return false;
	}

	public void dispose() {
		this.logo.dispose();
		this.background.dispose();
		this.playButton.dispose();
	}

	@Override
	public void hide() {
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
