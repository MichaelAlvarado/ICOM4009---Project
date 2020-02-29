package Components;
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

	private double p1;
	private double p2;
	private double height;
	private BufferedImage texture;
	private String color; 
	private String wid;
	
	// constructor 
	public Wall(String buildingName) {
		int id = 1;
		setID(buildingName + "_w" + String.valueOf(id));
		id++;
		this.color = "white";
	}
	
	// getters
	
	public double getP1() {
		return this.p1;
	}
	
	public double getP2() {
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
	
	public void setP1(double p1) {
		this.p1 = p1;
	}
	
	public void setP2(double p2) {
		this.p2 = p2;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	private void setID(String wid) {
		this.wid = wid;
	}
	
	 
}
