package Components;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * @author Fabiola Badillo Ramos
 * This class describes the Player instances] with all its attributes and methods
 *Date - March 20, 2020
 */

public class Player {

	private Point position;
	private BufferedImage avatar;
	private String name;
	
	public Player(String name, Point initialPosition) {
		this.name = name;
		position = initialPosition;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	public String getName() {
		return this.name;
	}
	public void setAvatar(BufferedImage avatar) {
		this.avatar = avatar;
	}
	public BufferedImage getAvatar() {
		return this.avatar;
	}
	public void moveTo(Point p) {
		this.position = p;
	}
	public Point getPosition() {
		return this.position;
	}
	public void moveOnX(int x) {
		position.x = x;
	}
	public void moveOnY(int y) {
		position.y = y;
	}
}


