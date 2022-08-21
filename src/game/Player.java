package game;

import java.awt.event.KeyEvent;

import engine.GameContainer;
import engine.Renderer;

public class Player extends GameObject{
	
	private float speed = 200;
	
	public Player(int posX, int posY) {
		this.tag = "Player";
		this.posX = posX * 16;
		this.posY = posY * 16;
		this.width = 16;
		this.height = 16;
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
		r.drawFillRect((int)posX, (int)posY, width, height, 0xffff0000);
	}

}
