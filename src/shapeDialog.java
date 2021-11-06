import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JToggleButton;


// Dialog zum ändern der zu malenden Figur
public class shapeDialog extends JDialog{

	ButtonGroup bg;
	JToggleButton selectRect;
	JToggleButton selectOval;
	JToggleButton selectTriangle;
	JToggleButton selectLine;
	
	public static boolean lineSelected = true;
	public static boolean rectSelected;
	public static boolean ovalSelected;
	public static boolean triangleSelected;
	
	
	
	shapeDialog(GUI g){
		super(g);
		setTitle("Choose a shape");
		setResizable(false);
		setModal(true);
		setLayout(new FlowLayout());
		
		selectRect = new JToggleButton("Rect");
		selectOval = new JToggleButton("Oval");
		selectTriangle = new JToggleButton("Triangle");
		selectLine = new JToggleButton("Line");
		
		selectRect.addActionListener(new ToggleButtonHandler());
		selectOval.addActionListener(new ToggleButtonHandler());
		selectTriangle.addActionListener(new ToggleButtonHandler());
		selectLine.addActionListener(new ToggleButtonHandler());
		
		
		add(selectLine);
		add(selectRect);
		add(selectOval);
		add(selectTriangle);
		
		// Knöpfe in einer ButtonGroup, damit immer nur ein Shape ausgewählt werden kann
		bg = new ButtonGroup();
		
		bg.add(selectOval);
		bg.add(selectRect);
		bg.add(selectTriangle);
		
		pack();
	}
	
	// ActionListener um zu schauen, welcher Knopf gedrückt wurde
	// setzt je nach dem die booleans true oder false
	
	private class ToggleButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == selectRect) {
				rectSelected = true;
				ovalSelected = false;
				triangleSelected = false;
				lineSelected = false;
			}else if(e.getSource() == selectOval) {
				ovalSelected = true;
				rectSelected = false;
				triangleSelected = false;
				lineSelected = false;
			}else if(e.getSource() == selectTriangle) {
				triangleSelected = true;
				ovalSelected = false;
				rectSelected = false;
				lineSelected = false;
			}else if(e.getSource() == selectLine) {
				lineSelected = true;
				triangleSelected = false;
				ovalSelected = false;
				rectSelected = false;
			}
			
		}
		
	}
}
