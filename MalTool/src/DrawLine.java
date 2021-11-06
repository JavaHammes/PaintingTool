import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

// Klasse die alles zeichnet 
public class DrawLine extends JPanel {


ArrayList<Point> pointStart = new ArrayList<Point>();
ArrayList<Point> pointEnd = new ArrayList<Point>();
public static ArrayList<Color> usedColors = new ArrayList<Color>();

ArrayList listeDerListen = new ArrayList();


Point startSinglePoint = new Point();
Point endSinglePoint = new Point();

int pointSize;
int pointEndSize;
Graphics2D g2d;


Line l;
Line ziehLinie;


Rect r;
Rect ziehRect;

Oval o;
Oval ziehOval;

Triangle t;
Triangle ziehTriangle;


boolean undo = false;


  public DrawLine() {
	  addMouseListener(new MouseHandler1());
	  addMouseMotionListener(new MouseHandler2());
  }

  public void paint(Graphics g) {

	  g2d = (Graphics2D) g;
	  g2d.drawRect(0, 0, 840, 480);
	  
	  
	  if(shapeDialog.lineSelected ) {
		  
		  // malt die Linie beim ziehen
		  ziehLinie = new Line(startSinglePoint, endSinglePoint, GUI.lineColor, GUI.lineWidth);
		  if(undo == false) {
		  ziehLinie.paint(g2d);
		  }else {
			  undo = false;
		  }
		  
  	}else if(shapeDialog.rectSelected ) {
  		
  	  // malt das rect beim ziehen
  	  ziehRect = new Rect(startSinglePoint.x , startSinglePoint.y ,endSinglePoint.x - startSinglePoint.x ,endSinglePoint.y - startSinglePoint.y, GUI.lineColor, GUI.lineWidth, GUI.fill);
  	  if(undo == false) {
  	  ziehRect.paint(g2d);
  	  }else {
  		  undo = false;
  	  }
  	  
  	}else if(shapeDialog.ovalSelected ) {
  		ziehOval = new Oval(startSinglePoint.x , startSinglePoint.y ,endSinglePoint.x - startSinglePoint.x ,endSinglePoint.y - startSinglePoint.y, GUI.lineColor, GUI.lineWidth, GUI.fill);
    	  if(undo == false) {
    	  ziehOval.paint(g2d);
    	  }else {
    		  undo = false;
    	  }
    	   
  	}else if(shapeDialog.triangleSelected) {
  		ziehTriangle = new Triangle(startSinglePoint, endSinglePoint ,  GUI.lineColor, GUI.lineWidth, GUI.fill);
  	  if(undo == false) {
  	  ziehTriangle.paint(g2d);
  	  }else {
  		  undo = false;
  	  }
  	}else {
  		
  		// falls es einen Error gibt 
  		System.out.println("error");
  	}
	  // malt alles nochmal
	  for(int i = 0; i < listeDerListen.size(); i++) {
	  		if(listeDerListen.get(i) instanceof Rect) {
		    ((Rect) listeDerListen.get(i)).paint(g2d);
	  		}else if(listeDerListen.get(i) instanceof Line) {
	  			((Line) listeDerListen.get(i)).paint(g2d);
	  		}else if(listeDerListen.get(i) instanceof Oval){
	  			((Oval) listeDerListen.get(i)).paint(g2d);
	  		}else {
	  			((Triangle) listeDerListen.get(i)).paint(g2d);
	  		}
		  }
	  	 
	  
}
  // löscht das letzte was man gemalt hat
  public void undo() {
	 
	  System.out.println(listeDerListen.size());
	  
	  if(listeDerListen.size() > 0) {
		  
		    listeDerListen.remove(listeDerListen.size() - 1);
		    undo = false ? false : true;
		   
		  repaint();
		  }
	 
  }
  


// MouseAdapter um die Punkte zu bekommen
private class MouseHandler1 extends MouseAdapter{
	 public void mousePressed(MouseEvent e) {
     	
         startSinglePoint = e.getPoint(); 
         pointStart.add(e.getPoint()); 
     	
     }

     public void mouseReleased(MouseEvent e) {
         pointEnd.add(e.getPoint()); 
         pointSize = pointStart.size();
   	     pointEndSize = pointEnd.size();
   	     
   	     // hinzufügen der neu gezeichneten Sache in die ListeDerListe
   	     if(shapeDialog.lineSelected) {
         l = new Line(pointStart.get(pointSize -1), pointEnd.get(pointEndSize- 1), GUI.lineColor, GUI.lineWidth );
         listeDerListen.add(l);
         
         repaint();  
   	     }else if(shapeDialog.rectSelected) {
   	    	 r = new Rect(pointStart.get(pointSize -1).x, pointStart.get(pointEndSize- 1).y , pointEnd.get(pointEndSize- 1).x -  pointStart.get(pointEndSize- 1).x, pointEnd.get(pointEndSize- 1).y -  pointStart.get(pointEndSize- 1).y , GUI.lineColor, GUI.lineWidth, GUI.fill );
   	         listeDerListen.add(r);
   	         
   	         repaint(); 
   	     }else if(shapeDialog.ovalSelected) {
   	    	o = new Oval(pointStart.get(pointSize -1).x, pointStart.get(pointEndSize- 1).y , pointEnd.get(pointEndSize- 1).x -  pointStart.get(pointEndSize- 1).x, pointEnd.get(pointEndSize- 1).y -  pointStart.get(pointEndSize- 1).y , GUI.lineColor, GUI.lineWidth , GUI.fill);
  	         listeDerListen.add(o);
  	         
  	         repaint();
   	     }else if(shapeDialog.triangleSelected) {
   	    	 t = new Triangle(pointStart.get(pointSize - 1),pointEnd.get(pointEndSize - 1) , GUI.lineColor, GUI.lineWidth , GUI.fill);
   	    	 listeDerListen.add(t);
   	    	 
   	    	 repaint();
   	     }else {
   	    	 System.out.println("Error");
   	     }
     }
 }


private class MouseHandler2 extends MouseAdapter{
	@Override 
	 public void mouseDragged(MouseEvent e) {
     	
         endSinglePoint = e.getPoint(); 
         
         repaint();
         
     }
     
 }


}

