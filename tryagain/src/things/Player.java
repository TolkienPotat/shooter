package things;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import dev.create.Initiate;

public class Player extends Entity{

	public int xInGame, yInGame;
	
	
	
	public boolean devMode;
	
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
		
		devMode = Initiate.game.devMode;
		
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
//		System.out.println(xInGame/40 + " " + yInGame/40);
//		System.out.println(xInGame + " " + yInGame);
		updateRect();
		guntipPos.setLocation(xInGame + 15,yInGame + 20);
		
	}
	
	public void move(int xVelocity, int yVelocity, Map map) {
		
		updateRect();
		
		if (devMode) {
			xInGame += xVelocity;
			yInGame += yVelocity;
			return;
		}
		
		if (xVelocity == 0 && yVelocity == 0) {
			return;
		}
		
		r.y += yVelocity;
		
		try {
		if (!(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()),40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3) 
		&& !(map.tiles[Math.floorDiv((int) (r.getX()),40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3) 
		&& !(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()),40)][Math.floorDiv((int) (r.getY()), 40)].id == 3)
		&& !(map.tiles[Math.floorDiv((int) (r.getX()),40)][Math.floorDiv((int) (r.getY()), 40)].id == 3) ) {
			yInGame += yVelocity;

		} else {
			Initiate.game.yVelocity = 0;
			r.y -= yVelocity;
		}
		} catch (ArrayIndexOutOfBoundsException e) {r.y -= yVelocity;Initiate.game.yVelocity = 0;}
		r.x += xVelocity;
		
		try {
		if (!(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()),40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3) 
		&& !(map.tiles[Math.floorDiv((int) (r.getX()),40)][Math.floorDiv((int) (r.getY() + r.getHeight()), 40)].id == 3) 
		&& !(map.tiles[Math.floorDiv((int) (r.getX() + r.getWidth()),40)][Math.floorDiv((int) (r.getY()), 40)].id == 3)
		&& !(map.tiles[Math.floorDiv((int) (r.getX()),40)][Math.floorDiv((int) (r.getY()), 40)].id == 3) ) {

			xInGame += xVelocity;
		} else {
			Initiate.game.xVelocity = 0;
		}
		} catch (ArrayIndexOutOfBoundsException e) {Initiate.game.xVelocity = 0;}
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
		
		//fast, straight, 13vel
		guns.add(new Gun(20, 1, 5, 11, "Rifle", 1));
		
		//shotgun
		guns.add(new Gun(2, 15, 15, 10, "Shotgun", 1));
		

		
	}
	
}
