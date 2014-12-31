package com.dreamstone.world;

import java.util.ArrayList;

import com.dreamstone.util.DystopiaLogger;

public class Quadrant {

	private ArrayList<ArrayList<Chunk>> quadrant;
	private final int QUADRANT_NUMBER;

	public Quadrant(Chunk startChunk) {
		quadrant = new ArrayList<>();
		if (startChunk.getCoordinate(0, 0).xCoordinate >= 0 && startChunk.getCoordinate(0, 0).yCoordinate >= 0) {
			System.out.println("Chunk should be added in Quadrant 1");
			QUADRANT_NUMBER = 1;
		}
		else if (startChunk.getCoordinate(0, 0).xCoordinate < 0 && startChunk.getCoordinate(0, 0).yCoordinate >= 0) {
			System.out.println("Chunk should be added in Quadrant 2");
			QUADRANT_NUMBER = 2;
		}
		else if (startChunk.getCoordinate(0, 0).xCoordinate < 0 && startChunk.getCoordinate(0, 0).yCoordinate < 0) {
			System.out.println("Chunk should be added in Quadrant 3");
			QUADRANT_NUMBER = 3;
		}
		else if (startChunk.getCoordinate(0, 0).xCoordinate >= 0 && startChunk.getCoordinate(0, 0).yCoordinate < 0) {
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
			System.out.println("(" + chunk.getCoordinate(0, 0).xCoordinate + ", " + chunk.getCoordinate(0, 0).yCoordinate + ")");
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
		
//		System.out.println(quadType.size());
		
		for (int y = 0; y < quadrant.size(); y++) {
			for (int x = 0; x < quadrant.get(y).size(); x++) {
				
				Coordinate check = quadrant.get(y).get(x).getCoords()[0][0];
//				System.out.println("Check: " + check + " vs Chunk: " + coords);
				
				if (coords.equals(check)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isChunkLegal(Chunk chunk) {
		
		if (quadrant.size() > 0) {
			
			if (chunk.getCoordinate(0, 0).xCoordinate >= 0 && chunk.getCoordinate(0, 0).yCoordinate >= 0 && QUADRANT_NUMBER != 1 ||
				chunk.getCoordinate(0, 0).xCoordinate < 0 && chunk.getCoordinate(0, 0).yCoordinate >= 0 && QUADRANT_NUMBER != 2 ||
				chunk.getCoordinate(0, 0).xCoordinate < 0 && chunk.getCoordinate(0, 0).yCoordinate < 0 && QUADRANT_NUMBER != 3 ||
				chunk.getCoordinate(0, 0).xCoordinate >= 0 && chunk.getCoordinate(0, 0).yCoordinate < 0 && QUADRANT_NUMBER != 4) {
				return false;
			}
		}
		return true;
	}
	
	private void fillChunksInQuadrant(Chunk chunk) {
		int tempY;
		
		if (quadrant.size() > 0) {
			//Checks to see if the chunks coords are more than a chunk farther than the other chunks in the quadrant
			//This one works!
			if (QUADRANT_NUMBER == 1) {
				tempY = 0;
				
//				System.out.println("HELLO Q1");
//				System.out.println("Quad Chunk Value: (" + quadrant.get(0).get(0).X_VALUE + ", " + quadrant.get(0).get(0).Y_VALUE + ")");
//				System.out.println("New Chunk Value: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
				for (int y = 0; y <= chunk.Y_VALUE; y++) {
					for (int x = 0; x <= chunk.X_VALUE; x++) {
						Chunk fillerChunk = new Chunk(x, y);
						
//						System.out.println("OLD Y: " + y + "NEW Y: " + y);
						//If the chunk is not created, add the new chunk.
						if (!(isChunkCreated(fillerChunk))) {
							if (tempY != y) {
								quadrant.add(new ArrayList<Chunk>());
								tempY = y;
							}
							
							quadrant.get(y).add(fillerChunk);
//							System.out.println("ADDING FILLER CHUNK!");
						}
					}
				}
//				System.out.println("QUADTYPE: (" + quadType.get(0).get(0).X_VALUE + ", " + quadType.get(0).get(0).Y_VALUE + ")");
//				System.out.println("CHUNK VALUE: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
			}
			else if (QUADRANT_NUMBER == 2) {
				tempY = 1;
				
//				System.out.println("HELLO Q2");
//				System.out.println("Chunk Value: (" + quadrant.get(0).get(0).X_VALUE + ", " + quadrant.get(0).get(0).Y_VALUE + ")");
//				System.out.println("Chunk Value: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
				for (int y = 1; y <= chunk.Y_VALUE; y++) {
					for (int x = -1; x >= chunk.X_VALUE; x--) {
						Chunk fillerChunk = new Chunk(x, y);
						
						//If the chunk is not created, add the new chunk.
						if (!(isChunkCreated(fillerChunk))) {
							if (tempY != y) {
								quadrant.add(new ArrayList<Chunk>());
								tempY = y;
							}
							
							quadrant.get(y - 1).add(fillerChunk);
//							System.out.println("ADDING FILLER CHUNK!");
						}
					}
				}
//				System.out.println("QUADTYPE: (" + quadType.get(0).get(0).X_VALUE + ", " + quadType.get(0).get(0).Y_VALUE + ")");
//				System.out.println("CHUNK VALUE: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
			}
			else if (QUADRANT_NUMBER == 3) {
				tempY = -1;
				
//				System.out.println("HELLO Q3");
//				System.out.println("Chunk Value: (" + quadrant.get(0).get(0).X_VALUE + ", " + quadrant.get(0).get(0).Y_VALUE + ")");
//				System.out.println("Chunk Value: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
				for (int y = -1; y >= chunk.Y_VALUE; y--) {
					for (int x = -1; x >= chunk.X_VALUE; x--) {
						Chunk fillerChunk = new Chunk(x, y);

						//If the chunk is not created, add the new chunk.
						if (!(isChunkCreated(fillerChunk))) {
							if (tempY != y) {
								quadrant.add(new ArrayList<Chunk>());
								tempY = y;
							}

							quadrant.get(Math.abs(y + 1)).add(fillerChunk);
//							System.out.println("ADDING FILLER CHUNK!");
						}
					}
				}
//				System.out.println("QUADTYPE: (" + quadType.get(0).get(0).X_VALUE + ", " + quadType.get(0).get(0).Y_VALUE + ")");
//				System.out.println("CHUNK VALUE: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
			}
			else if (QUADRANT_NUMBER == 4) {
				tempY = -1;
				
//				System.out.println("HELLO Q4");
//				System.out.println("Chunk Value: (" + quadrant.get(0).get(0).X_VALUE + ", " + quadrant.get(0).get(0).Y_VALUE + ")");
//				System.out.println("Chunk Value: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
				for (int y = -1; y >= chunk.Y_VALUE; y--) {
					for (int x = 1; x <= chunk.X_VALUE; x++) {
						Chunk fillerChunk = new Chunk(x, y);

						//If the chunk is not created, add the new chunk.
						if (!(isChunkCreated(fillerChunk))) {
							if (tempY != y) {
								quadrant.add(new ArrayList<Chunk>());
								tempY = y;
							}

							quadrant.get(Math.abs(y + 1)).add(fillerChunk);
//							System.out.println("ADDING FILLER CHUNK!");
						}
					}
				}
//				System.out.println("QUADTYPE: (" + quadType.get(0).get(0).X_VALUE + ", " + quadType.get(0).get(0).Y_VALUE + ")");
//				System.out.println("CHUNK VALUE: (" + chunk.X_VALUE + ", " + chunk.Y_VALUE + ")");
			}
		}
		else {
			//Adds the chunk to the quadrant
			quadrant.add(new ArrayList<Chunk>());
			quadrant.get(quadrant.size() - 1).add(chunk);
//			System.out.println("ADDING CHUNK!");
		}
	}
	
/*	public void fillQuadrantChunks() {
		int tempY = 0;
		
		//Checks to see if the edge of quadrant 2 and edge of quadrant 1 create a line. If not it fills in chunks.
		int Q2YValue = quad2.get(quad2.size() - 1).get(0).getCoordinate(0, Chunk.CHUNK_SIZE - 1).yCoordinate;
		int Q2XValue = quad2.get(quad2.size() - 1).get(0).getCoordinate(0, Chunk.CHUNK_SIZE - 1).xCoordinate;
		
		int Q1YValue = quad1.get(quad1.size() - 1).get(0).getCoordinate(0, Chunk.CHUNK_SIZE - 1).yCoordinate;
		int Q1XValue = quad1.get(quad1.size() - 1).get(0).getCoordinate(0, Chunk.CHUNK_SIZE - 1).xCoordinate;
		
//		System.out.println("(" + Q1XValue + ", " + Q1YValue + ")");
//		System.out.println("(" + Q2XValue + ", " + Q2YValue + ")");
		
		if (Q2YValue > Q1YValue) {
			
			System.out.println(quad1.get(quad1.size() - 1).get(0).Y_VALUE);
			System.out.println(quad2.get(quad2.size() - 1).get(0).Y_VALUE);
			
			for (int y = quad1.get(quad1.size() - 1).get(0).Y_VALUE; y <= quad2.get(quad2.size() - 1).get(0).Y_VALUE; y++) {
				for (int x = 0; x <= quad1.get(0).size() - 1; x++) {
					Chunk fillerChunk = new Chunk(x, y);
					//If the chunk is not created, add the new chunk.
					if (!(isChunkCreated(quad1, fillerChunk))) {
						if (tempY != y) {
							quad1.add(new ArrayList<Chunk>());
							tempY = y;
						}
						
						quad1.get(y).add(fillerChunk);
						System.out.println("ADDING FILLER CHUNK!");
					}
				}
			}
			
			System.out.println("Quad 2's Y value is bigger! " + Q2YValue);
			System.out.println("Quad 1's Y value: " + Q1YValue);
		}
		else if (Q2YValue < Q1YValue) {
			
			System.out.println(quad2.get(quad2.size() - 1).get(0).Y_VALUE);
			System.out.println(quad1.get(quad1.size() - 1).get(0).Y_VALUE);
			
			System.out.println("THIS IS THE X VALUE" + quad2.get(quad2.size() - 1).get(0).X_VALUE);
			
			for (int y = quad2.get(quad2.size() - 1).get(0).Y_VALUE; y <= quad1.get(quad1.size() - 1).get(0).Y_VALUE; y++) {
				for (int x = -1; x > quad2.get(quad2.size() - 1).get(0).X_VALUE; x--) {
					System.out.println("THIS IS X: " + x);
					Chunk fillerChunk = new Chunk(x, y);
					//If the chunk is not created, add the new chunk.
					if (!(isChunkCreated(quad2, fillerChunk))) {
						if (tempY != y) {
							quad2.add(new ArrayList<Chunk>());
							tempY = y;
						}
						
						quad2.get(y).add(fillerChunk);
						System.out.println("ADDING FILLER CHUNK!");
					}
				}
			}
			
			System.out.println("Quad 1's Y value is bigger! " + Q1YValue);
			System.out.println("Quad 2's Y value: " + Q2YValue);
		}
	}*/
	
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
