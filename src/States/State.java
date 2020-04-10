package States;

import java.awt.Graphics;

/**
 * Description - This is only for the States related to the game
 * @author Michael J. Alvarado
 *
 */
public interface State {

	public void tick();
	public void render(Graphics g);

}
