package com.xaigor.imagem;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtils {
	
	private String sourceImgs, destImages;
	
	public FileUtils(final String sourceImgs, final String destImages) {
		this.sourceImgs = sourceImgs;
		this.destImages = destImages;
		deleteFolder(new File(destImages + "/"));
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
	
	public void saveLCS(final Comparators lcs, final String folder) {
		String s = "";
		int sum = 0;
		for (int i=0; i<lcs.size(); i++) {
			Compare cmp = lcs.getLCS().get(i);
			s += " + " + cmp.getCount();
			sum += cmp.getCount();
			saveImg(cmp.getImage(), folder, i + "_" + cmp.getCount());
		}
		saveFile(folder, "soma_" + sum, s);
	}
	
	public void saveFile(final String folder, final String name, final String content) {
		BufferedWriter writer = null;
        try {
            File logFile = new File(destImages + "/" + folder + "/" + name + ".txt");
            logFile.getParentFile().mkdirs();
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
	}
	
	public boolean saveImg(final BufferedImage img, final String folder, final String name) {
		File destImg = new File(destImages + "/" + folder + "/" + name + ".png");
		try {
			destImg.mkdirs();
			return ImageIO.write(img, "png", destImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) {
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
}
