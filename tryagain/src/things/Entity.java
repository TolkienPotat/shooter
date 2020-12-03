package things;

import java.awt.Rectangle;

import dev.draw.Renderer;
import dev.draw.Texture;

public class Entity {

	public int x;
	public int y;
	public Texture texture;
	
	public int health;
	
	public Rectangle r;
	
	Renderer renderer;
	
	
	public Entity() {
		
		r = new Rectangle();
		renderer = new Renderer();
		
	}

	public void createTexture(String filePath) {
		texture = new Texture();
		texture = Texture.loadTexture(filePath);
		
		
	}
	
	public void bindTexture() {
		texture.bind();
	}
	
	public void draw() {
		renderer.begin();
		renderer.drawTexture(texture, x, y);
		texture.bind();
		renderer.end();
		
		
		
	}
}
