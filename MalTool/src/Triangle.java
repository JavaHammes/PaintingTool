import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Triangle extends JPanel {

	Point StartPunkt;
	Point EndPunkt;
	Color c;
	float lineWidth;
	boolean filled;
	
	Triangle(Point StartPunkt, Point EndPunkt, Color c, float lineWidth, boolean filled){
		this.StartPunkt = StartPunkt;
		this.EndPunkt = EndPunkt;
		this.c = c;
		this.lineWidth = lineWidth;
		this.filled = filled;
	}
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g2d);
		
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(lineWidth));
		
		
		
		
		int x[] = {(EndPunkt.x + StartPunkt.x) / 2, EndPunkt.x, StartPunkt.x};
		int y[] = {StartPunkt.y, EndPunkt.y, EndPunkt.y};
		
		if(filled == false) {
		g2d.drawPolygon(x , y, 3);  //   1. y = StartPunkt.y   x = (EndPunkt.x + StartPunkt.x) / 2   // 2 EndPunkt.x EndPunkt.y   // 3 x = StartPunkt.x   y = EndPunkt.y
		}else {
			g2d.fillPolygon(x , y, 3);
		}
	}
}
