package com.xaigor.imagem;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static FileUtils fu = new FileUtils("imagens", "gen");
	
	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		List<Comparators> group = new ArrayList<>();
		
		for (int i=1; i<11; i++) {
			Comparators lcs = new Comparators();
			Log l = new Log("Lendo imagem " + i + ".png");
			BufferedImage image = fu.readImg(i + "");
			l.ok();
			FullImage fi = new FullImage(image);
			try {
				l = new Log("Divindo imagem..");
				fi.sliceImg(285, 285, 2, 1, 3, 4);
				l.ok();
				trying(lcs, fi);
			} catch (Exception e) {
				e.printStackTrace();
			}
			group.add(lcs);
		}
		
		fu.saveLCS(Comparators.reduce(group));
		System.out.println("Tempo total: " + (System.currentTimeMillis() - t) + "ms");
	}
	
	public static void trying(final Comparators lcs, final FullImage fi) {
		try {
			Log l = new Log("- Comparando items");
			lcs.updateList(fi);
			l.ok();
			if (lcs.check26()) {
				System.out.println("V encontradas " + lcs.size() + " letras");
				return;
			} else {
				System.out.println("X encontradas " + lcs.size() + " letras");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		trying(lcs, fi);
	}
}
