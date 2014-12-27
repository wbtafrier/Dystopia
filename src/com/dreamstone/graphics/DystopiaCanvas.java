package com.dreamstone.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.dreamstone.core.DisplayCarrier;
import com.dreamstone.file.DirectoryMaster;
import com.dreamstone.file.FileSystem;
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
		BufferedImage grassTransformation = TransformImage.flipVertically(grassOriginal);
		int xOffset = ((this.getWidth() - grassTransformation.getWidth()) - this.getWidth() / 2) / 2;
		int yOffset = ((this.getHeight() - grassTransformation.getHeight())) / 2;
		
		g.setColor(new Color(0xEEEEEE));
		g.fillRect(0, 0, DisplayCarrier.getFrame().getWidth(), DisplayCarrier.getFrame().getHeight());
		g.drawImage(grassTransformation, xOffset, yOffset, grassTransformation.getWidth(), grassTransformation.getHeight(), null);
		
		grassTransformation= TransformImage.flipHorizontally(grassOriginal);
		
		int xOffset2 = ((this.getWidth() - grassTransformation.getWidth()) + this.getWidth() / 2) / 2;
		g.drawImage(grassTransformation, xOffset2, yOffset, grassTransformation.getWidth(), grassTransformation.getHeight(), null);
		//BufferedImage scaledImage = TransformImage.scaleImage(grassOriginal, 20);
		//g.drawImage(scaledImage, 0, 0, scaledImage.getWidth(), scaledImage.getHeight(), null);
		
		//BufferedImage scaledImage2 = TransformImage.scaleImage(grassOriginal, Math.sin((Dystopia.getGame().getTime() * 0.1115)) + 20);
		//g.drawImage(scaledImage2, 0, 100, scaledImage2.getWidth(), scaledImage2.getHeight(), null);
		
		//BufferedImage rotateImage = TransformImage.scaleImage(grassOriginal, (float)(8 * Math.sin(Dystopia.getGame().getTime() * 0.115) + 20));
		//BufferedImage rotateImage = TransformImage.scaleImage(grassOriginal, 20);
		//rotateImage = TransformImage.rotateImage(rotateImage, Dystopia.getGame().getTime());

//		g.drawImage(rotateImage, ((this.getWidth() - rotateImage.getWidth()) / 2), (this.getHeight() - rotateImage.getHeight()) / 2, null);
//		g.setColor(Color.BLACK);
		//g.drawRect((this.getWidth() - rotateImage.getWidth()) / 2, (this.getHeight() - rotateImage.getHeight()) / 2, rotateImage.getWidth(), rotateImage.getHeight());
		
		//float width = (float)(grassOriginal.getWidth() * (5 * Math.sin(Dystopia.getGame().getTime() * 0.0115) + (grassOriginal.getWidth() - 4)));
		//float height = (float)(grassOriginal.getHeight() * (5 * Math.cos(Dystopia.getGame().getTime() * 0.0115) + (grassOriginal.getHeight() - 4)));
		
		//BufferedImage croppedImage = TransformImage.scaleImage(grassOriginal, 25);
		//croppedImage = TransformImage.cropImage(croppedImage, 0, 0, width, height);
		//croppedImage = TransformImage.scaleImage(croppedImage, 20);
		//g.drawImage(croppedImage, ((this.getWidth() - croppedImage.getWidth()) / 2), (this.getHeight() - croppedImage.getHeight()) / 2, null);
		
		BufferedImage terrain = ResourceLoader.loadImageFromJar(DirectoryMaster.texturesFolder, "terrain.png");
		ArrayList<BufferedImage> rawr = new ArrayList<>();
		rawr = TransformImage.splitImage(terrain, 16, 16);
		
		int xCoord = 0;
		int yCoord = 0;
		
		for (int i = 0; i < rawr.size(); i++) {
			rawr.set(i, TransformImage.scaleImage(rawr.get(i), 5));
			
			if (i % 16 == 0) {
				xCoord = 0;
				yCoord += rawr.get(i).getHeight();
			}
			g.drawImage(rawr.get(i), (xCoord * rawr.get(i).getWidth()), yCoord + (rawr.get(i).getHeight()), null);
			xCoord++;
		}
		
		
		//BufferedImage[] lolAnimation = new BufferedImage[1];
		//lolAnimation = TransformImage.splitAnimation(grassOriginal, 8);
		
        g.dispose();
        bs.show();
	}
}
