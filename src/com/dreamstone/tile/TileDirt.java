package com.dreamstone.tile;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public class TileDirt extends TileTransition {

	public TileDirt(String name, String tileSheetName) {
		super(name, tileSheetName);
		this.initializeImageTiles();
	}
	
	@Override
	protected void initializeImageTiles() {
		//Sets full images
		defaultImages.add(TransformImage.getSubImageFromIndex(ResourceLoader.getTileSheet(this.getTileSheetName()), tileSize, tileSize, 23));
		defaultImages.add(TransformImage.getSubImageFromIndex(ResourceLoader.getTileSheet(this.getTileSheetName()), tileSize, tileSize, 26));
	}
	
	@Override
	protected void setTransitionImageTiles() {
		//Northwest Images
		
		//North Images
		
		//Northeast Images
		
		//West Images
		
		//East Images
		
		//Southwest Image
		
		//South Image
		
		//Southeast Image
	}

	@Override
	public int setRandomImageIndex() {
		
		int percent = rand.nextInt(100);
		
		if (percent >= 50)
			return 0;
		else
			return 1;
	}
}
