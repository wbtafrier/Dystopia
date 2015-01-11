package com.dreamstone.tile;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public class TileNull extends Tile {

	public TileNull(String name) {
		super(name);
		this.setImageTiles();
	}

	@Override
	protected void setImageTiles() {
		defaultImages.add(TransformImage.getSubImageFromIndex(ResourceLoader.getTileSheet(this.getTileSheetName()), this.tileSize, this.tileSize, 255));
	}
}
