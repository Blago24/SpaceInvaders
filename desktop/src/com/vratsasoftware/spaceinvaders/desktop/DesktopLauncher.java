package com.vratsasoftware.spaceinvaders.desktop;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vratsasoftware.spaceinvaders.SpaceInvaders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SpaceInvaders(), config);
		
		config.addIcon("img\\gameIcon.png", Files.FileType.Internal);
		config.width = 600;
		config.height = 800;
		config.title = "Space Invaders";
	}
}
