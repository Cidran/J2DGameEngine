package game;

import java.awt.event.MouseEvent;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;

public class GameManager extends AbstractGame {
	
	private Image image;
	
	public GameManager() {
		image = new Image("/texture.png");
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isButtonDown(MouseEvent.BUTTON1)) {
			System.out.println("Clicked!");
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(image, gc.getInput().getMouseX(), gc.getInput().getMouseY());
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
