package com.xaigor.imagem;

public class Log {

	private long t;
	
	public Log(String msg) {
		this.t = System.currentTimeMillis();
		System.out.print(msg + "...");
	}
	
	public void ok() {
		System.out.println(" " + (System.currentTimeMillis() - t) + "ms");
	}
}
