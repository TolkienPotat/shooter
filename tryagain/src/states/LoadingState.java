package states;

import dev.create.Initiate;
import dev.draw.Renderer;
import dev.draw.Texture;

public class LoadingState implements State{

	Renderer r = new Renderer();
	int ticks;
	
	Texture black = new Texture();
	
	@Override
	public void render() {
		
		r.clear();
		
		r.begin();
		black.bind();
		r.drawTexture(black, 0 - black.getWidth()/2, 0 - black.getHeight()/2);
		r.end();
		
	}

	@Override
	public void init() {
		ticks = 0;
		r.init();
		black = Texture.loadTexture("Textures/loading.png");
		
	}

	@Override
	public void exit() {
		
	}

	@Override
	public void input() {
		
	}

	@Override
	public void input(int moveXDirection, int moveYDirection, int moveXDirectionp, int moveYDirectionp) {
		
	}

	@Override
	public void tick(boolean shooting) {
		ticks++;
		if (ticks > 120) {
			Initiate.game.newState = "Lobby";
		}
	}

}
