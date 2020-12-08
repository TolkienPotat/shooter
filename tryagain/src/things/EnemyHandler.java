package things;

import java.util.ArrayList;

public class EnemyHandler {

	public ArrayList<Enemy> enemies;
	
	public EnemyHandler() {
		
		enemies = new ArrayList<Enemy>();
		
	}
	
	public void summon(int x, int y) {
		if (enemies.size() == 100) {
			return;
		}
		enemies.add(new Enemy());
		enemies.get(enemies.size() - 1).xInGame = x;
		enemies.get(enemies.size() - 1).yInGame = y;
		System.out.println("Summoning. at " + x + " " + y);
		
	}
	
	public void delete(int enemy) {
		enemies.remove(enemy);
	}
	
	public void tick(Player p, Map m) {
		System.out.println(enemies.size());
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).health <= 0 || enemies.get(i).shouldKillNextTick == true) {
				delete(i);
				continue;
			}
			enemies.get(i).tick(p, m);
		}
		
	}
	
	public void render() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw();
		}
	}
	
}
