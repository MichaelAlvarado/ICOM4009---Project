package States;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import GameSetUp.Handler;

public class PauseState implements State {
	
	
	public PauseState(){
		
	}

	@Override
	public void tick() {
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			Handler.setCurrentState(Handler.getGameState());
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
