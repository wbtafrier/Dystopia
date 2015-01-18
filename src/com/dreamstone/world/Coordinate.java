package com.dreamstone.world;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.dreamstone.tile.Tile;
import com.dreamstone.tile.TileList;
import com.dreamstone.util.DystopiaLogger;

public class Coordinate {

	public final int xCoordinate;
	public final int yCoordinate;
	private static Random rand = new Random();
	private Tile tileType;
//	private EnumDirection direction;
	private BufferedImage tileImage;
	private int tileImageIndex;
	
	protected Coordinate() {
		this(0, 0, TileList.nullTile);
	}

	protected Coordinate(int x, int y) {
		this(x, y, TileList.nullTile);
	}

	
	protected Coordinate(int x, int y, Tile t) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.setTileImage(t);
	}
	
	protected Coordinate(int x, int y, Tile t, int imageIndex) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.tileImageIndex = imageIndex;
		this.setTileImage(t, this.tileImageIndex);
	}
	
	public void setTileImage(Tile t) {
		if (t == null) {
			DystopiaLogger.logSevere("TILE EQUALS NULL!");
		}
		else if (t.getFullImageAmount() == 0) {
			this.tileImage = t.getImageTile(0);
		}
		else {
			this.tileImage = t.getImageTile(rand.nextInt(t.getFullImageAmount()));
		}
	}
	
	private void setTileImage(Tile t, int imageIndex) {
		this.tileImage = t.getImageTile(imageIndex);
	}
	
	public void setImageIndex(int index) {
		this.tileImageIndex = index;
	}
	
	public int getImageIndex() {
		return this.tileImageIndex;
	}
	
	public void setTileType(Tile t) {
		this.tileType = t;
		this.setTileImage(t);
	}

	public Tile getTile() {
		return this.tileType;
	}

	public int getQuadrant() {
		
		if (this.xCoordinate >= 0 && this.yCoordinate >= 0) {
			return 1;
		}
		else if (this.xCoordinate < 0 && this.yCoordinate >= 0) {
			return 2;
		}
		else if (this.xCoordinate < 0 && this.yCoordinate < 0) {
			return 3;
		}
		else if (this.xCoordinate >= 0 && this.yCoordinate < 0) {
			return 4;
		}
		
		DystopiaLogger.logSevere("COORDINATE IS CORRUPT! RETURNING 0.");
		return 0;
	}
	
//	/**
//	 * Sets the direction of the tile in a clockwise fashion.
//	 * @param i : The direction needed for the tile.
//	 * 			0 1 2
//	 * 			3 4 5
//	 * 			6 7 8
//	 */
//	public void setDirection(int i) {
//		switch (i) {
//		case 0: this.direction = EnumDirection.NORTHWEST;
//			break;
//		case 1: this.direction = EnumDirection.NORTH;
//			break;
//		case 2: this.direction = EnumDirection.NORTHEAST; 
//			break;
//		case 3: this.direction = EnumDirection.WEST;
//			break;
//		case 5: this.direction = EnumDirection.EAST;
//			break;
//		case 6: this.direction = EnumDirection.SOUTHWEST;
//			break;
//		case 7: this.direction = EnumDirection.WEST;
//			break;
//		case 8: this.direction = EnumDirection.SOUTHEAST;
//			break;
//		default: this.direction = EnumDirection.DEFAULT;
//		}
//	}
	
	public BufferedImage getImage() {
		return this.tileImage;
	}
	
//	public EnumDirection getDirection() {
//		return this.direction;
//	}
//	
//	public void setDirection(EnumDirection dir) {
//		this.direction = dir;
//	}
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Coordinate)) {
			return false;
		}

		Coordinate c = (Coordinate) obj;
		return (this.xCoordinate == c.xCoordinate) && (this.yCoordinate == c.yCoordinate);
	}

	@Override
	public String toString() {
		return "(" + this.xCoordinate + ", " + this.yCoordinate + ")";

	}
}