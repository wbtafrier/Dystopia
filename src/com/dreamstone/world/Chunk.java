package com.dreamstone.world;

import com.dreamstone.util.DystopiaLogger;

public class Chunk {
	private Coordinate[][] coordList;
	public static final int CHUNK_SIZE = 64;
	public final int X_VALUE;
	public final int Y_VALUE;
	
	/* GUIDE TO ADDING CHUNKS */
	//////////////////////////////////////////////
	/*
		Quadrant 1:
			X Coordinates
			- Always use inc8 for the x coordinate. This will give you the correct coordinate when multiplied.
			- The values start at 0 and move their way UP.

			Y Coordinates
			- Always use inc8 for the y coordinate. This will give you the correct coordinate when multiplied.
			- The values start at 0 and move their way UP.

		Quadrant 2:
			X Coordinates
			- Always use inc8 for the x coordinate. This will give you the correct coordinate when multiplied.
			- The values start at -1 and move their way DOWN.

			Y Coordinates
			- Always use inc7 for the y coordinate. This will give you the correct coordinate when multiplied.
			- The values start at 0 and move their way UP.

		Quadrant 3:
			X Coordinates
			- Always use inc8 for the x coordinate. This will give you the correct coordinate when multiplied.
			- The values start at -1 and move their way DOWN.

			Y Coordinates
			- Always use inc8 for the y coordinate. This will give you the correct coordinate when multiplied.
			- The values start at -1 and move their way DOWN.

		Quadrant 4:
			X Coordinates
			- Always use inc7 for the x coordinate. This will give you the correct coordinate when multiplied.
			- The values start at 1 and move their way UP.

			Y Coordinates
			- Always use inc8 for the y coordinate. This will give you the correct coordinate when multiplied.
			- The values start at -1 and move their way DOWN.	
	 */
	
	public Chunk(int startX, int startY) {
		X_VALUE = startX;
		Y_VALUE = startY;
		
		coordList = new Coordinate[CHUNK_SIZE][CHUNK_SIZE];
		
		if (startX >= 0 && startY >= 0) {
			startX *= CHUNK_SIZE;
			startY *= CHUNK_SIZE;
		}
		else if (startX < 0 && startY >= 0) {
			startX *= CHUNK_SIZE;
			startY *= CHUNK_SIZE;
			startY--;
		}
		else if (startX < 0 && startY < 0) {
			startX *= CHUNK_SIZE;
			startY *= CHUNK_SIZE;
		}
		else if (startX >= 0 && startY < 0) {
			startX *= CHUNK_SIZE;
			startX--;
			startY *= CHUNK_SIZE;
		}
		setChunkCoords(startX, startY);
	}
	
	private void setChunkCoords(int startX, int startY) {
		if (startX >= 0 && startY >= 0) {
			//QUADRANT 1
			for (int x = 0; x < CHUNK_SIZE; x++) {
				for (int y = 0; y < CHUNK_SIZE; y++) {
					coordList[x][y] = new Coordinate(startX + x, startY + y);
				}
			}
		}
		else if (startX < 0 && startY >= 0) {
			//QUADRANT 2
			for (int x = 0; x < CHUNK_SIZE; x++) {
				for (int y = 0; y < CHUNK_SIZE; y++) {
					coordList[(CHUNK_SIZE - x) - 1][(CHUNK_SIZE - y) - 1] = new Coordinate(startX + x, startY - y);
				}
			}
		}
		else if (startX < 0 && startY < 0) {
			//QUADRANT 3
			for (int x = 0; x < CHUNK_SIZE; x++) {
				for (int y = 0; y < CHUNK_SIZE; y++) {
					coordList[(CHUNK_SIZE - x) - 1][(CHUNK_SIZE - y) - 1] = new Coordinate(startX + x, startY + y);
				}
			}
		}
		else if (startX >= 0 && startY < 0) {
			//QUADRANT 4
			for (int x = 0; x < CHUNK_SIZE; x++) {
				for (int y = 0; y < CHUNK_SIZE; y++) {
					coordList[(CHUNK_SIZE - x) - 1][(CHUNK_SIZE - y) - 1] = new Coordinate(startX - x, startY + y);
				}
			}
		}
		else {
			DystopiaLogger.logWarning("COORDINATE IS NOT VALID, NOT CREATING CHUNK.");
			return;
		}
	}
	
	public Coordinate getStartingCoord() {
		return this.getCoords()[0][0];
	}
	
	/**
	 * The getCoordinateFromIndex method returns a Coordinate object at the index spots (x, y)
	 * @param x : The x index spot of the chunk.
	 * @param y : The y index spot of the chunk.
	 * @return : A Coordinate object at the index spot of the chunk.
	 */
	public Coordinate getCoordinateFromIndex(int x, int y) {
		for (int height = 0; height < CHUNK_SIZE; height++) {
			for (int width = 0; width < CHUNK_SIZE; width++) {
				if (width == x && height == y) {
					return this.getCoords()[x][y];
				}
			}
		}
		return null;
	}
	
	public Coordinate getCoordinate(int x, int y) {
		Coordinate startCoord = getCoordinateFromIndex(0, 0);
		
		if ((Math.abs(startCoord.xCoordinate) <= Math.abs(x)) && Math.abs(x) < (Math.abs(startCoord.xCoordinate) + (CHUNK_SIZE)) &&
			(Math.abs(startCoord.yCoordinate) <= Math.abs(y)) && Math.abs(y) < (Math.abs(startCoord.yCoordinate) + (CHUNK_SIZE))) {
			return getCoordinateFromIndex(Math.abs(x) - Math.abs(startCoord.xCoordinate), Math.abs(y) - Math.abs(startCoord.yCoordinate));
		}
		else {
			DystopiaLogger.logSevere("Coordinate is not in the chunk!");
			return null;
		}
	}
	
	public Coordinate[][] getCoords() {
		return coordList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int y = 0; y < CHUNK_SIZE; y++) {
			if (y == 0) {
				sb.append("\n");
			}
			
			for (int x = 0; x < CHUNK_SIZE; x++) {
				sb.append(coordList[x][y]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
