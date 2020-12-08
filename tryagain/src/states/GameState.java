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
import things.Player;

public class GameState implements State{
	
	private Renderer renderer;
	
	private Player player;
	
	private Map map;
	
	private int accuracy = 10;
	private int fireRate = 1;
	private int shotsTicked = 0;
	
//	private int shootsound;
//	private Source source;
	
	private ArrayList<Bullet> bullets;
	
	private Random random = new Random();
	
	private EnemyHandler enemy;
	
	private int summonRate = 30;
	private int ticksGoneNoSum = 0;

	ArrayList<Point> spawnSpaces = new ArrayList<Point>();
	
	public GameState() {
		
		renderer = new Renderer();
		player = new Player();
		map = new Map("Res/map1.txt", 100, 50);
		bullets = new ArrayList<Bullet>();
		enemy = new EnemyHandler();
		
		
		
	}

	@Override
	public void tick(boolean shooting) {
		
		if (shooting) {
			shoot();
		}
		
		player.updateRect();

		for (int i = 0; i < bullets.size(); i++) {
			boolean bulletDead = false;
			bullets.get(i).move();
			
			
			for (int j = 0; j < enemy.enemies.size(); j++) {
		
				if (enemy.enemies.get(j).r.intersects(bullets.get(i).r)) {
					enemy.enemies.get(j).health --;
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
				if ((bullets.get(i).time > 300) || map.tiles[(int) ((bullets.get(i).xInGame) / 40)][(int) ((bullets.get(i).yInGame) / 40)].id == 3) {
					bullets.remove(i);
				}
				} catch (ArrayIndexOutOfBoundsException e) {
					bullets.remove(i);
				}
			
		}
		
	
		
		ticksGoneNoSum++;
		if (ticksGoneNoSum == summonRate) {
			enemy.summon(spawnSpaces.get(random.nextInt(spawnSpaces.size())).x * 40, spawnSpaces.get(random.nextInt(spawnSpaces.size())).y * 40);
//			enemy.summon(50*40, 25*40);
			ticksGoneNoSum = 0;
		}
		
		enemy.tick(player, map);
		
		
		
	}

	@Override
	public void render() {
		renderer.clear();
		map.renderMap(player);
		player.draw();
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).posX = bullets.get(i).xInGame - player.xInGame;
			bullets.get(i).posY = bullets.get(i).yInGame - player.yInGame;
			bullets.get(i).render();
		}
		enemy.render();
	}

	@Override
	public void init() {
		
		renderer.init();
		System.out.println("initiating the renderer");
		player.createTexture("Textures/character.png");
		player.xInGame = (map.length * 40) / 2 + (random.nextInt(480) - 240);
		player.yInGame = map.width * 40 / 2 + (random.nextInt(480) - 240);

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





		
	}
	
	private void addSpawnPoint(int x, int y) {
		
		spawnSpaces.add(new Point(x, x));
	
	}
	

	@Override
	public void exit() {
		
	}

	@Override
	public void input(int moveXDirection,int moveYDirection,int moveXDirectionp,int moveYDirectionp) {

		player.moveLeftRight(moveXDirection, map);
		player.moveUpDown(moveYDirection, map);
		player.moveLeftRight(moveXDirectionp, map);
		player.moveUpDown(moveYDirectionp, map);
		
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

	public Point getCursor(long windowID) {
		Point point = new Point();
		DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(windowID, xBuffer, yBuffer);
		double x = xBuffer.get(0);
		double y = yBuffer.get(0);
		
		x -= Initiate.window.width/2;
		y = -y + Initiate.window.height/2;
		
		point.x = (int) x;
		point.y = (int) y;
		return point;
		
	}
	
	public float getAngle(Point target) {
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - 0, target.x - 0));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public void shoot() {
		shotsTicked++;
		if (shotsTicked == fireRate) {
			bullets.add(new Bullet((getAngle(getCursor(Initiate.window.id)) + (random.nextInt(accuracy) - accuracy)), renderer));
			bullets.get(bullets.size() - 1).xInGame = player.xInGame + 25;
			bullets.get(bullets.size() - 1).yInGame = player.yInGame + 15;
			bullets.get(bullets.size() - 1).render();
			shotsTicked = 0;
			
			
		}
		
	}
	
}
