package com.dreamstone.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.dreamstone.core.Dystopia;
import com.dreamstone.file.FileSystem;
import com.dreamstone.util.TransformImage;

public final class DystopiaCanvas extends Canvas {

	private static final long serialVersionUID = -5025704194120253102L;
	
	public DystopiaCanvas() {
		
		DisplayManager.setScale(20);
		Dimension defaultSize = new Dimension(DisplayManager.getWidth() * DisplayManager.getScale(), DisplayManager.getHeight() * DisplayManager.getScale());
		this.setPreferredSize(defaultSize);
		
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(DisplayManager.getBuffers());
			this.requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//System.out.println(FileSystem.getResourcesFolder().getAbsolutePath() + "\\textures\\Individual Tiles\\Grass Tiles\\grass_to_dirt_east_1.png");
		File grass = FileSystem.makeFile(FileSystem.getResourcesFolder().getAbsolutePath(), "\\textures\\Individual Tiles\\Grass Tiles\\grass_to_dirt_east_1.png");
		BufferedImage grassOriginal = null;
		
		try {
			grassOriginal = FileSystem.readImageFile(grass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(Dystopia.getGame().getTime());
		
		BufferedImage grassTransformation = TransformImage.flipVertically(grassOriginal);
		int xOffset = ((this.getWidth() - grassTransformation.getWidth() * DisplayManager.getScale()) - this.getWidth() / 2) / 2;
		int yOffset = ((this.getHeight() - grassTransformation.getHeight() * DisplayManager.getScale())) / 2;
		
		g.setColor(new Color(0xEEEEEE));
		g.fillRect(0, 0, Dystopia.getGame().getFrame().getWidth(), Dystopia.getGame().getFrame().getHeight());
		g.drawImage(grassTransformation, xOffset, yOffset, grassTransformation.getWidth() * DisplayManager.getScale(), grassTransformation.getHeight() * DisplayManager.getScale(), null);
		
		grassTransformation= TransformImage.flipHorizontally(grassOriginal);
		
		int xOffset2 = ((this.getWidth() - grassTransformation.getWidth() * DisplayManager.getScale()) + this.getWidth() / 2) / 2;
		g.drawImage(grassTransformation, xOffset2, yOffset, grassTransformation.getWidth() * DisplayManager.getScale(), grassTransformation.getHeight() * DisplayManager.getScale(), null);
		
        g.dispose();
        bs.show();
	}
}
