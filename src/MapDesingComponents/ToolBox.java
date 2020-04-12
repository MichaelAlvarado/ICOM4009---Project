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
			buttonIcon = ImageIO.read(new File("res/icons/addButton.png"));
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
				resetTreesLocation();			
				plane.repaint();
			}
		});
		tool.setBounds(plane.getWidth()-65, plane.getHeight()/2-60, 60, 60);
		plane.add(tool);
	}

	public boolean isOpen() {
		return isOpen;
	}

	public Tree pressedOnTree(int x, int y) {
		Tree tree = null;
		if(defaultTrees1.getBound().contains(x,y)) {
			tree = new Tree(plane.getMap(), defaultTrees1.getTreeSpecies(), defaultTrees1.getP1());
		}
		else if(defaultTrees2.getBound().contains(x,y))	{
			tree = new Tree(plane.getMap(), defaultTrees2.getTreeSpecies(), defaultTrees2.getP1());
		}
		else if(defaultTrees3.getBound().contains(x,y))	{
			tree = new Tree(plane.getMap(), defaultTrees3.getTreeSpecies(), defaultTrees3.getP1());
		}
		return tree;
	}

	public void paint(Graphics g) {
		if(isOpen) {
			tool.setLocation(plane.getWidth()-165, plane.getHeight()/2-60);
			int x = getToolBoxX();//x of panel
			g.setColor(new Color(0,0,0,100));
			g.fillRect(x, 0, 100, plane.getHeight());
			resetTreesLocation();
			g.drawImage(defaultTrees1.getTreeImage(), defaultTrees1.getP1().x, defaultTrees1.getP1().y, defaultTrees1.getWidth(), defaultTrees1.getHeight(), null);
			g.drawImage(defaultTrees2.getTreeImage(), defaultTrees2.getP1().x, defaultTrees2.getP1().y, defaultTrees2.getWidth(), defaultTrees2.getHeight(),null);
			g.drawImage(defaultTrees3.getTreeImage(), defaultTrees3.getP1().x, defaultTrees3.getP1().y, defaultTrees3.getWidth(), defaultTrees3.getHeight(),null);
		}
		else {
			tool.setLocation(plane.getWidth()-65, plane.getHeight()/2-60);
		}
	}

	private void resetTreesLocation() {
		int x = getToolBoxX();//x of panel
		defaultTrees1.setP1(new Point((x+50)-(defaultTrees1.getWidth()/2),10));
		defaultTrees2.setP1(new Point((x+50)-(defaultTrees2.getWidth()/2),20 + defaultTrees1.getHeight()));
		defaultTrees3.setP1(new Point(x,30 +defaultTrees1.getHeight()+defaultTrees2.getHeight()));
	}
	public int getToolBoxX() {
		if(isOpen())
			return plane.getWidth()-toolWidth();
		else
			return plane.getWidth();//x of panel
	}
	public int toolWidth() {
		return 100;
	}

}
