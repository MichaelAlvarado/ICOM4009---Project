package Components;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.image.BufferedImage; 
import java.lang.String;

/**
 * 
 * @author Fabiola Badillo
 * Date - Feb 29, 2020
 * This class describes the Building instances with all its attributes and methods  
 *
 */

public class Building {

	private LinkedList<Wall> walls; // = new LinkedList<Wall>();
	private String buildingName; 
	private BufferedImage picture;
	private LinkedList<Question> questionPool;
	private boolean found;
	private int buildingHeight; //this is default height of walls
	
	
	// constructor
	public Building(String buildingName) {
		this.buildingName = buildingName;
		walls = new LinkedList<Wall>();
		questionPool = new LinkedList<Question>();
	}
	
	//Constructor used for testing text file generator
	public Building(String name, BufferedImage pic, LinkedList<Question> qs, LinkedList<Wall> ws, boolean fd) {
		this.buildingName = name;
		this.picture = pic;
		this.questionPool = qs;
		this.walls = ws;
		this.found = fd;
		
	}
	
	public String getQuestions() {
		String result = "";
		int counter = 1;
		for (Question q: this.questionPool) {
			result += counter + ") " +  q.getQuestion() + ", Answers: " + q.getAnswers() + ", ";
			counter++;
		}
		return result;
	}
	
	public String getWallInfo() {
		String result = "";
		for (Wall w: this.walls) {
			result += "ID: " + w.getID() + ", Height: " + w.getHeight() + ", First Point: " + w.getP1().x
			+ ", " + w.getP1().y + ", Second Point: " + w.getP2().x + ", " + w.getP2().y + ", Wall Image: " 
			+ w.getTexture() + ", ";
		}
		
		return result;
	}
	// getters 
	public LinkedList<Wall> getWalls(){
		return this.walls;
	}
	public String getName() {
		return this.buildingName;
	}
	public BufferedImage getPicture() {
		return this.picture;
	}
	public LinkedList<Question> getQuestionPool(){
		return this.questionPool;
	}
	public boolean getFound() {
		return this.found;
	}
	
	// setters
	public void setWalls(LinkedList<Wall> walls) {
		this.walls = walls;
	}
	public void setName(String name) {
		this.buildingName = name;
	}
	public void setPicture(BufferedImage pic) {
		this.picture = pic;
	}
	public void setQuestions(LinkedList<Question> questionPool) {
		this.questionPool = questionPool;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
	
	/**
	 * @author Fabiola Badillo
	 * Date - Feb 29, 2020
	 * Objective - This method will add a new wall to an already existing building
	 * @param w - a wall instance with all its attributes
	 */
	public void addWalls(Wall w) {
		this.walls.add(w);		
	}
	/**
	 * @author Fabiola Badillo
	 * Date - Feb 29, 2020
	 * Objective - This method will add a new question to an already existing question pool
	 * @param q - a Question instance with all its attributes
	 */
	public void addQuestion(Question q) {
		this.questionPool.add(q);
	}

	public int getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(int buildingHeight) {
		this.buildingHeight = buildingHeight;
	}
}
