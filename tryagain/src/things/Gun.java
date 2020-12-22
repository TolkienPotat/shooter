package things;




public class Gun {

	public int firerate;
	
	public int shotsPerShot;
	
	public int spread;
	
	public String name;
	
	public int velocity;
	
	public int damage;
	
	public Gun(int firerate, int shotsPerShot, int spread, int velocity, String name, int damage) {
	
		this.firerate = 60/firerate;
		
		this.shotsPerShot = shotsPerShot;
		
		this.spread = spread;
		
		this.name = name;
		
		this.velocity = velocity;
		
		this.damage = damage;
		
	}
	
}
