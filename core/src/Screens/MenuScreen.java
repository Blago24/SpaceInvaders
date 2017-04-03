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
	private SpriteBatch batch;
	private Texture background;
	private Texture playButton;
	private Texture logo;
	private float buttonX;
	private float buttonY;
	SpaceInvaders si = new SpaceInvaders();

	Game game;

	public MenuScreen(Game game) {
		this.game = game;
	}

	public void dispose() {
		this.logo.dispose();
		this.playButton.dispose();
	}

	@Override
	public void show() {
		this.background = new Texture("images//background.jpg");
		this.playButton = new Texture("images//play-button.png");
		this.batch = new SpriteBatch();
		System.out.println(this.HEIGHT);
		System.out.println(this.WIDTH);
		this.logo = new Texture("images//backgr.png");
		buttonX = (this.WIDTH / 2) - (playButton.getWidth() / 2  - 75);
		buttonY = this.HEIGHT / 2 - 135;
		Gdx.input.setInputProcessor(this);
		render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(logo, -30, 0, this.WIDTH, this.HEIGHT);
		batch.draw(playButton, buttonX, buttonY, 150, 150);
		batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float pointerX = InputTransform.getCursorToModelX(this.WIDTH, screenX);
		float pointerY = InputTransform.getCursorToModelY(this.HEIGHT, screenY);

		if (pointerY <= buttonY) {
			System.out.println("gosho");
			game.setScreen(new ComponentsScreen(game));
		}

		return false;

		// System.out.println("pointer y " + pointerY);
		//
		// System.out.println("pointer X " + pointerX);
		// System.out.println("button y " + buttonY);
		// System.out.println("button x " + buttonX);
		// return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		float pointerX = InputTransform.getCursorToModelX(this.WIDTH, screenX);
		float pointerY = InputTransform.getCursorToModelY(this.HEIGHT, screenY);

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
