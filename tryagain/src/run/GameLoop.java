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

	public static boolean wDown;

	public static boolean aDown;

	public static boolean sDown;

	public static boolean dDown;
	
	public boolean shooting;
	
	public boolean leftMouseDown;
	public boolean rightMouseDown;

	public boolean rightMouseDownPrevious;
	public boolean leftMouseDownPrevious;
	
	public int score;
	
	public int highScore;
	
	public int xVelocity = 0;
	public int yVelocity = 0;
	
	private int speed = 6;
	
	public FileReadWrite fileReadWriter = new FileReadWrite("Res/score.jafn");
	
	public GameLoop() {
		
		
		
	}
	
	

	public void run() {
		
		
		int ups = 60;
		double timePerTick = 1000000000 / ups;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		
		
		initiate();
		
		while (!glfwWindowShouldClose(Initiate.window.id)) {
		    now = System.nanoTime();
			
			
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if (delta >= 1) {
				input();
				tick(shooting);
				render();
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
		leftMouseDownPrevious = leftMouseDown;
	}

	private void input() {

		if (dDown) {
			if (xVelocity < speed) {
				xVelocity++;
			}
		} else if (xVelocity > 0) {
			xVelocity--;
		}
		if (aDown) {
			if (xVelocity > -speed) {
				xVelocity--;
			}
		} else if (xVelocity < 0) {
			xVelocity++;
		}
		
		if (wDown) {
			if (yVelocity < speed) {
				yVelocity++;
			}
		} else if (yVelocity > 0) {
			yVelocity--;
		}
		if (sDown) {
			if (yVelocity > -speed) {
				yVelocity--;
			}
		} else if (yVelocity < 0) {
			yVelocity++;
		}
		
		state.input();
		
		
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
		
		highScore = getHighScore();

	}
	
	public void getSwitchStates() {
		
		if (newState != previousState) {
			state.change(newState);
			previousState = newState;
		}
		
	}
	
	public int getHighScore() {
		return (fileReadWriter.readScore());
	}
	
	public void writeHighScore() {
		if (score > highScore) { 
			fileReadWriter.writeScore("score=" + String.valueOf(score));
			highScore = score;
		}
	}
	
	
}
