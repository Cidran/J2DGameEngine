package game;

import java.awt.event.KeyEvent;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;

public class Player extends GameObject{
	
	private Image image;
	
	private float speed = 200;
	
	public Player(int posX, int posY) {
		this.tag = "Player";
		this.posX = posX * 16;
		this.posY = posY * 16;
		this.width = 16;
		this.height = 16;
		
		image = new Image("/images/player.png");
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isKey(KeyEvent.VK_W)) {
			posY -= dt * speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_S)) {
			posY += dt * speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_A)) {
			posX -= dt * speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			posX += dt * speed;
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(image, gc.getInput().getMouseX()-16, gc.getInput().getMouseY()-16);
	}

}
