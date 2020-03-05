package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JFileChooser;
import Components.Building;
import Components.Wall;

public class AddWallBox extends JPanel{
	Wall wall;
	JTextField height, imageURL;
	static BufferedImage texture;
	JFileChooser browser;
	JButton enter, exit;
	Plane plane;

	public AddWallBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.plane = plane;
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Wall");
		this.setBackground(new Color(190,190,190));

		// name = new JTextField("Wall");

		enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(wall != null) {
					//Edit wall
					
				}
				else {
					//Create new wall
				}
				setVisible(false);
			}
		});

		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				wall = null;
			}
		});

		
		add(enter);
		add(exit);
	}
	
	public void edit(Wall wall) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		// name.setText(wall.getID());
		this.wall = wall;
		setVisible(true);
	}
}

