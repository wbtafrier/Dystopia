package com.dreamstone.world;

public class Chunk {

	private Coordinate[] coords;
	public static final int CHUNK_SIZE = 8;
	
	public Chunk(int startX, int startY) {
		coords = new Coordinate[CHUNK_SIZE * CHUNK_SIZE];
		
		for (int x = 0; x < CHUNK_SIZE; x++) {
			for (int y = 0; y < CHUNK_SIZE; y++) {
				coords[x + y * CHUNK_SIZE] = new Coordinate(startX + x, startY + y);
			}
		}
	}
	
	public Coordinate getStartingCoord() {
		return this.getCoords()[0];
	}
	
	public Coordinate getCoordinate(int x, int y) {
		
		for (int height = 0; height < CHUNK_SIZE; height++) {
			for (int width = 0; width < CHUNK_SIZE; width++) {
				if (width == x && height == y) {
					return this.getCoords()[y + x * CHUNK_SIZE];
				}
			}
		}
		return null;
	}
	
	public Coordinate[] getCoords() {
		return coords;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int y = 0; y < CHUNK_SIZE; y++) {
			for (int x = 0; x < CHUNK_SIZE; x++) {
				sb.append(coords[y + x * CHUNK_SIZE]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
