package com.dreamstone.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.file.ResourceLoader;
import com.dreamstone.tile.Tile;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.util.TransformImage;
import com.dreamstone.world.Chunk;
import com.dreamstone.world.Coordinate;
import com.dreamstone.world.Grid;
import com.dreamstone.world.Quadrant;

public class GridDisplay {

	private static BufferedImage loadTileImage(Tile t) {
		String tileImgName = t.getImageName();
		if (tileImgName.equals("terrain")) {
			BufferedImage terrain = ResourceLoader.terrainSheet;
			ArrayList<BufferedImage> tiles = TransformImage.splitImage(terrain, 256, 256);
			return tiles.get(t.getIndex());
		}
		else {
			//TODO: return pink square as separate image
			DystopiaLogger.logSevere("Failed to load tile " + t.getName() + " at index " + t.getIndex() + " from terrain.png");
			return ResourceLoader.nullImage;
		}
	}
	
	static void drawGrid(Graphics2D display, Grid grid) {
		BufferedImage terrain = ResourceLoader.terrainSheet;
		ArrayList<BufferedImage> rawr = new ArrayList<>();
		rawr = TransformImage.splitImage(terrain, 16, 16);

		BufferedImage grass = TransformImage.scaleImage(rawr.get(17), 4F);

		ArrayList<Quadrant> quads = grid.QUADRANTS;
		ArrayList<ArrayList<Chunk>> chunks;

		int screenWidth = DisplayCarrier.getCanvas().getWidth();
		int screenHeight = DisplayCarrier.getCanvas().getHeight();
		FontRenderContext context = display.getFontRenderContext();

		Font f = new Font("Comic Sans MS", Font.PLAIN, 16);
		display.setFont(f);

		for (int i = 0; i < quads.size(); i++) {
			chunks = quads.get(i).getChunks();
			for (int y = 0; y < chunks.size(); y++) {
				for (int x = 0; x < chunks.get(y).size(); x++) {
					for (int yy = 0; yy < Chunk.CHUNK_SIZE; yy++) {
						for (int xx = 0; xx < Chunk.CHUNK_SIZE; xx++) {
							Coordinate c = chunks.get(y).get(x).getCoordinate(xx, yy);
							int startX = 0, startY = 0;
							float strX, strY, ascent, baseY;

							if (i == 0) {
								startX = screenWidth / 2 + c.xCoordinate * grass.getWidth();
								startY = screenHeight / 2 - (c.yCoordinate + 1) * grass.getHeight();
								display.drawImage(grass, startX, startY, null);
								
								String coord = "(" + Integer.toString(c.xCoordinate) + ", " + Integer.toString(c.yCoordinate) + ")";
								display.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
								Rectangle2D bounds = f.getStringBounds(coord, context);

								strX = (float) (startX + (grass.getWidth() / 2 - bounds.getWidth() / 2));
								strY = (float) (startY + (grass.getHeight() / 2 - bounds.getHeight() / 2));
								ascent = (float) -bounds.getY();
								baseY = strY + ascent;


								display.drawString(coord, (int) strX, (int) baseY);
							}
							else if (i == 1) {
								startX = screenWidth / 2 - Math.abs(c.xCoordinate) * grass.getWidth();
								startY = screenHeight / 2 - (c.yCoordinate + 1) * grass.getHeight();
								display.drawImage(grass, startX, startY, null);
								
								String coord = "(" + Integer.toString(c.xCoordinate) + ", " + Integer.toString(c.yCoordinate) + ")";
								display.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
								Rectangle2D bounds = f.getStringBounds(coord, context);

								strX = (float) (startX + (grass.getWidth() / 2 - bounds.getWidth() / 2));
								strY = (float) (startY + (grass.getHeight() / 2 - bounds.getHeight() / 2));
								ascent = (float) -bounds.getY();
								baseY = strY + ascent;

								display.drawString(coord, (int) strX, (int) baseY);
							}
							else if (i == 2) {
								startX = screenWidth / 2 - Math.abs(c.xCoordinate) * grass.getWidth();
								startY = screenHeight / 2 + Math.abs(c.yCoordinate + 1) * grass.getHeight();
								display.drawImage(grass, startX, startY, null);
								
								String coord = "(" + Integer.toString(c.xCoordinate) + ", " + Integer.toString(c.yCoordinate) + ")";
								display.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
								Rectangle2D bounds = f.getStringBounds(coord, context);

								strX = (float) (startX + (grass.getWidth() / 2 - bounds.getWidth() / 2));
								strY = (float) (startY + (grass.getHeight() / 2 - bounds.getHeight() / 2));
								ascent = (float) -bounds.getY();
								baseY = strY + ascent;

								display.drawString(coord, (int) strX, (int) baseY);
							}
							else if (i == 3) {
								startX = screenWidth / 2 + c.xCoordinate * grass.getWidth();
								startY = screenHeight / 2 + Math.abs(c.yCoordinate + 1) * grass.getHeight();
								display.drawImage(grass, startX, startY, null);
								
								String coord = "(" + Integer.toString(c.xCoordinate) + ", " + Integer.toString(c.yCoordinate) + ")";
								display.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
								Rectangle2D bounds = f.getStringBounds(coord, context);

								strX = (float) (startX + (grass.getWidth() / 2 - bounds.getWidth() / 2));
								strY = (float) (startY + (grass.getHeight() / 2 - bounds.getHeight() / 2));
								ascent = (float) -bounds.getY();
								baseY = strY + ascent;

								display.drawString(coord, (int) strX, (int) baseY);
							}
						}
					}
				}
			}
		}

		display.setColor(Color.BLACK);
		display.drawLine(0, screenHeight / 2, screenWidth, screenHeight / 2);
		display.drawLine(screenWidth / 2, screenHeight, screenWidth / 2, 0);
	}
}
