package com.xaigor.imagem;

import java.util.ArrayList;
import java.util.List;

public class ListCompare {
	
	private List<Compare> lcs;
	
	public ListCompare() {
		this.lcs = new ArrayList<>();
	}
	
	public void cleanCount() {
		for (Compare cmp: lcs) {
			cmp.clearCount();
		}
	}
	
	public boolean check26() {
		return lcs.size() == 26;
	}
	
	public int size(){
		return lcs.size();
	}
	
	public List<Compare> getLCS(){
		return lcs;
	}

	public void updateList(final FullImage fi, final int precision) {
		fi.cleanSelection();
		// Filtra o array slicedImage pelas imagens presentes no LCS
		for (Compare cmp : lcs) {
			for (int j=0; j<fi.getSize(); j++) {
				if (fi.checkSelected(j)) continue;
				if (cmp.compare(fi.getImage(j), precision)) {
					fi.setSelected(j);
				}
			}
		}
		// As imagens restantes são adicionadas no LCS
		for (int j=0; j<fi.getSize(); j++) {
			if (fi.checkSelected(j)) continue;
			Compare compare = new Compare(fi.getImage(j));
			for (int i=0; i<fi.getSize(); i++) {
				if (fi.checkSelected(i)) continue;
				if (compare.compare(fi.getImage(i), precision)) {
					fi.setSelected(i);
				}
			}
			lcs.add(compare);
		}
	}
}
