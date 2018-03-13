package com.xaigor.imagem;

import java.awt.image.BufferedImage;

public class Main {
	static FileUtils fu = new FileUtils("imagens", "gen");
	
	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		ListCompare lcs = new ListCompare();
		
		for (int i=1; i<2; i++) {
			System.out.println("Lendo imagem " + i + ".png");
			BufferedImage image = fu.readImg(i + "");
			FullImage fi = new FullImage(image);
			try {
				System.out.println("Divindo imagem..");
				fi.sliceImg(285, 285, 2, 1, 3, 4);
				trying(lcs, fi, 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			fu.saveLCS(lcs);
			System.out.println(": Imagens gravadas em gen/");
		}
		
		System.out.println(": Tempo gasto: " + (System.currentTimeMillis() - t)/1000 + "s");
	}
	
	public static void trying(final ListCompare lcs, final FullImage fi, final int precision) {
		System.out.println("- Tentando com precisao: " + precision);
		try {
			System.out.println("- Comparando items..");
			lcs.updateList(fi, precision);
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
