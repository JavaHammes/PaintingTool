import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;


// für die GUI / Steuerung der Knöpfe...
public class GUI extends JFrame {

	JButton changeBackground;
	JButton openColor;
	JButton openDicke;
	JButton safePicture;
	JButton openChooseShape;
	JButton undo;
	JToggleButton fillB;
	Color backgroundColor = Color.WHITE;
	static Color lineColor = Color.BLACK;
	public static boolean fill = false;
	static float lineWidth = (int) ThicknessDialog.sliderInit;
	DrawLine draw;


	GUI() {
		super("Drawtool");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);


		draw = new DrawLine();
		draw.setBounds(30, 60, 840, 480);
		getContentPane().add(draw);


		changeBackground = new JButton("Background");
		changeBackground.setToolTipText("Select backgroundcolor");
		changeBackground.addActionListener(e -> {
			backgroundColor = JColorChooser.showDialog(GUI.this, "Choose a color!", backgroundColor);
			draw.setBackground(backgroundColor);
			requestFocus();
		});
		changeBackground.setBounds(20, 20, 100, 25);
		add(changeBackground);

		openColor = new JButton("Color");
		openColor.setToolTipText("Select linecolor");
		openColor.addActionListener(e -> {
			lineColor = JColorChooser.showDialog(GUI.this, "Choose a color!", lineColor);

		});
		openColor.setBounds(140, 20, 100, 25);
		add(openColor);

		openDicke = new JButton("Thickness");
		openDicke.setToolTipText("Change thickness of lines");
		openDicke.addActionListener(e -> {
			ThicknessDialog g = new ThicknessDialog(GUI.this);
			g.setLocationRelativeTo(GUI.this);
			g.setVisible(true);
		});
		openDicke.setBounds(260, 20, 100, 25);
		add(openDicke);

		safePicture = new JButton("Safe");
		safePicture.setToolTipText("Safe your drawn picture");
		safePicture.addActionListener(e -> {

			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showSaveDialog(GUI.this);
			File file = fc.getSelectedFile();
			requestFocus();
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				safeImage(file);
			} else if (returnValue == JFileChooser.CANCEL_OPTION) {
				System.out.println("Es wurde kein File ausgewählt");
			} else if (returnValue == JFileChooser.ERROR_OPTION) {
				System.out.println("Error!");
			}


		});
		safePicture.setBounds(620, 20, 100, 25);
		add(safePicture);

		undo = new JButton("<-");
		undo.setToolTipText("undo last move");
		undo.addActionListener(e -> {
			draw.undo();

		});
		undo.setBounds(740, 20, 100, 25);
		add(undo);

		openChooseShape = new JButton("Shapes");
		openChooseShape.setToolTipText("Select shape");
		openChooseShape.addActionListener(e -> {
			shapeDialog sd = new shapeDialog(GUI.this);
			sd.setLocationRelativeTo(GUI.this);
			sd.setVisible(true);

		});
		openChooseShape.setBounds(500, 20, 100, 25);
		add(openChooseShape);

		fillB = new JToggleButton("Fill");
		fillB.setToolTipText("If selected every new drawn onject will be filled!");
		fillB.addItemListener(e -> {
			if (fillB.isSelected()) {
				fill = true;
			} else {
				fill = false;
			}
		});
		fillB.setBounds(380, 20, 100, 25);
		add(fillB);


		addWindowListener(new WindowHandler());
		setFocusable(true);
	}

	// Methode um das was man gemalt hat abzuspeichern
	private void safeImage(File file) {
		BufferedImage image = new BufferedImage(draw.getHeight(), draw.getWidth(), BufferedImage.TYPE_INT_ARGB);

		try {
			image = new Robot().createScreenCapture(draw.bounds());
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		Graphics2D g2d = image.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, image.getWidth(), image.getWidth());
		draw.paint(g2d);

		try {
			ImageIO.write(image, "png", new File(file.getPath()));
		} catch (Exception e) {
			System.out.println("error");
		}
	}


	// WindowListener um nochmal zu bestätigen, dass man das Program wirklich beenden möchte
	private class WindowHandler implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			int confirm = JOptionPane.showConfirmDialog(GUI.this, "Do you really want to close the program?", "Close?", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else if (confirm == JOptionPane.NO_OPTION) {

			}

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}


}
