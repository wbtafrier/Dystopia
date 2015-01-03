package com.dreamstone.tile;

import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.DystopiaLogger;

public class Tile {
	
	private String tileName = "null";
	private String imageName;
	private int index = 0;
	
	/**
	 * Instantiating this constructor will create a new Tile with a specific name and independent image file.
	 * Sets the index to -1, representing no index.
	 * @param name : The identity or ID of the Tile.
	 * @param imgName : The independent file name of the image of this Tile (DO NOT include file extension).
	 */
	public Tile(String name, String imgName) {
		this.setName(name);
		this.setImageName(imgName);
		this.setIndex(-1);
	}
	
	/**
	 * Instantiating this constructor will create a new Tile with a specific name and index from a specific tilesheet.
	 * @param name : The identity or ID of the Tile.
	 * @param tileSheetName : The file name of the tilesheet sent in (DO NOT include file extension).
	 * @param index : The index of this Tile's sprite from the specified tilesheet. 
	 */
	public Tile(String name, String tileSheetName, int index) {
		this.setName(name);
		
		if (isIndexLegal(index)) {
			this.setImageName(tileSheetName);
			this.setIndex(index);
		}
		else {
			DystopiaLogger.logWarning("The index \"" + index + "\" is not a valid index number. Must be between 0 - 255.");
			this.setImageName(ResourceLoader.nullImage.toString());
			this.setIndex(-1);
		}
	}
	
	/**
	 * Sets the ID of this Tile.
	 * @param name : Name of Tile as String.
	 */
	protected void setName(String name) {
		this.tileName = name;
	}
	
	/**
	 * Sets the image or tilesheet file name.
	 * @param img : The image or tilesheet file name (NO extension).
	 */
	protected void setImageName(String img) {
		this.imageName = img + ".png";
	}
	
	/**
	 * Sets the index of this Tile in a tilesheet.
	 * @param i : The index of this Tile in a tilesheet.
	 */
	public void setIndex(int i) {
		this.index = i;
	}
	
	public String getName() {
		return this.tileName;
	}
	
	public String getImageName() {
		return this.imageName;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * Checks if the index of this Tile is legal in a standard tilesheet. Must be between 0 and 255.
	 * If this Tile is NOT in a tilesheet, index will automatically be set to -1.
	 * @return A boolean condition reflecting whether or not this Tile's index is legal in a tilesheet.
	 */
	public boolean isIndexLegal(int i) {
		return i >= 0 && i < 256;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
