package com.dreamstone.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import com.dreamstone.core.DisplayCarrier;

public class DystopiaRenderer {
	
	public static BufferedImage display;
	private static int[] pixels;
	private static int width;
	private static int height;
	
	static void render(Graphics2D g, int xOffset, int yOffset) {
		
		Random rand = new Random();
		int xx, yy, r;
		
		display = new BufferedImage((int)DisplayCarrier.getCanvas().getWidth(), (int)DisplayCarrier.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)display.getRaster().getDataBuffer()).getData();
		
		setDimensions();
		for (int y = 0; y < height; y++) {
			yy = y + yOffset;
			if (yy < 0 || yy >= height) {
				continue;
			}
			for (int x = 0; x < width; x++) {
				xx = x + xOffset;
				if (xx < 0 || xx >= width) {
					continue;
				}
				r = (rand.nextInt());
				pixels[xx + (yy * display.getWidth())] = r;
			}
		}
		g.drawImage(display, 0, 0, null);
	}
	
	private static void setDimensions() {
		width = DisplayCarrier.getCanvas().getWidth();
		height = DisplayCarrier.getCanvas().getHeight();
	}
}
