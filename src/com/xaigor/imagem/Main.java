package com.xaigor.imagem;

import java.awt.image.BufferedImage;

public class Main {
	static FileUtils fu = new FileUtils("imagens", "gen");
	
	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		ListCompare lcs = new ListCompare();
		
		for (int i=1; i<2; i++) {
			Log l = new Log("Lendo imagem " + i + ".png");
			BufferedImage image = fu.readImg(i + "");
			l.ok();
			FullImage fi = new FullImage(image);
			try {
				l = new Log("Divindo imagem..");
				fi.sliceImg(285, 285, 2, 1, 3, 4);
				l.ok();
				trying(lcs, fi, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			fu.saveLCS(lcs);
		}
		
		System.out.println("Tempo total: " + (System.currentTimeMillis() - t) + "ms");
	}
	
	public static void trying(final ListCompare lcs, final FullImage fi, final int precision) {
		System.out.println("- Tentando com precisao: " + precision);
		try {
			Log l = new Log("- Comparando items");
			lcs.updateList(fi, precision);
			l.ok();
			if (lcs.check26()) {
				return;
			} else {
				System.out.println("* encontradas " + lcs.size() + " letras");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		trying(lcs, fi, precision + 1);
	}
}
