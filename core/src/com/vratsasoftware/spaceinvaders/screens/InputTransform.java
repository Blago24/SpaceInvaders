package com.vratsasoftware.spaceinvaders.screens;

public class InputTransform {
	private static int appWidth = 900;
	private static int appHeight = 950;

	public static float getCursorToModelX(int screenX, int cursorX) {
		return (((float) cursorX) * appWidth) / ((float) screenX);
	}

	public static float getCursorToModelY(int screenY, int cursorY) {
		return ((float) (screenY - cursorY)) * appHeight / ((float) screenY);
	}
}