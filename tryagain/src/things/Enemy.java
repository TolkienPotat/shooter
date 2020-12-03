package things;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;



public class Enemy extends Entity{

	public boolean alive;
	
	public int xInGame, yInGame;
	
	private int shake = 0;
	private int movingShake = 0;
	private boolean atNeg = false;
	
	private int ticksNotPathFound = 601;
	
	ArrayList<Integer> movement = new ArrayList<Integer>();
	
	public Line2D line;
	
	public Enemy() {
		
		createTexture("Textures/enemy.png");
		
		health = 10;
		line = new Line2D.Float();
		
		
	}
	
	
	
	public void tick(Player p, Map m) {
		if (ticksNotPathFound > 600) {
			pathFind(p, m);
			ticksNotPathFound = 0;
		}
		ticksNotPathFound++;
		
		r.setBounds(xInGame, yInGame, texture.getWidth(), texture.getHeight());
		
		if (shake == 0 && movement.size() > 0) {
			if (movement.get(0) == 1)
				xInGame+=4;
			else if (movement.get(0) == 2)
				xInGame-=4;
			else if (movement.get(0) == 3)
				yInGame+=4;
			else if (movement.get(0) == 4) 
				yInGame-=4;
			movement.remove(0);
		}
		if (xInGame > p.xInGame) {
			x = xInGame - p.xInGame - doShake();
			y = yInGame - p.yInGame;
			if (shake != 0 && movement.size() > 0) {
				if (movement.get(0) == 1)
					xInGame+=4;
				else if (movement.get(0) == 2)
					xInGame-=4;
				else if (movement.get(0) == 3)
					yInGame+=4;
				else if (movement.get(0) == 4) 
					yInGame-=4;
				
			movement.remove(0);
			}
		} else {
			x = xInGame - p.xInGame + doShake();
			y = yInGame - p.yInGame;
		}
		
	}
	
	public void shake(int amount) {
		if (shake == 0) {
			shake = amount;
			movingShake = amount;
		}
		
	}

	private int doShake() {
		if (shake == 0) {
			return 0;
		}
		if (movingShake > -shake && atNeg == false) {
			movingShake--;
			
		} else if (movingShake == -shake) {
			movingShake++;
			atNeg = true;
		} else if (movingShake == 0 && atNeg == true) {
			shake = 0;
			atNeg = false;
		} else if (movingShake < 0 && atNeg == true){
			movingShake++;
			
		}
		return movingShake;
	}
	
	public void pathFind(Player p, Map map) {
		
		int pX = Math.floorDiv(p.xInGame, 40);
		int pY = Math.floorDiv(p.yInGame, 40);
		
		int eX = Math.floorDiv(xInGame, 40);
		int eY = Math.floorDiv(yInGame, 40);
		
		Tile[][] tiles = map.tiles;
		int width = tiles.length;
		int height = tiles[0].length;
		
		Node[][] nodes = new Node[tiles.length][tiles[0].length];
		Node target = new Node (pX, pY);
		Node start = new Node(eX, eY);
		Node current = start;
		
		Node[] edge = new Node[62];	
		
		
		
		start.printCoords();
		target.printCoords();
		ArrayList<Node> open = new ArrayList<Node>();
		HashMap <String, Node> closed = new HashMap<String, Node>();
		
		open.add(0, start);
		movement.clear();
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				nodes[i][j] = new Node();
				nodes[i][j].x = i;
				nodes[i][j].y = j;
				
				if (tiles[i][j].id == 3) {
					nodes[i][j].passable = false;
				}
				
			}
		}
		
		boolean foundTarget = false;
		while (!foundTarget) {
			
			System.out.println(open.size());
			current = open.get(0);
			for (int i = 0; i < open.size(); i++) {
				System.out.println(open.size());
				open.get(i).getfCost(target, start);
				current.getfCost(target, start);
				System.out.println(i);
				if (current.fCost > open.get(i).fCost) {
					
					current = open.get(i);
					open.remove(i);
					open.add(0, current);
				}
				
			}
			
			
			
			closed.put(new Point(current.x, current.y).toString(), current);
			if (open.size() > 0) {
				open.remove(0);
				
			} else {
				current = start;
			}

//			System.out.println(current.fCost);
			current.printCoords();
			try {
				if (nodes[current.x + 1][current.y].passable && !closed.containsKey(new Point(current.x + 1, current.y).toString())) {			
					nodes[current.x + 1][current.y].comesFrom = current;
					open.add(nodes[current.x + 1][current.y]);
				}
				
			} catch (ArrayIndexOutOfBoundsException e) {}
			
			try {
				if (nodes[current.x - 1][current.y].passable && !closed.containsKey(new Point(current.x - 1, current.y).toString())) {
					nodes[current.x - 1][current.y].comesFrom = current;
					open.add(nodes[current.x - 1][current.y]);


				}
				
				} catch (ArrayIndexOutOfBoundsException e) {}
			
			try {
				if (nodes[current.x][current.y + 1].passable && !closed.containsKey(new Point(current.x, current.y + 1).toString())) {
					nodes[current.x ][current.y + 1].comesFrom = current;
					open.add(nodes[current.x][current.y + 1]);


				}
				
				} catch (ArrayIndexOutOfBoundsException e) {}
			
			try {
				if (nodes[current.x][current.y - 1].passable && !closed.containsKey(new Point(current.x, current.y - 1).toString())) {
					nodes[current.x][current.y - 1].comesFrom = current;
					open.add(nodes[current.x][current.y - 1]);

				}
				} catch (ArrayIndexOutOfBoundsException e) {}
			
			if (current.x == target.x && current.y == target.y) {
				foundTarget = true;
				System.out.println("found path");
			}
		}
		boolean movedBack = false;
		while (!movedBack) {

			if (current.x == start.x && current.y == start.y) {
				movedBack = true;
				continue;
			}
			
			if (current.x > current.comesFrom.x) {
				for (int i = 0; i < 10; i++) {
					movement.add(0, 1);
				}
			} else if (current.x < current.comesFrom.x) {
				for (int i = 0; i < 10; i++) {
					movement.add(0, 2);
				}
			} else if (current.y > current.comesFrom.y) {
				for (int i = 0; i < 10; i++) {
					movement.add(0, 3);
				}
			} else if (current.y < current.comesFrom.y) {
				for (int i = 0; i < 10; i++) {
					movement.add(0, 4);
				}
			}
			current = current.comesFrom;
			
			
		}
		
	}
	
}
