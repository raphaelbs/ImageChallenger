package com.xaigor.imagem;

import java.util.ArrayList;
import java.util.List;

public class Comparators {
	
	private List<Compare> lcs;
	private int precision;
	
	public Comparators() {
		this.lcs = new ArrayList<>();
	}
	
	public Comparators(List<Compare> lcs) {
		this.lcs = lcs;
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
	
	/**
	 * Filters the LCS for repeated images.
	 */
	private void filter() {
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
		precision++;
	}

	/**
	 * Images and counts are added to the LCS.
	 * @param fi
	 */
	public void updateList(final FullImage fi) {
		System.out.println("- Tentando com precisao: " + precision);
		if (lcs.size() > 0) {
			filter();
			return;
		}
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
		precision++;
	}
	
	public static Comparators reduce(final List<Comparators> comparatorList) {
		List<Compare> lcs = new ArrayList<>();
		Comparators c = new Comparators(lcs);
		for (Comparators cm: comparatorList) {
			lcs.addAll(cm.getLCS());
		}
		c.filter();
		return c;
	}
}
