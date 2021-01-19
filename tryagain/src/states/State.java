package states;

public interface State {

	
	
	public void render();
	
	public void init();
	
	public void exit();

	public void input();

	

	void tick(boolean shooting);
	
	
	
}
