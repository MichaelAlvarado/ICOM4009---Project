package GUI;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class OptionPanel extends JPanel {
	private JFrame display;
	private Plane plane;
	public OptionPanel(int x, int y, int width, int height, JFrame parent, Plane plane) {
		super();
		this.display = parent;
		this.plane = plane;
		setBackground(new Color(190,190,190));
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setBounds(0, 0, width, height);
		setLayout(null);
		

		JButton help = new JButton("Help");
		help.setBounds(width-110, 15, 100, 25);
		add(help);

		JButton setting = new JButton("Setting");
		setting.setBounds(width-110, height-35, 100, 25);
		add(setting);
		
		JButton addBuilding = new JButton("Add Building");
		addBuilding.setBounds((getWidth()/2)+5, 15, 125, 25);
		add(addBuilding);
	}
	

	private void settingPopUp(int x, int y) {
		PopupMenu setting = new PopupMenu();

		MenuItem clear = new MenuItem("clear current line");
		MenuItem grid = new MenuItem("Grid");
		MenuItem cpColor = new MenuItem("change current point Color");
		MenuItem clColor = new MenuItem("change current line Color");
		MenuItem ppColor = new MenuItem("change previous point Color");
		MenuItem plColor = new MenuItem("change previous line Color");

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		grid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.gridIsOn = !plane.gridIsOn;
				plane.repaint();
			}
		});

		cpColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"currentPoint");
			}
		});

		clColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"currentLine");
			}
		});

		ppColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"previousPoint");
			}
		});

		plColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"previousLine");
			}
		});

		setting.add(clear);
		setting.add(grid);
		setting.add(cpColor);
		setting.add(clColor);
		setting.add(ppColor);
		setting.add(plColor);

		display.add(setting);
		setting.show(display, x, y);
	}

	private void colorPopup(int x, int y, String component) {
		PopupMenu ColorPopup = new PopupMenu();

		MenuItem magenta = new MenuItem("Magenta");
		MenuItem green = new MenuItem("Green");
		MenuItem red = new MenuItem("Red");
		MenuItem blue = new MenuItem("Blue");
		MenuItem orange = new MenuItem("Orange");


		magenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.MAGENTA); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.MAGENTA);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.MAGENTA);
				else if(component.equals("previousLine"))
					plane.pL = (Color.MAGENTA);				
				plane.repaint();
			}
		});

		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color green = new Color(20,198,5); //Green
				if(component.equals("currentPoint"))
					plane.cP = (green); 
				else if(component.equals("currentLine"))
					plane.cL = (green);
				else if(component.equals("previousPoint"))
					plane.pP = (green);
				else if(component.equals("previousLine"))
					plane.pL = (green);
				plane.repaint();
			}
		});

		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.RED); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.RED);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.RED);
				else if(component.equals("previousLine"))
					plane.pL = (Color.RED);
				plane.repaint();
			}
		});

		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.BLUE); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.BLUE);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.BLUE);
				else if(component.equals("previousLine"))
					plane.pL = (Color.BLUE);
				plane.repaint();
			}
		});

		orange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.ORANGE); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.ORANGE);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.ORANGE);
				else if(component.equals("previousLine"))
					plane.pL = (Color.ORANGE);
				plane.repaint();
			}
		});

		ColorPopup.add(orange);
		ColorPopup.add(blue);
		ColorPopup.add(red);
		ColorPopup.add(green);
		ColorPopup.add(magenta);
		display.add(ColorPopup);
		ColorPopup.show(display, x, y);
	}

	private void helpScreen() {
		String Instructions = "Help";
		JOptionPane.showMessageDialog(display, Instructions);
	}

}
