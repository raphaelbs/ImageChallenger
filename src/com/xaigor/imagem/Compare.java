package com.xaigor.imagem;

import java.awt.image.BufferedImage;

public class Compare {
	private BufferedImage image;
	private int count;
	
	public Compare (final BufferedImage image) {
		this.image = image;
		this.count = 1;
	}
	
	public void add(){
		this.count++;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void clearCount(){
		this.count = 1;
	}
	
	public boolean compare(final BufferedImage oImage, final int tol){
		if (oImage.equals(this)) 
			return false;
		
		int error = 0;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				if (oImage.getRGB(x, y) != image.getRGB(x, y)) {
					error++;
					if (error > tol)
						return false;
				}
			}
	    }
		count++;
		return true;
	}
}