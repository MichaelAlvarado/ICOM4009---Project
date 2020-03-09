package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Components.Building;
import Components.Question;

public class AddQuestionsBox extends JPanel{

	private Plane plane;
	private Building building;
	private JTextField question, correct, fail1, fail2, fail3;
	private JButton enter, exit;

	public AddQuestionsBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.plane = plane;
		setLayout(null);
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Questions");
		this.setBackground(new Color(190,190,190));

		//Question Statement
		JLabel questionLabel = new JLabel("State the Question:");
		questionLabel.setBounds(30, 40, this.getWidth()-60, 20);
		question = new JTextField();
		question.setHorizontalAlignment(JTextField.CENTER);
		question.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		question.setBounds(30, questionLabel.getY()+questionLabel.getHeight(), this.getWidth()-60, 60);

		//Option 1 (Correct answer)
		JLabel correctLabel = new JLabel("Correct Option");
		correctLabel.setBounds(30, question.getY()+question.getHeight()+25, this.getWidth()-60, 20);
		correct = new JTextField();
		correct.setHorizontalAlignment(JTextField.CENTER);
		correct.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		correct.setBounds(30, correctLabel.getY()+correctLabel.getHeight(), this.getWidth()-60, 40);

		//Option 2 (incorrect answer)
		JLabel fail1Label = new JLabel("Incorrect Option");
		fail1Label.setBounds(30, correct.getY()+correct.getHeight()+10, this.getWidth()-60, 20);
		fail1 = new JTextField();
		fail1.setHorizontalAlignment(JTextField.CENTER);
		fail1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		fail1.setBounds(30, fail1Label.getY()+fail1Label.getHeight(), this.getWidth()-60, 40);

		//Option 3 (incorrect answer)
		JLabel fail2Label = new JLabel("Incorrect Option");
		fail2Label.setBounds(30, fail1.getY()+fail1.getHeight()+10, this.getWidth()-60, 20);
		fail2 = new JTextField();
		fail2.setHorizontalAlignment(JTextField.CENTER);
		fail2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		fail2.setBounds(30, fail2Label.getY()+fail2Label.getHeight(), this.getWidth()-60, 40);

		//Option 4 (incorrect answer)
		JLabel fail3Label = new JLabel("Incorrect Option");
		fail3Label.setBounds(30, fail2.getY()+fail2.getHeight()+10, this.getWidth()-60, 20);
		fail3 = new JTextField();
		fail3.setHorizontalAlignment(JTextField.CENTER);
		fail3.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		fail3.setBounds(30, fail3Label.getY()+fail3Label.getHeight(), this.getWidth()-60, 40);

		//Enter button
		enter = new JButton("Enter");
		enter.setBounds(width/2-50, height-60, 100, 50);		
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(plane.currentBuilding != null) {
					plane.currentBuilding.addQuestion(
							new Question(question.getText(), correct.getText(),
									fail1.getText(), fail2.getText(), fail3.getText()));
				}
				question.setText("");
				correct.setText("");
				fail1.setText("");
				fail2.setText("");
				fail3.setText("");
				exit();
			}
		});

		exit = new JButton("Exit");
		exit.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.BLACK));
		exit.setBounds(width-55, 5, 50, 30);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		add(questionLabel);
		add(question);
		add(correctLabel);
		add(correct);
		add(fail1Label);
		add(fail1);
		add(fail2Label);
		add(fail2);
		add(fail3Label);
		add(fail3);
		add(enter);
		add(exit);
	}

	public void edit(Building building) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		this.building = building;
		plane.disable();
		setVisible(true);
	}
	public void addQuestion() {
		setVisible(true);
		plane.disable();
	}
	private void exit() { 
		plane.enable();
		setVisible(false);
		building = null;
	}


}
