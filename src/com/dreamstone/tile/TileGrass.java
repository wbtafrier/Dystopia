package com.dreamstone.tile;


public class TileGrass extends Tile implements ITransition {

	private EnumDirection direction;
	
	
	public TileGrass(String name, String tileSheetName, int index) {
		super(name, tileSheetName, index);
	}
	
//	private static void initializeDirections() {
//		grassDirections = new TileGrass[8];
//		for (int i = 0; i < grassDirections.length; i++) {
//			grassDirections[i] = new TileGrass("grass_" + i, "terrain.png", setDirection(i));
//		}
//	}

	public int setDirection(int i) {
		switch (i) {
		case 0: this.direction = EnumDirection.NORTHWEST;
			return i - 17;
		case 1: this.direction = EnumDirection.NORTH;
			return i - 16;
		case 2: this.direction = EnumDirection.NORTHEAST; 
			return i - 15;
		case 3: this.direction = EnumDirection.WEST;
			return i - 1;
		case 4: this.direction = EnumDirection.EAST;
			return i + 1;
		case 5: this.direction = EnumDirection.SOUTHWEST;
			return i + 15;
		case 6: this.direction = EnumDirection.SOUTH;
			return i + 16;
		case 7: this.direction = EnumDirection.SOUTHEAST; 
			return i + 17;
		}
		return i;
	}

	public static int getRandomIndex() {
		//TODO: Add 20 into this method
		int r = (int)Math.random() * 2;
		return 17; 
	}
	
	@Override
	public EnumDirection getDirection() {
		return this.direction;
	}
	
}
