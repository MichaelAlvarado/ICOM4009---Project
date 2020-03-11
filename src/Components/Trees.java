package Components;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.image.BufferedImage; 
import java.lang.String;
import java.awt.Point;

/**
 * 
 * @author Fabiola Badillo
 * Date - March 11, 2020
 * This class describes the Tree instances with all its attributes and methods  
 *
 */

public class Trees {

	private int treeSpecies; // 1, 2 or 3
	private BufferedImage treeImage;
	private boolean found; // idk yet if trees are to be found or will be present in the map from the beginning 
	private int treeHeight; 
	private Point p1;
	private Point p2;
	private String tid;
	
	
	// constructor
	public Trees(int treeSpecies, Point p1, Point p2) {
		this.treeSpecies = treeSpecies;
		this.p1 = p1;
		this.p2 = p2;
	}
	
//	Constructor used for testing text file generator
//	public Building(String name, BufferedImage pic, LinkedList<Question> qs, LinkedList<Wall> ws, boolean fd) {
//		this.buildingName = name;
//		this.picture = pic;
//		this.questionPool = qs;
//		this.walls = ws;
//		this.found = fd;
//		
//	}
	
	
//	/**
//	 * @author jorgecalderon
//	 * Objective - Generate a string containing the questions with their answers
//	 * Date - 03/08/2020
//	 * @param - N/A
//	 * @param - N/A
//	 * @return - N/A 
//	 */
//	public String getQuestions() {
//		String result = "";
//		int counter = 1;
//		for (Question q: this.questionPool) {
//			result += counter + ") " +  q.getQuestion() + ", Answers: " + q.getAnswers() + ", ";
//			counter++;
//		}
//		return result;
//	}
	
//	/**
//	 * @author jorgecalderon
//	 * Objective - Generate a string containing the questions with their answers
//	 * Date - 03/08/2020
//	 * @param - N/A
//	 * @param - N/A
//	 * @return - N/A 
//	 */
//	public String getWallInfo() {
//		String result = "";
//		for (Wall w: this.walls) {
//			result += "ID: " + w.getID() + ", Height: " + w.getHeight() + ", First Point: " + w.getP1().x
//			+ ", " + w.getP1().y + ", Second Point: " + w.getP2().x + ", " + w.getP2().y +
//			", Wall Image: (" + w.getTexture() + "), " + "\n";
//		}
//		
//		return result;
//	}

	// TODO: method that identifies each tree individually
	
	// getters and setters
	
	public Point getP1() {
		return this.p1;
	}
	
	public Point getP2() {
		return this.p2;
	}
	
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	public void setTreeSpecies(int treeSpecies) {
		this.treeSpecies = treeSpecies;
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
	public void isFound(boolean found) {
		this.found = found;
	}
	public boolean isFound() {
		return this.found;
	}
	private void setTreeHeight(int treeHeight) {
		this.treeHeight = treeHeight;
	}
	public int getTreeHeight() {
		return this.treeHeight;
	}
	public void setID(String tid) {
		this.tid = tid;
	}
	
	
//   *	
//	 * @author Fabiola Badillo
//	 * Objective - Predefine the characteristics of the different types of trees
//	 * Date - 03/08/2020
//	 * @param - N/A
//	 * @param - N/A
//	 * @return - N/A 
//	 */
	public void defineTree() {
		if (this.treeSpecies == 1) {
			setTreeHeight(25);
			// setTreeImage(treeImage1);
		}
		else if (this.treeSpecies == 2) {
			setTreeHeight(50);
			// setTreeImage(treeImage2);
		}
		else {
			setTreeHeight(100);
			// setTreeImage(treeImage3);
		}
	}
	
}
