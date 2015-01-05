package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.util.DystopiaLogger;

public class Quadrant {

	private ArrayList<ArrayList<Chunk>> quadrant;
	private final int QUADRANT_NUMBER;

	public Quadrant(Chunk startChunk) {
		quadrant = new ArrayList<>();
		if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate >= 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate >= 0) {
			System.out.println("Chunk should be added in Quadrant 1");
			QUADRANT_NUMBER = 1;
		}
		else if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate < 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate >= 0) {
			System.out.println("Chunk should be added in Quadrant 2");
			QUADRANT_NUMBER = 2;
		}
		else if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate < 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate < 0) {
			System.out.println("Chunk should be added in Quadrant 3");
			QUADRANT_NUMBER = 3;
		}
		else if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate >= 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate < 0) {
			System.out.println("Chunk should be added in Quadrant 4");
			QUADRANT_NUMBER = 4;
		}
		else {
			DystopiaLogger.logWarning("Chunk is not valid! Setting value to 0.");
			QUADRANT_NUMBER = 0;
		}
		
		growQuadrant(startChunk);
	}

	public void growQuadrant(Chunk chunk) {
		
		if (!(isChunkLegal(chunk))) {
			System.out.println("(" + chunk.getCoordinateFromIndex(0, 0).xCoordinate + ", " + chunk.getCoordinateFromIndex(0, 0).yCoordinate + ")");
			DystopiaLogger.logWarning("THIS CHUNK CANNOT BE ADDED TO QUADRANT " + getQuadrant() + ". RETURNING.");
			return;
		}
		if (isChunkCreated(chunk)) {
			DystopiaLogger.logWarning("CHUNK CANNOT BE CREATED! THE CHUNK IS ALREADY CREATED.");
			return;
		}
		else {
			fillChunksInQuadrant(chunk);
		}
	}
	
	private boolean isChunkCreated(Chunk c) {

		//Gets the first coordinate point in the chunk.
		Coordinate coords = c.getCoords()[0][0];
		
		for (int y = 0; y < quadrant.size(); y++) {
			for (int x = 0; x < quadrant.get(y).size(); x++) {
				
				Coordinate check = quadrant.get(y).get(x).getCoords()[0][0];
				
				if (coords.equals(check)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isChunkLegal(Chunk chunk) {
		
		if (quadrant.size() > 0) {
			
			if (chunk.getCoordinateFromIndex(0, 0).xCoordinate >= 0 && chunk.getCoordinateFromIndex(0, 0).yCoordinate >= 0 && QUADRANT_NUMBER != 1 ||
				chunk.getCoordinateFromIndex(0, 0).xCoordinate < 0 && chunk.getCoordinateFromIndex(0, 0).yCoordinate >= 0 && QUADRANT_NUMBER != 2 ||
				chunk.getCoordinateFromIndex(0, 0).xCoordinate < 0 && chunk.getCoordinateFromIndex(0, 0).yCoordinate < 0 && QUADRANT_NUMBER != 3 ||
				chunk.getCoordinateFromIndex(0, 0).xCoordinate >= 0 && chunk.getCoordinateFromIndex(0, 0).yCoordinate < 0 && QUADRANT_NUMBER != 4) {
				return false;
			}
		}
		return true;
	}
	
	private void fillChunksInQuadrant(Chunk chunk) {
		int tempY;
		
		if (quadrant.size() > 0) {
			//Checks to see if the chunks coords are more than a chunk farther than the other chunks in the quadrant
			if (QUADRANT_NUMBER == 1) {
				tempY = 0;
				
				for (int y = 0; y <= chunk.Y_VALUE; y++) {
					if (quadrant.size() == y) {
						if (tempY != y) {
							tempY = y;
							quadrant.add(new ArrayList<Chunk>());
						}
					}
					for (int x = 0; x <= chunk.X_VALUE; x++) {
						Chunk fillerChunk = new Chunk(x, y);
						
						//If the chunk is not created, add the new chunk.
						if (!(isChunkCreated(fillerChunk))) {
							quadrant.get(y).add(fillerChunk);
						}
					}
				}
			}
			else if (QUADRANT_NUMBER == 2) {
				tempY = 0;
				
				for (int y = 0; y < chunk.Y_VALUE; y++) {
					if (quadrant.size() == y) {
						if (tempY != y) {
							tempY = y;
							quadrant.add(new ArrayList<Chunk>());
						}
					}
					for (int x = -1; x >= chunk.X_VALUE; x--) {
						Chunk fillerChunk = new Chunk(x, y + 1);
						
						//If the chunk is not created, add the new chunk.
						if (!(isChunkCreated(fillerChunk))) {
							quadrant.get(y).add(fillerChunk);
						}
					}
				}
			}
			else if (QUADRANT_NUMBER == 3) {
				tempY = -1;
				
				for (int y = -1; y >= chunk.Y_VALUE; y--) {
					if (quadrant.size() == Math.abs(y + 1)) {
						if (tempY != y) {
							tempY = y;
							quadrant.add(new ArrayList<Chunk>());
						}
					}
					for (int x = -1; x >= chunk.X_VALUE; x--) {
						Chunk fillerChunk = new Chunk(x, y);

						//If the chunk is not created, add the new chunk.
						if (!(isChunkCreated(fillerChunk))) {
							quadrant.get(Math.abs(y + 1)).add(fillerChunk);
						}
					}
				}
			}
			else if (QUADRANT_NUMBER == 4) {
				tempY = -1;
				
				for (int y = -1; y >= chunk.Y_VALUE; y--) {
					if (quadrant.size() == Math.abs(y + 1)) {
						if (tempY != y) {
							tempY = y;
							quadrant.add(new ArrayList<Chunk>());
						}
					}
					for (int x = 0; x <= chunk.X_VALUE; x++) {
						Chunk fillerChunk = new Chunk(x + 1, y);

						//If the chunk is not created, add the new chunk.
							if (!(isChunkCreated(fillerChunk))) {
								quadrant.get(Math.abs(y + 1)).add(fillerChunk);
							}
						}
					}
			}
		}
		else {
			//Adds the chunk to the quadrant
			quadrant.add(new ArrayList<Chunk>());
			quadrant.get(quadrant.size() - 1).add(chunk);
		}
	}
	
	/**
	 * getQuadrant returns what number the quadrant is on a graph.
	 * @return : int - either 1, 2, 3, or 4 representing what a traditional graph looks like. Returns 0 if nothing else works.
	 */
	public int getQuadrant() {
			return QUADRANT_NUMBER;
	}
	
	public ArrayList<ArrayList<Chunk>> getChunks() {
		return quadrant;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nQuadrant " + getQuadrant() + ":\n");
		sb.append(quadrant);
		return sb.toString();
	}
}
