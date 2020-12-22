package things;

import java.util.ArrayList;

import dev.draw.Renderer;

public class EnemyHandler {

	public ArrayList<Enemy> enemies;
	
	BodyHandler bodyHandler;
	
	public EnemyHandler() {
		
		enemies = new ArrayList<Enemy>();
		
		bodyHandler = new BodyHandler();
		
	}
	
	public void summon(int x, int y) {
		if (enemies.size() == 100) {
			return;
		}
		enemies.add(new Enemy());
		enemies.get(enemies.size() - 1).xInGame = x;
		enemies.get(enemies.size() - 1).yInGame = y;
//		System.out.println("Summoning. at " + x/40 + " " + y/40);
		
	}
	
	public void delete(int enemy, boolean dead) {
		
		
		if (dead) {
			bodyHandler.add(enemies.get(enemy).xInGame, enemies.get(enemy).yInGame);
		}
		enemies.remove(enemy);
	}
	
	public void tick(Player p, Map m) {
		
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).health <= 0) {
				delete(i, true);
				continue;
			} else if (enemies.get(i).shouldKillNextTick == true) {
				delete(i, true);
				continue;
			}
			enemies.get(i).tick(p, m);
		}
		
		bodyHandler.tick();
		
	}
	
	public void render(Renderer r, Player p) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw();
		}
		
		bodyHandler.render(r, p);
		
	}
	
}
