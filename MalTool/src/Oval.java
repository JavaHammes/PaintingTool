import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Oval extends JPanel {

	int x;
	int y;
	int width;
	int height;
	Color c;
	float lineWidth;
	boolean filled;
	
	Oval(int x, int y, int width, int height, Color c, float lineWidth, boolean filled){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
		
		if(filled == false) {
		g2d.drawOval(x, y, width, height);
		}else {
			g2d.fillOval(x, y, width, height);
		}
	}
}