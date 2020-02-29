package GUI;

import javax.swing.JFrame;
/**
 * @author Michael Alvarado
 * Date - 28/Feb/2020
 * This class its a initialize JFrame
 */
public class Display extends JFrame{
	
	public Display(String title, int width, int height) {
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
	}
}
