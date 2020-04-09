package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 * @author Michael Alvarado
 * Date - 28/Feb/2020
 * This class its a initialize JFrame
 */
public class Display extends JFrame{

	public Display(String title, int width, int height) throws IOException {
		super();
		setTitle(title);
		setPreferredSize(new Dimension(width, height));
		pack();
		setLocationRelativeTo(null);
		//setResizable(false);
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLoadingScreen();
		setSize(getPreferredSize()); //make sure its on the PreferredSize
	}
	public void setLoadingScreen() {
		try {
			getContentPane().getGraphics().drawImage(ImageIO.read(new File("res/backgrounds/loadingscreen.png")), 0, 0, getContentPane().getWidth(), getContentPane().getHeight(),null);//loading screen 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
