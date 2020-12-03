package things;

import java.awt.Rectangle;

import dev.draw.Texture;

public class Tile {

	public int id;
	
	public int x,y;
	
	public Rectangle r;
	
	public Texture texture;
	
	public int xInGame, yInGame;
	
	public Tile() {
		
		id = 0;
		texture = new Texture();
		r = new Rectangle();
		
		xInGame = 0;
		yInGame = 0;
		
	}

}
