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
		if (lcs.size() > 0) {
			// Filtra o array slicedImage pelas imagens presentes no LCS
			lcsA: for (int i=lcs.size()-1; i>=0; i--) {
				Compare cmp = lcs.get(i);
				for (int j=i-1; j>=0; j--) {
					Compare _cmp = lcs.get(j);
					if (cmp.compare(_cmp.getImage(), precision)){
						_cmp.add(cmp.getCount());
						cmp = _cmp;
						lcs.remove(i);
						continue lcsA;
					}
				}
			}
		} else {
			// As imagens restantes são adicionadas no LCS
			for (int j=0; j<fi.getSize(); j++) {
				if (fi.checkBlack(j)) continue;
				Compare compare = new Compare(fi.getImage(j));
				for (int i=0; i<fi.getSize(); i++) {
					if (fi.checkBlack(i)) continue;
					if (compare.compare(fi.getImage(i), precision)) {
						fi.setBlack(i);
					}
				}
				lcs.add(compare);
			}
		}
	}
}
