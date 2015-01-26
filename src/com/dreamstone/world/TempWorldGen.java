package com.dreamstone.world;

import java.util.ArrayList;
import java.util.Random;

import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.TileList;

public class TempWorldGen {
	
	public static void setTilesForTestWorld() {
		
//		Random rand = new Random();
		ArrayList<Quadrant> quads = Dystopia.getGame().getCurrentWorld().getGrid().QUADRANTS;
		ArrayList<ArrayList<Chunk>> chunks;
		
		for (int i = 0; i < quads.size(); i++) {
			chunks = quads.get(i).getChunks();
			for (int y = 0; y < chunks.size(); y++) {
				for (int x = 0; x < chunks.get(y).size(); x++) {
					for (int yy = 0; yy < Chunk.CHUNK_SIZE; yy++) {
						for (int xx = 0; xx < Chunk.CHUNK_SIZE; xx++) {
							Coordinate c = chunks.get(y).get(x).getCoordinateFromIndex(xx, yy);
//							c.setTileType(TileList.tiles.get(rand.nextInt(TileList.tiles.size())));
							c.setTileType(TileList.tiles.get(1));
						}
					}
				}
			}
		}
	}
}
