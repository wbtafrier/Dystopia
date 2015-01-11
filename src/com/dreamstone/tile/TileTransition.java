package com.dreamstone.tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.dreamstone.util.DystopiaLogger;

public abstract class TileTransition extends Tile {
	
	protected ArrayList<ArrayList<BufferedImage>> transitionImages;
	
	/**
	 * Instantiating this constructor will create a new Tile with a specific name and index from a specific tilesheet.
	 * @param name : The identity or ID of the Tile.
	 * @param tileSheetName : The file name of the tilesheet sent in (Include file extension).
	 * @param dir : The direction of the transition.
	 */
	public TileTransition(String name) {
		this(name, "terrain.png");
	}
	
	/**
	 * Instantiating this constructor will create a new Tile with a specific name and index from a specific tilesheet.
	 * @param name : The identity or ID of the Tile.
	 * @param tileSheetName : The file name of the tilesheet sent in (Include file extension).
	 * @param dir : The direction of the transition.
	 */
	public TileTransition(String name, String tileSheetName) {
		super(name, tileSheetName);
		this.transitionImages = new ArrayList<>();
		
		//Directions from 0 - 7 = Northwest -> Southeast excluding Default.
		for (int i = 0; i < (EnumDirection.values().length) - 1; i++) {
			transitionImages.add(new ArrayList<BufferedImage>());
		}
	}
	
	protected abstract void setTransitionImageTiles();
	
	public BufferedImage getTransitionImageTile(EnumDirection dir, int index) {
		if (!(dir.ordinal() >= transitionImages.size() || dir.ordinal() < 0) && !(index >= transitionImages.get(dir.ordinal()).size() || index < 0)) {
			return transitionImages.get(dir.ordinal()).get(index);
		}
		else {
			DystopiaLogger.logWarning("THE INDEX OR DIRECTION ASKED FOR IS NOT IN THE ARRAYLIST. RETURNING IMAGE AT DIRECTION 0 AND INDEX 0.");
			return transitionImages.get(0).get(0);
		}
	}
	
	public BufferedImage getTransitionImageTile(int dir, int index) {
		if (!(dir >= transitionImages.size() || dir < 0) && !(index >= transitionImages.get(dir).size() || index < 0)) {
			return transitionImages.get(dir).get(index);
		}
		else {
			DystopiaLogger.logWarning("THE INDEX OR DIRECTION ASKED FOR IS NOT IN THE ARRAYLIST. RETURNING IMAGE AT DIRECTION 0 AND INDEX 0.");
			return transitionImages.get(0).get(0);
		}
	}
}
