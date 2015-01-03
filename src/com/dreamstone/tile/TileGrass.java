package com.dreamstone.tile;

public class TileGrass extends Tile implements ITransition {

	private EnumDirection direction;
	
	public TileGrass(String name, String tileSheetName) {
		super(name, tileSheetName, getRandomIndex());
	}

	@Override
	public void setDirection(EnumDirection dir) {
		int i = this.getIndex();
		this.direction = dir;
		
		switch (this.direction) {
		case NORTHWEST: this.setIndex(i - 17);
			break;
		case NORTH: this.setIndex(i - 16);
			break;
		case NORTHEAST: this.setIndex(i - 15);
			break;
		case WEST: this.setIndex(i - 1);
			break;
		case EAST: this.setIndex(i + 1);
			break;
		case SOUTHWEST: this.setIndex(i + 15);
			break;
		case SOUTH: this.setIndex(i + 16);
			break;
		case SOUTHEAST: this.setIndex(i + 17);
			break;
		}
	}

	public static int getRandomIndex() {
		int r = (int)Math.random() * 2;
		return r == 0 ? 17 : 20; 
	}
	
	@Override
	public EnumDirection getDirection() {
		return this.direction;
	}
	
}
