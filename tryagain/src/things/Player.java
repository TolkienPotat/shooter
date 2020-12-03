package things;

public class Player extends Entity{

	public int xInGame, yInGame;
	
	private boolean devMode = false;
	
	private int speed = 4;
	
	public Player() {
		
		x = 0;
		y = 0;
		
		xInGame = 10;
		yInGame = 10;
		
		health = 50;
		
	}
	
	
	

	public void moveUpDown(int direction, Map map) {
		
		updateRect();
		
		
		
		if (direction == 0) {
			return;
		} else if (direction == 1) {
			
			if (devMode) {
				yInGame += speed;
				return;
			}
			
			if (yInGame < map.width * 40 - texture.getHeight()) {
				r.height +=speed;
				try {
					if (((r.intersects(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3))) || ((r.intersects(map.tiles[Math.floorDiv((int) (r.getX()), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX()), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3)))) {
					
						return;
					
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return;
				}
				yInGame += speed;
			}
			
		} else  if (direction == 2) {
			if (devMode) {
				yInGame -= speed;
				return;
			}
			
			if (yInGame > 0) {
				r.y -=speed;
				try {
					if ((r.intersects(map.tiles[Math.floorDiv((int) (r.getX()), 40)][Math.floorDiv((int) (r.getY()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX()), 40)][Math.floorDiv((int) (r.getY()), 40)].id == 3)  || ((r.intersects(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY()), 40)].id == 3))))) {
					
						return;
					
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return;
				}
				yInGame -= speed;
				
			}
			
			
		} 
		
	}
	
	public void moveLeftRight(int direction, Map map) {
		
		updateRect();

		if (direction == 0) {
			return;
		} else if (direction == 1) {
			if (devMode) {
				xInGame -= speed;
				return;
			}
			if (xInGame > 0) {
				r.x -=speed;
				
				try {
					if ((r.intersects(map.tiles[Math.floorDiv((int) (r.getX()), 40)][Math.floorDiv((int) (r.getY()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX()), 40)][Math.floorDiv((int) (r.getY()), 40)].id == 3)) || (r.intersects(map.tiles[Math.floorDiv((int) (r.getX()), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX() ), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3))) {
					
						return;
					
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return;
				}
				xInGame -= speed;
			}
			
		} else  if (direction == 2) {
			if (devMode) {
				xInGame += speed;
				return;
			}
			if (xInGame < map.length * 40 - texture.getWidth()) {
				r.x +=speed;
				
				try {
				if (((r.intersects(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3))) || ((r.intersects(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY()), 40)].r) && (map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()), 40)][Math.floorDiv((int) (r.getY()), 40)].id == 3)))) {
					
					return;
					
				}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return;
				}
				xInGame += speed;
			}
			
		} 
		
	}
	
	public void updateRect() {
		
		r.setBounds(xInGame, yInGame, texture.getWidth(), texture.getHeight());
		
	}
	
	public String getCoords() {
		return xInGame + " " + yInGame;
	}
}
