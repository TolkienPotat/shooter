package states;

import dev.create.Initiate;
import dev.draw.Renderer;
import dev.draw.Texture;
import things.NumberWriter;

public class DeathState  implements State{

	Renderer r;
	NumberWriter scoreWriter;
	
	Texture background;
	
	int ticks;
	
	int score;
	
	@Override
	public void render() {
		
		r.clear();
		
		r.begin();
		r.drawTexture(background,  - background.getWidth()/2, - background.getHeight()/2, 10000, 10000);
		background.bind();
		r.end();
		scoreWriter.draw(-65, -31, r, score);
		
	}

	@Override
	public void init() {
		ticks = 0;
		r = new Renderer();
		r.init();
		
		score = Initiate.game.score;
		
		Initiate.game.writeHighScore();
		
		System.out.println("Highscore is " + Initiate.game.highScore);
		
		scoreWriter = new NumberWriter();
		
		background = Texture.loadTexture("Textures/death.png");
		
	}

	@Override
	public void exit() {
		
	}

	@Override
	public void input() {
		
	}

	

	@Override
	public void tick(boolean shooting) {
		ticks++;
		if (shooting && !Initiate.game.leftMouseDownPrevious && ticks > 90) {
			Initiate.game.newState = "Lobby";
		}
		
	}

}
