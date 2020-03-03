package GUI;

import java.awt.Canvas;
import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Line;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Components.Wall;

public class Traces extends Canvas{
	
	/**
	 * 
	 * @author Michael Alvarado
	 * Date - 03/March/2020  
	 *
	 */
		ArrayList<Wall> lines;
		Point[] currentPointPair; //this is the current trace being drawn
		Point drag; //this is use to interconnect points
		String currentBuilding;
		int pointWidth; //points of coordinates
		int xOrigin, yOrigin; //Pixel position on canvas origin
		Color cP, cL, pP, pL; //Color for c=current p=previous P=Point L=Line

		public Traces() {
			pointWidth = 10;
			cP = Color.BLUE;
			cL = Color.MAGENTA;
			pP = Color.RED;
			pL = new Color(20,198,5); //Green
			lines = new ArrayList<Wall>();
			currentPointPair = new Point[2];


			addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Pressed / "+"x: " + arg0.getX() + "  y:" + (getHeight() -arg0.getY()));
					if(drag == null) {
					currentPointPair[0] = new Point(arg0.getX(), arg0.getY());
					}
					else {
						currentPointPair[0] = drag;
					}
					currentPointPair[1] = null;
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Released / "+"x: " + arg0.getX() + "  y:" + (getHeight() - arg0.getY()));
					currentPointPair[1] = new Point(arg0.getX(), arg0.getY());
					lines.add(new Wall(currentBuilding, currentPointPair[0], currentPointPair[1]));
					currentPointPair[0] = null;
					currentPointPair[1] = null;
					repaint();
				}
			});

			addMouseMotionListener(new MouseMotionAdapter(){
				@Override
				public void mouseDragged(MouseEvent arg0) {
					currentPointPair[1] = new Point(arg0.getX(), arg0.getY());
					repaint();
				}

				@Override
				public void mouseMoved(MouseEvent arg0) {
					//This will be use to drag mouse to the near point
					for(Wall line: lines) {
						if(line.getP1().distance(arg0.getX(), arg0.getY()) < 15) {
							System.out.println("Near Point");
							drag = line.getP1();
						}
						else if(line.getP2().distance(arg0.getX(), arg0.getY()) < 15) {
							System.out.println("Near Point");
							drag = line.getP2();
						}
						else {
							drag = null;
						}
					}
				}
			});
		}

		@Override 
		public void paint(Graphics g) {
			super.paint(g);
			int gridLineQuantity = 16;
			xOrigin = 0; //position in canvas of point x origin
			yOrigin = this.getHeight(); //position in canvas of point y origin

			//Draw lines from point to point
			for(Wall line: lines) {
				g.setColor(pP);//Color of Points
				g.fillOval((int)(line.getP1().getX()-(pointWidth/2)), (int)(line.getP1().getY()-(pointWidth/2)), pointWidth, pointWidth);
				g.fillOval((int)(line.getP2().getX()-(pointWidth/2)), (int)(line.getP2().getY()-(pointWidth/2)), pointWidth, pointWidth);
				g.setColor(pL);//Color of Lines
				g.drawLine((int)line.getP1().getX(), (int)line.getP1().getY(), (int)line.getP2().getX(), (int)line.getP2().getY());
			}

			//Draw current Points
			g.setColor(cP);
			if(currentPointPair[0] != null) {
				g.fillOval((int)(currentPointPair[0].getX()-(pointWidth/2)), (int)(currentPointPair[0].getY()-(pointWidth/2)), pointWidth, pointWidth);
			}
			if(currentPointPair[1] != null) {
				g.fillOval((int)(currentPointPair[1].getX()-(pointWidth/2)), (int)(currentPointPair[1].getY()-(pointWidth/2)), pointWidth, pointWidth);
			}

			//Draw current Line
			g.setColor(cL);
			if(currentPointPair[0] != null && currentPointPair[1] != null) {
				g.drawLine((int)currentPointPair[0].getX(), (int)currentPointPair[0].getY(), (int)currentPointPair[1].getX(), (int)currentPointPair[1].getY());
			}

			//		//Draw panel with coordinates
			//		g.setColor(new Color(0,0,0,100));
			//		g.fillRect(this.getWidth()-200, 0, 200, 30);
			//		//Draw coordinates point on panel
			//		g.setFont(new Font("Arial", Font.PLAIN, 20));
			//		g.setColor(cP);
			//		g.drawString("( " + currentPoint.getX() + " , " + currentPoint.getY() + " )", this.getWidth()-200, 20);

		}
}
