package States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class State {
	protected OrthographicCamera cam;
	protected Vector2 keyboard;
	protected GameStateManager gsm;
	
	
	protected State(GameStateManager gsm) {
		this.gsm = gsm;
		cam = new OrthographicCamera();
		keyboard = new Vector2();
		
	}
	
	protected abstract void handleInput();
	public abstract void update(float delta);
	public abstract void render(SpriteBatch sb);
	
}
