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

public class AddQuestionsBox extends JPanel{
	Building building;
	JTextField question;
	JButton enter, exit;
	
	public AddQuestionsBox(int x, int y, int width, int height) {
		super();
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Questions");
		this.setBackground(new Color(190,190,190));
		
		question = new JTextField("Question");
		
		enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				building = null;
			}
		});
		
		add(question);
		add(enter);
		add(exit);
	}
	
	
	
	public void edit(Building building) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		this.building = building;
		setVisible(true);

	}


}
