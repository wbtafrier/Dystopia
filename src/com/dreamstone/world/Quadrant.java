package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.util.DystopiaLogger;

public class Quadrant {

	private ArrayList<ArrayList<Chunk>> quadrant;
	private final int QUADRANT_NUMBER;

	/**
	 * Initializes this Quadrant with a starting Chunk and determines what Quadrant this is based on the Chunk's starting Coordinate values.
	 */
	public Quadrant(Chunk startChunk) {
		quadrant = new ArrayList<>();
		if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate >= 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate >= 0) {
			QUADRANT_NUMBER = 1;
		}
		else if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate < 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate >= 0) {
			QUADRANT_NUMBER = 2;
		}
		else if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate < 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate < 0) {
			QUADRANT_NUMBER = 3;
		}
		else if (startChunk.getCoordinateFromIndex(0, 0).xCoordinate >= 0 && startChunk.getCoordinateFromIndex(0, 0).yCoordinate < 0) {
			QUADRANT_NUMBER = 4;
		}
		else {
			DystopiaLogger.logWarning("Chunk is not valid! Setting value to 0.");
			QUADRANT_NUMBER = 0;
		}
		
		growQuadrant(startChunk);
	}

	/**
	 * Add a Chunk to this Quadrant, and add any other necessary Chunks (with null Tiles in each additional Chunk).
	 * This does NOT replace an already existing Chunk!
	 * @param chunk The chunk to add to this Quadrant.
	 */
	public void growQuadrant(Chunk chunk) {
		
		if (!(isChunkLegal(chunk))) {
			System.out.println("(" + chunk.getCoordinateFromIndex(0, 0).xCoordinate + ", " + chunk.getCoordinateFromIndex(0, 0).yCoordinate + ")");
			DystopiaLogger.logWarning("THIS CHUNK CANNOT BE ADDED TO QUADRANT " + getQuadrantNumber() + ". RETURNING.");
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
	
	public void replaceChunk(Chunk chunk) {
		if (!isChunkLegal(chunk)) {
			DystopiaLogger.logWarning("CHUNK WITH STARTING COORDS (" + chunk.getStartingCoord().xCoordinate + ", "
					+ chunk.getStartingCoord().yCoordinate + ") IS NOT LEGAL AND CANNOT BE REPLACED IN QUADRANT " + getQuadrantNumber() + "!");
			return;
		}
		if (!isChunkCreated(chunk)) {
			this.growQuadrant(chunk);
		}
		else {
			
			for (int y = 0; y < this.quadrant.size(); y++) {
				for (int x = 0; x < this.quadrant.get(y).size(); x++) {
					
					if (this.getChunks().get(y).get(x).getStartingCoord().xCoordinate == chunk.getStartingCoord().xCoordinate &&
						this.getChunks().get(y).get(x).getStartingCoord().yCoordinate == chunk.getStartingCoord().yCoordinate) {
						
						for (int yy = 0; yy < Chunk.CHUNK_SIZE; yy++) {
							for (int xx = 0; xx < Chunk.CHUNK_SIZE; xx++) {
								
								this.getChunks().get(y).get(x).getCoordinateFromIndex(xx, yy).setCoordinate(chunk.getCoordinateFromIndex(xx, yy));
							}
						}
					}
					
				}
			}
			
//			for (int y = 0; y < this.quadrant.size(); y++) {
//				ArrayList<Chunk> col = this.quadrant.get(y);
//				for (int x = 0; x < col.size(); x++) {
//					Chunk currentChunk = col.get(x);
//					if (currentChunk.getStartingCoord().xCoordinate == chunk.getStartingCoord().xCoordinate &&
//							currentChunk.getStartingCoord().yCoordinate == chunk.getStartingCoord().yCoordinate) {
//						col.set(x, chunk);
//					}
//				}
//			}
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
					for (int x = 0; x < chunk.X_VALUE; x++) {
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
	public int getQuadrantNumber() {
			return QUADRANT_NUMBER;
	}
	
	public ArrayList<ArrayList<Chunk>> getChunks() {
		return quadrant;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nQuadrant " + getQuadrantNumber() + ":\n");
		sb.append(quadrant);
		return sb.toString();
	}
}