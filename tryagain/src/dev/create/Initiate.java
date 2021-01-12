package dev.create;

import dev.destroy.DestroyWindow;
import dev.draw.Renderer;
import run.GameLoop;

public class Initiate {
	
public static Window window;
public static GameLoop game;

public static int fps;

	public static void main(String[] args) {

		if (args[0] != null) {
			fps = Integer.parseInt(args[0]);
		} else {
			fps = 60;
		}
		
		
		window = new Window(640, 480);
		window.initialize(window.width, window.height);
		game = new GameLoop();
		game.run();
		Renderer.dispose();
		DestroyWindow.Destroy(window);
		
		
		
		
	}

}
