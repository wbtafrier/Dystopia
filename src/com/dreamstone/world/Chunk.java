package com.dreamstone.world;

public class Chunk {

	private Coordinate[] chunk;
	public static final int CHUNK_SIZE = 8;
	
	public Chunk(int startX, int startY) {
		chunk = new Coordinate[CHUNK_SIZE * CHUNK_SIZE];
		
		for (int y = 0; y < CHUNK_SIZE; y++) {
			for (int x = 0; x < CHUNK_SIZE; x++) {
				//TODO: Check if the coord already exists first before creating
				chunk[y + x * CHUNK_SIZE] = new Coordinate(startX + x, startY + y);
			}
		}
	}
	
	public Coordinate getStartingCoord() {
		return this.getChunk()[0];
	}
	
	public Coordinate getCoordinate(int x, int y) {
		
		for (int height = 0; height < CHUNK_SIZE; height++) {
			for (int width = 0; width < CHUNK_SIZE; width++) {
				if (width == x && height == y) {
					return this.getChunk()[y + x * CHUNK_SIZE];
				}
			}
		}
		return null;
	}
	
	public Coordinate[] getChunk() {
		return chunk;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int y = 0; y < CHUNK_SIZE; y++) {
			for (int x = 0; x < CHUNK_SIZE; x++) {
				sb.append(chunk[y + x * CHUNK_SIZE]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
