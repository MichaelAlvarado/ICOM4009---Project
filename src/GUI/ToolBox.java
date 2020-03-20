/**
 * 
 */
package GUI;

import java.awt.Point;

import Components.Trees;

/**
 * @author Michael J. Alvarado
 * This class will let you place default trees
 * @date Mar 12, 2020
 */
public class ToolBox {

	Trees defaultTrees[];
	Point p1;
	
	public ToolBox() {
		p1 = new Point(0,0);
		defaultTrees[0] = new Trees(1,p1);
		defaultTrees[1] = new Trees(2,p1);
		defaultTrees[2] = new Trees(3,p1);
	}
}
