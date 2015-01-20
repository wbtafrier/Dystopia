package com.dreamstone.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.dreamstone.tile.EnumDirection;
import com.dreamstone.util.TransformImage;

public class EntityImageStorage {
	
	private ArrayList<BufferedImage> images;
	private final BufferedImage idleStrip;
	private final BufferedImage northAnimationStrip;
	private final BufferedImage southAnimationStrip;
	private final BufferedImage eastAnimationStrip;
	private final BufferedImage westAnimationStrip;
	public int animationIterator;
	
	public EntityImageStorage(BufferedImage idleStrip, BufferedImage northStrip, BufferedImage southStrip, BufferedImage eastStrip, BufferedImage westStrip) {
		this.idleStrip = idleStrip;
		this.northAnimationStrip = northStrip;
		this.southAnimationStrip = southStrip;
		this.eastAnimationStrip = eastStrip;
		this.westAnimationStrip = westStrip;
		this.initiaizeImageList(this.idleStrip, this.northAnimationStrip, this.southAnimationStrip, this.eastAnimationStrip, this.westAnimationStrip);
		animationIterator = 0;
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
	
	public BufferedImage[] getEastAnimationStrip() {
		return TransformImage.splitAnimation(eastAnimationStrip, 8);
	}
	
	public BufferedImage[] getWestAnimationStrip() {
		return TransformImage.splitAnimation(westAnimationStrip, 8);
	}
	
	
	//THIS IS FOR UPDATING COLOR
//	public void updateImages(EntityCharacteristics characteristics) {
//		
//		int[] imagePixels;
//		int width;
//		int height;
//		
//		for (BufferedImage bi : images) {
//			imagePixels = ((DataBufferInt)bi.getRaster().getDataBuffer()).getData();
//			width = bi.getWidth();
//			height = bi.getHeight();
//			
//			for (int y = 0; y < height; y++) {
//				for (int x = 0; x < width; x++) {
//					
//					
//					
//				}
//			}
//		}
//	}
	
	private ArrayList<BufferedImage> initiaizeImageList(BufferedImage... imageList) {
		images = new ArrayList<>();
		for (int i = 0; i < imageList.length; i++) {
			images.add(imageList[i]);
		}
		return images;
	}
}
