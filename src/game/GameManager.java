package game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.audio.SoundClip;
import engine.gfx.Image;
import engine.gfx.ImageTile;

public class GameManager extends AbstractGame {

	private Image image;
	private ImageTile image2;
	private SoundClip clip;
	
	private int positionX = 10;
	private int positionY = 10;
	private int speed = 5;

	public GameManager() {
		
		image = new Image("/images/texture.png");
		image.setAlpha(true);
		image2 = new ImageTile("/images/texture_alpha_test.png", 32, 16);
		image2.setAlpha(true);
		clip = new SoundClip("/audio/sample.wav");
	}
	
	public void reset() {
		
	}

	@Override
	public void update(GameContainer gc, float dt) {

		if (gc.getInput().isButtonDown(MouseEvent.BUTTON1)) {
			System.out.println("Clicked!");
			clip.play();
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_W)) {
			positionY -= speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_S)) {
			positionY += speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_A)) {
			positionX -= speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			positionX += speed;
		}

		temp += dt * 20;
		if (temp > 4) {
			temp = 0;
		}
	}

	float temp = 0;

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.setzDepth(1);
		r.drawImageTile(image2, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 8, 1, 1);
		r.setzDepth(0);
		r.drawImage(image, positionX, positionY);
		
		
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
