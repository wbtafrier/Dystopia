package com.dreamstone.world;

import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.TileList;
import com.dreamstone.tile.TileTransitionList;


public class TestMap {
	
	public static void testWorld(Coordinate c) {
		
		int x = c.xCoordinate, y = c.yCoordinate;
		
		if (x == 0 && y == 0) {
			Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileList.grass1);
		}
		else if (x == -1 && y == 0) {
			Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileTransitionList.grassToDirtWest1);
		}
		else if (x == 1 && y == 0) {
			Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileTransitionList.grassToDirtEast1);
		}
		else if (x == -1 && y == -1) {
			Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileTransitionList.grassToDirtSouthWest1);
		}
		else if (x == 0 && y == -1) {
			Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileTransitionList.grassToDirtSouth1);
		}
		else if (x == 1 && y == -1) {
			Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileTransitionList.grassToDirtSouthEast1);
		}
		else {
			int r = (int) (Math.random() * 2);
			if (r == 0) {
				Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileList.dirt1);
			}
			else if (r == 1) {
				Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileList.dirt2);
			}
		}
		
		//Dystopia.grid.setTile(c.xCoordinate, c.yCoordinate, TileList.grass1);
	}
}
