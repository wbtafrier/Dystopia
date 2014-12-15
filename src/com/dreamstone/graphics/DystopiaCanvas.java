package com.dreamstone.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.core.Dystopia;
import com.dreamstone.file.ResourceLoader;
import com.dreamstone.util.TransformImage;

public final class DystopiaCanvas extends Canvas {

	private static final long serialVersionUID = -5025704194120253102L;
	private static final int BUFFERS = 3;
	
	public DystopiaCanvas() {
		
		Dimension defaultSize = new Dimension((int)(DisplayCarrier.getDisplaySize().getWidth() / 2), (int)(DisplayCarrier.getDisplaySize().getHeight() / 2));
		this.setPreferredSize(defaultSize);
		
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(DystopiaCanvas.BUFFERS);
			this.requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		BufferedImage grassOriginal = ResourceLoader.grassDirtEast1;
		System.out.println(Dystopia.getGame().getTime());
		
		BufferedImage grassTransformation = TransformImage.flipVertically(grassOriginal);
		int xOffset = ((this.getWidth() - grassTransformation.getWidth()) - this.getWidth() / 2) / 2;
		int yOffset = ((this.getHeight() - grassTransformation.getHeight())) / 2;
		
		g.setColor(new Color(0xEEEEEE));
		g.fillRect(0, 0, DisplayCarrier.getFrame().getWidth(), DisplayCarrier.getFrame().getHeight());
		g.drawImage(grassTransformation, xOffset, yOffset, grassTransformation.getWidth(), grassTransformation.getHeight(), null);
		
		grassTransformation= TransformImage.flipHorizontally(grassOriginal);
		
		int xOffset2 = ((this.getWidth() - grassTransformation.getWidth()) + this.getWidth() / 2) / 2;
		g.drawImage(grassTransformation, xOffset2, yOffset, grassTransformation.getWidth(), grassTransformation.getHeight(), null);
		
        g.dispose();
        bs.show();
	}
}
