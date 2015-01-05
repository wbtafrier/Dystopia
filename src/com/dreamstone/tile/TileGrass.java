package com.dreamstone.tile;

public class TileGrass extends Tile {
	
	public TileGrass(String name, String tileSheetName, int index) {
		super(name, tileSheetName, index);
	}

	public static int getRandomIndex() {
		int r = (int)Math.random() * 2;
		return 17; 
	}
}
