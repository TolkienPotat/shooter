package things;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import dev.create.Initiate;

public class Player extends Entity{

	public int xInGame, yInGame;
	
	
	//walls don't stop you and enemies don't kill you if this is enabled
	public boolean devMode = false;
	
	//movement speed
	private int speed = 4;
	
	//position bullets are fired from
	public Point guntipPos;
	
	public Point center;
	
	public Point gtpOnScreen;
	
	Random random;


	public Gun currentGun;
	
	int gunPositionValue;
	
	private ArrayList<Gun> guns;
	
	private int spawnDistance = 240;
	
	public int score = 0;
	
	public Player() {
		
		x = 0;
		y = 0;
		
		xInGame = 10;
		yInGame = 10;
		
		health = 50;
		
		guntipPos = new Point(xInGame + 15,yInGame + 20);
		
		gtpOnScreen = new Point(15, 20);
		
		center = new Point(12, 25);
		
		random = new Random();
		
		
		
		guns = new ArrayList<Gun>();
		
		addGuns();
		
		currentGun = guns.get(0);
		gunPositionValue = 0;
		
	}
	
	public void tick() {
		
		if (health <= 0) {
			Initiate.game.newState = "Death";
			Initiate.game.score = score;
		}
		
		updateRect();
		guntipPos.setLocation(xInGame + 15,yInGame + 20);
		
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
	
	public void init(Map map) {
		createTexture("Textures/character.png");
		xInGame = (map.length * 40) / 2 + (random.nextInt(spawnDistance *2) - spawnDistance);
		yInGame = map.width * 40 / 2 + (random.nextInt(spawnDistance*2) - spawnDistance);
		health = 50;
	}
	
	public void switchWeapons() {
		
		gunPositionValue++;
		
		if (gunPositionValue >= guns.size()) {
			
			gunPositionValue = 0;
			
		} 
		
		currentGun = guns.get(gunPositionValue);
		
		System.out.println("Switched to gun " + currentGun.name);
		
	}
	
	public void addGuns() {
		
		//fast, straight
		guns.add(new Gun(20, 1, 5, 13, "Auto-Rifle", 1));
		
		//shotgun
		guns.add(new Gun(2, 15, 15, 10, "Shotgun", 1));
		
		guns.add(new Gun(1, 1, 1, 35, "Sniper", 10));
		
	}
	
}
