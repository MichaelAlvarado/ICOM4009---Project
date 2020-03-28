package States;

import java.awt.Graphics;

public interface State {

	public void tick();
	public void render(Graphics g);

}
