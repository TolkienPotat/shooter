package run;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import dev.create.Initiate;
import states.DeathState;
import states.GameState;
import states.LoadingState;
import states.LobbyState;
import states.StateMachine;

public class GameLoop {
	
	//Is the game running in a normal state, should be true usually.
	public static boolean isDefaultContext = true;
	
	
	public StateMachine state;
	
	public String newState;
	private String previousState;

	public static int moveXDirection;

	public static int moveYDirection;

	public static int moveXDirectionp;

	public static int moveYDirectionp;
	
	public boolean shooting;
	
	public boolean leftMouseDown;
	public boolean rightMouseDown;

	public boolean rightMouseDownPrevious;
	
	public int score;
	
	
	public GameLoop() {
		
		
		
	}
	
	

	public void run() {
		int fps = Initiate.fps;
		double timePerTickf = 1000000000 / fps;
		double deltaf = 0;
		
		
		
		
		
		int ups = 60;
		double timePerTick = 1000000000 / ups;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		
		
		initiate();
		
		while (!glfwWindowShouldClose(Initiate.window.id)) {
		    now = System.nanoTime();
			
			System.out.println(deltaf);
			deltaf += (now - lastTime) / timePerTickf;
			if (deltaf >=1) {
				render();
				deltaf --;
			}
			
			
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if (delta >= 1) {
				input();
				tick(shooting);
				
				getSwitchStates();
				
				delta --;
			}
			
			if (timer >= 1000000000) {
				
				
				timer = 0;
			}
			
			
			
			
			//reset for next time?
			glfwSwapBuffers(Initiate.window.id);
			glfwPollEvents();
			
			
		}
		
	}

	private void render() {

		state.render();
		
		
		
		
		
		
	}



	private void tick(boolean shooting) {
		shooting = leftMouseDown;
		state.tick(shooting);
		
		
		
		rightMouseDownPrevious = rightMouseDown;
	}

	private void input() {

		state.input();
		state.input(moveXDirection, moveYDirection, moveXDirectionp, moveYDirectionp);
		
	}

	public void initiate() {
		
		state = new StateMachine();
		state.add("Game", (new GameState()));
		state.add("Lobby", (new LobbyState()));
		state.add("Loading", (new LoadingState()));
		state.add("Death", (new DeathState()));
		state.change("Loading");
		newState = "Loading";
		previousState = "Loading";

	}
	
	public void getSwitchStates() {
		
		if (newState != previousState) {
			state.change(newState);
			previousState = newState;
		}
		
	}
	
	
}
