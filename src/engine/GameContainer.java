package engine;

public class GameContainer implements Runnable {

	private Thread thread;
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGame game;

	private boolean render = false;
	private boolean running = false;
	private final double FPS_TARGET = 120.0;
	private final double UPDATE_CAP = 1.0 / FPS_TARGET;

	private final int WIDTH = 320;
	private final int HEIGHT = 240;
	private float scale = 2f;
	private String title = "2DJava Game";

	public GameContainer(AbstractGame game) {
		this.game = game;
	}

	public void init() {
		thread = new Thread(this);
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
	}

	public void start() {
		init();
		thread.run();
	}

	public void stop() {

	}

	@Override
	public void run() {
		running = true;

		render = false;
		double firstTime = 0;
		double lastTime = currentTime();
		double passedTime = 0;
		double unprocessedTime = 0;

		double frameTime = 0;
		int frames = 0;
		int fps = 0;

		while (running) {
			firstTime = currentTime();
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			frameTime += passedTime;

			while (unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;

				game.update(this, (float) UPDATE_CAP);
				input.update();

				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}

				if (render) {
					renderer.clear();
					game.render(this, renderer);
					renderer.process();
					renderer.drawText("FPS: " + fps, 0, 0, 0xffffff00);
					window.update();
					frames++;
				} else {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		dispose();
	}

	public void dispose() {

	}

	public double currentTime() {
		return System.nanoTime() / 1000000000.0;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public Window getWindow() {
		return window;
	}

	public Input getInput() {
		return input;
	}

}
