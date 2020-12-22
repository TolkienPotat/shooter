package things;

import java.awt.Rectangle;

import dev.draw.Renderer;
import dev.draw.Texture;



public class Bullet {

	public float posX, posY;
	
	public float xInGame, yInGame;
	
	float angle;
	
	float velocity;
	
	double velocityX, velocityY;
	
	public int time = 0;
	
	private Renderer renderer;
	
	public int owner;
	
	public int damage;
	
	Texture texture;
	
	public Rectangle r;
	
	public Bullet(float angle, Renderer renderer, int owner, int xInGame, int yInGame, int velocity, int damage) {
		
		texture = new Texture();
		texture = Texture.loadTexture("Textures/bullet.png");
		
		this.renderer = renderer;
		
		this.owner = owner;
		
		this.angle = angle;
		
		this.xInGame = xInGame;
		this.yInGame = yInGame;
		
		this.velocity = velocity;
		
		this.damage = damage;
		
		r = new Rectangle();
		
	}
	

	public void move() {
		
		velocityX =  (velocity*Math.cos(Math.toRadians(angle)));
		velocityY =  (velocity*Math.sin(Math.toRadians(angle)));
		
		
		
		xInGame += velocityX;
		yInGame += velocityY;
		
		time++;
		
		r.setBounds((int)xInGame,(int) yInGame, texture.getWidth(), texture.getHeight());
		
	}

	public void render() {

		renderer.drawTexture(texture, posX, posY);
		texture.bind();
		
	}
	
}
