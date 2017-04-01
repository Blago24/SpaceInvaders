package States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class MenuState extends State {
	
	private Texture background;
	private Texture playBtn;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, SpaceInvaders.SCREEN_WIDTH / 2, SpaceInvaders.SCREEN_HEIGHT / 2);
		background = new Texture("images//bg.png");
		playBtn = new Texture("images//play-button.png");
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(background, 0, 0, SpaceInvaders.SCREEN_WIDTH, SpaceInvaders.SCREEN_HEIGHT);
		sb.draw(playBtn, (cam.position.x - playBtn.getWidth() / 2),  SpaceInvaders.SCREEN_HEIGHT / 2);
		sb.end();
	}
}