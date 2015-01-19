package com.dreamstone.input;

import com.dreamstone.util.DystopiaLogger;


public class KeyOptions {

	private static final int KEY_AMOUNT = 4;
	private static char[] moveNorth = {'w', 'W'};
	private static char[] moveSouth = {'s', 'S'};
	private static char[] moveEast = {'d', 'D'};
	private static char[] moveWest = {'a', 'A'};
	
	//Method will eventually be used for when the player has custom keys saved. Needs to be looked at when doing the LoadingManager!
	public static void initializeKeys(Character[] loadedKeyOptions) {
		
		if (!(loadedKeyOptions.length == KEY_AMOUNT)) {
			DystopiaLogger.logSevere("Your key options are corrupt!");
			throw new IllegalArgumentException();
		}
		
		setKeyNorth(loadedKeyOptions[0]);
		setKeySouth(loadedKeyOptions[1]);
		setKeyEast(loadedKeyOptions[2]);
		setKeyWest(loadedKeyOptions[3]);
	}
	
	public static void setKeyNorth(char ch) {
		moveNorth[0] = ch;
		moveNorth[1] = Character.toUpperCase(ch);
	}
	
	public static void setKeySouth(char ch) {
		moveSouth[0] = ch;
		moveSouth[1] = Character.toUpperCase(ch);
	}
	
	public static void setKeyEast(char ch) {
		moveEast[0] = ch;
		moveEast[1] = Character.toUpperCase(ch);
	}
	
	public static void setKeyWest(char ch) {
		moveWest[0] = ch;
		moveWest[1] = Character.toUpperCase(ch);
	}
	
	public static char[] getKeyNorth() {
		return moveNorth;
	}
	
	public static char[] getKeySouth() {
		return moveSouth;
	}
	
	public static char[] getKeyEast() {
		return moveEast;
	}
	
	public static char[] getKeyWest() {
		return moveWest;
	}
}
