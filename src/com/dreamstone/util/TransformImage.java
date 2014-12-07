package com.dreamstone.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TransformImage {

	public static BufferedImage flipVertically(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		
		BufferedImage flippedImage = new BufferedImage(width, height, bi.getType());
		
		Graphics2D g = flippedImage.createGraphics();
		g.drawImage(bi, 0, 0, width, height, 0, height, width, 0, null);
		g.dispose();
		return flippedImage;
	}
	
	public static BufferedImage flipHorizontally(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		
		BufferedImage flippedImage = new BufferedImage(width, height, bi.getType());
		
		Graphics2D g = flippedImage.createGraphics();
		g.drawImage(bi, 0, 0, width, height, width, 0, 0, height, null);
		g.dispose();
		return flippedImage;
	}
}
