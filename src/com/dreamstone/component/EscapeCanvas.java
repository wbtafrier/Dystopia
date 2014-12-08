package com.dreamstone.component;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.dreamstone.core.Main;
import com.dreamstone.file.FileSystem;
import com.dreamstone.util.TransformImage;

public final class EscapeCanvas extends Canvas {

	private static final long serialVersionUID = -5025704194120253102L;
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	private static final int DEFAULT_WIDTH = gd.getDisplayMode().getWidth() / 40;
	private static final int DEFAULT_HEIGHT = gd.getDisplayMode().getHeight() / 40;
	private static final int SCALE = 20;
	private static final int BUFFERS = 3;
	
	public EscapeCanvas() {
		Dimension preferredSize = new Dimension(DEFAULT_WIDTH * SCALE, DEFAULT_HEIGHT * SCALE);
		this.setPreferredSize(preferredSize);
		
		Toolkit.getDefaultToolkit().setDynamicLayout(false);;
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(BUFFERS);
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
		
		System.out.println(Main.game.getTime());
		
		BufferedImage grassTransformation = TransformImage.flipVertically(grassOriginal);
		int xOffset = ((this.getWidth() - grassTransformation.getWidth() * SCALE) - this.getWidth() / 2) / 2;
		int yOffset = ((this.getHeight() - grassTransformation.getHeight() * SCALE)) / 2;
		
		g.setColor(new Color(0xEEEEEE));
		g.fillRect(0, 0, Main.game.getWidth(), Main.game.getHeight());
		g.drawImage(grassTransformation, xOffset, yOffset, grassTransformation.getWidth() * SCALE, grassTransformation.getHeight() * SCALE, null);
		
		grassTransformation= TransformImage.flipHorizontally(grassOriginal);
		
		int xOffset2 = ((this.getWidth() - grassTransformation.getWidth() * SCALE) + this.getWidth() / 2) / 2;
		g.drawImage(grassTransformation, xOffset2, yOffset, grassTransformation.getWidth() * SCALE, grassTransformation.getHeight() * SCALE, null);
		
        g.dispose();
        bs.show();
	}
}
