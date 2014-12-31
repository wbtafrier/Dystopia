package com.dreamstone.world;

import com.dreamstone.tile.Tile;

public class Coordinate {
	
	public final int xCoordinate;
	public final int yCoordinate;
	private Tile tileType;
	
	protected Coordinate() {
		xCoordinate = 0;
		yCoordinate = 0;
		tileType = null;
	}
	
	protected Coordinate(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
		tileType = null;
	}
	
	protected Coordinate(int x, int y, Tile t) {
		xCoordinate = x;
		yCoordinate = y;
		tileType = t;
	}
	
	public void setTile(Tile t) {
		tileType = t;
	}

	public Tile getTile() {
		return tileType;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		
		Coordinate c = (Coordinate) obj;
		return (this.xCoordinate == c.xCoordinate) && (this.yCoordinate == c.yCoordinate);
	}
	
//	public static int counter = 0;
	@Override
	public String toString() {
//		counter++;
//		
//		if (counter == 8) {
//			counter = 0;
//			return "(" + xCoordinate + ", " + yCoordinate + ")\n";
//		}
		return "(" + xCoordinate + ", " + yCoordinate + ")";
		
	}
}
