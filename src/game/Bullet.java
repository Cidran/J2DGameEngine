package game;

import java.awt.event.MouseEvent;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;

public class Bullet extends GameObject{
	
	private Image bullet;
	private int posX = -1;
	private int posY = -1;
	float temp = 0;
	
	
	public Bullet() {
		bullet = new Image("/images/bullet.png");
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isButtonDown(MouseEvent.BUTTON1)){
			posX = gc.getInput().getMouseX() -10;
			posY = gc.getInput().getMouseY() -16;
		}
		
		temp += dt;
		if(posY > -5) {
			posY = posY - 7;
			temp = 0;
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(bullet, posX, posY);
		
	}

}
