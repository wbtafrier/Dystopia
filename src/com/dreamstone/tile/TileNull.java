package com.dreamstone.tile;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public class TileNull extends Tile {

	public TileNull(String name) {
		super(name);
		this.initializeImageTiles();
	}

	@Override
	protected void initializeImageTiles() {
		defaultImages.add(TransformImage.getSubImageFromIndex(ResourceLoader.getTileSheet(this.getTileSheetName()), tileSize, tileSize, 255));
	}

	@Override
	public int setRandomImageIndex() {
		return 0;
	}
}
