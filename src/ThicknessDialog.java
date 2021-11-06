import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;


// Dialog zum ändern der Strichdicke
public class ThicknessDialog extends JDialog{

	JSlider slider;
	static final int SLIDER_MAX = 10;
	static final int SLIDER_MIN = 1;
	static int sliderInit = 2;
	
	static float selectedThickness;
	ThicknessDialog(GUI g){
		super(g);
		setSize(200,200);
		setTitle("Change Thickness");
		setResizable(false); 
		setModal(true);
		setLayout(null);
		Draw d = new Draw();
		d.setBounds(0,0,200,200);
		add(d);
		JSlider slider = new JSlider(JSlider.HORIZONTAL , SLIDER_MIN, SLIDER_MAX, sliderInit);
		slider.setBounds(0,140,200,20);
		add(slider);
		
		slider.addChangeListener(e -> {
			selectedThickness = slider.getValue();
	        GUI.lineWidth = selectedThickness;
	        sliderInit = (int) selectedThickness;
	        repaint();
		});
	}
	
	// malt die Vorschau der Dicke, um es nochmal schön visualisiert zu haben
	private class Draw extends JLabel{
		
		@Override
		
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paint(g2d);
			g2d.setColor(GUI.lineColor);
			g2d.fillOval(90,75, sliderInit * 2, sliderInit * 2);
			
		}
	}
	
	
}
