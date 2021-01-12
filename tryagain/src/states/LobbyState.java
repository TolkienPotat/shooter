package states;

import java.util.Random;

import dev.create.Initiate;
import dev.draw.Renderer;
import things.Map;
import things.Player;

public class LobbyState implements State {

	private Renderer renderer;
	
	private Player player;
	
	private Map map;
	
	private Random random = new Random();

	
	public LobbyState() {
		
		renderer = new Renderer();
		player = new Player();
		map = new Map("Res/lobby.txt", 16, 12);
		
	}

	@Override
	public void tick(boolean shooting) {

		player.updateRect();
		
		if (player.r.intersects(map.tiles[7][11].r) || player.r.intersects(map.tiles[8][11].r)) {
			Initiate.game.newState = "Game";
		}
		
	}

	@Override
	public void render() {

		renderer.clear();
		map.renderMap(player);
		player.draw(player.xInGame, player.yInGame);
		
	}

	@Override
	public void init() {

		renderer.init();
		
		player.createTexture("Textures/character.png");
		player.xInGame = (map.length * 40) / 2 + (random.nextInt(160) - 80);
		player.yInGame = map.width * 40 / 2 + (random.nextInt(160) - 80);

		
	}

	@Override
	public void exit() {

	}

	@Override
	public void input() {
		
	}

	@Override
	public void input(int moveXDirection, int moveYDirection, int moveXDirectionp, int moveYDirectionp) {
		
		player.moveLeftRight(moveXDirection, map);
		player.moveUpDown(moveYDirection, map);
		player.moveLeftRight(moveXDirectionp, map);
		player.moveUpDown(moveYDirectionp, map);
		
	}

}
