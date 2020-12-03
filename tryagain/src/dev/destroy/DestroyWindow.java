package dev.destroy;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwTerminate;


import dev.create.Window;

public class DestroyWindow {

	public static void Destroy(Window window) {
		glfwDestroyWindow(window.id);
		window.input.free();
		glfwTerminate();
		window.errorCallback.free();
		System.out.println("Successfully Closed Window.");
	}
	
}
