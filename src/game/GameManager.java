package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import engine.gfx.Light;

public class GameManager extends AbstractGame {

	private Image image, wood;
	private Light light;

	public GameManager() {
		
		image = new Image("/images/pattern_bg.png");
		image.setAlpha(true);
		wood = new Image("/images/texture.png");
		wood.setAlpha(true);
		
		light = new Light(50, 0xffffffff);
		
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
		
		r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());	
		r.setzDepth(0);
		r.drawImage(image, 0, 0);
		r.setzDepth(1);
		//r.drawImage(wood, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		

	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.setWindowSize(360, 240, 2f);
		gc.setTitle("CidranEngine");
		gc.start();
	}

}
