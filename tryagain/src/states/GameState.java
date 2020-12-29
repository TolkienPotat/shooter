package states;

import java.awt.Point;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import dev.create.Initiate;
import dev.draw.Renderer;
import things.Bullet;
import things.EnemyHandler;
import things.Map;
import things.NumberWriter;
import things.Player;

public class GameState implements State {

	private Renderer renderer;

	private Player player;

	private Map map;

	private int shotsTicked = 0;

	// distance in which the player spawns from the center of the map

	private ArrayList<Bullet> bullets;

	private Random random = new Random();

	private EnemyHandler enemy;

	// speed enemies spawn - lower is faster 1-60
	private int summonRate = 20;

	private int ticksGoneNoEnemySummon = 0;
	
	
	
	NumberWriter scoreWriter;

	ArrayList<Point> spawnSpaces = new ArrayList<Point>();

	public GameState() {

		renderer = new Renderer();
		player = new Player();
		map = new Map("Res/map1.txt", 100, 50);
		bullets = new ArrayList<Bullet>();
		enemy = new EnemyHandler();
		scoreWriter = new NumberWriter();

	}

	@Override
	public void tick(boolean shooting) {
		shotsTicked++;
		if (Initiate.game.rightMouseDown && !Initiate.game.rightMouseDownPrevious) {
			player.switchWeapons();
		}

		if (shooting) {
			shoot();
		}

		player.tick();

		for (int i = 0; i < bullets.size(); i++) {
			boolean bulletDead = false;
			bullets.get(i).move();

			if (bullets.get(i).r.intersects(player.r) && bullets.get(i).owner == 1) {
				player.health -= bullets.get(i).damage;
				System.out.println("Your health is now " + player.health);
				bullets.remove(i);
				bulletDead = true;
			}

			if (bulletDead) {
				bulletDead = false;
				continue;
			}

			for (int j = 0; j < enemy.enemies.size(); j++) {

				if (enemy.enemies.get(j).r.intersects(bullets.get(i).r) && bullets.get(i).owner == 0) {
					enemy.enemies.get(j).health -= bullets.get(i).damage;
					enemy.enemies.get(j).shake(2);
					bullets.remove(i);
					bulletDead = true;
					break;
				}

			}

			if (bulletDead) {
				bulletDead = false;
				continue;
			}

			try {
				if ((bullets.get(i).time > 300)
						|| map.tiles[(int) ((bullets.get(i).xInGame) / 40)][(int) ((bullets.get(i).yInGame)
								/ 40)].id == 3) {
					bullets.remove(i);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				bullets.remove(i);
			}

		}

		ticksGoneNoEnemySummon++;
		if (ticksGoneNoEnemySummon == summonRate) {
			Point p = spawnSpaces.get(random.nextInt(spawnSpaces.size()));
			enemy.summon(p.x * 40, p.y * 40);
			ticksGoneNoEnemySummon = 0;
		}

		enemy.tick(player, map);

		player.score = enemy.score;
		
		for (int i = 0; i < enemy.enemies.size(); i++) {
			if (enemy.enemies.get(i).shootThisTick) {
				bullets.add(new Bullet(getAngle(player.center, enemy.enemies.get(i).center), renderer, 1,
						enemy.enemies.get(i).gunTipPos.x, enemy.enemies.get(i).gunTipPos.y, 10, 1));
			}
		}

	}

	@Override
	public void render() {
		renderer.clear();
		map.renderMap(player);

		renderer.begin();
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).posX = bullets.get(i).xInGame - player.xInGame;
			bullets.get(i).posY = bullets.get(i).yInGame - player.yInGame;
			bullets.get(i).render();
		}

		renderer.end();

		enemy.render(renderer, player);
		player.draw();
		
		scoreWriter.draw(220, 200, renderer, player.score);
		
	}

	@Override
	public void init() {

		renderer.init();
		player.init(map);
		addSpawnSpots();
		

	}

	private void addSpawnSpots() {

		addSpawnPoint(1, 48);
		addSpawnPoint(25, 35);
		addSpawnPoint(34, 34);
		addSpawnPoint(35, 47);
		addSpawnPoint(24, 17);
		addSpawnPoint(56, 46);
		addSpawnPoint(95, 35);
		addSpawnPoint(98, 48);
		addSpawnPoint(75, 23);
		addSpawnPoint(77, 17);
		addSpawnPoint(89, 1);
		addSpawnPoint(80, 48);
		addSpawnPoint(6, 19);
		addSpawnPoint(1, 1);
		addSpawnPoint(14, 3);
		addSpawnPoint(45, 4);
		addSpawnPoint(71, 12);
		addSpawnPoint(66, 29);
		addSpawnPoint(12, 15);
		addSpawnPoint(45, 39);
		addSpawnPoint(39, 21);
		addSpawnPoint(53, 14);
		addSpawnPoint(96, 3);
		addSpawnPoint(89, 21);
		addSpawnPoint(76, 7);
		addSpawnPoint(25, 24);
		addSpawnPoint(40, 30);
		addSpawnPoint(87, 28);
		addSpawnPoint(4, 44);
		addSpawnPoint(19, 46);
		addSpawnPoint(9, 36);
		addSpawnPoint(0, 31);
		addSpawnPoint(2, 28);
		addSpawnPoint(58, 8);
		addSpawnPoint(31, 1);
		addSpawnPoint(72, 18);
		addSpawnPoint(75, 45);

		
		//test
		
//		addSpawnPoint(50, 25);
//		addSpawnPoint(51, 25);
	}

	private void addSpawnPoint(int x, int y) {

		spawnSpaces.add(new Point(x, y));

	}

	@Override
	public void exit() {
		player.score = 0;
		bullets.clear();
		enemy.clear();
	}

	@Override
	public void input(int moveXDirection, int moveYDirection, int moveXDirectionp, int moveYDirectionp) {

		player.moveLeftRight(moveXDirection, map);
		player.moveUpDown(moveYDirection, map);
		player.moveLeftRight(moveXDirectionp, map);
		player.moveUpDown(moveYDirectionp, map);

	}

	@Override
	public void input() {

	}

	public Point getCursor(long windowID) {
		Point point = new Point();
		DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(windowID, xBuffer, yBuffer);
		double x = xBuffer.get(0);
		double y = yBuffer.get(0);

		x -= Initiate.window.width / 2;
		y = -y + Initiate.window.height / 2;

		point.x = (int) x;
		point.y = (int) y;
		return point;

	}

	public float getAngle(Point target, Point start) {
		float angle = (float) Math.toDegrees(Math.atan2(target.y - start.y, target.x - start.x));

		if (angle < 0) {
			angle += 360;
		}

		return angle;
	}

	public void shoot() {

		if (shotsTicked >= player.currentGun.firerate) {

			for (int i = 0; i < player.currentGun.shotsPerShot; i++) {
				bullets.add(new Bullet(
						(getAngle(getCursor(Initiate.window.id), player.gtpOnScreen)
								+ (random.nextInt(player.currentGun.spread * 2) - player.currentGun.spread)),
						renderer, 0, player.guntipPos.x, player.guntipPos.y, player.currentGun.velocity,
						player.currentGun.damage));
			}
			bullets.get(bullets.size() - 1).render();
			shotsTicked = 0;

		}

	}

}
