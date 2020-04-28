package testers;
import java.awt.Point;
import Components.Wall;

public class DataValidacionTest {

	public static void main(String[] arg) {
		Wall wall1 = new Wall("Test 1", new Point(0,0), new Point(10,0)); //Should have 0 degree rotation
		Wall wall2 = new Wall("Test 2", new Point(3,0), new Point(3,10)); //Should have 90 degree rotation
		Wall wall3 = new Wall("Test 3", new Point(5,8), new Point(25,28)); //Should have 135 degree rotation
		Wall wall4 = new Wall("Test 4", new Point(0,-3), new Point(6,9)); //Should have 116.5 degree rotation
		Wall wall5 = new Wall("Test 5", new Point(5,10), new Point(0,0)); //Should have 116.5 degree rotation
		Wall wall6 = new Wall("Test 6", new Point(0,0), new Point(-40,40)); //Should have 45 degree rotation
		Wall wall7 = new Wall("Test 7", new Point(100,100), new Point(-120,-120)); //Should have 135 degree rotation
		Wall wall8 = new Wall("Test 8", new Point(-1,-2), new Point(-6,8)); //Should have 63.4 degree rotation

		VRMLWallRotation(wall1);
		VRMLWallRotation(wall2);
		VRMLWallRotation(wall3);
		VRMLWallRotation(wall4);
		VRMLWallRotation(wall5);
		VRMLWallRotation(wall6);
		VRMLWallRotation(wall7);
		VRMLWallRotation(wall8);
	}
	
	/**
	 * Description - Data Test of the values of Wall to a VRML standard (This method is implemented in FileManager class)
	 * In VRML the x, y and z position is the center of the object (Box in this case).
	 * In VRML a rotation must be in Radians and its positive clockwise.
	 * The walls contains two points (P1 and P2).
	 * 
	 * The Idea in this method is to create a horizontal wall with a fixed small width and 
	 * rotate it so that the wall will be positioned in its corresponding place.
	 * 
	 * @author - Michael J. Alvarado
	 * @date Apr 26, 2020
	 * @param wall - wall to be converted to VRML code
	 */
	private static double VRMLWallRotation(Wall wall) {
		double x = (wall.getP1().getX() + wall.getP2().getX())/2; //center X position of wall
		double y = (wall.getP1().getY() + wall.getP2().getY())/2; //center Y position of wall
		double length = wall.getP1().distance(wall.getP2()); //width of the wall
		/*
		 * Rotation calculation using Cosine Law in a isosceles 
		 */
		double a = length/2;
		double c = wall.getP2().y > wall.getP1().y? wall.getP1().distance(x+a, y): wall.getP2().distance(x+a, y); //takes the bottom most point so the rotation will be clockwise
		double rotation = Math.acos(1-((c*c)/(2.00*a*a))); //This rotation will only be applied in Z axis
		
		System.out.println("Testing for: "+wall.getID());
		System.out.println("Center X position: "+x);
		System.out.println("Center Y position: "+y);
		System.out.println("lenght of the wall: "+length);
		System.out.println("Isosceles sides: " + a + " and " + c); //if c is 0 then no triangle is form since the wall its horizontal
		System.out.println("Rotation in rad: "+rotation+"  Rotation in deg: "+(180*rotation/Math.PI) + "\n");
		return rotation;
	}
}
