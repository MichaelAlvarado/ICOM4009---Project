package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class AddMapBox extends JPanel{

	JTextField name;
	JButton enter, exit;

	public AddMapBox(int x, int y, int width, int height) {
		super();
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Map");
		this.setBackground(new Color(190,190,190));

		name = new JTextField("Map");

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
			}
		});

		add(name);
		add(enter);
		add(exit);
	}
	public void edit() {
		setVisible(true);
	}

}
