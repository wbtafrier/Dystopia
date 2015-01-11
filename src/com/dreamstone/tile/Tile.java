package com.dreamstone.tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.dreamstone.graphics.GraphicsOptions;
import com.dreamstone.util.DystopiaLogger;

public abstract class Tile {
	
	private String tileName = "null";
	private String tileSheetName = "null";
	protected ArrayList<BufferedImage> defaultImages;
	protected int tileSize = (int)(16 * GraphicsOptions.getScale());
	
	/**
	 * Instantiating this constructor will create a new Tile with a specific name and independent image file.
	 * @param name : The identity or ID of the Tile.
	 */
	public Tile(String name) {
		this(name, "terrain.png");
	}
	
	public Tile(String name, String tileSheetName) {
		this.tileName = name;
		this.tileSheetName = tileSheetName;
		this.defaultImages = new ArrayList<>();
	}
	
	protected abstract void setImageTiles();
	
	public BufferedImage getImageTile(int index) {
		if (!(index >= defaultImages.size() || index < 0)) {
			return defaultImages.get(index);
		}
		else {
			DystopiaLogger.logWarning("THE INDEX ASKED FOR IS NOT IN THE ARRAYLIST. RETURNING IMAGE AT INDEX 0.");
			return defaultImages.get(0);
		}
	}
	
	public int getFullImageAmount() {
		return defaultImages.size();
	}
	
	/**
	 * Sets the ID of this Tile.
	 * @param name : Name of Tile as String.
	 */
	protected void setName(String name) {
		this.tileName = name;
	}
	
	public String getName() {
		return this.tileName;
	}
	
	/**
	 * Sets the Tilesheet name of this Tile.
	 * @param tileSheetName : Name of Tile as String.
	 */
	protected void setTileSheetName(String tileSheetName) {
		this.tileSheetName = tileSheetName;
	}
	
	public String getTileSheetName() {
		return this.tileSheetName;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
