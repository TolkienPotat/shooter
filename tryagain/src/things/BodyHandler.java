package things;

import java.util.ArrayList;

import dev.draw.Renderer;
import dev.draw.Texture;

public class BodyHandler {

	ArrayList<Texture> textures;

	ArrayList<Body> bodies;

	public BodyHandler() {
		textures = new ArrayList<Texture>();
		bodies = new ArrayList<Body>();
		addTextures();
	}

	private void addTextures() {
		textures.add(Texture.loadTexture("/enemyDeath/1.png"));
		textures.add(Texture.loadTexture("/enemyDeath/2.png"));
		textures.add(Texture.loadTexture("/enemyDeath/3.png"));
		textures.add(Texture.loadTexture("/enemyDeath/4.png"));
		textures.add(Texture.loadTexture("/enemyDeath/5.png"));
		textures.add(Texture.loadTexture("/enemyDeath/6.png"));
		textures.add(Texture.loadTexture("/enemyDeath/7.png"));
		textures.add(Texture.loadTexture("/enemyDeath/8.png"));
	}

	public void tick() {
		for (int i = 0; i < bodies.size(); i++) {

			if (bodies.get(i).age < 239) {
				bodies.get(i).age++;
			}

		}
	}

	public void render(Renderer r, Player p) {
		
		for (int i = 0; i < bodies.size(); i++) {
			r.begin();
			r.drawTexture(textures.get(Math.floorDiv(bodies.get(i).age, 30)), bodies.get(i).x - p.xInGame, bodies.get(i).y - p.yInGame, bodies.get(i).x, bodies.get(i).y);
			textures.get(bodies.get(i).age / 30).bind();
			r.end();
		}
		
	}

	public void add(int x, int y) {
		bodies.add(new Body(x, y));
	}

}
