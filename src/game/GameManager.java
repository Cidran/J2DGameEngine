package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;

public class GameManager extends AbstractGame {

	private Image image;
	private Image image2;

	public GameManager() {
		
		image = new Image("/images/light.png");
		image.setAlpha(true);
		image2 = new Image("/images/white.png");
		image2.setAlpha(true);
	}
	
	public void reset() {
		
	}

	@Override
	public void update(GameContainer gc, float dt) {

		temp += dt * 20;
		if (temp > 4) {
			temp = 0;
		}
	}

	float temp = 0;

	@Override
	public void render(GameContainer gc, Renderer r) {
		
		for(int x = 0; x < image.getW(); x++) {
			for(int y = 0; y < image.getH(); y++) {
				r.setLightMap(x, y, image.getP()[x + y * image.getW()]);
			}
		}
		
		r.setzDepth(0);
		r.drawImage(image2, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		r.setzDepth(1);
		r.drawImage(image, 10, 10);

	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
