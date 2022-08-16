package game;

import java.awt.event.MouseEvent;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.audio.SoundClip;
import engine.gfx.ImageTile;

public class GameManager extends AbstractGame {

	private ImageTile image;
	private SoundClip clip;

	public GameManager() {
		image = new ImageTile("/boom.png", 16, 16);
		clip = new SoundClip("/audio/sample.wav");

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
		r.drawImageTile(image, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 8, (int) temp, 0);
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
