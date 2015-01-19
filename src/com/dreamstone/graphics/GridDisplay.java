package com.dreamstone.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.tile.Tile;
import com.dreamstone.util.DebugSettings;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.Grid;
import com.dreamstone.world.Quadrant;

public class GridDisplay {

	static void drawGrid(Graphics2D display) {
		Grid grid = Dystopia.getGame().currentWorld.getGrid();
		ArrayList<Quadrant> quads = grid.QUADRANTS;
		ArrayList<ArrayList<Chunk>> chunks;
		BufferedImage tileImg;
		float strX, strY, ascent, baseY;
		String coord;

		int screenWidth = DisplayCarrier.getCanvas().getWidth();
		int screenHeight = DisplayCarrier.getCanvas().getHeight();
		int xCenter = (screenWidth / 2);
		int yCenter = (screenHeight / 2);
		FontRenderContext context = display.getFontRenderContext();

		Font f = new Font("Comic Sans MS", Font.PLAIN, 16);
		display.setFont(f);
		
		for (int i = 0; i < quads.size(); i++) {
			chunks = quads.get(i).getChunks();
			for (int y = 0; y < chunks.size(); y++) {
				for (int x = 0; x < chunks.get(y).size(); x++) {
					for (int yy = 0; yy < Chunk.CHUNK_SIZE; yy++) {
						for (int xx = 0; xx < Chunk.CHUNK_SIZE; xx++) {
							Coordinate c = chunks.get(y).get(x).getCoordinateFromIndex(xx, yy);
							
							if (c.getTile() == null) {
								continue;
							}
							
							tileImg = c.getImage();
							
							if (i == 0) {
								c.setXScreenPosition(xCenter + (c.xCoordinate * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getXOffset());
								c.setYScreenPosition(yCenter - ((c.yCoordinate + 1) * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getYOffset());
							}
							else if (i == 1) {
								c.setXScreenPosition(xCenter - (Math.abs(c.xCoordinate) * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getXOffset());
								c.setYScreenPosition(yCenter - ((c.yCoordinate + 1) * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getYOffset());
							}
							else if (i == 2) {
								c.setXScreenPosition(xCenter - (Math.abs(c.xCoordinate) * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getXOffset());
								c.setYScreenPosition(yCenter + (Math.abs(c.yCoordinate + 1) * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getYOffset());
							}
							else if (i == 3) {
								c.setXScreenPosition(xCenter + (c.xCoordinate * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getXOffset());
								c.setYScreenPosition(yCenter + (Math.abs(c.yCoordinate + 1) * Tile.getTileSize()) + Dystopia.getGame().currentWorld.getYOffset());
							}
							
							display.drawImage(tileImg, c.getXScreenPos(), c.getYScreenPos(), null);
							
							if (DebugSettings.SHOW_GRIDLINES) {
								display.setColor(Color.DARK_GRAY);
								display.drawRect(c.getXScreenPos(), c.getYScreenPos(), Tile.getTileSize(), Tile.getTileSize());
							}
							
							if (DebugSettings.SHOW_COORDS) {
								display.setColor(Color.WHITE);
								coord = "(" + Integer.toString(c.xCoordinate) + ", " + Integer.toString(c.yCoordinate) + ")";
//								coord = "(" + Integer.toString(c.getXScreenPos()) + ", " + Integer.toString(c.getYScreenPos()) + ")";
								
//								coord = c.getTile().getName();
								display.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
								Rectangle2D bounds = f.getStringBounds(coord, context);
								
								strX = (float) (c.getXScreenPos() + (Tile.getTileSize() / 2 - bounds.getWidth() / 2));
								strY = (float) (c.getYScreenPos() + (Tile.getTileSize() / 2 - bounds.getHeight() / 2));
								
								ascent = (float) -bounds.getY();
								baseY = strY + ascent;
								
								display.drawString(coord, (int) strX, (int) baseY);
							}
						}
					}
				}
			}
		}
		
		if (DebugSettings.SHOW_AXES) {
			display.setColor(Color.BLACK);
			display.setStroke(new BasicStroke(4));
			display.drawLine(0, grid.getCoordinate(0, -1).getYScreenPos(), screenWidth, grid.getCoordinate(0, -1).getYScreenPos());
			display.drawLine(grid.getCoordinate(0, 0).getXScreenPos(), screenHeight, grid.getCoordinate(0, 0).getXScreenPos(), 0);
			display.setStroke(new BasicStroke(1));
		}
	}
}