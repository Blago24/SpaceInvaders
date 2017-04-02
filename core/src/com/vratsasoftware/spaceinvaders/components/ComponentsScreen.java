package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ComponentsScreen implements Screen {

	Ship ship;
	Laser laser;
	SpriteBatch batch;
	Aliens alien;
	Wall wall;
	Background background;
	Boss boss;

	public ArrayList<Laser> lasersShot;
	float currentShipXPosition;
	boolean superShot;
	boolean areAliensGoingRight = true;
	long startTimer;
	boolean bossSpawned = false;

	@Override
	public void show() {
		ship = new Ship();
		batch = new SpriteBatch();
		laser = new Laser(ship.getPlayerX());
		lasersShot = new ArrayList<Laser>();
		alien = new Aliens();
		alien.createNewAliens();
		wall = new Wall();
		background = new Background();
		superShot = true;
		boss = new Boss();
		areAliensGoingRight = true;
		startTimer = System.currentTimeMillis();
	}

	// create a timer to launch a new boss every ~30s.
	@Override
	public void render(float delta) {
		// System.out.println(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(this.batch);

	}

	private void update(SpriteBatch batch) {
		batch.begin();
		background.showBackground(batch);
		batch.draw(ship.getShipTexture(), ship.getPlayerX(), ship.getPlayerY(), 50, 50);
		if (alien.checkForWin()) {
			System.exit(0);
			// break;
		}
		laser.shootNewLaser(this.lasersShot, currentShipXPosition, this.ship);
		// System.out.println(superShot);
		superShot();
		laser.displayLasersShot(this.lasersShot, this.batch);
		timerForTheBoss(this.batch, Gdx.graphics.getDeltaTime());
		// if (seconds == 28) {
		// boss = new Boss();
		// bossSpawned = true;
		// }
		// if (bossSpawned) {
		// boss.update(Gdx.graphics.getDeltaTime(), this.batch);
		// currentTime = 0;
		// }
		// alien.testShow(this.batch);
		// alien.showX();

		// boss.update(Gdx.graphics.getDeltaTime(), this.batch);
		// //boss.bringBackToStartingPosition();
		alien.showAliens(this.batch);
		wall.display(batch);
		currentShipXPosition = ship.getPlayerX();
		checkForCollision();
		ship.update(Gdx.graphics.getDeltaTime());
		batch.end();

	}

	private void timerForTheBoss(SpriteBatch batch, float timer) {

		
		int start = (int) (startTimer / 1000) % 60 ;
		
		int end=(int) (System.currentTimeMillis()/ 1000) % 60;
		System.out.println("start"+start);
		System.out.println("end"+end);
		//boss.update(timer,batch);
		if(end>start){
			if(end-start==5){
				boss=new Boss();
				startTimer=System.currentTimeMillis();
			}
			
		}else{
			if((60-start)+end==20){
				boss=new Boss();
				startTimer=System.currentTimeMillis();
			}
		}
			if(boss!=null){
				boss.update(timer,batch);
		}
		
		
	}

	private void superShot() {
		if (this.superShot) {
			if (laser.shootSuperLaser(this.lasersShot, currentShipXPosition, this.ship)) {
				// this.superShot = false;
			}

		}
	}

	protected int checkTheLaserCoordinatesY(ArrayList<Laser> lasersShot, SpriteBatch batch, int x) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(x).getLaserY();
		}
		return 0;
	}

	protected int checkTheLaserCoordinatesX(ArrayList<Laser> lasersShot, SpriteBatch batch, int x) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(x).getLaserX();
		}
		return 0;

	}

	private boolean checkForCollision() {
		int laserX = 0;
		int laserY = 0;

		boolean killed = false;
		if (lasersShot.size() > 0) {
			for (int x = 0; x < lasersShot.size(); x++) {

				laserX = checkTheLaserCoordinatesX(lasersShot, batch, x);
				laserY = checkTheLaserCoordinatesY(lasersShot, batch, x);

				for (int i = 0; i < alien.aliensCoordinatesX.length; i++) {
					for (int j = 0; j < alien.aliensCoordinatesX[0].length; j++) {
						int alienX = alien.getAliensCoordinatesX(i, j);
						int alienY = alien.getAliensCoordinatesY(i, j);

						for (int alienSize = 5; alienSize <= alien.getAlien().getHeight() / 10; alienSize++) {
							if ((laserY == (alienY - alien.getAlien().getHeight() + 100 + alienSize))
									&& (laserX >= alienX - 30) && (laserX <= (alienX + 30))
									&& alien.isAlienAlive(i, j)) {
								alien.killAlien(i, j);
								killed = true;
								if (lasersShot.size() == 1) {
									lasersShot.clear();
								} else {
									lasersShot.remove(x);
								}
							}
						}
						if (killed) {
							killed = false;
							break;
						}
					}
				}
			}
		}
		return false;

	}

	public boolean areAliensGoingRight() {
		return areAliensGoingRight;
	}

	public void setAreAliensGoingRight(boolean areAliensGoingRight) {
		this.areAliensGoingRight = areAliensGoingRight;
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public int test() {
		System.out.println("P[as");
		return 6;
	}
}
