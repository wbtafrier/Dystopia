package com.dreamstone.tile;

public class TileTransition extends Tile{

	private EnumDirection direction;
	
	/**
	 * Instantiating this constructor will create a new Tile with a specific name and index from a specific tilesheet.
	 * @param name : The identity or ID of the Tile.
	 * @param tileSheetName : The file name of the tilesheet sent in (Include file extension).
	 * @param index : The index of this Tile's sprite from the specified tilesheet. 
	 */
	public TileTransition(String name, String tileSheetName, int index, EnumDirection dir) {
		super(name, tileSheetName, index);
		this.direction = dir;
	}
	
	/**
	 * Instantiating this constructor will create a new Tile with a specific name and index from a specific tilesheet.
	 * @param name : The identity or ID of the Tile.
	 * @param tileSheetName : The file name of the tilesheet sent in (Include file extension).
	 * @param index : The index of this Tile's sprite from the specified tilesheet. 
	 */
	public TileTransition(String name, String tileSheetName, int index, int dir) {
		super(name, tileSheetName, index);
		this.setDirection(dir);
	}
	
	/**
	 * Sets the direction of the tile in a clockwise fashion.
	 * @param i : The direction needed for the tile.
	 * 			0 1 2
	 * 			3 X 4
	 * 			5 6 7
	 */
	public void setDirection(int i) {
		switch (i) {
		case 0: this.direction = EnumDirection.NORTHWEST;
			break;
		case 1: this.direction = EnumDirection.NORTH;
			break;
		case 2: this.direction = EnumDirection.NORTHEAST; 
			break;
		case 3: this.direction = EnumDirection.EAST;
			break;
		case 4: this.direction = EnumDirection.SOUTHEAST;
			break;
		case 5: this.direction = EnumDirection.SOUTH;
			break;
		case 6: this.direction = EnumDirection.SOUTHWEST;
			break;
		case 7: this.direction = EnumDirection.WEST; 
			break;
		}
	}
	
	public EnumDirection getDirection() {
		return this.direction;
	}
	
	public void setDirection(EnumDirection dir) {
		this.direction = dir;
	}
}
