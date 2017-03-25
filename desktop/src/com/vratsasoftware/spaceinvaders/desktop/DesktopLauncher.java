package com.vratsasoftware.spaceinvaders.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class DesktopLauncher {


	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


		config.addIcon("images\\iconata.png", Files.FileType.Internal);
		config.width = SpaceInvaders.SCREEN_WIDTH;
		config.height = SpaceInvaders.SCREEN_HEIGHT;
		config.title = SpaceInvaders.GAME_NAME;
		new LwjglApplication(new SpaceInvaders(), config);


		
	}
}
