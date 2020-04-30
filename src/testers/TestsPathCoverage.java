package testers;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import Components.Building;
import Components.Wall;

public class TestsPathCoverage {
	/*
	 * Run some robust tests based on the path coverage technique
	 */
		
	public static void main(String[] args) {
		// Test for function 1
		Building b = new Building("B1");
		Point p1 = new Point(50, 100);
		Point p2 = new Point(30, 150);
		Wall w = new Wall("w1", p1, p2);
		b.addWalls(w);
		Rectangle r = perimeter(b);
		System.out.println("Dimensions of the rectangle should be 20 by 50.");
		System.out.println("Dimensions of the rectangle are: " + r.getSize());
		
		// Test for function 2
		ArrayList<Building> buildings = new ArrayList<Building>();
		Building b2 = new Building("B2");
		b.setFound(true);
		b2.setFound(false);
		buildings.add(b);
		buildings.add(b2);
		System.out.println("The number of undiscovered buildings should be 1");
		System.out.println("The number of undiscovered buildings is: " + undiscoveredBuildings(buildings));
	}
	
	public static int undiscoveredBuildings(ArrayList<Building> b) {
		int count = b.size();
		for(Building building:b) {
			if(building.getFound()) {
				count--;
			}
		}
		return count;
	}
	
	public static Rectangle perimeter(Building b) {
		Rectangle r = new Rectangle();
		if(b.getWalls().size() == 0) {
			return r;
		}
		double smallX, bigX, smallY, bigY;
		ArrayList<Point> points = (ArrayList<Point>) allPoints(b);
		smallX = points.get(0).getX();
		bigX = points.get(0).getX();
		smallY = points.get(0).getY();
		bigY = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			if (points.get(i).getX() < smallX)
				smallX = points.get(i).getX();
			if (points.get(i).getX() > bigX)
				bigX = points.get(i).getX();
			if (points.get(i).getY() < smallY)
				smallY = points.get(i).getY();
			if (points.get(i).getY() > bigY)
				bigY = points.get(i).getY();
		}
		r.setBounds((int)smallX, (int)smallY, (int)(bigX-smallX), (int)(bigY-smallY));
		return r;
	}
	
	private static List<Point> allPoints(Building b){
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < b.getWalls().size(); i++) {
			points.add(b.getWalls().get(i).getP1());
			points.add(b.getWalls().get(i).getP2());
		}
		return points;
	}
}
