/* CreateLineArt.java
 * 
 * Adapted as a swing application from an
 * awt applet by unknown author
 * 
 * Nick Brixius, July 2005
*/

import javax.swing.*;
import java.awt.*;

//  Driver for the Line Art application

public class CreateLineArt {
	
	static public JButton button1, button2, button3, button4;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Create Line Art");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		LineArt la = new LineArt();
		frame.addComponentListener(new LineArtComponentListener(la));
		frame.getContentPane().add(la, BorderLayout.CENTER);
		
		ControlPanel control = new ControlPanel(frame, la);
		JToolBar toolBar = new JToolBar();
		button1 = new JButton("Draw Monocolor Art");
		button1.addActionListener(control);
		toolBar.add(button1);
		button2 = new JButton("Draw Moving Lines");
		button2.addActionListener(control);
		toolBar.add(button2);
		button3 = new JButton("Draw Multicolor Art");
		button3.addActionListener(control);
		toolBar.add(button3);
		button4 = new JButton("Draw Kaleidoscope");
		button4.addActionListener(control);
		toolBar.add(button4);
		
		frame.getContentPane().add(toolBar, BorderLayout.PAGE_START);
		frame.setVisible(true);
	}
}
