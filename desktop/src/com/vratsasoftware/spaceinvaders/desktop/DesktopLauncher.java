package com.vratsasoftware.spaceinvaders.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;
import com.vratsasoftware.spaceinvaders.runGame;

public class DesktopLauncher {

	private final static int WIDTH = 600;
	private final static int HEIGHT = 800;

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


		config.addIcon("images\\iconata.png", Files.FileType.Internal);
		config.width = WIDTH;
		config.height = HEIGHT;
		config.title = "Space Invaders";
		new LwjglApplication(new SpaceInvaders(), config);


		
	}
}
