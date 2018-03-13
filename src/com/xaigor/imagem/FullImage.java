package com.xaigor.imagem;

import java.awt.image.BufferedImage;

public class FullImage {
	
	private BufferedImage fullImage;
	private BufferedImage[] isolatedImage;
	private boolean[] checkArray;
	
	public FullImage(final BufferedImage fullImage) {
		this.fullImage = fullImage;
	}
	
	public void sliceImg(final int xSlice, final int ySlice, 
			final int clipLeft, final int clipTop, final int clipRight, final int clipBottom) throws Exception {
		int imageW = (int)fullImage.getWidth() - (clipRight + clipLeft);
		int imageH = (int)fullImage.getWidth() - (clipTop + clipBottom);
		
		// Confere se a imagem grandona pode ser cortada
		if (imageW % xSlice != 0) {
			throw new Exception("Expect image width % xSlice should be 0, gets " + imageW + " % " + xSlice + " = " + (imageW % xSlice));
		}
		if (imageH % ySlice != 0) {
			throw new Exception("Expect image height % ySlice should be 0, gets " + imageH + " % " + ySlice + " = " + (imageH % ySlice));
		}
		
		int iWidth = imageW / xSlice;
		int iHeight = imageH / ySlice;
		
		// Instancia o array das imagens
		BufferedImage[] isolatedImages = new BufferedImage[xSlice * ySlice];
		for (int i = 0; i < isolatedImages.length; i++) {
			isolatedImages[i] = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_4BYTE_ABGR);
		}
		
		// Preenche os RGB
		int c = 0;
		for (int y = clipTop; y < imageH; y += iHeight) {
			for (int x = clipLeft; x < imageW; x += iWidth) {
				
				for (int yy = 0; yy < iHeight; yy++) {
					for (int xx = 0; xx < iWidth; xx++) {
						isolatedImages[c].setRGB(xx, yy, fullImage.getRGB(x+xx, y+yy));
					}
				}
				c++;
			}
		}
		
		this.isolatedImage = isolatedImages;
		this.checkArray = new boolean[isolatedImages.length];
	}

	public BufferedImage[] getIsolatedImage() {
		return isolatedImage;
	}
	
	public BufferedImage getImage(int index) {
		return isolatedImage[index];
	}
	
	public int getSize() {
		return this.isolatedImage.length;
	}
	
	public boolean checkSelected(int index) {
		return this.checkArray[index] == true;
	}
	
	public void setSelected(int index) {
		this.checkArray[index] = true;
	}
	
	public void cleanSelection() {
		for (int i = 0; i< checkArray.length; i++) {
			checkArray[i] = false;
		}
	}
}
