package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;
import com.vratsasoftware.spaceinvaders.files.writeInFile;

import Screens.GameOverScreen;

public class ComponentsScreen extends SpaceInvaders implements Screen {

	protected Ship ship;
	Laser laser;
	protected SpriteBatch batch;
	Aliens alien;
	Wall wall;
	Background background;
	Boss boss;

	public ArrayList<Laser> lasersShot;
	public ArrayList<Laser> aliensLasersShot;
	public ArrayList<Integer> xIndexesOfAliensWhichCanShoot;
	public ArrayList<Integer> yIndexesOfAliensWhichCanShoot;
	float currentShipXPosition;
	boolean superShot;
	boolean areAliensGoingRight = true;
	long startTimer;
	long timerForAliensShot;
	boolean bossSpawned = false;
	int playerPoints;
	int aliensKilled;
	BitmapFont points;
	SpaceInvaders si = new SpaceInvaders();
	Game game;
	int explosionIndex;
	boolean isPlayerAlieve;
	long timerForExpolosions;
	int level;
	protected int lives;

	public ComponentsScreen(Game game, int points, int aliensKilled, int level, int lives) {
		// Get the current lives of the player
		this.game = game;
		this.playerPoints = points;
		// System.out.println("pp " + this.playerPoints);
		this.aliensKilled = aliensKilled;
		this.level = level;
		this.lives = lives;
		// System.out.println("ak " + this.aliensKilled);

	}

	public ComponentsScreen() {
	}

	@Override
	public void show() {

		ship = new Ship(game, lives);
		batch = new SpriteBatch();
		laser = new Laser(ship.getPlayerX());
		lasersShot = new ArrayList<Laser>();
		aliensLasersShot = new ArrayList<Laser>();
		xIndexesOfAliensWhichCanShoot = new ArrayList<Integer>();
		yIndexesOfAliensWhichCanShoot = new ArrayList<Integer>();
		alien = new Aliens();
		alien.createNewAliens();
		wall = new Wall();
		wall.createWalls();
		background = new Background();
		superShot = true;
		boss = new Boss();
		areAliensGoingRight = true;
		startTimer = System.currentTimeMillis();
		timerForAliensShot = System.currentTimeMillis();
		points = new BitmapFont();
		explosionIndex = 0;
		isPlayerAlieve = true;

		points.getData().setScale(4f);
	}

	public int getPlayerPoints() {
		return playerPoints;
	}

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
		checkIfPlayerLose(batch);
		laser.displayLasersShot(this.lasersShot, this.batch);

		if (isPlayerAlieve) {
			ship.update(Gdx.graphics.getDeltaTime(), batch);

			timerForTheBoss(this.batch, Gdx.graphics.getDeltaTime());
			timerForAliensShot(this.batch, Gdx.graphics.getDeltaTime());
			laser.shootNewLaser(this.lasersShot, currentShipXPosition, this.ship);
			superShot();
		}
		laser.displayAliensLasersShot(aliensLasersShot, batch);
		isBossOutOfBounds();
		checkForCollisionWithTheWalls();
		alien.showAliens(this.batch);
		wall.display(batch);
		currentShipXPosition = ship.getPlayerX();
		checkForCollision();
		if (alien.checkForWin()) {
			this.level++;
			resetGameIfAliensAreKilled();
		}
		checkForCollisionWithTheBoss();
		points.draw(batch, playerPoints + " ", 50, Gdx.graphics.getHeight() - 25);
		checkForCollisionWithAliensShot();
		ship.drawLives(batch);
		System.out.println("LEVELCHETO " + level);
		batch.end();

	}

	private void resetGameIfAliensAreKilled() {
		game.setScreen(new ComponentsScreen(game, playerPoints, aliensKilled, this.level, this.lives));
	}

	private void checkIfPlayerLose(SpriteBatch batch) {
		PlayerLost playerLost = new PlayerLost(ship, isPlayerAlieve, explosionIndex, timerForExpolosions,batch);
		playerLost.checkIfPlayerLose(batch);
		playerLost = null;

	}

	private void timerForAliensShot(SpriteBatch batch2, float deltaTime) {
		int start = (int) (timerForAliensShot / 1000) % 60;
		int end = (int) (System.currentTimeMillis() / 1000) % 60;
		if (end > start) {
			if (end - start == 2) {
				xIndexesOfAliensWhichCanShoot.clear();
				yIndexesOfAliensWhichCanShoot.clear();
				aliensLasersShot.clear();
				checkForAliensWhichCanShoot();
				timerForAliensShot = System.currentTimeMillis();
			}

		} else {
			if ((60 - start) + end == 2) {
				xIndexesOfAliensWhichCanShoot.clear();
				yIndexesOfAliensWhichCanShoot.clear();
				aliensLasersShot.clear();
				checkForAliensWhichCanShoot();
				timerForAliensShot = System.currentTimeMillis();
			}
		}

	}

	private void checkForAliensWhichCanShoot() {
		AliensWhichShoot aliensWhichShoot = new AliensWhichShoot(alien, laser, xIndexesOfAliensWhichCanShoot, aliensLasersShot, yIndexesOfAliensWhichCanShoot, level);
		aliensWhichShoot.checkForAliensWhichCanShoot();
		aliensWhichShoot = null;

	}

	protected void isBossOutOfBounds() {
		if (boss != null) {
			if (boss.getBossX() < -100) {
				boss = null;
			}
		}
	}

	private void checkForCollisionWithAliensShot() {
		CollisionWithAliensShot collisionWithAliensShot = new CollisionWithAliensShot(ship, lives, aliensLasersShot, batch);
		collisionWithAliensShot.checkForCollisionWithAliensShot();
		collisionWithAliensShot = null;
	}

	private void checkForCollisionWithTheBoss() {
		CollisionWithBoss collisionWithBoss = new CollisionWithBoss(boss, aliensLasersShot, playerPoints, aliensKilled, batch);
		collisionWithBoss.checkForCollisionWithTheBoss();
		collisionWithBoss = null;
	}

	private void timerForTheBoss(SpriteBatch batch, float timer) {

		int start = (int) (startTimer / 1000) % 60;

		int end = (int) (System.currentTimeMillis() / 1000) % 60;

		if (end > start) {
			if (end - start == 5) {
				boss = new Boss();
				startTimer = System.currentTimeMillis();
			}

		} else {
			if ((60 - start) + end == 5) {
				boss = new Boss();
				startTimer = System.currentTimeMillis();
			}
		}
		if (boss != null) {
			boss.update(timer, batch);
		}

	}

	private void superShot() {
		if (this.superShot) {
			if (laser.shootSuperLaser(this.lasersShot, currentShipXPosition, this.ship)) {
				this.superShot = false;
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

	private void checkForCollision() {
		CollisionWithAliens collisionWithAliens = new CollisionWithAliens(alien, lasersShot, this.playerPoints, aliensKilled, batch);
		collisionWithAliens.checkForCollision();
		collisionWithAliens = null;

	}

	private void checkForCollisionWithTheWalls() {
		CollisionWithWalls collisionWithWalls = new CollisionWithWalls(wall, aliensLasersShot, lasersShot,batch);
		collisionWithWalls.checkForCollisionWithTheWalls();
		collisionWithWalls = null;
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