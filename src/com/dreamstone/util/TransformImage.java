package com.dreamstone.util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.dreamstone.core.DisplayCarrier;

/**
 * The TransformImage class is a static class that comes with a variety of methods designed to help with manipulating images.
 */
public class TransformImage {
	
	/*
	 * FUTURE METHODS TO ADD:
	 * ===========================
	 * splitImage method
	 * color effect method (changing hues, saturation, brightness, grayscale, etc).
	 */
	
	/**
	 * flipVertically takes in a BufferedImage, flips the image vertically, and returns the new image.
	 * @param bi : The BufferedImage you want flipped.
	 * @return : A new BufferedImage that is flipped vertically.
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
	 * @param bi : The BufferedImage you want flipped.
	 * @return : A new BufferedImage that is flipped horizontally.
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
	
	/**
	 * scaleImage takes in a BufferedImage and a percentage to scale the image with. Returns the new scaled image.
	 * @param bi : The original BufferedImage.
	 * @param percentage : The percentage you want your image scaled by. Must be greater than 0.
	 * @return : A new BufferedImage that is scaled to your liking.
	 */
	public static BufferedImage scaleImage(BufferedImage bi, float percentage) {
		
		if (percentage <= 0) {
			DystopiaLogger.logWarning("THE PERCENTAGE: " + percentage + " CANNOT BE LESS THAN OR EQUAL TO ZERO. RETURNING IMAGE WITH DEFAULT SIZE.");
			return bi;
		}

		int width = (int) ((bi.getWidth() * percentage) + 0.5);
		int height = (int) ((bi.getHeight() * percentage) + 0.5);
		System.out.println(width);
		
		BufferedImage scaledImage = new BufferedImage(width, height, bi.getType());
		Graphics2D g;
		g = scaledImage.createGraphics();
		g.drawImage(bi, 0, 0, width, height, null);
		g.dispose();
		return scaledImage;
	}
	
	/**
	 * rotateImage takes in a BufferedImage and the amount of degrees to rotate the image. Returns the new rotated image.
	 * NOTE: The size of the BufferedImage might be changed depending on the degrees rotated. Take this into account.
	 * @param bi : The original BufferedImage.
	 * @param degrees : The degrees you want rotated. The method converts this number to radians for you. If the number sent in is negative, 
	 * the rotation will occur counter-clockwise.
	 * @return : A new BufferedImage that is rotated the amount of degrees specified. 
	 */
	public static BufferedImage rotateImage(BufferedImage bi, float degrees) {
		
		double cos = Math.abs(Math.cos(Math.toRadians(degrees)));
        double sin = Math.abs(Math.sin(Math.toRadians(degrees)));
        double width = bi.getWidth();
        double height = bi.getHeight();
        int w = (int)(width * cos + height * sin);
        w = (w % 2 != 0) ? w + 1 : w;
        
        int h = (int)(width * sin + height * cos);
        h = (h % 2 != 0) ? h + 1 : h;
        
        BufferedImage rotatedImage = new BufferedImage(w, h, bi.getType());
        Graphics2D g2 = rotatedImage.createGraphics();
        
        double x = w / 2;
        double y = h / 2;
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(degrees), x, y);
        x = (w - width) / 2;
        y = (h - height) / 2;
        at.translate(x, y);
        g2.drawRenderedImage(bi, at);
        g2.dispose();
        return rotatedImage;
	}
	
	/**
	 * splitImage takes in an image and splits it up into a bunch of tiles based on the width and height provided.
	 * @param bi : The original BufferedImage to split.
	 * @param width : The width of the new images.
	 * @param height : The height of the new images.
	 * @return : An ArrayList of new small images from the original BufferedImage.
	 */
	public static ArrayList<BufferedImage> splitImage(BufferedImage bi, int width, int height) {
		if (bi.getWidth() % width != 0 || bi.getHeight() % height != 0) {
			DystopiaLogger.logSevere("It looks like there was a problem dividing up a tileset. :(");
			DisplayCarrier.getFrame().dispose();
			throw new IllegalArgumentException();
		}
		
		ArrayList<BufferedImage> splitImages = new ArrayList<>();
		
		int xTiles = bi.getWidth() / width;
		int yTiles = bi.getHeight() / height;
		
		for (int y = 0; y < yTiles; y++) {
			for (int x = 0; x < xTiles; x++) {
				BufferedImage tile = new BufferedImage(width, height, bi.getType());
				tile = bi.getSubimage(width * x, height * y, width, height);
				splitImages.add(tile);
			}
		}
		
		return splitImages;
	}
	
	public static BufferedImage getSubImageFromIndex(BufferedImage bi, int width, int height, int index) {
		if ((bi.getWidth() != 0 && bi.getHeight() != 0) && (bi.getWidth() % width != 0 || bi.getHeight() % height != 0)) {
			DystopiaLogger.logSevere("It looks like there was a problem retrieving a subimage. :(");
			DisplayCarrier.getFrame().dispose();
			throw new IllegalArgumentException();
		}
		
		ArrayList<BufferedImage> splitImages = new ArrayList<>();

		int xTiles = bi.getWidth() / width;
		int yTiles = bi.getHeight() / height;

		for (int y = 0; y < yTiles; y++) {
			for (int x = 0; x < xTiles; x++) {
				BufferedImage tile = new BufferedImage(width, height, bi.getType());
				tile = bi.getSubimage(width * x, height * y, width, height);
				splitImages.add(tile);
			}
		}
		return splitImages.get(index);
	}
	
	/**
	 * splitAnimation takes an image and splits it horizontally into a bunch of smaller images that are generally used for animation.
	 * NOTE: If the image is not divisible by the width, THE GAME FORCE CRASHES.
	 * @param bi : The original BufferedImage.
	 * @param width : The width of every frame.
	 * @return : An array of frames all with the same height and specifed width.
	 */
	public static BufferedImage[] splitAnimation(BufferedImage bi, int width) {
		
		if (bi.getWidth() % width != 0) {
			DystopiaLogger.logSevere("It looks like there was a problem dividing up an animation strip. :(");
			DisplayCarrier.getFrame().dispose();
			throw new IllegalArgumentException();
		}
		
		BufferedImage[] animation = new BufferedImage[bi.getWidth() / width];
		
		for (int i = 0; i < animation.length; i++) {
			BufferedImage frame = new BufferedImage(width, bi.getHeight(), bi.getType());
			frame = bi.getSubimage(width * i, 0, width, bi.getHeight());
			animation[i] = frame;
		}
		
		return animation;
	}
	
	/**
	 * This version of cropImage takes in a BufferedImage, startingX, startingY, endingX, and endingY, and returns a new image from those dimensions.
	 * NOTE: This method INCLUDES ending values.
	 * @param bi : The original BufferedImage.
	 * @param x1 : The coordinates of the starting X value where you want the cropping to begin.
	 * @param y1 : The coordinates of the starting Y value where you want the cropping to begin.
	 * @param x2 : The coordinates of the ending X value.
	 * @param y2 : The coordinates of the ending Y value.
	 * @return : A new cropped BufferedImage to your liking.
	 */
	public static BufferedImage cropImage(BufferedImage bi, float x1, float y1, float x2, float y2) {
		
		int startX = (int)(x1 + 0.5);
		int startY = (int)(y1 + 0.5);
		int endX = (int)(x2 + 0.5);
		int endY = (int)(y2 + 0.5);
		return cropImage(bi, startX, startY, endX, endY);
	}
	
	/**
	 * This version of cropImage takes in a BufferedImage, endingX, and endingY, and returns a new image starting from (0, 0) to the endingX and endingY dimensions.
	 * NOTE: This method INCLUDES ending values and defaults the starting position of the cropping to (0, 0).
	 * @param bi : The original BufferedImage.
	 * @param x2 : The coordinates of the ending X value.
	 * @param y2 : The coordinates of the ending Y value.
	 * @return : A new cropped BufferedImage to your liking.
	 */
	public static BufferedImage cropImage(BufferedImage bi, float x2, float y2) {
		
		int endX = (int)(x2 + 0.5);
		int endY = (int)(y2 + 0.5);
		return cropImage(bi, 0, 0, endX, endY);
	}
	
	/**
	 * A private helper version of the cropImage method that the public methods use.
	 * This method makes sure the coordinates sent in are within bounds of the image being cropped.
	 * @param bi : The original BufferedImage.
	 * @param x1 : The coordinates of the starting X value.
	 * @param y1 : The coordinates of the starting Y value.
	 * @param x2 : The coordinates of the ending X value.
	 * @param y2 : The coordinates of the ending Y value.
	 * @return : The cropped BufferedImage.
	 */
	private static BufferedImage cropImage(BufferedImage bi, int x1, int y1, int x2, int y2) {
		if (x1 < 0) {
			x1 = 0;
		}
		else if (x1 > bi.getWidth()) {
			x1 = bi.getWidth();
		}
		
		if (y1 < 0) {
			y1 = 0;
		}
		else if (y1 > bi.getHeight()) {
			y1 = bi.getHeight();
		}
		
		if (x2 < 1) {
			x2 = 1;
		}
		else if (x2 > bi.getWidth()) {
			x2 = bi.getWidth();
		}
		
		if (y2 < 1) {
			y2 = 1;
		}
		else if (y2 > bi.getHeight()) {
			y2 = bi.getHeight();
		}
		
		BufferedImage croppedImage = bi.getSubimage(x1, y1, x2, y2);
		return croppedImage;
	}
}
