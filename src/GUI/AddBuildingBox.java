package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Components.Building;

public class AddBuildingBox extends JPanel{

	Building building;
	JTextField name;
	JButton enter, exit;
	Plane plane;

	public AddBuildingBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.plane = plane;
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Building");
		this.setBackground(new Color(190,190,190));

		name = new JTextField("Building");

		enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(building != null) {
					//Edit Building
					building.setName(name.getText());
				}
				else {
					//Create new building
				}
				setVisible(false);
				plane.enable();
			}
		});

		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				building = null;
				plane.enable();

			}
		});

		add(name);
		add(enter);
		add(exit);
	}
	public void edit(Building building) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		name.setText(building.getName());
		this.building = building;
		setVisible(true);

	}
}
/*
 * Need to add method to create new building Object
 * input values (name, Color, Image, etc)
 * close and enter button
 */
