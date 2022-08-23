package game;

import java.util.ArrayList;
import java.util.List;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;

public class GameManager extends AbstractGame {
	

	private List<GameObject> objects = new ArrayList<GameObject>();

	public GameManager() {
		
		objects.add(new Player(2, 2));
		objects.add(new Bullet());
	}
	
	@Override
	public void init(GameContainer gc) {
		gc.getRenderer().setAmbientColor(0-1);
		
	}

	@Override
	public void update(GameContainer gc, float dt) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, dt);
			if (objects.get(i).isDead()) {
				objects.remove(i);
				i--;
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for(GameObject obj : objects) {
			obj.render(gc, r);
		}
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.setWindowSize(360, 240, 3f);
		gc.setTitle("CidranEngine");
		gc.start();
	}

}
