package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.tile.Tile;
import com.dreamstone.util.DystopiaLogger;

public class Grid {
	
	public ArrayList<Quadrant> QUADRANTS;
	private Quadrant quad1;
	private Quadrant quad2;
	private Quadrant quad3;
	private Quadrant quad4;
	
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
	
	public void setTile(int x, int y, Tile t) {
		this.getCoordinate(x, y).setTileType(t);
	}
	
	public Tile getTileFromCoordinate(int x, int y) {
		return this.getCoordinate(x, y).getTile();
	}
	
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
		
	
//	public Point getChunkFromCoordinate(int x, int y) {
//		int chunkX;
//		int chunkY;
//		
//		//QUADRANT 1
//		//5, 5 ---> (0, 0)
//		//5, 9 ---> (0, 1)
//		
//		//QUADRANT 2
//		//-5, 5 ---> (-1, 1)
//		//-5, 9 ---> (-1, 2)
//		
//		//QUADRANT 3
//		//-5, -5 ---> (-1, -1)
//		//-5, -9 ---> (-1, -2)
//		
//		//QUADRANT 4
//		//5, -5 ---> (1, -1)
//		//5, -9 ---> (1, -2)
//		
//		if (x >= 0 && y >= 0) {
//			//QUADRANT 1
//			return new Point(x / Chunk.CHUNK_SIZE, y / Chunk.CHUNK_SIZE);
//		}
//		else if (x < 0 && y >= 0) {
//			//QUADRANT 2
//		}
//		else if (x < 0 && y < 0) {
//			//QUADRANT 3
//		}
//		else if (x >= 0 && y < 0) {
//			//QUADRANT 4
//		}
//		
//		
//		DystopiaLogger.logSevere("THIS COORDINATE IS NOT IN THE GRID. RETURNING NULL.");
//		return null;
//	}
}
