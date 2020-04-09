package Components;

import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.image.BufferedImage; 
import java.lang.String;
import java.awt.Graphics;
import java.awt.Point;
import javax.imageio.ImageIO;

import GameSetUp.Handler;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @author Fabiola Badillo
 * Date - March 11, 2020
 * This class describes the Tree instances with all its attributes and methods  
 *
 */

public class Tree {

	private int treeSpecies; // 1, 2 or 3
	private BufferedImage treeImage;
	private boolean found; // idk yet if trees are to be found or will be present in the map from the beginning 
	private String pictureURL;
	private Point p1;
	private int width, height;
	private String tid;
	
	
	// constructor
	@SuppressWarnings("static-access")
	public Tree(Map map, int treeSpecies, Point p1) {
		this.treeSpecies = treeSpecies;
		this.p1 = p1;
		this.tid = map.getMapName() + "_t" + map.getTrees().size();
	}
	
	public Tree() {};
	
//	/**
//	 * @author jorgecalderon
//	 * Objective - Generate a string containing the Trees info
//	 * Date - 03/11/2020
//	 * @param - N/A
//	 * @param - N/A
//	 * @return - N/A 
//	 */
	public String getTreeInfo() {
		String result = "";
		result += "\nID: " + this.getID() + ", Species: " + this.getTreeSpecies()
				+ "\nPosition: " + this.getP1().x + ", " + this.getP1().y 
				+ "\nImage: "  + this.getPictureURL();
		return result;
	}

	// TODO: method that identifies each tree individually
	
	// getters and setters
	
	public Point getP1() {
		return this.p1;
	}
	
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	public void setTreeSpecies(int treeSpecies) {
		this.treeSpecies = treeSpecies;
		defineTreeImage();
	}
	public int getTreeSpecies() {
		return this.treeSpecies;
	}
	public void setTreeImage(BufferedImage treeImage) {
		this.treeImage = treeImage;
	}
	public BufferedImage getTreeImage() {
		return this.treeImage;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
	public boolean isFound() {
		return this.found;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return this.width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return this.height;
	}
	public void setID(String tid) {
		this.tid = tid;
	}
	public String getID() {
		return this.tid;
	}
	
	
//   *	
//	 * @author Fabiola Badillo
//	 * Objective - Predefine the characteristics of the different types of trees
//	 * Date - 03/08/2020
//	 * @param - N/A
//	 * @param - N/A
//	 * @return - N/A 
//	 */
	public void defineTreeImage() {
		if (this.treeSpecies == 1) {
			setHeight(25);
			setWidth(25);
			try {
				setTreeImage(ImageIO.read(new File("res/treeImages/species1.png")));
				setPictureURL("res/treeImages/species1.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (this.treeSpecies == 2) {
			setHeight(50);
			setWidth(50);
			try {
				setTreeImage(ImageIO.read(new File("res/treeImages/species2.png")));
				setPictureURL("res/treeImages/species2.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			setHeight(100);
			setWidth(100);
			try {
				setTreeImage(ImageIO.read(new File("res/treeImages/species3.png")));
				setPictureURL("res/treeImages/species3.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public String getPictureURL() {
		return pictureURL;
	}


	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public void render(Graphics g) {
		g.drawImage(treeImage, p1.x, p1.y, width, height, null);
	}
}
