package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class AddMapBox extends JPanel{

	JTextField name;
	JFileChooser browser;
	JLabel nameLabel, imageLabel;
	JButton enter, exit, browse;

	public AddMapBox(int x, int y, int width, int height) {
		super();
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
		//		try {
		//			Scanner reader = new Scanner(browser.getSelectedFile());
		//		} catch (FileNotFoundException e1) {
		//			e1.printStackTrace();
		//		}
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
				setVisible(false);
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
