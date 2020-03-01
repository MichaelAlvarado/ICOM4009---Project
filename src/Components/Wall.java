package Components;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage; 
import java.lang.String;

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
	private double height;
	private BufferedImage texture;
	private Color color; 
	private String wid;
	
	// constructor 
	public Wall(String buildingName) {
		int id = 1;
		setID(buildingName + "_w" + String.valueOf(id));
		id++;
		this.color = Color.WHITE;
	}
	public Wall(String buildingName, Point p1, Point p2) {
		int id = 1;
		setID(buildingName + "_w" + String.valueOf(id));
		id++;
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
	
	public double getHeight() {
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
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	private void setID(String wid) {
		this.wid = wid;
	}
	
	 
}
