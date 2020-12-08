package things;

public class Node {

	int x, y;
	
	int fCost, gCost, hCost;
	
	boolean passable = true;
	
	Node comesFrom;
	
	
	
	public Node() {
		
		
		
	}
	
	public Node(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		
		
	}
	
	public int getfCost(Node target, Node start) {
		int gX = Math.abs(x - target.x);
		int gY = Math.abs(y - target.y);
		
		gCost = gX + gY;
		
		fCost = gCost;
		
		int hX = Math.abs(start.x - x);
		int hY = Math.abs(start.y - y);
		
		hCost = hY + hX;
		
		fCost = gCost + hCost;
		return fCost;
		
	}
	
	public void printCoords() {
		System.out.println("Coordinates of node " + this + " are x " + x + " y " + y);
	}
	
}
