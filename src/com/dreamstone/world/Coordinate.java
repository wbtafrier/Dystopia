package com.dreamstone.world;

import java.awt.image.DataBufferInt;
import com.dreamstone.tile.Tile;
import com.dreamstone.util.DystopiaLogger;

public class Coordinate {

	public final int xCoordinate;
	public final int yCoordinate;
	private Tile tileType;
	private int[] pixels;

	protected Coordinate() {
		this(0, 0, null);
	}

	protected Coordinate(int x, int y) {
		this(x, y, null);
	}

	protected Coordinate(int x, int y, Tile t) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.tileType = t;
		if (this.tileType != null) {
			this.pixels = ((DataBufferInt)t.getImage().getRaster().getDataBuffer()).getData();
		}
	}

	public void setTile(Tile t) {
		this.tileType = t;
	}

	public Tile getTile() {
		return this.tileType;
	}
	
	public int[] getPixels() {
		return this.pixels;
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
		
		DystopiaLogger.logSevere("COORDINATE IS CORUPT! RETURNING 0.");
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Coordinate)) {
			return false;
		}

		Coordinate c = (Coordinate) obj;
		return (this.xCoordinate == c.xCoordinate)
				&& (this.yCoordinate == c.yCoordinate);
	}

	@Override
	public String toString() {
		return "(" + this.xCoordinate + ", " + this.yCoordinate + ")";

	}
}
