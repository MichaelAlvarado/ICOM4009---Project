/**
 * 
 */
package MapDesingComponents;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Components.Tree;
import Components.Map;

/**
 * @author Michael J. Alvarado
 * This class will let you place default trees
 * @date Mar 12, 2020
 */
public class ToolBox{

	private Plane plane;
	private Tree defaultTrees1, defaultTrees2, defaultTrees3;
	private boolean isOpen;
	private JButton tool;

	public ToolBox(Plane plane) {
		isOpen = false;
		this.plane = plane;
		Point p1 = new Point();
		defaultTrees1 = new Tree(plane.map, 0,p1);
		defaultTrees2 = new Tree(plane.map, 1,p1);
		defaultTrees3 = new Tree(plane.map, 2,p1);
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File("res/addButton.png"));
		}
		catch(Exception ex) {
		}
		// Set the image icon here
		Image dimg = buttonIcon.getScaledInstance(60, 60,Image.SCALE_SMOOTH); //scale the image
		tool = new JButton(new ImageIcon(dimg));
		tool.setBorderPainted(false);
		tool.setContentAreaFilled(false);
		tool.setFocusPainted(false);
		tool.setOpaque(false);
		tool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isOpen = !isOpen;
				plane.repaint();
			}
		});
		tool.setBounds(plane.getWidth()-65, plane.getHeight()/2-60, 60, 60);
		plane.add(tool);
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void actions() {

	}

	public void paint(Graphics g) {
		if(isOpen) {
			tool.setLocation(plane.getWidth()-165, plane.getHeight()/2-60);
			int x = tool.getX()+tool.getWidth();//x of panel
			g.setColor(new Color(0,0,0,100));
			g.fillRect(x, 0, 100, plane.getHeight());
			g.drawImage(defaultTrees1.getTreeImage(), x, 10,defaultTrees1.getWidth(), defaultTrees1.getHeight(),null);
			g.drawImage(defaultTrees2.getTreeImage(), x, 15 + defaultTrees1.getHeight(), defaultTrees2.getWidth(), defaultTrees2.getHeight(),null);
			g.drawImage(defaultTrees3.getTreeImage(), x, 15 +defaultTrees1.getHeight()+defaultTrees2.getHeight(),defaultTrees3.getWidth(), defaultTrees3.getHeight(),null);
		}
		else {
			tool.setLocation(plane.getWidth()-65, plane.getHeight()/2-60);
		}
	}

	public void placeTree() {

	}

}
