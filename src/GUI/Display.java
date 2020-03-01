package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * @author Michael Alvarado
 * Date - 28/Feb/2020
 * This class its a initialize JFrame
 */
public class Display extends JFrame{

	public Display(String title, int width, int height) {
		super();
		setTitle(title);
	    setPreferredSize(new Dimension(width, height+24));
	    pack();
	    setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setSize(width,height);
	}
}
