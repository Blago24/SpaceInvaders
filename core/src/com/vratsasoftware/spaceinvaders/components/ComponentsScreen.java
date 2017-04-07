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

	Ship ship;
	Laser laser;
	SpriteBatch batch;
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
	int i;
	boolean isPlayerAlieve;
	long timerForExpolosions;

	public ComponentsScreen(Game game, int points, int aliensKilled) {
		//Get the current lives of the player
		this.game = game;
		this.playerPoints = points;
		System.out.println("pp " + this.playerPoints);
		this.aliensKilled = aliensKilled;
		System.out.println("ak " + this.aliensKilled);
		

	}

	@Override
	public void show() {
		ship = new Ship(game);
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
		i = 0;
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
		if (alien.checkForWin()) {
			alien.resetAliens();
			alien.rightCol = 10;
			alien.leftCol = 0;
			alien.resetXandY();
		}
		checkIfPlayerLose(batch);

		if (isPlayerAlieve) {
			ship.update(Gdx.graphics.getDeltaTime(), batch);

			timerForTheBoss(this.batch, Gdx.graphics.getDeltaTime());
			timerForAliensShot(this.batch, Gdx.graphics.getDeltaTime());
			laser.shootNewLaser(this.lasersShot, currentShipXPosition, this.ship);
			superShot();
		}
		laser.displayLasersShot(this.lasersShot, this.batch);
		laser.displayAliensLasersShot(aliensLasersShot, batch);
		isBossOutOfBounds();
		checkForCollisionWithTheWalls();
		alien.showAliens(this.batch);
		wall.display(batch);
		currentShipXPosition = ship.getPlayerX();
		checkForCollision();
		if (alien.checkForWin()) {
			resetGameIfAliensAreKilled();
		}
		checkForCollisionWithTheBoss();
		points.draw(batch, playerPoints + " ", 50, Gdx.graphics.getHeight() - 25);
		checkForCollisionWithAliensShot();
		ship.drawLives(batch);
		batch.end();

	}
	

	private void resetGameIfAliensAreKilled() { 
		game.setScreen(new ComponentsScreen(game, playerPoints, aliensKilled));
		System.out.println(playerPoints + " ofjas");
		System.out.println(aliensKilled + "dasdas");
	}

	private void checkIfPlayerLose(SpriteBatch batch) {
		if (ship.chechIfLose()) {
			isPlayerAlieve = false;
			if (i == 0) {
				ship.explosion(batch, -1, Gdx.graphics.getDeltaTime());
				timerForExpolosions = System.currentTimeMillis();
				i++;
			}

			int start = (int) (timerForExpolosions / 1000) % 60;
			int end = (int) (System.currentTimeMillis() / 1000) % 60;
//			System.out.println("START" + start);
//			System.out.println("END" + end);
			if (end - start == 1) {
				i++;
				timerForExpolosions = System.currentTimeMillis();
			} else {
				if ((60 - start) + end == 1 || (60 - start) + end == 0) {
					i++;
					timerForExpolosions = System.currentTimeMillis();
				}

				if (i == 5) {
					game.setScreen(new GameOverScreen(game, playerPoints, aliensKilled));
				} else {
					ship.explosion(batch, i, Gdx.graphics.getDeltaTime());
				}

			}
		}
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
		alien.checkForLowestAliensAlive(xIndexesOfAliensWhichCanShoot, yIndexesOfAliensWhichCanShoot);
		int rand = randomIndex();

		float currentAlienX = alien.getAliensCoordinatesX(yIndexesOfAliensWhichCanShoot.get(rand),
				xIndexesOfAliensWhichCanShoot.get(rand));
		float currentAlienY = alien.getAliensCoordinatesY(yIndexesOfAliensWhichCanShoot.get(rand),
				xIndexesOfAliensWhichCanShoot.get(rand));

		laser.aliensNewLaser(aliensLasersShot, currentAlienX, currentAlienY);

	}

	private int randomIndex() {

		Random rand = new Random();

		return rand.nextInt(xIndexesOfAliensWhichCanShoot.size());

	}

	protected void isBossOutOfBounds() {
		if (boss != null) {
			if (boss.getBossX() < -100) {
				boss = null;
			}
		}
	}

	private void checkForCollisionWithAliensShot() {
		int aliensLaserX = 0;
		int aliensLaserY = 0;

		boolean killed = false;

		if (aliensLasersShot.size() > 0) {
			for (int x = 0; x < aliensLasersShot.size(); x++) {

				aliensLaserX = checkTheLaserCoordinatesX(aliensLasersShot, batch, x);
				aliensLaserY = checkTheLaserCoordinatesY(aliensLasersShot, batch, x);
				int shipX = ship.getPlayerX();
				int shipY = ship.getPlayerY();
				// System.out.println("SHIPX" + shipX);
				// System.out.println("SHIPY" + shipY);
				for (int shipSize = 1; shipSize <= ship.getShipTexture().getHeight() / 100 + 10; shipSize++) {
					if ((aliensLaserY == (shipY - shipSize)) && (aliensLaserX >= shipX - 50)
							&& (aliensLaserX <= (shipX + 50))) {
						ship.lowerTheLives();

						killed = true;
						if (aliensLasersShot.size() == 1) {
							aliensLasersShot.clear();
						} else {
							aliensLasersShot.remove(x);
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

	private void checkForCollisionWithTheBoss() {
		int laserX = 0;
		int laserY = 0;

		boolean killed = false;
		if (boss != null) {
			if (lasersShot.size() > 0) {
				for (int x = 0; x < lasersShot.size(); x++) {

					laserX = checkTheLaserCoordinatesX(lasersShot, batch, x);
					laserY = checkTheLaserCoordinatesY(lasersShot, batch, x);
					int bossX = boss.getBossX();
					int bossY = boss.getBossY();

					for (int bossSize = 1; bossSize <= boss.getBoss().getHeight() / 100 + 10; bossSize++) {
						if ((laserY == (bossY - bossSize)) && (laserX >= bossX - 20) && (laserX <= (bossX + 90))
								&& boss.isBossAlive()) {
							boss.setBossValue(0);
							playerPoints += 100;
							aliensKilled++;
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

						for (int alienSize = 5; alienSize <= alien.getAlienSizes().getHeight() / 10; alienSize++) {
							if ((laserY == (alienY - alien.getAlienSizes().getHeight() + 100 + alienSize))
									&& (laserX >= alienX - 30) && (laserX <= (alienX + 30))
									&& alien.isAlienAlive(i, j)) {
								alien.killAlien(i, j);
								playerPoints += 20;
								aliensKilled++;
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

	private void checkForCollisionWithTheWalls() {
		int aliensLaserX = 0;
		int aliensLaserY = 0;
		int laserX = 0;
		int laserY = 0;
		boolean killed = false;
		int wallY = wall.getWALL_Y();
		for (int i = 0; i < wall.walls.length; i++) {

			if (aliensLasersShot.size() > 0) {
				for (int x = 0; x < aliensLasersShot.size(); x++) {

					aliensLaserX = checkTheLaserCoordinatesX(aliensLasersShot, batch, x);
					aliensLaserY = checkTheLaserCoordinatesY(aliensLasersShot, batch, x);
					int wallX = (int) wall.getWallX(i);

					System.out.println("wallX" + wallX);
					System.out.println("jitsss" + wall.checkHowManyTimesWallIsHit(i));

					// System.out.println("SHIPY" + wallY);
					for (int wallSize = 1; wallSize <= wall.getWallNoHit().getHeight() / 100 + 10; wallSize++) {
						if (aliensLaserY == (wallY + wallSize) && (aliensLaserX >= wallX - 70)
								&& (aliensLaserX <= (wallX + 70)) && !wall.isWallDestroyed(i)) {
							// TODO wall.isWallDestroyed(0) have to be different
							wall.increaseTheNumberOfHits(i);
							System.out.println("jitsss" + i + wall.checkHowManyTimesWallIsHit(i));
							if (wall.checkHowManyTimesWallIsHit(i) > 6) {
								wall.destroyWall(i);
								break;
							}
							killed = true;
							if (aliensLasersShot.size() == 1) {
								aliensLasersShot.clear();
							} else {
								aliensLasersShot.remove(x);
							}
						}
					}
					if (killed) {
						killed = false;
						break;
					}
				}
			}
			if (lasersShot.size() > 0) {
				for (int x = 0; x < lasersShot.size(); x++) {
					int wallX = (int) wall.getWallX(i);

					laserX = checkTheLaserCoordinatesX(lasersShot, batch, x);
					laserY = checkTheLaserCoordinatesY(lasersShot, batch, x);
					System.out.println("l" + laserY);
					System.out.println("w" + wallY);
					System.out.println("HEI" + wall.getWallNoHit().getHeight() / 100);
					for (int wallSize = 1; wallSize <= wall.getWallNoHit().getHeight() / 100 + 10; wallSize++) {
						System.out.println("laserY" + laserY + "  wall" + ((wallY + wallSize)));
						System.out.println("X" + laserX);
						if ((laserY == (wallY + wallSize)) && (laserX >= wallX - 70) && (laserX <= (wallX + 70))
								&& !wall.isWallDestroyed(i)) {

							wall.increaseTheNumberOfHits(i);
							System.out.println("jitsss" + i + wall.checkHowManyTimesWallIsHit(i));
							if (wall.checkHowManyTimesWallIsHit(i) > 6) {
								wall.destroyWall(i);
								break;
							}
							System.out.println("HITTTT");
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