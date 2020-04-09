/**
 * 
 */
package MapDesingComponents;


import java.awt.Point;

import Components.Tree;
import Components.Map;

/**
 * @author Michael J. Alvarado
 * This class will let you place default trees
 * @date Mar 12, 2020
 */
public class ToolBox {

	Tree defaultTrees[];
	Point p1;
	Map map;
	
	public ToolBox() {
		p1 = new Point(0,0);
		defaultTrees[0] = new Tree(map, 1,p1);
		defaultTrees[1] = new Tree(map, 2,p1);
		defaultTrees[2] = new Tree(map, 3,p1);
	}
}
