package com.dreamstone.world;

import java.util.Random;

import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.TileList;

public class TestMap {
	
	private static final int CHUNKS = Chunk.CHUNK_SIZE * 2;
	
	public static void testWorld() {
		
		Random rand = new Random();
		
		for (int y = -CHUNKS; y < CHUNKS; y++) {
			for (int x = -CHUNKS; x < CHUNKS; x++) {
				Dystopia.getGame().grid.setTile(x, y, TileList.tiles.get(rand.nextInt(TileList.tiles.size())));
			}
		}
	}
}
