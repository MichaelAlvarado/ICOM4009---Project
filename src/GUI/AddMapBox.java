package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Components.Map;


public class AddMapBox extends JPanel{

	JTextField name;
	static BufferedImage image;
	JFileChooser browser;
	JLabel nameLabel, imageLabel;
	JButton enter, exit, browse;
	Map map;
	Plane plane;

	public AddMapBox(int x, int y, int width, int height, Plane plane) {
		super();
		map = new Map();
		this.plane = plane;
		setLayout(null);
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Map");
		this.setBackground(new Color(190,190,190));

		nameLabel = new JLabel("Name of map");
		nameLabel.setBounds(10, 60, 100,25);
		name = new JTextField("Map");
		name.setBounds(130, 60, (width/2), 25);

		imageLabel = new JLabel("Image of map");
		imageLabel.setBounds(10, 100, 100,25);
		browser = new JFileChooser();
		browser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File imageFile = browser.getSelectedFile();
				try {						
					System.out.println("loading file...");
					image = ImageIO.read(imageFile);
					System.out.println("load file");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		browse = new JButton("Browse");
		browse.setBounds(130, 100, 120, 25);
		browse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				browser.showOpenDialog(AddMapBox.this);
			}

		});

		enter = new JButton("Enter");
		enter.setBounds(width/2-100, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(image != null) {
					map.setPicture(image);
				}
				map.setBuildingName(name.getText());
				AddMapBox.this.plane.setMap(map);
				setVisible(false);
				AddMapBox.this.plane.repaint();
			}
		});

		exit = new JButton("Exit");
		exit.setBounds(width/2, height-60, 100, 50);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		add(name);
		add(nameLabel);
		add(imageLabel);
		add(enter);
		add(exit);
		add(browse);
	}
	public void edit() {
		setVisible(true);
	}


}
