package com.dreamstone.tile;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public class TileDirt extends TileTransition {

	public TileDirt(String name, String tileSheetName) {
		super(name, tileSheetName);
		this.setImageTiles();
	}
	
	@Override
	protected void setImageTiles() {
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
	
}
