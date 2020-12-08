package dev.create;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;


import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL;
import run.GameLoop;


public class Window {

	public final int width;
	public final int height;
	
	public GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
	
	public long id;
	
	public Window window;
	
	public GLFWKeyCallback input;
	
	public GLFWMouseButtonCallback minput;
	
	public Window(int width, int height) {
		
		this.width = width;
		this.height = height;
		
	}
	
	
	
	public void initialize(int width, int height) {
		
		glfwSetErrorCallback(errorCallback);
		if (!glfwInit()) {
		    throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, 0);
		//glfwSwapInterval(1);
		
		id = glfwCreateWindow(width, height, "Game", NULL, NULL);
		if (id == NULL) {
		    glfwTerminate();
		    throw new RuntimeException("Failed to create the GLFW window");
		}
		
		
		minput = new GLFWMouseButtonCallback() {

		    public void invoke(long window, int button, int action, int mods) {
		    	if (button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_PRESS)
		    	Initiate.game.shooting = true;
		    }
		};
		setKCBs();
		glfwMakeContextCurrent(id);
		GL.createCapabilities();
		GLFW.glfwSwapInterval(1);
		
	}
	
	public long getID() {
		return id;
	}
	
	
	public void setKCBs() {
		
		input = new GLFWKeyCallback(){

	        @Override
	        public void invoke(long window, int key, int scancode, int action, int mods) {
	        	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
	            	
	            	glfwSetWindowShouldClose(window, true);
	            	
	            }
	        	if (key == GLFW_KEY_W && action == GLFW_PRESS) {
	            	GameLoop.moveYDirectionp = 1;
	            	//Initiate.game.shooting = true;
	            	
	            }
	            if (key == GLFW_KEY_W && action == GLFW_RELEASE) {
	            	GameLoop.moveYDirectionp = 0;
	            	//Initiate.game.shooting = false;
	            }
	            if (key == GLFW_KEY_S && action == GLFW_PRESS) {
	            	GameLoop.moveYDirection = 2;
	            	
	            }
	            if (key == GLFW_KEY_S && action == GLFW_RELEASE) {
	            	GameLoop.moveYDirection = 0;
	            	
	            }
	            if (key == GLFW_KEY_A && action == GLFW_PRESS) {
	            	GameLoop.moveXDirection = 1;
	            	
	            	
	            	
	            }
	            if (key == GLFW_KEY_A && action == GLFW_RELEASE) {
	            	GameLoop.moveXDirection = 0;
	            	
	            }
	            if (key == GLFW_KEY_D && action == GLFW_PRESS) {
	            	GameLoop.moveXDirectionp = 2;
	            	
	            }
	            if (key == GLFW_KEY_D && action == GLFW_RELEASE) {
	            	GameLoop.moveXDirectionp = 0;
	            	
	            }
	            
	            
	            
	            
	            
	        }
	    };
		GLFW.glfwSetKeyCallback(id, input);
		
		GLFW.glfwSetMouseButtonCallback(id, minput = new GLFWMouseButtonCallback() {
			public void invoke(long window, int button, int action, int mods) {
				if (button == GLFW_MOUSE_BUTTON_LEFT) {
					if (action == GLFW_PRESS)
						Initiate.game.leftMouseDown = true;
					else if (action == GLFW_RELEASE)
						Initiate.game.leftMouseDown = false;
				} else if (button == GLFW_MOUSE_BUTTON_RIGHT) {
					if (action == GLFW_PRESS)
						Initiate.game.rightMouseDown = true;
					else if (action == GLFW_RELEASE)
						Initiate.game.rightMouseDown = false;
				}
			}
		});
		
	}
	
}
