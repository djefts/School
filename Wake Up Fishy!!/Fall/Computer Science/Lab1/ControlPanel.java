/* ControlPanel.java
 * 
 * Adapted as a swing application from an
 * awt applet by unknown author
 * 
 * Nick Brixius, July 2005
*/
import java.awt.*;
import java.awt.event.*;

//  Provides a control panel window with buttons for different drawings

class ControlPanel implements ActionListener {
	private Frame parent;
	private LineArt drawPanel;

	ControlPanel(Frame parent, LineArt drawPanel) {
		this.parent = parent;
		this.drawPanel = drawPanel;
	}

	public void actionPerformed(ActionEvent e) {
		
			if (e.getSource().equals(CreateLineArt.button1)) {
				drawPanel.changeMode(1);
			} else if (e.getSource().equals(CreateLineArt.button2)) {
				drawPanel.changeMode(2);
			} else if (e.getSource().equals(CreateLineArt.button3)) {
				drawPanel.changeMode(3);
			} else if (e.getSource().equals(CreateLineArt.button4)) {
				drawPanel.changeMode(4);
			}
	}
}

