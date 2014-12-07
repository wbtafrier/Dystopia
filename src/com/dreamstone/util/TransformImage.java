package com.dreamstone.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The TransformImage class is a static class that comes with a variety of methods designed to help with transforming images.
 */
public class TransformImage {
	
	/*
	 * FUTURE METHODS TO ADD:
	 * ===========================
	 * Rotate method
	 * scale method
	 * splitAnimation method
	 * crop method
	 * color effect method (changing hues, saturation, brightness, grayscale, etc). <-- Maybe - would be useful for transitioning from night to day.
	 */
	
	/**
	 * flipVertically takes in a BufferedImage, flips the image vertically, and returns the new image.
	 * @param bi : the BufferedImage you want flipped.
	 * @return : a new BufferedImage that is flipped vertically.
	 */
	public static BufferedImage flipVertically(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		
		BufferedImage flippedImage = new BufferedImage(width, height, bi.getType());
		
		Graphics2D g = flippedImage.createGraphics();
		g.drawImage(bi, 0, 0, width, height, 0, height, width, 0, null);
		g.dispose();
		return flippedImage;
	}
	
	/**
	 * flipHorizontally takes in a BufferedImage, flips the image horizontally, and returns the new image.
	 * @param bi : the BufferedImage you want flipped.
	 * @return : a new BufferedImage that is flipped horizontally.
	 */
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
