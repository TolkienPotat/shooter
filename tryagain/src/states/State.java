package states;

public interface State {

	
	
	public void render();
	
	public void init();
	
	public void exit();

	public void input();

	public void input(int moveXDirection, int moveYDirection, int moveXDirectionp, int moveYDirectionp);

	void tick(boolean shooting);
	
	
	
}
