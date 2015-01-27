package com.dreamstone.world;

import java.awt.Point;
import java.util.ArrayList;

import com.dreamstone.tile.Tile;
import com.dreamstone.util.DystopiaLogger;

public class Grid {
	
	public ArrayList<Quadrant> QUADRANTS;
	private Quadrant quad1;
	private Quadrant quad2;
	private Quadrant quad3;
	private Quadrant quad4;
	
	/**
	 * Creates a new Grid by initializing the map and printing the initialized coordinates.
	 */
	public Grid() {
		
		QUADRANTS = new ArrayList<>();
		initializeMap();
		
//		quad1.growQuadrant(new Chunk(1, 1));
//		quad2.growQuadrant(new Chunk(-2, 2));
//		quad3.growQuadrant(new Chunk(-2, -2));
//		quad4.growQuadrant(new Chunk(2, -2));
		
		System.out.println(quad1);
		System.out.println(quad2);
		System.out.println(quad3);
		System.out.println(quad4);
	}
	
	/**
	 * Initializes the map by adding the first four Chunks to each Quadrant, and adding the quadrants to the grid.
	 */
	private void initializeMap() {
		
		QUADRANTS.add(new Quadrant(new Chunk(0, 0)));
		QUADRANTS.add(new Quadrant(new Chunk(-1, 1)));
		QUADRANTS.add(new Quadrant(new Chunk(-1, -1)));
		QUADRANTS.add(new Quadrant(new Chunk(1, -1)));
		
		quad1 = QUADRANTS.get(0);
		quad2 = QUADRANTS.get(1);
		quad3 = QUADRANTS.get(2);
		quad4 = QUADRANTS.get(3);
	}
	
	/**
	 * Modifies the Tile of a specific coordinate.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param t The Tile.
	 */
	public void setTile(int x, int y, Tile t) {
		this.getCoordinate(x, y).setTileType(t);
	}
	
	/**
	 * Gets a Tile from a specific coordinate.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @return The Tile at this coordinate.
	 */
	public Tile getTileFromCoordinate(int x, int y) {
		return this.getCoordinate(x, y).getTile();
	}
	
	/**
	 * Gets a Coordinate object based on an x and y location.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @return The correct Coordinate object.
	 */
	public Coordinate getCoordinate(int x, int y) {
		int chunkX;
		int chunkY;
		
		if (x >= 0 && y >= 0) {
			chunkX = x / Chunk.CHUNK_SIZE;
			chunkY = y / Chunk.CHUNK_SIZE;
			return quad1.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y);
		}
		else if (x < 0 && y >= 0) {
			chunkX = (Math.abs(x) - 1) / Chunk.CHUNK_SIZE;
			chunkY = y / Chunk.CHUNK_SIZE;
			return quad2.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y);
		}
		else if (x < 0 && y < 0) {
			chunkX = (Math.abs(x) - 1) / Chunk.CHUNK_SIZE;
			chunkY = (Math.abs(y) - 1) / Chunk.CHUNK_SIZE;
			return quad3.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y);
		}
		else if (x >= 0 && y < 0) {
			chunkX = x / Chunk.CHUNK_SIZE;
			chunkY = (Math.abs(y) - 1) / Chunk.CHUNK_SIZE;
			return quad4.getChunks().get(chunkY).get(chunkX).getCoordinate(x, y);
		}

		DystopiaLogger.logSevere("THIS COORDINATE IS NOT IN THE GRID. RETURNING NULL.");
		return null;
	}
	
	/**
	 * Takes in an x and y screen position and returns the coordinate at that spot.
	 */
	public Coordinate getCoordinateFromDisplay(int xCoord, int yCoord) {
		
//		int x1 = xCoord - Tile.getTileSize();
//		int x2 = xCoord + Tile.getTileSize();
//		int y1 = yCoord - Tile.getTileSize();
//		int y2 = yCoord + Tile.getTileSize();
		
		ArrayList<ArrayList<Chunk>> chunks;
		for (int i = 0; i < this.QUADRANTS.size(); i++) {
			chunks = this.QUADRANTS.get(i).getChunks();
			for (int y = 0; y < chunks.size(); y++) {
				for (int x = 0; x < chunks.get(y).size(); x++) {
					for (int yy = 0; yy < Chunk.CHUNK_SIZE; yy++) {
						for (int xx = 0; xx < Chunk.CHUNK_SIZE; xx++) {
							Coordinate c = chunks.get(y).get(x).getCoordinateFromIndex(xx, yy);
							
							if ((c.getXScreenPos() <= xCoord && xCoord < c.getXScreenPos() + Tile.getTileSize()) &&
								(c.getYScreenPos() <= yCoord && yCoord < c.getYScreenPos() + Tile.getTileSize())) {
								return c;
							}
						}
					}
				}
			}
		}
		
		DystopiaLogger.logSevere("GLITCHHH!");
		return null;
	}
		
	
	/**
	 * Determines what Chunk a coordinate point is in and returns it as a Point object.
	 */
	public static Point getChunkFromCoordinate(int x, int y) {
		int chunkX = 0;
		int chunkY = 0;
		
		if (x >= 0 && y >= 0) {
			chunkX = x / Chunk.CHUNK_SIZE;
			chunkY = y / Chunk.CHUNK_SIZE;
		}
		else if (x < 0 && y >= 0) {
			chunkX = (x + 1) / Chunk.CHUNK_SIZE - 1;
			chunkY = y / Chunk.CHUNK_SIZE;
		}
		else if (x < 0 && y < 0) {
			chunkX = (x + 1) / Chunk.CHUNK_SIZE - 1;
			chunkY = (y + 1) / Chunk.CHUNK_SIZE - 1;
		}
		else if (x >= 0 && y < 0) {
			chunkX = x / Chunk.CHUNK_SIZE;
			chunkY = (y + 1) / Chunk.CHUNK_SIZE - 1;
		}
		return new Point(chunkX, chunkY);
	}
}
