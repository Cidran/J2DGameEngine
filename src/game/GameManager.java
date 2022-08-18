package game;

import java.awt.event.MouseEvent;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.audio.SoundClip;
import engine.gfx.Image;

public class GameManager extends AbstractGame {

	private Image image, alphaImage;
	private SoundClip clip;

	public GameManager() {
		
		image = new Image("/images/texture.png");
		alphaImage = new Image("/images/texture_alpha_test.png");
		alphaImage.setAlpha(true);
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

		temp += dt * 20;
		if (temp > 4) {
			temp = 0;
		}
	}

	float temp = 0;

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(alphaImage, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 8);
		r.drawImage(image, 10, 10);
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
