package Components;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage; 
import java.lang.String;
import java.util.List;
import java.util.LinkedList;

/**
 * 
 * @author Fabiola Badillo
 * Date - Feb 29, 2020
 * This class describes the Wall instances with all its attributes and methods  
 *
 */

public class Wall {

	private Point p1;
	private Point p2;
	private int height;
	private BufferedImage texture;
	private String textureURL;
	private Color color; 
	private String wid;
	
	// constructor 
	public Wall(String name, Point p1, Point p2) {
		wid = name;
		this.p1 = p1;
		this.p2 = p2;
		this.color = Color.WHITE;
	}
	
	public Wall(String name, int height, Point p1, Point p2) {
		wid = name;
		this.p1 = p1;
		this.p2 = p2;
		this.height = height;
		this.color = Color.WHITE;
	}
	
	public Wall(String buildingName, LinkedList<Wall> walls, Point p1, Point p2) {
		if (walls == null)
			setID(buildingName + "_w" + String.valueOf(1));
		else {
			setID(buildingName + "_w" + String.valueOf(walls.size()));
		}
		this.color = Color.WHITE;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	// getters
	
	public Point getP1() {
		return this.p1;
	}
	
	public Point getP2() {
		return this.p2;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public BufferedImage getTexture() {
		return this.texture;
	}
	
	public String getColor() {
		return this.getColor();
	}
	
	public String getID() {
		return this.wid;
	}
	// setters
	
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setID(String wid) {
		this.wid = wid;
	}

	/**
	 * @return the textureURL
	 */
	public String getTextureURL() {
		return textureURL;
	}

	/**
	 * @param textureURL the textureURL to set
	 */
	public void setTextureURL(String textureURL) {
		this.textureURL = textureURL;
	}
	
	 
}
