package states;

import java.util.HashMap;
import java.util.Map;

public class StateMachine implements State{

	private Map<String, State> states;
	
	private State currentState;
	
	
	public StateMachine() {
		
	states = new HashMap<>();
	currentState = new EmptyState();
	states.put(null, currentState);
		
	}
	
	public void add(String name, State state) {
		states.put(name, state);
	}
	
	public void change(String name) {
		currentState.exit();
		currentState = states.get(name);
		currentState.init();
	}

	@Override
	public void tick(boolean shooting) {
		currentState.tick(shooting);
	}

	@Override
	public void render() {
		currentState.render();
	}

	@Override
	public void init() {
		currentState.init();
	}

	@Override
	public void exit() {
		currentState.exit();
	}

	@Override
	public void input() {
		currentState.input();
	}

	@Override
	public void input(int moveXDirection, int moveYDirection, int moveXDirectionp, int moveYDirectionp) {
		currentState.input(moveXDirection, moveYDirection, moveXDirectionp, moveYDirectionp);
	}

	
	
}
