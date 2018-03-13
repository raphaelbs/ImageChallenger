package com.xaigor.imagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtils {
	
	private String sourceImgs, destImages;
	
	public FileUtils(final String sourceImgs, final String destImages) {
		this.sourceImgs = sourceImgs;
		this.destImages = destImages;
	}
	
	public BufferedImage readImg(final String img) {
		File sourceimage = new File(sourceImgs + "/" + img + ".png");
		try {
			return ImageIO.read(sourceimage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveLCS(final ListCompare lcs) {
		cleanFolder();
		int count = 0;
		for (int i=0; i<lcs.size(); i++) {
			Compare cmp = lcs.getLCS().get(i);
			count += cmp.getCount();
			saveImg(cmp.getImage(), i + "_" + cmp.getCount());
		}
		System.out.println(count);
	}
	
	public boolean saveImg(final BufferedImage img, final String name) {
		File sourceimage = new File(destImages + "/" + name + ".png");
		try {
			return ImageIO.write(img, "png", sourceimage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void cleanFolder() {
	    File[] files = new File(destImages + "/").listFiles();
	    if(files!=null) {
	        for(File f: files) {
	        	f.delete();
	        }
	    }
	}
}
