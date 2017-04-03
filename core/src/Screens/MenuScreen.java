package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;
import com.vratsasoftware.spaceinvaders.components.ComponentsScreen;

public class MenuScreen extends SpaceInvaders implements Screen, InputProcessor {
	
	
	private final int HEIGHT = Gdx.graphics.getHeight();
	private final int WIDTH = Gdx.graphics.getWidth();
	
	private float buttonX;
	private float buttonY;
	private boolean displayScreen;

	private SpriteBatch batch;
	private Texture background;
	private Texture playButton;
	private Texture logo;


	Game game;

	public MenuScreen(Game game) {
		this.game = game;
		this.displayScreen = false;

	}

	public void dispose() {
		this.logo.dispose();
		this.playButton.dispose();
	}

	@Override
	public void show() {
		this.background = new Texture("images//backgr.png");
		this.playButton = new Texture("images//play-button.png");
		this.batch = new SpriteBatch();
		buttonX = (this.WIDTH / 2) - (playButton.getWidth() / 2 - 75);
		buttonY = this.HEIGHT / 2 - 135;
		Gdx.input.setInputProcessor(this);
		render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(background, -30, 0, this.WIDTH, this.HEIGHT);
		batch.draw(playButton, buttonX, buttonY, 150, 150);
		if (displayScreen) {
			batch.dispose();
		}
		batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float pointerX = InputTransform.getCursorToModelX(this.WIDTH, screenX);
		float pointerY = InputTransform.getCursorToModelY(this.HEIGHT, screenY);
		
		if (buttonIsClicked(pointerX, pointerY)) {
			openGameScreen();
		}
		return false;
	}

	private void openGameScreen() {
		game.setScreen(new ComponentsScreen(game));
	}

	private boolean buttonIsClicked(float pointerX, float pointerY) {
		int buttonTopY = 420;
		int buttonBottomY = 385;
		int buttonLeftX = 420;
		int buttonRightX = 505;

		if ((pointerY >= buttonBottomY && pointerY <= buttonTopY) && (pointerX >= buttonLeftX && pointerX <= buttonRightX)) {
			if (displayScreen == false) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		displayScreen = true;
		return false;
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
