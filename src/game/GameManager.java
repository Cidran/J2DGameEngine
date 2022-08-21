package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import engine.gfx.Light;

public class GameManager extends AbstractGame {

	private Image image, blockTest;
	private Light light;

	public GameManager() {
		
		image = new Image("/images/pattern_bg.png");
		image.setAlpha(true);
		blockTest = new Image("/images/block_test.png");
		blockTest.setLightBlock(Light.FULL);
		blockTest.setAlpha(true);
		
		light = new Light(300, 0xffffffff);
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
			
		r.setzDepth(0);
		r.drawImage(image, 0, 0);
		r.setzDepth(1);
		
		r.drawImage(blockTest, 120, 80);
		
		r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		

	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.setWindowSize(360, 240, 1.5f);
		gc.setTitle("CidranEngine");
		gc.start();
	}

}
