package engine;

import java.awt.image.DataBufferInt;

import engine.gfx.Font;
import engine.gfx.Image;
import engine.gfx.ImageTile;

public class Renderer {

	private int pW, pH;
	private int[] p;
	private Font font = Font.STANDARD;

	public Renderer(GameContainer gc) {
		pW = gc.getWIDTH();
		pH = gc.getHEIGHT();
		p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}

	public void clear() {
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;
		}
	}

	public void setPixel(int x, int y, int value) {
		if ((x < 0 || x >= pW || y < 0 || y >= pH) || ((value >> 24) & 0xff) == 0){
			return;
		}
		p[x + y * pW] = value;
	}

	public void drawText(String text, int offX, int offY, int color) {

		Image fontimImage = font.getFontImage();

		text = text.toUpperCase();
		int offset = 0;

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;

			for (int y = 0; y < fontimImage.getH(); y++) {
				for (int x = 0; x < font.getWidths()[unicode]; x++) {
					if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
							+ y * font.getFontImage().getW()] == 0xffffffff) {
						setPixel(x + offX + offset, y + offY, color);
					}
				}
			}

			offset += font.getWidths()[unicode];
		}
	}

	public void drawImage(Image image, int offX, int offY) {

		if (offX < -image.getW() || offY < -image.getH() || offX >= pW || offY >= pH) return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getW();
		int newHeight = image.getH();

		if (newX + offX < 0) newX -= offX;
		if (newY + offY < 0) newY -= offY;
		if (newWidth + offX > pW)newWidth -= newWidth + offX - pW;
		if (newHeight + offY > pH)newHeight -= newHeight + offY - pH;

		for (int y = 0; y < newHeight; y++) {
			for (int x = 0; x < newWidth; x++) {
				setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
			}
		}
	}

	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
		
		if (offX < -image.getW() || offY < -image.getH() || offX >= pW || offY >= pH) return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getTileW();
		int newHeight = image.getTileH();

		if (newX + offX < 0) newX -= offX;
		if (newY + offY < 0) newY -= offY;
		if (newWidth + offX > pW) newWidth -= newWidth + offX - pW;
		if (newHeight + offY > pH) newHeight -= newHeight + offY - pH;

		for (int y = 0; y < newHeight; y++) {
			for (int x = 0; x < newWidth; x++) {
				setPixel(x + offX, y + offY,
						image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);
			}
		}
	}

	public void drawRect(int offX, int offY, int width, int height, int color) {
		
		for (int y = 0; y <= height; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y + offY, color);
		}
		for (int x = 0; x <= width; x++) {
			setPixel(x + offX, +offY, color);
			setPixel(x + offX, offY + height, color);
		}
	}

	public void drawFillRect(int offX, int offY, int width, int height, int color) {
		
		if (offX < -width || offY < -height || offX >= pW || offY >= pH) return;

		int newX = 0;
		int newY = 0;
		int newWidth = width;
		int newHeight = height;

		if (newX + offX < 0) newX -= offX;
		if (newY + offY < 0) newY -= offY;
		if (newWidth + offX > pW)newWidth -= newWidth + offX - pW;
		if (newHeight + offY > pH)newHeight -= newHeight + offY - pH;
		
		for (int y = newY; y <= newHeight; y++) {
			for (int x = newX; x <= newWidth; x++) {
				setPixel(x + offX, y + offY, color);
			}
		}

	}

}
