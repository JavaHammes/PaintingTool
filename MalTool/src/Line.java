import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Line extends JPanel{

	
	Point pointStart;
    Point pointEnd;
	Color c;
	float width; 
	
	// Line, Rect, Oval, Triangle sollten rltv. selbst erklärend sein
	// Line hat kein boolean filled wie alle anderen, da eine Linie ja nicht ausgefüllt werden kann
	public Line(Point pointStart, Point pointEnd, Color c, float width) {
		this.pointStart = pointStart;
		this.pointEnd = pointEnd;
		this.c = c;
		this.width = width;
	}
	
	

	public Color getColor() {
		return this.c;
	}
	
	public void setColor(Color color) {
		c = color;
	}
	
	public int getWidth() {
		return (int) this.width;
	}
	
	public void getPosition() {
		System.out.println("Startpoint: " + pointStart.toString());
		System.out.println("Endpoint: " + pointEnd.toString());
	}
	
	public void setPosition(int x1, int y1, int x2, int y2) {
		pointStart = new Point(x1,y1);
		pointEnd = new Point(x2,y2);
	}
	
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g2d);
		
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(width));
		
		g2d.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
	}
}
