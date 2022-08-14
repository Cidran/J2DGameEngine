package engine;

import java.awt.image.DataBufferInt;

public class Renderer {
	
	private int pW, pH;
	private int[] p;
	
	public Renderer(GameContainer gc) {
		pW = gc.getWIDTH();
		pH = gc.getHEIGHT();
		p = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	public void clear() {
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;
		}
	}
}
