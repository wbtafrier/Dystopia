package com.dreamstone.file;

import java.awt.image.BufferedImage;

import com.dreamstone.tile.EnumDirection;
import com.dreamstone.util.TransformImage;

public class EntityImage {
	
	private BufferedImage idleStrip;
	private BufferedImage northAnimationStrip;
	private BufferedImage southAnimationStrip;
	private BufferedImage eastAnimationStrip;
	private BufferedImage westAnimationStrip;
	
	public EntityImage(BufferedImage idleStrip, BufferedImage northStrip, BufferedImage southStrip, BufferedImage walkingHorizontalStrip) {
		this.idleStrip = idleStrip;
		this.northAnimationStrip = northStrip;
		this.southAnimationStrip = southStrip;
		this.eastAnimationStrip = walkingHorizontalStrip;
		this.westAnimationStrip = TransformImage.flipHorizontally(walkingHorizontalStrip);
	}

	public BufferedImage getIdleImage(EnumDirection dir) {
		
		BufferedImage[] idleImages = TransformImage.splitAnimation(idleStrip, 3);
		
		switch(dir) {
			case EAST: return idleImages[1];
			case WEST: return TransformImage.flipHorizontally(idleImages[1]);
			case NORTH: return idleImages[2];
			default: return idleImages[0];
		}
	}
	
	public BufferedImage getNorthImage(int index) {
		BufferedImage[] northAnimationStripImages = TransformImage.splitAnimation(northAnimationStrip, 4);
		
		if (index >= northAnimationStripImages.length) {
			return northAnimationStripImages[0];
		}
		else {
			return northAnimationStripImages[index];
		}
	}
	
	public BufferedImage getSouthImage(int index) {
		BufferedImage[] southAnimationStripImages = TransformImage.splitAnimation(southAnimationStrip, 4);
		
		if (index >= southAnimationStripImages.length) {
			return southAnimationStripImages[0];
		}
		else {
			return southAnimationStripImages[index];
		}
	}
	
	public BufferedImage getEastImage(int index) {
		BufferedImage[] eastAnimationStripImages = TransformImage.splitAnimation(eastAnimationStrip, 8);
		
		if (index >= eastAnimationStripImages.length) {
			return eastAnimationStripImages[0];
		}
		else {
			return eastAnimationStripImages[index];
		}
	}
	
	public BufferedImage getWestImage(int index) {
		BufferedImage[] westAnimationStripImages = TransformImage.splitAnimation(westAnimationStrip, 8);
		
		if (index >= westAnimationStripImages.length) {
			return westAnimationStripImages[0];
		}
		else {
			return westAnimationStripImages[index];
		}
	}
	
	public BufferedImage[] getNorthAnimationStrip() {
		return TransformImage.splitAnimation(northAnimationStrip, 4);
	}
	
	public BufferedImage[] getSouthAnimationStrip() {
		return TransformImage.splitAnimation(southAnimationStrip, 4);
	}
	
	public BufferedImage[] getEasthAnimationStrip() {
		return TransformImage.splitAnimation(eastAnimationStrip, 8);
	}
	
	public BufferedImage[] getWestAnimationStrip() {
		return TransformImage.splitAnimation(westAnimationStrip, 8);
	}
}
